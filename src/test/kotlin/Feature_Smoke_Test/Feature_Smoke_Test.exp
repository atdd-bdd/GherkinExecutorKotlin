package gherkinexecutor.Feature_Smoke_Test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Smoke_Test{

    @Test
    fun test_Scenario_Steps_with_all_tables(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "One",
            "   ",
            ),
           listOf<String>(
            "Three",
            "Four",
            ),
            )
        feature_Smoke_Test_glue_object.Given_a_single_step_with_a_table(stringListList1)
        }
    @Test
    fun test_Scenario_Steps_with_no_tables(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        feature_Smoke_Test_glue_object.Given_a_single_step_with_no_table()

        feature_Smoke_Test_glue_object.When_a_step_with_no_table()

        feature_Smoke_Test_glue_object.Then_a_step_with_no_table()
        }
    @Test
    fun test_Scenario_One_step_with_star(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        feature_Smoke_Test_glue_object.Star_a_single_step_with_star()
        }
    @Test
    fun test_Scenario_Step_with_a_string(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        val string1 =
            """
            One line
            Two line
            """.trimIndent()
        feature_Smoke_Test_glue_object.Then_a_step_with_a_string(string1)
        }
    @Test
    fun test_Scenario_Step_with_a_string_to_list(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        val stringList1 = listOf<String>(
            "One line separate",
            "Two line separate",
            )
        feature_Smoke_Test_glue_object.Then_a_step_with_a_string_list(stringList1)
        }
    @Test
    fun test_Scenario_Steps_with_various_table_forms(){
        val feature_Smoke_Test_glue_object = Feature_Smoke_Test_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "One",
            "Two",
            ),
           listOf<String>(
            "Three",
            "Four",
            ),
            )
        feature_Smoke_Test_glue_object.Given_table_to_be_list_of_list(stringListList1)
        }
    }

