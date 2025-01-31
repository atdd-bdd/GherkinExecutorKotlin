package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Parse_CSV_glue
import gherkinexecutor.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Parse_CSV{

    @Test
    fun test_Scenario_Convert_a_CSV_file_to_Gherkin_Table(){
        val Feature_Parse_CSV_glue_object = Feature_Parse_CSV_glue()

        val string1 = 
            """
            a,"b,c","c"","
            1,2,"3,""b,"",,"
            """.trimIndent()
        Feature_Parse_CSV_glue_object.Given(string1)


        val string2 = 
            """
            |a|b,c|c",|
            |1|2|3,"b,",,|
            """.trimIndent()
        Feature_Parse_CSV_glue_object.When_converted_result_is(string2)

        }
    @Test
    fun test_Scenario_Transpose_a_table(){
        val Feature_Parse_CSV_glue_object = Feature_Parse_CSV_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "a",
            "b",
            "c",
            ),
           listOf<String>(
            "d",
            "e",
            "f",
            ),
            )
        Feature_Parse_CSV_glue_object.Given_input_table(stringListList1)


        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "a",
            "d",
            ),
           listOf<String>(
            "b",
            "e",
            ),
           listOf<String>(
            "c",
            "f",
            ),
            )
        Feature_Parse_CSV_glue_object.When_transposed_result_is(stringListList2)

        }
    }


