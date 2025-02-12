import java.io.File
import java.io.FileWriter


class Translate {
    private val scenarios = mutableMapOf(Pair("", "")) // used to check if duplicate scenario names
    private val glueFunctions = mutableMapOf(Pair("", "")) // used to make sure only one glue implementation
    private val dataNames = mutableMapOf(Pair("", "")) // used to check for duplicate data
    private var stepCount = 0 // use to label duplicate scenarios
    private val basePath = Configuration.featureSubDirectory
    private var glueClass = ""  // glue class name
    private var glueObject = ""  // glue object name
    private var stepNumberInScenario: Int = 0  // use to label variables in scenario
    private var dataIn = InputIterator("")
    private var firstScenario = true // If first scenario
    private var background = false  // Have seen background
    private var cleanup = false // Have seen cleanup
    private var inCleanupScenario = false // Scenario is cleanup

    // Create the output files, save names for deletions
    private val testFilename = basePath + "Test" + ".tmp"
    private var testFile = FileWriter(testFilename)
    //    private lateinit var aTestFile: FileWriter  - to not create tmp file

    private val glueTemplateFilename = basePath + "Glue" + ".tmp"
    private var glueTemplateFile = FileWriter(glueTemplateFilename)
    private val dataDefinitionFilename = basePath + "DataDefinition" + ".tmp"
    private var dataDefinitionFile = FileWriter(dataDefinitionFilename)
    private var featureActedOn = false // Have found a feature step

    fun translateInTests(name: String) {
        dataIn = InputIterator(name)
        var eof = false
        while (!eof) {
            val line = dataIn.next()
            if (line.equals(InputIterator.EOF)) {
                eof = true
                continue
            }
            actOnLine(line)
        }
        endUp()
    }

    private fun actOnLine(line: String) {
        val (words, comment) = splitLine(line)
        if (words.size > 0) {
            var keyword = words[0].trim(':')
            if (keyword == "*") {
                keyword = "Star"
            }
            words[0] = keyword
            actOnKeyword(keyword, words, comment)
        }
    }

    private fun splitLine(line: String): Pair<MutableList<String>, List<String>> {
        val allWords = line.split(" ")
        val words = mutableListOf<String>()
        val comment = mutableListOf<String>()
        var inComment = false
        for (aWord in allWords) {
            var word = aWord.trim()
            if (word.isEmpty()) continue
            if (word.endsWith(":")) {
                word = word.trim(':')
            }
            if (inComment) {
                comment.add(word)
                continue
            }
            if (word[0] == '#') {
                inComment = true
                word = word.trim('#')
                if (word.isNotEmpty()) {
                    comment.add(word)
                }
                continue
            }
            words.add(word)
        }
        return Pair(words, comment)
    }

    private fun actOnKeyword(keyword: String, words: List<String>, comment: List<String>) {
        val fullName = words.joinToString("_")
        trace("Act on keyword " + keyword + " " + fullName)
        inCleanupScenario = false
        when (keyword) {
            "Feature" -> actOnFeature(fullName)
            "Scenario" -> actOnScenario(fullName, true)
            "Background" -> {
                actOnScenario(fullName, false)
                background = true
            }

            "Cleanup" -> {
                actOnScenario(fullName, false)
                inCleanupScenario = true
                cleanup = true
            }

            "Given" -> actOnStep(fullName, comment)
            "When" -> actOnStep(fullName, comment)
            "Then" -> actOnStep(fullName, comment)
            "And" -> actOnStep(fullName, comment)
            "Star" -> actOnStep(fullName, comment)
            "Rule" -> actOnStep(fullName, comment)
            "Calculation" -> actOnStep(fullName, comment)
            "Data" -> actOnData(words)
            "Arrange" -> actOnStep(fullName, comment)
            "Act" -> actOnStep(fullName, comment)
            "Assert" -> actOnStep(fullName, comment)
            else -> error("keyword not recognized " + keyword)
        }
    }

    private fun actOnFeature(featureName: String) {
        if (featureActedOn) {
            error("Feature keyword duplicated - it is ignored " + featureName)
            return
        }
        featureActedOn = true
        val testPathname = Configuration.testSubDirectory + featureName + "\\" + featureName + ".kt"
        println(" Writing " + testPathname)
        val directoryName = Configuration.testSubDirectory + featureName
        val result = File(directoryName).mkdirs()
        println("Creating " + directoryName + " " + result)
        cleanFiles()
        testFile = FileWriter(testPathname, false)
        val templateFilename = Configuration.testSubDirectory + featureName + "\\" + featureName + "_glue.tmpl"
        glueTemplateFile = FileWriter(templateFilename, false)
        val dataDefinitionPathname =
            Configuration.testSubDirectory + featureName + "\\" + featureName + "_data." + Configuration.dataDefinitionFileExtension
        trace(" Writing " + dataDefinitionPathname)
        dataDefinitionFile = FileWriter(dataDefinitionPathname, false)

        glueClass = featureName + "_glue"

        glueObject = makeName(featureName) + "_glue_object"
        testPrintLn("package " + Configuration.packageName + "." + featureName)
        testPrintLn("import org.junit.jupiter.api.Test")
        testPrintLn("import org.junit.jupiter.api.TestInstance")
        testPrintLn("@TestInstance(TestInstance.Lifecycle.PER_CLASS)")
        testPrintLn("class " + featureName + "{")
        testPrintLn("")

        templatePrintLn("package " + Configuration.packageName + "." + featureName)
        templatePrintLn("import kotlin.test.fail")
        templatePrintLn("")
        templatePrintLn("class " + glueClass + " {")
        templatePrintLn("")
        dataDefinitionPrintLn("package " + Configuration.packageName + "." + featureName)
    }

    private fun makeName(input: String): String {
        if (input.isEmpty()) return "NAME_IS_EMPTY"
        val temp = input.replace(' ', '_')
        return temp[0].lowercaseChar() + temp.substring(1)
    }


    private fun actOnScenario(fullName: String, addBackground: Boolean) {
        var fullNameToUse = fullName
        if (scenarios.containsKey(fullName)) {
            fullNameToUse += stepCount
            error("Scenario name duplicated renamed " + fullNameToUse)
        } else scenarios.put(fullNameToUse, "")
        stepNumberInScenario = 0
        if (firstScenario) {
            firstScenario = false
        } else {
            if (cleanup && addBackground && !inCleanupScenario) testPrintLn("        test_Cleanup()")
            testPrintLn("        }") // end previous scenario
        }
        testPrintLn("    @Test")
        testPrintLn("    fun test_" + fullNameToUse + "(){")
        testPrintLn("        val " + glueObject + " = " + glueClass + "()")
        if (background && addBackground) testPrintLn("        test_Background()")
    }

    private fun actOnStep(fullName: String, comment: List<String>) {
        stepNumberInScenario += 1
        val (followType, table) = lookForFollow()
        testPrintLn("")
        when (followType) {
            "TABLE" -> {
                createTheTable(comment, table, fullName)
            }

            "NOTHING" -> {
                noParameter(fullName)
            }

            "STRING" -> {
                createTheStringCode(comment, table, fullName)
            }
        }
    }

    private fun lookForFollow(): Pair<String, MutableList<String>> {
        var line = dataIn.peek()
        val empty = mutableListOf<String>()
        while (line.isNotEmpty() && line[0] == '#') {
            dataIn.next()
            line = dataIn.peek()
        }
        line = line.trim()
        if (line.isEmpty()) return Pair("NOTHING", empty)
        if (line[0] == '|') {
            val retValue = readTable()
            trace("Table is " + retValue)
            return Pair("TABLE", retValue)
        }
        if (line.equals("\"\"\"")) {
            val retValue = readString()
            trace("Multi Line String is " + retValue)
            return Pair("STRING", retValue)
        }
        return Pair("NOTHING", empty)
    }

    private fun createTheStringCode(
        comment: List<String>,
        table: MutableList<String>,
        fullName: String,
    ) {
        var option = "String"
        if (comment.size > 0 && comment[0].isNotEmpty()) option = comment[0]
        if (option.equals("ListOfString")) stringToList(table, fullName)
        else stringToString(table, fullName)
    }

    private fun createTheTable(
        comment: List<String>,
        table: MutableList<String>,
        fullName: String,
    ): Boolean {
        var option = "ListOfList"
        if (comment.size > 0 && comment[0].isNotEmpty()) option = comment[0]
        if (option.equals("ListOfList")) {
            if (comment.size > 1 && comment[1].length > 0) {
                val objectName = comment[1]
                tableToListOfListOfObject(table, fullName, objectName)
            } else tableToListOfList(table, fullName)
        } else if (option.equals("String") || option.equals("string")) tableToString(table, fullName)
        else if (option.equals("ListOfObject")) {
            if (comment.size < 2) {
                error("No class name specified")
                return true
            }

            val className = comment[1]
            var transpose = false
            if (comment.size > 2) {
                val action = comment[2]
                if (!(action.equals("transpose") || action.equals("Transpose"))) error("Action not recognized " + action)
                else transpose = true
            }
            tableToListOfObject(table, fullName, className, transpose)
        } else {
            error("Option not found, default to ListOfList " + option)
            tableToListOfList(table, fullName)
        }
        return false
    }

    private fun tableToListOfObject(
        table: MutableList<String>,
        fullName: String,
        className: String,
        transpose: Boolean,
    ) {
        trace("classNames " + className)
        val s = stepNumberInScenario.toString()
        val dataType = "List<" + className + ">"
        val dataTypeInitializer = "listOf<" + className + ">"
        testPrintLn("        val objectList" + s + " = " + dataTypeInitializer + "(")
        var inHeaderLine = true
        val dataList = convertToListList(table, transpose)
        var headers = listOf("")
        for (row in dataList) {
            if (inHeaderLine) {
                headers = row
                inHeaderLine = false
                continue
            }
            convertBarLineToParameter(headers, row, className)
        }
        testPrintLn("            )")
        testPrintLn("        " + glueObject + "." + fullName + "(objectList" + s + ")")
        makeFunctionTemplate(dataType, fullName)
    }

    private fun noParameter(fullName: String) {
        testPrintLn("        " + glueObject + "." + fullName + "()")
        makeFunctionTemplate("", fullName)
    }

    private fun convertToListList(table: MutableList<String>, transpose: Boolean): List<List<String>> {
        val temporary = mutableListOf(mutableListOf<String>())
        temporary.removeAt(0)
        for (line in table) {
            temporary.add(parseLine(line))
        }
        var result = temporary
        if (transpose) result = transpose(temporary)
        return result
    }

    private fun convertBarLineToParameter(headers: List<String>, values: List<String>, className: String) {
        trace("Headers " + headers)
        var size = headers.size
        if (headers.size > values.size) {
            size = values.size
            error("not sufficient values, using what is there" + values)
        }
        testPrintLn("            " + className + "(")
        for (i in 0 until size) {
            testPrintLn(
                "                " + makeName(headers[i]) + " = \"" + values[i].replace(
                    Configuration.spaceCharacters, ' '
                ) + "\","
            )
        }
        testPrintLn("                ),")
    }

    private fun makeFunctionTemplate(dataType: String, fullName: String) {
        if (glueFunctions.contains(fullName)) {
            val currentDataType = glueFunctions.get(fullName)
            if (!currentDataType.equals(dataType)) {
                error("function $fullName datatype $currentDataType not equals $dataType")
                return
            }
            return  // already have a prototype
        }
        glueFunctions.put(fullName, dataType)
        if (dataType.isEmpty()) templatePrintLn("    fun " + fullName + "(){")
        else templatePrintLn("    fun " + fullName + "( value " + ": " + dataType + ") {")
        templatePrintLn("        println(\"---  \" + " + "\"" + fullName + "\")")
        if (dataType.length != 0) templatePrintLn("        println(value)")
        if (!Configuration.inTest)
            templatePrintLn("        fail(\"Must implement\")")
        else
            templatePrintLn("")
        templatePrintLn("    }")
        templatePrintLn("")
    }

    private fun stringToList(table: MutableList<String>, fullName: String) {
        val s = stepNumberInScenario.toString()
        val dataType = "List<String>"
        val dataTypeInitializer = "listOf<String>"
        testPrintLn("        val stringList" + s + " = " + dataTypeInitializer + "(")
        for (line in table) {
            testPrintLn("            \"" + line + "\",")
        }
        testPrintLn("            )")
        testPrintLn("        " + glueObject + "." + fullName + "(stringList" + s + ")")
        makeFunctionTemplate(dataType, fullName)
    }

    private fun stringToString(table: MutableList<String>, fullName: String) {
        val s = stepNumberInScenario.toString()
        testPrintLn("        val string" + s + " =")
        testPrintLn("            \"\"\"")
        for (line in table) {
            testPrintLn("            " + line)
        }
        testPrintLn("            \"\"\".trimIndent()")
        testPrintLn("        " + glueObject + "." + fullName + "(string" + s + ")")
        makeFunctionTemplate("String", fullName)
    }

    private fun tableToString(table: MutableList<String>, fullName: String) {
        val s = stepNumberInScenario.toString()
        testPrintLn("        val table" + s + " =")
        testPrintLn("            \"\"\"")
        for (line in table) {
            testPrintLn("            " + line)
        }
        testPrintLn("            \"\"\".trimIndent()")
        testPrintLn("        " + glueObject + "." + fullName + "(table" + s + ")")
        makeFunctionTemplate("String", fullName)
    }

    private fun tableToListOfList(table: MutableList<String>, fullName: String) {
        val s = stepNumberInScenario.toString()
        val dataType = "List<List<String>>"
        val dataTypeInitializer = "listOf<List<String>>"
        testPrintLn("        val stringListList" + s + " = " + dataTypeInitializer + "(")
        for (line in table) {
            convertBarLineToList(line)
        }
        testPrintLn("            )")
        testPrintLn("        " + glueObject + "." + fullName + "(stringListList" + s + ")")
        makeFunctionTemplate(dataType, fullName)
    }

    private fun tableToListOfListOfObject(table: MutableList<String>, fullName: String, objectName: String) {
        val s = stepNumberInScenario.toString()
        val dataType = "List<List<" + objectName + ">>"
        val dataTypeInitializer = "listOf<List<" + objectName + ">>"
        testPrintLn("        val objectListList" + s + " = " + dataTypeInitializer + "(")
        for (line in table) {
            convertBarLineToListOfObject(line, objectName)
        }
        testPrintLn("            )")
        testPrintLn("        " + glueObject + "." + fullName + "(objectListList" + s + ")")
        makeFunctionTemplate(dataType, fullName)
    }

    private fun convertBarLineToList(line: String) {
        testPrintLn("           listOf<String>(")
        val elements = parseLine(line)
        for (element in elements) {
            testPrintLn("            \"" + element + "\",")
        }
        testPrintLn("            ),")
    }

    private fun convertBarLineToListOfObject(line: String, objectName: String) {
        testPrintLn("           listOf<" + objectName + ">(")
        val elements = parseLine(line)
        for (element in elements) {
            testPrintLn("            " + objectName + "(\"" + element + "\"),")
        }
        testPrintLn("            ),")
    }

    private fun parseLine(line: String): MutableList<String> {
        var lineShort = line.trim()
        if (lineShort[0] == '|') lineShort = lineShort.substringAfter('|')
        else {
            error("table not begin with | " + line)
            return mutableListOf("ERROR IN TABLE LINE " + line)
        }
        val last = lineShort.length - 1
        if (last < 0) {
            error("table format error " + line)
            return mutableListOf("ERROR IN TABLE LINE " + line)
        }
        if (lineShort[last] == '|') lineShort = lineShort.substring(0, last)
        else error("table not end with | " + line)
        val elements = lineShort.split("|")
        val elementsTrimmed = mutableListOf<String>()
        for (element in elements) {
            var current = element.trim()
            current = current.replace(Configuration.spaceCharacters, ' ')
            elementsTrimmed.add(current)
        }
        return elementsTrimmed
    }

    private fun readTable(): MutableList<String> {
        val retValue = mutableListOf<String>()
        var line = dataIn.peek().trim()
        while (line.isNotEmpty() && (line[0] == '|' || line[0] == '#')) {
            line = dataIn.next().trim()
            if (line[0] == '|' && line.endsWith('|')) {
                retValue.add(line)
            } else error("Invalid line in table " + line)
            line = dataIn.peek().trim()
        }
        return retValue
    }

    private fun readString(): MutableList<String> {
        val retValue = mutableListOf<String>()
        val firstLine = dataIn.peek()
        val countIndent = countIndent(firstLine)
        dataIn.next()
        var line = dataIn.next()
        while (!line.trim().equals("\"\"\"")) {
            retValue.add(line.substring(countIndent))
            line = dataIn.next()
        }
        return retValue
    }

    private fun countIndent(firstLine: String): Int {
        val line = firstLine.trimStart()
        return firstLine.length - line.length

    }

    private fun cleanFiles() {
        testFile.close()
        glueTemplateFile.close()
        dataDefinitionFile.close()
        File(testFilename).delete()
        File(glueTemplateFilename).delete()
        File(dataDefinitionFilename).delete()

    }

    private fun testPrintLn(line: String) {
        testFile.write(line)
        testFile.write("\n")
    }

    private fun dataDefinitionPrintLn(line: String) {
        dataDefinitionFile.write(line)
        dataDefinitionFile.write("\n")
    }

    private fun templatePrintLn(line: String) {
        glueTemplateFile.write(line)
        glueTemplateFile.write("\n")
    }

    private fun endUp() {
        if (cleanup && !inCleanupScenario) testPrintLn("        test_Cleanup()")
        testPrintLn("        }")   // End last scenario
        testPrintLn("    }") // End the class
        testPrintLn("")
        templatePrintLn("    }")   // End the class
        testFile.close()
        glueTemplateFile.close()
        dataDefinitionFile.close()
    }

    private fun transpose(matrix: List<List<String>>): MutableList<MutableList<String>> {
        val transposed = MutableList(matrix[0].size) { MutableList(matrix.size) { "" } }
        val sizeRow = matrix[0].size
        for (element in matrix) {
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

    private fun trace(value: String) {
        if (Configuration.traceOn) {
            println(value)

        }

    }

    private fun error(value: String) {
        println("*** " + value)
    }

    data class DataValues(
        val name: String,
        val default: String,
        val dataType: String = "String",
        val notes: String = "",
    )

    private fun actOnData(words: List<String>) {
        val internalClassName: String
        if (words.size < 2) {
            error("Need to specify data class name")
        }
        var className = words[1]
        if (words.size > 2) internalClassName = words[2]
        else internalClassName = className + "Internal"
        val (followType, table) = lookForFollow()
        if (!followType.equals("TABLE")) {
            error("Error table does not follow data " + words[0] + " " + words[1])
            return
        }
        if (dataNames.containsKey(className)) {
            error("Data name is duplicated, has been renamed " + className)
            className += stepCount
        }
        trace("Creating class for " + className)
        dataNames.put(className, "")
        dataDefinitionPrintLn("data class " + className + "(")
        val variables = mutableListOf<DataValues>()
        val doInternal = createVariableList(table, variables)
        for (variable in variables) {
            dataDefinitionPrintLn(
                "    val " + makeName(variable.name) + ": String = \"" + variable.default + "\","
            )
        }

        dataDefinitionPrintLn("    )")

        if (doInternal) {
            createConversionMethod(internalClassName, variables)
            createInternalClass(internalClassName, className, variables)
        }
    }

    private fun createConversionMethod(
        internalClassName: String,
        variables: MutableList<DataValues>,
    ) {
        dataDefinitionPrintLn(" {")
        dataDefinitionPrintLn("    fun to" + internalClassName + "() : " + internalClassName + "{")
        dataDefinitionPrintLn("        return " + internalClassName + "(")
        for (variable in variables) {
            val initializer = makeToString(variable)
            dataDefinitionPrintLn("        " + initializer + ",")
        }
        dataDefinitionPrintLn("        ) }") // end function

        dataDefinitionPrintLn("    }") // end class
    }

    private fun makeToString(variable: DataValues): String {
        // Add in additional dataTypes
        var initializer = makeName(variable.name) + ".to" + variable.dataType + "()"
        if (variable.dataType.equals("String"))
            initializer = makeName(variable.name)
        return initializer
    }

    private fun createVariableList(
        table: MutableList<String>,
        variables: MutableList<DataValues>,
    ): Boolean {
        var headerLine = true
        var doInternal = false
        for (line in table) {
            if (headerLine) {
                val headers = parseLine(line)
                checkHeaders(headers)
                headerLine = false

                if (headers.size > 2) doInternal = true
                continue
            }
            val elements = parseLine(line)
            if (elements.size < 2) {
                error(" Data line has less than 2 entries " + line)
                continue
            }
            if (elements.size > 3) variables.add(DataValues(elements[0], elements[1], elements[2], elements[3]))
            else if (elements.size > 2) variables.add(DataValues(elements[0], elements[1], elements[2]))
            else variables.add(DataValues(elements[0], elements[1]))
        }
        return doInternal
    }

    private fun checkHeaders(headers: MutableList<String>) {
        val expected = listOf("Name", "Default", "Datatype", "Notes")
        if (!(headers[0].equals(expected[0]) && headers[1].equals(expected[1]))) error("Headers should start with " + expected)
    }

    private fun createInternalClass(className: String, otherClassName: String, variables: List<DataValues>) {
        var classNameInternal = className
        if (dataNames.containsKey(classNameInternal)) {
            error("Data name is duplicated, has been renamed " + classNameInternal)
            classNameInternal += stepCount
        }
        trace("Creating internal class for " + classNameInternal)
        dataNames.put(classNameInternal, "")
        dataDefinitionPrintLn("data class " + classNameInternal + "(")
        for (variable in variables) {
            val initializer = makeInitializer(variable)
            dataDefinitionPrintLn(
                "    val " + makeName(variable.name) + ": " + variable.dataType + " = " + initializer + ","
            )
        }
        dataDefinitionPrintLn("    ) {")
        dataDefinitionPrintLn("    fun to" + otherClassName + "() : " + otherClassName + "{")
        dataDefinitionPrintLn("        return " + otherClassName + "(")
        for (variable in variables) {
            if (variable.dataType.equals("String"))
                dataDefinitionPrintLn("        " + makeName(variable.name) + ",")
            else
                dataDefinitionPrintLn("        " + makeName(variable.name) + ".toString(),")
        }
        dataDefinitionPrintLn("        ) }") // end function

        dataDefinitionPrintLn("    }") // end class
    }

    private fun makeInitializer(variable: DataValues): String {
        // Add in additional data types that don't use the standard
        var initializer = "\"" + variable.default + "\".to" + variable.dataType + "()"
        if (variable.dataType.equals("String"))
            initializer = "\"" + variable.default + "\""
        return initializer
    }

    class InputIterator(name: String) {
        private var linesIn = mutableListOf<String>()
        private var index = 0

        companion object {
            var includeCount = 0
            const val EOF = "EOF"
        }

        init {
            index = 0
            if (name.isNotEmpty()) {
                readFile(name)
            }
        }

        private fun readFile(fileName: String) {
            includeCount++
            if (includeCount > 20) {
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
                    if (parts[1].isEmpty()) {
                        error("Error zero length filename " + line)
                        continue
                    }
                    val includedFileName = parts[1].trim()
                    trace("Including " + includedFileName)
                    if (includedFileName.endsWith(".csv")) {
                        includeCSVFile(includedFileName)

                    } else readFile(includedFileName)
                } else {
                    if (line.isNotEmpty() && line[0] != '#') linesIn.add(line.trim())
                }
            }
            includeCount--
        }

        private fun includeCSVFile(includedFileName: String) {
            val raw = File(Configuration.featureSubDirectory + includedFileName).readLines()
            for (line in raw) {
                if (line.isEmpty()) continue
                val contents = convertCSVtoTable(line)
                linesIn.add(contents.trim())
            }
        }

        private fun convertCSVtoTable(csvData: String): String {
            val lines = csvData.split("\n")
            val data = lines.map { parseCsvLine(it) }
            val formattedData = data.map { row ->
                "|" + row.joinToString("|") + "|"
            }
            return formattedData.joinToString("\n")
        }

        private fun parseCsvLine(line: String): List<String> {
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

        fun peek(): String {
            if (index < linesIn.size) {
                return linesIn[index]
            } else {
                return EOF
            }
        }

        fun next(): String {
            if (index < linesIn.size) {
                val item = linesIn[index]
                index++
                return item
            } else {
                return EOF
            }
        }

        private fun trace(value: String) {
            if (Configuration.traceOn) {
                println(value)
            }
        }

        private fun error(value: String) {
            println("*** " + value)
        }
    }

}


class Configuration {
    companion object {
        val inTest = false  // switch to true for development of Translator
        var traceOn = false // set to true to see trace
        var spaceCharacters = '^'  // Will replace with space in tables
        var currentDirectory = ""
        var featureSubDirectory = "src\\test\\kotlin\\"
        var packageName = "gherkinexecutor"
        var testSubDirectory = "src\\test\\kotlin\\" + packageName + "\\"
        var dataDefinitionFileExtension = "tmpl" // change to kt if altering data file
        val featureFiles = mutableListOf(
//            "tictactoe.feature",
//            "GherkinTranslator.feature",
//            "include.feature",
//            "testfeature.feature",
            "examples.feature",
//            "tablesandstrings.feature",
//            "data_definition.feature",
//            "ParseCSV.feature",
//            "SimpleTest.feature",
//           "GherkinTranslatorFullTest.feature",
//            "fulltest.feature"
        )
    }
}

fun main(args: Array<String>) {
    println("Gherkin Executor")
    Configuration.currentDirectory = System.getProperty("user.dir")
    println("Arguments")
    for (arg in args) {
        println("   " + arg)
        Configuration.featureFiles.add(arg)
    }
    for (name in Configuration.featureFiles) {
        val translate = Translate()
        println("Translating " + name)
        translate.translateInTests(name)
    }
}
