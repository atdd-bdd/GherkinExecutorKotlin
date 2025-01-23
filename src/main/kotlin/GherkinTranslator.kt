import java.io.File
import java.io.FileWriter

class Translate {
    val basePath = "src\\test\\kotlin\\"
    var glue_class = ""
    var glue_object = ""
    var step_number_in_scenario = 0
    var data = InputIterator("")
    var first_scenario = true
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
        clean_up()
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
                    continue
                }
            }
            words.add(word)
        }
        return Pair(words, comment)
    }

    fun act_on_keyword(keyword: String, words: List<String>, comment: List<String>) {
        var full_text = words.joinToString("_")
        when (keyword) {
            "Feature" -> act_on_feature(full_text)
            "Scenario" -> act_on_scenario(full_text)
            "Given" -> act_on_step(full_text, comment)
            "When" -> act_on_step(full_text, comment)
            "Then" -> act_on_step(full_text, comment)
            "Star" -> act_on_step(full_text, comment)
        }
    }

    var test_file = FileWriter(basePath + "Test" + ".tmp")
    var glue_template_file = FileWriter(basePath + "Glue" + ".tmp")

    fun act_on_feature(full_text: String) {
        val test_pathname = basePath + full_text + ".kt"
        test_file = FileWriter(test_pathname, false)
        glue_template_file = FileWriter(basePath + full_text + "_glue.kt", false)

        glue_class = full_text + "_glue"
        glue_object = full_text + "_glue_object"

        test_print("package gherkinexecutor")
        test_print("import org.junit.jupiter.api.Test")
        test_print("import org.junit.jupiter.api.TestInstance")
        test_print("import gherkinexecutor."+ glue_class)
        test_print("@TestInstance(TestInstance.Lifecycle.PER_CLASS)")
        test_print("class " + full_text + "{")
        test_print("")
        test_print("    val " + glue_object + " = " + glue_class + "()");

        template_print("package gherkinexecutor")
        template_print("import kotlin.test.assertEquals")
        template_print("")
        template_print("class " + glue_class + " {")
        template_print("")
    }

    fun test_print(line: String) {
        test_file.write(line)
        test_file.write("\n")
    }

    fun template_print(line: String) {
        glue_template_file.write(line)
        glue_template_file.write("\n")
    }

    fun clean_up() {
        test_print("        }")
        test_print("    }")
        test_print("")
        test_print("")
        template_print("    }")
        test_file.close()
        glue_template_file.close()

    }

    fun act_on_scenario(full_text: String) {
        step_number_in_scenario = 0
        if (first_scenario){
            first_scenario = false
        }
        else
            test_print("        }") // end previous scenario
        test_print("    @Test")
        test_print("    fun test_" + full_text + "(){")
        template_print("")
    }

    fun act_on_step(full_text: String, comment: List<String>) {
        step_number_in_scenario += 1
        val (followtype, table) = look_for_follow()

        template_print("")

        if (followtype == "TABLE") {
            // switch output based on desired type in the comments
            tableToString(table, full_text)
        }
        if (followtype == "NOTHING") {
            noParameter(full_text)
        }
        if (followtype == "STRING") {
            StringToString(table, full_text)

        }
        template_print("        assertEquals(true, false)  ")
        template_print("    }")
    }

    private fun noParameter(full_text: String) {
        test_print("        " + glue_object + "." + full_text + "()")
        template_print("    fun " + full_text + "(){")
    }

    private fun StringToString(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        test_print("        val string" + s + " = \"\"\"\\")
        for (line in table) {
            test_print("            " + line)
        }
        test_print("            \"\"\".trimIndent()")
        test_print("")
        test_print("        " + glue_object + "." + full_text + "(string" + s + ")")
        template_print("    fun " + full_text + "( string" + s + ":String ){")
    }

    private fun tableToString(table: MutableList<String>, full_text: String) {
        val s = step_number_in_scenario.toString()
        test_print("        val table" + s + " = \"\"\"\\")
        for (line in table) {
            test_print("            " + line)
        }
        test_print("            \"\"\".trimIndent()")
        test_print("")
        test_print("        " + glue_object + "." + full_text + "(table" + s + ")")
        template_print("    fun " + full_text + "( table" + s + ": String){")
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
        var ret_value = mutableListOf<String>()
        var line = data.next()
        line = data.next()
        while (!line.trim().equals("\"\"\"")) {
            ret_value.add(line)
            line = data.next()
        }
        return ret_value
    }


    fun look_for_follow(): Pair<String, MutableList<String>> {
        var line = data.peek()
        var empty = mutableListOf<String>()
        while (line.length > 0 && line[0] == '#') {
            line = data.next()
            line = data.peek()
        }
        line = line.trim()
        if (line.length == 0) return Pair("NOTHING", empty)
        if (line[0] == '|') {
            var ret_value = read_table()
            return Pair("TABLE", ret_value)
        }
        if (line.equals("\"\"\"")) {
            var ret_value = read_string()
            return Pair("STRING", ret_value)
        }
        return Pair("NOTHING", empty)
    }


    class InputIterator(name: String) {

        var data = mutableListOf<String>()
        var _index = 0

        init {
            _index = 0
            if (!name.equals("")) {
                var raw = File(name).readLines()
                for (line in raw) {
                    if (line.length > 0)
                        data.add(line.trim())
                }
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

        companion object {
            val EOF = "FOR"
        }
    }

}

fun main(args: Array<String>) {
    println("Gherkin Executor ")
    val translate = Translate()
    val path = System.getProperty("user.dir")
    println("Working Directory = $path")
    translate.translate_in_tests("testfeature.feature")
}
