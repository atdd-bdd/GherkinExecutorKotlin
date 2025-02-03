package gherkinexecutor.Feature_Gherkin_Translator_Smoke_Test
import kotlin.test.assertEquals
import Translate
import java.io.File

class Feature_Gherkin_Translator_Smoke_Test_glue {

    var featureFileName = ""
    fun Given_feature_file_is( value : List<List<String>>) {
        featureFileName = value[0][0]
    }

    fun When_translated(){
        val translate = Translate()
        println("Translating " + featureFileName)
        translate.translate_in_tests(featureFileName)

    }

    fun Then_test_file_should_match_expected( value : List<FileNames>) {
        println("expected " + value[0].expected)
        println("actual " + value[0].actual)
        val expected = File(Configuration.featureSubDirectory + value[0].expected).readText()
        val actual = File(Configuration.featureSubDirectory + value[0].actual).readText()
        assertEquals(expected, actual)
    }

    fun And_data_file_should_match_expected( value : List<FileNames>) {
        println("expected " + value[0].expected)
        println("actual " + value[0].actual)
        val expected = File(Configuration.featureSubDirectory + value[0].expected).readText()
        val actual = File(Configuration.featureSubDirectory + value[0].actual).readText()
        assertEquals(expected, actual)
    }

    fun And_glue_template_file_should_match_expected( value : List<FileNames>) {
        println("expected " + value[0].expected)
        println("actual " + value[0].actual)
        val expected = File(Configuration.featureSubDirectory + value[0].expected).readText()
        val actual = File(Configuration.featureSubDirectory + value[0].actual).readText()
        assertEquals(expected, actual)
    }
    }
