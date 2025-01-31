package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_FlowGrid_glue
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_FlowGrid{

    @Test
    fun test_Scenario_One_Run(){
        val Feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = """\
            Run
            """.trimIndent()
        Feature_FlowGrid_glue_object.Given_log_contains(string1)


        Feature_FlowGrid_glue_object.When_displayed()

        val table3 = """\
            | R   | UC  |
            | UC  | UC  |
            """.trimIndent()
        Feature_FlowGrid_glue_object.Then_grid_is(table3)

        }
    @Test
    fun test_Scenario_Another_Run(){
        val Feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = """\
            Run
            Commit
            """.trimIndent()
        Feature_FlowGrid_glue_object.Given_log_contains(string1)


        Feature_FlowGrid_glue_object.When_displayed()

        val table3 = """\
            | R  | C  |
            | C  | C  |
            """.trimIndent()
        Feature_FlowGrid_glue_object.Then_grid_is(table3)

        }
    @Test
    fun test_Scenario_One_Test_that_fails(){
        val Feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = """\
            Test Fail
            Run
            """.trimIndent()
        Feature_FlowGrid_glue_object.Given_log_contains(string1)


        Feature_FlowGrid_glue_object.When_displayed()

        val table3 = """\
            | F  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        Feature_FlowGrid_glue_object.Then_grid_is(table3)

        }
    @Test
    fun test_Scenario_One_Test_that_passes(){
        val Feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = """\
            Test Pass
            Run
            """.trimIndent()
        Feature_FlowGrid_glue_object.Given_log_contains(string1)


        Feature_FlowGrid_glue_object.When_displayed()

        val table3 = """\
            | P  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        Feature_FlowGrid_glue_object.Then_grid_is(table3)

        }
    @Test
    fun test_Scenario_One_Test_that_passes_and_one_fails(){
        val Feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = """\
            Test Pass Fail
            Run
            """.trimIndent()
        Feature_FlowGrid_glue_object.Given_log_contains(string1)


        Feature_FlowGrid_glue_object.When_displayed()

        val table3 = """\
            | F  | R  | C  |
            | P  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        Feature_FlowGrid_glue_object.Then_grid_is(table3)

        }
    }


