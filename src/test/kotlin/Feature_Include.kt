package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Include_glue
import gherkinexecutor.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Include{

    @Test
    fun test_Scenario_Some_scenario_here(){
        val Feature_Include_glue_object = Feature_Include_glue()

        val string1 = 
            """
            This is an include string
            """.trimIndent()
        Feature_Include_glue_object.Given_a_string(string1)


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
        Feature_Include_glue_object.Then_a_table(stringListList2)

        }
    }


