package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Gherkin_Test_Short_glue
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Gherkin_Test_Short{

    @Test
    fun test_Scenario_Steps_with_all_tables(){
        val Feature_Gherkin_Test_Short_glue_object = Feature_Gherkin_Test_Short_glue()

        Feature_Gherkin_Test_Short_glue_object.Given_a_single_step_with_a_table()

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "A",
            "",
            ),
           listOf<String>(
            "C",
            "",
            ),
            )
        Feature_Gherkin_Test_Short_glue_object.When_a_step_with_a_table(stringListList2)


        val string3 = """\
            One line
            Two line
            """.trimIndent()
        Feature_Gherkin_Test_Short_glue_object.Then_a_step_with_a_string(string3)

        }
    }


