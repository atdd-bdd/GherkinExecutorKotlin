package gherkinexecutor.Feature_Gherkin_Translator_Full_Test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Gherkin_Translator_Full_Test{

    @Test
    fun test_Scenario_Run_the_Test_and_check_the_output(){
        val feature_Gherkin_Translator_Full_Test_glue_object = Feature_Gherkin_Translator_Full_Test_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "fulltest.feature",
            ),
            )
        feature_Gherkin_Translator_Full_Test_glue_object.Given_feature_file_is(stringListList1)

        feature_Gherkin_Translator_Full_Test_glue_object.When_translated()

        val objectList3 = listOf<FileNames>(
            FileNames(
                expected = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test.exp",
                actual = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test.kt",
                ),
            )
        feature_Gherkin_Translator_Full_Test_glue_object.Then_test_file_should_match_expected(objectList3)

        val objectList4 = listOf<FileNames>(
            FileNames(
                expected = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test_data.exp",
                actual = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test_data.tmpl",
                ),
            )
        feature_Gherkin_Translator_Full_Test_glue_object.And_data_file_should_match_expected(objectList4)

        val objectList5 = listOf<FileNames>(
            FileNames(
                expected = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test_glue.exp",
                actual = "gherkinexecutor/Feature_Full_Test/Feature_Full_Test_glue.tmpl",
                ),
            )
        feature_Gherkin_Translator_Full_Test_glue_object.And_glue_template_file_should_match_expected(objectList5)
        }
    }

