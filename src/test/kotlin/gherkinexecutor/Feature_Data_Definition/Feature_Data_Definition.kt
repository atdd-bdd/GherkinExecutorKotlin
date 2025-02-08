package gherkinexecutor.Feature_Data_Definition
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Data_Definition{

    @Test
    fun test_Scenario_Simple(){
        val feature_Data_Definition_glue_object = Feature_Data_Definition_glue()

        val objectList1 = listOf<ATest>(
            ATest(
                anInt = "1",
                aString = "something",
                aDouble = "1.2",
                ),
            )
        feature_Data_Definition_glue_object.Given_table_is(objectList1)
        }
    @Test
    fun test_Scenario_One_with_Data(){
        val feature_Data_Definition_glue_object = Feature_Data_Definition_glue()

        val objectList1 = listOf<MyClass>(
            MyClass(
                one = "v1",
                two = "v2",
                ),
            MyClass(
                one = "v1a",
                two = "v1b",
                ),
            )
        feature_Data_Definition_glue_object.Given_a_step(objectList1)
        }
    @Test
    fun test_Scenario_One_with_Data_Transpose(){
        val feature_Data_Definition_glue_object = Feature_Data_Definition_glue()

        val objectList1 = listOf<MyClass>(
            MyClass(
                one = "v1",
                two = "v2",
                ),
            MyClass(
                one = "v1a",
                two = "v1b",
                ),
            )
        feature_Data_Definition_glue_object.Given_a_step(objectList1)
        }
    }

