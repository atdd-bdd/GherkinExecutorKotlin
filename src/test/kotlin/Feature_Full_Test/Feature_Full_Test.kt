package gherkinexecutor.Feature_Full_Test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Full_Test{

    @Test
    fun test_Scenario_Some_scenario_here(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()

        val string1 =
            """
            This is an include string
            """.trimIndent()
        feature_Full_Test_glue_object.Given_a_string(string1)

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
        feature_Full_Test_glue_object.Then_a_table(stringListList2)
        }
    @Test
    fun test_Scenario_Convert_a_CSV_file_to_Gherkin_Table(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()

        val string1 =
            """
            a,"b,c","c"","
            1,2,"3,""b,"",,"
            """.trimIndent()
        feature_Full_Test_glue_object.Given(string1)

        val string2 =
            """
            |a|b,c|c",|
            |1|2|3,"b,",,|
            """.trimIndent()
        feature_Full_Test_glue_object.When_converted_result_is(string2)
        }
    @Test
    fun test_Scenario_Transpose_a_table(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()

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
        feature_Full_Test_glue_object.Given_input_table(stringListList1)

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
        feature_Full_Test_glue_object.When_transposed_result_is(stringListList2)
        }
    @Test
    fun test_Background(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()

        feature_Full_Test_glue_object.Given_Background_Function()
        }
    @Test
    fun test_Cleanup(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()

        feature_Full_Test_glue_object.Given_Cleanup_Function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Should_have_Background_and_Cleanup(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        feature_Full_Test_glue_object.Given_a_regular_function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Should_also_have_Background_and_Cleanup(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        feature_Full_Test_glue_object.Given_a_regular_function()
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Simple(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<ATest>(
            ATest(
                anInt = "1",
                aString = "something",
                aDouble = "1.2",
                ),
            )
        feature_Full_Test_glue_object.Given_table_is(objectList1)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_One_with_Data(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<MyClass>(
            MyClass(
                one = "v1",
                two = "v2",
                ),
            MyClass(
                one = "v1a",
                two = "v1b",
                ),
            )
        feature_Full_Test_glue_object.Given_a_step(objectList1)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_One_with_Data_Transpose(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<MyClass>(
            MyClass(
                one = "v1",
                two = "v2",
                ),
            MyClass(
                one = "v1a",
                two = "v1b",
                ),
            )
        feature_Full_Test_glue_object.Given_a_step(objectList1)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Filter_Data(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<NameValue>(
            NameValue(
                iD = "a",
                value = "1",
                ),
            NameValue(
                iD = "b",
                value = "2",
                ),
            NameValue(
                iD = "a",
                value = "3",
                ),
            )
        feature_Full_Test_glue_object.Given_list_of_numbers(objectList1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "a",
            ),
            )
        feature_Full_Test_glue_object.When_filtered_by_ID_value(stringListList2)

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "4",
            ),
            )
        feature_Full_Test_glue_object.Then_sum_is(stringListList3)
        test_Cleanup()
        }
    @Test
    fun test_Scenario_Temperature(){
        val feature_Full_Test_glue_object = Feature_Full_Test_glue()
        test_Background()

        val objectList1 = listOf<TemperatureComparison>(
            TemperatureComparison(
                f = "32",
                c = "0",
                notes = "Freezing",
                ),
            TemperatureComparison(
                f = "212",
                c = "100",
                notes = "Boiling",
                ),
            TemperatureComparison(
                f = "-40",
                c = "-40",
                notes = "Below zero",
                ),
            )
        feature_Full_Test_glue_object.Star_Convert_F_to_C(objectList1)
        test_Cleanup()
        }
    }

