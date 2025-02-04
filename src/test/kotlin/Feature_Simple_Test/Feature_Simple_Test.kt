package gherkinexecutor.Feature_Simple_Test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Simple_Test{

    @Test
    fun test_Scenario_Simple(){
        val feature_Simple_Test_glue_object = Feature_Simple_Test_glue()

        val objectList1 = listOf<ATest>(
            ATest(
                anInt = "1",
                aString = "something",
                aDouble = "1.2",
                ),
            )
        feature_Simple_Test_glue_object.Given_table_is(objectList1)
        }
    }

