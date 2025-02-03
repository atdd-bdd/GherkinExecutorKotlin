package gherkinexecutor.Feature_Full_Test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Full_Test{

    @Test
    fun test_Scenario_Some_scenario_here(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()

        val string1 = 
            """
            This is an include string
            """.trimIndent()
        Feature_Full_Test_glue_object.Given_a_string(string1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "a",
            "b,c",
            "d,",
            ),
           listOf<String>(
            "1",
            "2",
            "3",
            ),
            )
        Feature_Full_Test_glue_object.Then_a_table(stringListList2)
        }
    @Test
    fun test_Scenario_Convert_a_CSV_file_to_Gherkin_Table(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()

        val string1 = 
            """
            a,"b,c","c"","
            1,2,"3,""b,"",,"
            """.trimIndent()
        Feature_Full_Test_glue_object.Given(string1)

        val string2 = 
            """
            |a|b,c|c",|
            |1|2|3,"b,",,|
            """.trimIndent()
        Feature_Full_Test_glue_object.When_converted_result_is(string2)
        }
    @Test
    fun test_Scenario_Transpose_a_table(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()

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
        Feature_Full_Test_glue_object.Given_input_table(stringListList1)

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
        Feature_Full_Test_glue_object.When_transposed_result_is(stringListList2)
        }
    @Test
    fun test_Background(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()

        Feature_Full_Test_glue_object.Given_Background_Function()
        }
    @Test
    fun test_Cleanup(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()

        Feature_Full_Test_glue_object.Given_Cleanup_Function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Should_have_Background_and_Cleanup(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        Feature_Full_Test_glue_object.Given_a_regular_function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Should_also_have_Background_and_Cleanup(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        Feature_Full_Test_glue_object.Given_a_regular_function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_One_with_Data(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<MyClass>(
        MyClass(
            One = "v1",
            Two = "v2",
        ),
        MyClass(
            One = "v1a",
            Two = "v1b",
        ),
            )
        Feature_Full_Test_glue_object.Given_a_step(objectList1)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_One_with_Data_Transpose(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<MyClass>(
        MyClass(
            One = "v1",
            Two = "v2",
        ),
        MyClass(
            One = "v1a",
            Two = "v1b",
        ),
            )
        Feature_Full_Test_glue_object.Given_a_step(objectList1)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Filter_Data(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<NameValue>(
        NameValue(
            ID = "a",
            Value = "1",
        ),
        NameValue(
            ID = "b",
            Value = "2",
        ),
        NameValue(
            ID = "a",
            Value = "3",
        ),
            )
        Feature_Full_Test_glue_object.Given_list_of_numbers(objectList1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "ID",
            "a",
            ),
            )
        Feature_Full_Test_glue_object.When_filtered_by(stringListList2)

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "4",
            ),
            )
        Feature_Full_Test_glue_object.Then_sum_is(stringListList3)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Temperature(){
        val Feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<Temperature>(
        Temperature(
            F = "32",
            C = "0",
            Notes = "Freezing",
        ),
        Temperature(
            F = "212",
            C = "100",
            Notes = "Boiling",
        ),
        Temperature(
            F = "-40",
            C = "-40",
            Notes = "Below zero",
        ),
            )
        Feature_Full_Test_glue_object.Star_Convert_F_to_C(objectList1)
        test_Cleanup()
        }
    }

