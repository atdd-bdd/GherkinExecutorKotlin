package gherkinexecutor.Feature_FlowGrid
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_FlowGrid{

    @Test
    fun test_Scenario_One_Run(){
        val feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = 
            """
            Run
            """.trimIndent()
        feature_FlowGrid_glue_object.Given_log_contains(string1)

        feature_FlowGrid_glue_object.When_displayed()

        val table3 = 
            """
            | R   | UC  |
            | UC  | UC  |
            """.trimIndent()
        feature_FlowGrid_glue_object.Then_grid_is(table3)
        }
    @Test
    fun test_Scenario_Another_Run(){
        val feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = 
            """
            Run
            Commit
            """.trimIndent()
        feature_FlowGrid_glue_object.Given_log_contains(string1)

        feature_FlowGrid_glue_object.When_displayed()

        val table3 = 
            """
            | R  | C  |
            | C  | C  |
            """.trimIndent()
        feature_FlowGrid_glue_object.Then_grid_is(table3)
        }
    @Test
    fun test_Scenario_One_Test_that_fails(){
        val feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = 
            """
            Test Fail
            Run
            """.trimIndent()
        feature_FlowGrid_glue_object.Given_log_contains(string1)

        feature_FlowGrid_glue_object.When_displayed()

        val table3 = 
            """
            | F  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        feature_FlowGrid_glue_object.Then_grid_is(table3)
        }
    @Test
    fun test_Scenario_One_Test_that_passes(){
        val feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = 
            """
            Test Pass
            Run
            """.trimIndent()
        feature_FlowGrid_glue_object.Given_log_contains(string1)

        feature_FlowGrid_glue_object.When_displayed()

        val table3 = 
            """
            | P  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        feature_FlowGrid_glue_object.Then_grid_is(table3)
        }
    @Test
    fun test_Scenario_One_Test_that_passes_and_one_fails(){
        val feature_FlowGrid_glue_object = Feature_FlowGrid_glue()

        val string1 = 
            """
            Test Pass Fail
            Run
            """.trimIndent()
        feature_FlowGrid_glue_object.Given_log_contains(string1)

        feature_FlowGrid_glue_object.When_displayed()

        val table3 = 
            """
            | F  | R  | C  |
            | P  | R  | C  |
            | C  | C  | C  |
            """.trimIndent()
        feature_FlowGrid_glue_object.Then_grid_is(table3)
        }
    }

