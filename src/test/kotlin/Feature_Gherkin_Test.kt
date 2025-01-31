package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Gherkin_Test_glue
import gherkinexecutor.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Gherkin_Test{

    @Test
    fun test_Scenario_Steps_with_all_tables(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

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
        Feature_Gherkin_Test_glue_object.Given_a_single_step_with_a_table(stringListList1)


        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "A",
            "B",
            ),
           listOf<String>(
            "C",
            "D",
            ),
            )
        Feature_Gherkin_Test_glue_object.When_a_step_with_a_table(stringListList2)


        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "1",
            "2",
            ),
           listOf<String>(
            "3",
            "4",
            ),
            )
        Feature_Gherkin_Test_glue_object.Then_a_step_with_a_table(stringListList3)

        }
    @Test
    fun test_Scenario_Steps_with_no_tables(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

        Feature_Gherkin_Test_glue_object.Given_a_single_step_with_no_table()

        Feature_Gherkin_Test_glue_object.When_a_step_with_no_table()

        Feature_Gherkin_Test_glue_object.Then_a_step_with_no_table()
        }
    @Test
    fun test_Scenario_One_step_with_star(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

        Feature_Gherkin_Test_glue_object.Star_a_single_step_with_star()
        }
    @Test
    fun test_Scenario_Step_with_a_string(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

        val string1 = 
            """
            One line
            Two line
            """.trimIndent()
        Feature_Gherkin_Test_glue_object.Then_a_step_with_a_string(string1)

        }
    @Test
    fun test_Scenario_Step_with_a_string_to_list(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

        val stringList1 = listOf<String>(
            "One line separate",
            "Two line separate",
            )
        Feature_Gherkin_Test_glue_object.Then_a_step_with_a_string_list(stringList1)

        }
    @Test
    fun test_Scenario_Steps_with_various_table_forms(){
        val Feature_Gherkin_Test_glue_object = Feature_Gherkin_Test_glue()

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
        Feature_Gherkin_Test_glue_object.Given_table_to_be_list_of_list(stringListList1)

        }
    }


