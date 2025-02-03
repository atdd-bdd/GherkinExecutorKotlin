import java.io.File
import java.io.FileWriter


class Translate {
    val scenarios = mutableMapOf(Pair("", "")) // used to check if duplicate scenario names
    val glue_functions = mutableMapOf(Pair("", "")) // used to make sure only one glue implementaiton
    val data_names = mutableMapOf(Pair("", "")) // used to check for duplicate data
    var step_count = 0 // use to label duplicate scenarios
    val basePath = Configuration.testSubDirectory
    var glue_class = ""  // glue class name
    var glue_object = ""  // glue object name
    var step_number_in_scenario = 0  // use to label variables in scenario
    var data = InputIterator("")
    var first_scenario = true // If first scenario
    var background = false  // Have seen background
    var cleanup = false // Have seen cleanup

    // Create the output files, save names for deletions
    val test_filename = basePath + "Test" + ".tmp"
    var test_file = FileWriter(test_filename)
    val glue_template_filename = basePath + "Glue" + ".tmp"
    var glue_template_file = FileWriter(glue_template_filename)
    val data_definition_filename = basePath + "DataDefinition" + ".tmp"
    var data_definition_file = FileWriter(data_definition_filename)
    var feature_acted_on = false // Have found a feature step

    fun translate_in_tests(name: String) {
        data = InputIterator(name)
        var eof = false
        while (!eof) {
            val line = data.next()
            if (line.equals(InputIterator.EOF)) {
                eof = true
                continue
            }
            act_on_line(line)
        }
        endUp()
    }

    fun act_on_line(line: String) {
        val (words, comment) = split_line(line)
        if (words.size > 0) {
            var keyword = words[0].trim(':')
            if (keyword == "*") {
                keyword = "Star"
            }
            words[0] = keyword
            act_on_keyword(keyword, words, comment)
        }
    }

    fun split_line(line: String): Pair<MutableList<String>, List<String>> {
        val all_words = line.split(" ")
        val words = mutableListOf<String>()
        val comment = mutableListOf<String>()
        var in_comment = false
        for (a_word in all_words) {
            var word = a_word.trim()
            if (word.length <= 0)
                continue
            if (in_comment) {
                comment.add(word)
                continue
            }
            if (word[0] == '#') {
                in_comment = true
                word = word.trim('#')
                if (word.length > 0) {
                    comment.add(word)
                }
                continue
            }
            words.add(word)
        }
        return Pair(words, comment)
    }

    fun act_on_keyword(keyword: String, words: List<String>, comment: List<String>) {
        val full_text = words.joinToString("_")
        trace("Act on keyword " + keyword + " " + full_text)
        when (keyword) {
            "Feature" -> act_on_feature(full_text)
            "Scenario" -> act_on_scenario(full_text, true)
            "Background" -> {
                act_on_scenario(full_text, false)
                background = true
            }
            "Cleanup" -> {
                act_on_scenario(full_text, false)
                cleanup = true
            }
            "Given" -> act_on_step(full_text, comment)
            "When" -> act_on_step(full_text, comment)
            "Then" -> act_on_step(full_text, comment)
            "And" -> act_on_step(full_text, comment)
            "Star" -> act_on_step(full_text, comment)
            "Data" -> act_on_data(words[1])
            else -> error("keyword not recognized " + keyword)
        }
    }

    fun act_on_feature(full_text: String) {
        if (feature_acted_on) {
            error("Feature keyword duplicated - it is ignored "  + full_text)
            return
        }
        val feature_name = full_text
        feature_acted_on = true
        val test_pathname =
            Configuration.featureSubDirectory + feature_name + "\\" + feature_name+ ".kt"
        println(" Writing " + test_pathname)
        clean_files()
        File(Configuration.featureSubDirectory + feature_name).mkdir()
        test_file = FileWriter(test_pathname, false)
        val template_filename =
            Configuration.featureSubDirectory + feature_name + "\\" + feature_name+"_glue.tmpl"
        glue_template_file = FileWriter(template_filename, false)
        val data_definition_pathname =
            Configuration.featureSubDirectory + feature_name +
                    "\\" + feature_name+"_data." +
                    Configuration.data_definition_file_extension
        trace(" Writing " + data_definition_pathname)
        data_definition_file =
            FileWriter(data_definition_pathname, false)

        glue_class = full_text + "_glue"

        glue_object = makeName(full_text) + "_glue_object"
//        val data_class = full_text + "_data"
        test_print("package " + Configuration.packageName + "." + feature_name)
        test_print("import org.junit.jupiter.api.Test")
        test_print("import org.junit.jupiter.api.TestInstance")
//        test_print("import " + Configuration.packageName + "." + glue_class)
//        test_print("import " + Configuration.packageName + "." + data_class)
        test_print("@TestInstance(TestInstance.Lifecycle.PER_CLASS)")
        test_print("class " + full_text + "{")
        test_print("")

        template_print("package " + Configuration.packageName + "." + feature_name)
        template_print("import kotlin.test.assertEquals")
//        template_print("import " + Configuration.packageName + "." + data_class)
        template_print("")
        template_print("class " + glue_class + " {")
        template_print("")
        data_definition_print("package " + Configuration.packageName + "." + feature_name)
    }

    private fun makeName(input: String): String {
        if (input.isEmpty()) return "NAME_IS_EMPTY"
        return input[0].lowercaseChar() + input.substring(1)
    }


    private fun act_on_data(fullText: String) {
        val names = fullText.split(" ")
        if (names.size > 1)
            error("Internal class not yet supported ")
        var fullTextToUse = names[0]
        println("Data is " + fullText)
        val (followtype, table) = look_for_follow()
        if (!followtype.equals("TABLE")) {
            error("Error table does not follow data " + fullText)
            return
        }
        if (data_names.containsKey(fullText)) {
            error("Data name is duplicated, has been renamed " + fullTextToUse)
            fullTextToUse += step_count
        }
        trace("Creating class for " + fullTextToUse)
        data_names.put(fullTextToUse, "")
        data_definition_print("data class " + fullTextToUse + "(")
        var headerLine = true
        for (line in table) {
            if (headerLine) {
                headerLine = false
                continue
            }
            val elements = parseLine(line)
            data_definition_print("    var " + makeName(elements[0]) + ": String = \"" + elements[1].trim() + "\",")
        }
        data_definition_print(") ")
    }

    fun act_on_scenario(full_text: String, add_background: Boolean) {
        var full_text_to_use = full_text
        if (scenarios.containsKey(full_text)) {
            full_text_to_use += step_count
            error("Scenario name duplicated renamed " + full_text_to_use)
        } else scenarios.put(full_text_to_use, "")
        step_number_in_scenario = 0
        if (first_scenario) {
            first_scenario = false
        } else {
            if (cleanup && add_background)
                test_print("        test_Cleanup()")
            test_print("        }") // end previous scenario
        }
        test_print("    @Test")
        test_print("    fun test_" + full_text_to_use + "(){")
        test_print("        val " + glue_object + " = " + glue_class + "()")
        if (background && add_background)
            test_print("        test_Background()")
        template_print("")
    }

    fun act_on_step(full_text: String, comment: List<String>) {
        step_number_in_scenario += 1
        val (followtype, table) = look_for_follow()
        template_print("")
        test_print("")
        if (followtype == "TABLE") {
            var option = "ListOfList"
            if (comment.size > 0 && comment[0].length > 0)
                option = comment[0]
            if (option.equals("ListOfList"))
                tableToListOfList(table, full_text)
            else if (option.equals("String"))
                tableToString(table, full_text)
            else if (option.equals("ListOfObject")) {
                if (comment.size < 2) {
                    error("No class name specified")
                    return
                }
                var action = ""
                val className = comment[1]
                if (comment.size > 2) {
                    action = comment[2]
                    if (!action.equals("transpose"))
                        error("Action not recognized " + action)
                }
                tableToListOfObject(table, full_text, className, action)
            } else {
                error("Option not found, default to ListOfList " + option)
                tableToListOfList(table, full_text)
            }
        }
        if (followtype == "NOTHING") {
            noParameter(full_text)
        }
        if (followtype == "STRING") {
            var option = "String"
            if (comment.size > 0 && comment[0].length > 0)
                option = comment[0]
            if (option.equals("ListOfString"))
                StringToList(table, full_text)
            else
                StringToString(table, full_text)
        }
    }

    fun look_for_follow(): Pair<String, MutableList<String>> {
        var line = data.peek()
        val empty = mutableListOf<String>()
        while (line.length > 0 && line[0] == '#') {
            data.next()
            line = data.peek()
        }
        line = line.trim()
        if (line.length == 0) return Pair("NOTHING", empty)
        if (line[0] == '|') {
            val ret_value = read_table()
            trace("Table is " + ret_value)
            return Pair("TABLE", ret_value)
        }
        if (line.equals("\"\"\"")) {
            val ret_value = read_string()
            trace("Multi Line String is " + ret_value)
            return Pair("STRING", ret_value)
        }
        return Pair("NOTHING", empty)
    }

    private fun tableToListOfObject(
        table: MutableList<String>,
        full_text: String,
        className: String,
        action: String,
    ) {
        trace("classNames " + className)
        val s = step_number_in_scenario.toString()
        val dataType = "List<" + className + ">"
        val dataTypeInitalizer = "listOf<" + className + ">"
        test_print("        val objectList" + s + " = " + dataTypeInitalizer + "(")
        var in_header_line = true
        val data_list = convertToListList(table, action)
        var headers = listOf("")
        for (row in data_list) {
            if (in_header_line) {
                headers = row
                in_header_line = false
                continue
            }
            val values = row
            convertBarLineToParameter(headers, values, className)
        }
        test_print("            )")
        test_print("        " + glue_object + "." + full_text + "(objectList" + s + ")")
//        test_print("")
        make_function_template(dataType, full_text)
    }

    private fun convertToListList(table: MutableList<String>, transpose: String): List<List<String>> {
        val temporary =
            mutableListOf(mutableListOf<String>())
        temporary.removeAt(0)
        for (line in table) {
            temporary.add(parseLine(line))
        }
        var result = temporary
        if (transpose.equals("transpose"))
            result = transpose(temporary)
        return result
    }

    private fun convertBarLineToParameter(headers: List<String>, values: List<String>, className: String) {
        trace("Headers " + headers)
        var size = headers.size
        if (headers.size > values.size) {
            size = values.size
            error("not sufficient values, using what is there" + values)
        }
        test_print("            " + className + "(")
        for (i in 0 until size) {
            test_print(
                "                " + makeName(headers[i]) + " = \"" +
                        values[i].replace(
                            Configuration.spaceCharacters,
                            ' '
                        )
                        + "\","
            )
        }
        test_print("                ),")
    }

    private fun noParameter(full_text: String) {
        test_print("        " + glue_object + "." + full_text + "()")
        make_function_template("", full_text)
    }

    private fun make_function_template(dataType: String, full_text: String) {
        if (glue_functions.contains(full_text)) {
            val current_data_type = glue_functions.get(full_text)
            if (!current_data_type.equals(dataType)) {
                error("function $full_text datatype $current_data_type not equals $dataType")
                return
            }
            return  // already have a prototype
        }
        glue_functions.put(full_text, dataType)
        if (dataType.length == 0)
            template_print("    fun " + full_text + "(){")
        else
            template_print("    fun " + full_text + "( value " + ": " + dataType + ") {")
        template_print("        println(\"*******\")")
        if (dataType.length != 0)
            template_print("        println(value)")
        template_print("         assertEquals(true, false)")
        template_print("    } ")
    }

    private fun StringToList(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        val dataType = "List<String>"
        val dataTypeInitalizer = "listOf<String>"
        test_print("        val stringList" + s + " = " + dataTypeInitalizer + "(")
        for (line in table) {
            test_print("            \"" + line + "\",")
        }
        test_print("            )")
        test_print("        " + glue_object + "." + full_text + "(stringList" + s + ")")
//        test_print("")
        make_function_template(dataType, full_text)
    }

    private fun StringToString(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        test_print("        val string" + s + " = ")
        test_print("            \"\"\"")
        for (line in table) {
            test_print("            " + line)
        }
        test_print("            \"\"\".trimIndent()")
        test_print("        " + glue_object + "." + full_text + "(string" + s + ")")
//        test_print("")
        make_function_template("String", full_text)
    }

    private fun tableToString(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        test_print("        val table" + s + " = ")
        test_print("            \"\"\"")
        for (line in table) {
            test_print("            " + line)
        }
        test_print("            \"\"\".trimIndent()")
        test_print("        " + glue_object + "." + full_text + "(table" + s + ")")
//        test_print("")
        make_function_template("String", full_text)
    }

    private fun tableToListOfList(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        val dataType = "List<List<String>>"
        val dataTypeInitalizer = "listOf<List<String>>"
        test_print("        val stringListList" + s + " = " + dataTypeInitalizer + "(")
        for (line in table) {
            convertBarLineToList(line)
        }
        test_print("            )")
        test_print("        " + glue_object + "." + full_text + "(stringListList" + s + ")")
//        test_print("")
        make_function_template(dataType, full_text)
    }

    private fun convertBarLineToList(line: String) {
        test_print("           listOf<String>(")
        val elements = parseLine(line)
        for (element in elements) {
            test_print("            \"" + element + "\",")
        }
        test_print("            ),")
    }

    private fun parseLine(line: String): MutableList<String> {
        var lineShort = line.trim()
        if (lineShort[0] == '|')
            lineShort = lineShort.substringAfter('|')
        else
            error("table not begin with | " + line)
        val last = lineShort.length - 1
        if (lineShort[last] == '|')
            lineShort = lineShort.substring(0, last)
        else
            error("table not end with | " + line)
        val elements = lineShort.split("|")
        val elements_trimmed = mutableListOf<String>()
        for (element in elements) {
            var current = element.trim()
            current = current.replace(Configuration.spaceCharacters, ' ')
            elements_trimmed.add(current)
        }
        return elements_trimmed
    }

    fun read_table(): MutableList<String> {
        val ret_value = mutableListOf<String>()
        var line = data.peek().trim()
        while (line.length > 0 && (line[0] == '|' || line[0] == '#')) {
            line = data.next().trim()
            if (line[0] == '|') {
                ret_value.add(line)
            }
            line = data.peek().trim()
        }
        return ret_value
    }

    fun read_string(): MutableList<String> {
        val ret_value = mutableListOf<String>()
        val firstLine=data.peek()
        val countIndent = countIndent(firstLine)
        data.next()
        var line = data.next()
        while (!line.trim().equals("\"\"\"")) {
            ret_value.add(line.substring(countIndent))
            line = data.next()
        }
        return ret_value
    }

    private fun countIndent(firstLine: String): Int {
        val line = firstLine.trimStart()
        return firstLine.length - line.length

    }

    private fun clean_files() {
        test_file.close()
        glue_template_file.close()
        data_definition_file.close()
        File(test_filename).delete()
        File(glue_template_filename).delete()
        File(data_definition_filename).delete()

    }

    fun test_print(line: String) {
        test_file.write(line)
        test_file.write("\n")
    }

    fun data_definition_print(line: String) {
        data_definition_file.write(line)
        data_definition_file.write("\n")
    }

    fun template_print(line: String) {
        glue_template_file.write(line)
        glue_template_file.write("\n")
    }

    fun endUp() {
        if (cleanup)
            test_print("        test_Cleanup()")
        test_print("        }")
        test_print("    }")
        test_print("")
        template_print("    }")
        test_file.close()
        glue_template_file.close()
        data_definition_file.close()
    }

    class InputIterator(name: String) {
        var data = mutableListOf<String>()
        var _index = 0

        companion object {
            var inc_count = 0
            val EOF = "EOF"
        }

        init {
            _index = 0
            if (name.isNotEmpty()) {
                readFile(name)
            }
        }

        private fun readFile(fileName: String) {
            inc_count++
            if (inc_count > 20) {
                error("Too many levels of include")
                return
            }
            val raw = File(Configuration.featureSubDirectory + fileName).readLines()
            for (line in raw) {
                if (line.startsWith("Include")) {
                    val parts = line.split("\"")
                    trace("Parts are " + parts)
                    if (parts.size < 2) {
                        error("Error filename not surrounded by quotes: " + line)
                        continue
                    }
                    if (parts[1].length < 1) {
                        error("Error zero length filename " + line)
                        continue
                    }
                    val includedFileName = parts[1].trim()
                    trace("Including " + includedFileName)
                    if (includedFileName.endsWith(".csv")) {
                        includeCSVFile(includedFileName)

                    } else
                        readFile(includedFileName)
                } else {
                    if (line.isNotEmpty() && line[0] != '#')
                        data.add(line.trim())
                }
            }
            inc_count--
        }

        private fun includeCSVFile(includedFileName: String) {
            val raw = File(Configuration.featureSubDirectory + includedFileName).readLines()
            for (line in raw) {
                if (line.isEmpty())
                    continue
                val contents = convertCSVtoTable(line)
                data.add(contents.trim())
            }
        }

        fun peek(): String {
            if (_index < data.size) {
                val item = data[_index]
                return item
            } else {
                return EOF
            }
        }

        fun next(): String {
            if (_index < data.size) {
                val item = data[_index]
                _index++
                return item
            } else {
                return EOF
            }
        }

    }

}

fun convertCSVtoTable(csvData: String): String {
    val lines = csvData.split("\n")
    val data = lines.map { parseCsvLine(it) }
    val formattedData = data.map { row ->
        "|" + row.joinToString("|") + "|"
    }
    return formattedData.joinToString("\n")
}

fun transpose(matrix: List<List<String>>): MutableList<MutableList<String>> {
    val transposed = MutableList(matrix[0].size) { MutableList(matrix.size) { "" } }
    val sizeRow = matrix[0].size
    for (element in matrix){
        if (element.size != sizeRow) {
            error("*** Table has uneven rows - not transposed")
            error("     Will probably exit ")
        }
    }
    for (i in matrix.indices) {

        for (j in matrix[i].indices) {
            transposed[j][i] = matrix[i][j]
        }
    }
    return transposed
}

fun parseCsvLine(line: String): List<String> {
    val result = mutableListOf<String>()
    var current = StringBuilder()
    var inQuotes = false

    val length = line.length
    var i = 0

    while (i < length) {
        val char = line[i]
        when {
            char == '"' -> {
                if (inQuotes && i + 1 < length && line[i + 1] == '"') {
                    current.append('"')
                    i++
                } else {
                    inQuotes = !inQuotes
                }
            }

            char == ',' && !inQuotes -> {
                result.add(current.toString())
                current = StringBuilder()
            }

            else -> {
                current.append(char)
            }
        }
        i++
    }
    result.add(current.toString())
    return result
}

fun trace(value: String) {
    if (Configuration.trace_on) {
        println(value)

    }
}

fun error(value: String) {
    println("*** " + value)
}

class Configuration {
    companion object {
         var trace_on = false   // Set to true to see trace
        var spaceCharacters = '^'  // Will replace with space in tables
        var currentDirectory = ""
        var featureSubDirectory = "src\\test\\kotlin\\"
        var testSubDirectory = "src\\test\\kotlin\\"
        var packageName = "gherkinexecutor"
        var data_definition_file_extension = "kt" // change to tmpl if altering data file
        val featureFiles = mutableListOf(
             "tictactoe.feature",
//            "smoketest.feature",
//            "GherkinTranslator.feature",
//            "include.feature",
//            "testfeature.feature",
            "examples.feature",
//            "FlowGrid.feature",
//            "data_definition.feature",
//            "ParseCSV.feature",
//            "fulltest.feature",
//            "GherkinTranslatorSmokeTest.feature",
//            "GherkinTranslatorFullTest.feature"
        )
    }
}

fun main(args: Array<String>) {
    println("Gherkin Executor ")
    Configuration.currentDirectory = System.getProperty("user.dir")
    println("Arguments ")
    for (arg in args) {
        println("   " + arg)
        Configuration.featureFiles.add(arg)
    }
    for (name in Configuration.featureFiles) {
        val translate = Translate()
        println("Translating " + name)
        translate.translate_in_tests(name)
    }
}
