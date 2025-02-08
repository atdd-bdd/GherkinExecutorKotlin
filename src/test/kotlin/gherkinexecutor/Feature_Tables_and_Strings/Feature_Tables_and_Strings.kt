package gherkinexecutor.Feature_Tables_and_Strings
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Tables_and_Strings{

    @Test
    fun test_Scenario_Here_are_string_options(){
        val feature_Tables_and_Strings_glue_object = Feature_Tables_and_Strings_glue()

        val string1 =
            """
            One line
            Two line
            """.trimIndent()
        feature_Tables_and_Strings_glue_object.Star_A_multiline_string_to_a_string(string1)

        val stringList2 = listOf<String>(
            "Three line",
            "Four line",
            )
        feature_Tables_and_Strings_glue_object.Star_A_multiline_string_to_a_List_of_String(stringList2)
        }
    @Test
    fun test_Scenario_Here_are_table_options(){
        val feature_Tables_and_Strings_glue_object = Feature_Tables_and_Strings_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "aa",
            "bb",
            ),
           listOf<String>(
            "cc",
            "dd",
            ),
            )
        feature_Tables_and_Strings_glue_object.Star_A_table_to_List_of_List_of_String(stringListList1)

        val objectList2 = listOf<ExampleClass>(
            ExampleClass(
                fieldA = "a",
                fieldB = "b",
                ),
            ExampleClass(
                fieldA = "c",
                fieldB = "d",
                ),
            )
        feature_Tables_and_Strings_glue_object.Star_A_table_to_List_of_Object(objectList2)

        val objectList3 = listOf<ExampleClass>(
            ExampleClass(
                fieldA = "a",
                fieldB = "b",
                ),
            ExampleClass(
                fieldA = "c",
                fieldB = "d",
                ),
            )
        feature_Tables_and_Strings_glue_object.Star_A_table_to_List_of_Object(objectList3)

        val objectList4 = listOf<ExampleClass>(
            ExampleClass(
                fieldA = "a",
                ),
            ExampleClass(
                fieldA = "c",
                ),
            )
        feature_Tables_and_Strings_glue_object.Star_A_table_to_List_of_Object_with_Defaults(objectList4)

        val objectList5 = listOf<ExampleClassWithBlanks>(
            ExampleClassWithBlanks(
                field_1 = "a",
                field_2 = "b",
                ),
            ExampleClassWithBlanks(
                field_1 = "c",
                field_2 = "d",
                ),
            )
        feature_Tables_and_Strings_glue_object.Star_A_table_to_List_of_Object_with_Blanks_in_Name(objectList5)

        val table6 =
            """
            | aa  | bb  |
            | cc  | dd  |
            """.trimIndent()
        feature_Tables_and_Strings_glue_object.Star_A_table_to_String(table6)
        }
    }

