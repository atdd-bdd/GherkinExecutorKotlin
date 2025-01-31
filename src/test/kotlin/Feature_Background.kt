package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Background_glue
import gherkinexecutor.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Background{

    @Test
    fun test_Background(){
        val Feature_Background_glue_object = Feature_Background_glue()

        Feature_Background_glue_object.Given_Background_Function()
        }
    @Test
    fun test_CleanUp(){
        val Feature_Background_glue_object = Feature_Background_glue()

        Feature_Background_glue_object.Given_Cleanup_Function()
        test_CleanUp()
        }
    @Test
    fun test_Scenario_Should_have_Background_and_Cleanup(){
        val Feature_Background_glue_object = Feature_Background_glue()
        test_Background()

        Feature_Background_glue_object.Given_a_regular_function()
        test_CleanUp()
        }
    @Test
    fun test_Scenario_Should_also_have_Background_and_Cleanup(){
        val Feature_Background_glue_object = Feature_Background_glue()
        test_Background()

        Feature_Background_glue_object.Given_a_regular_function()
        test_CleanUp()
        }
    }


