package gherkinexecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import gherkinexecutor.Feature_Data_glue
import gherkinexecutor.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Data{

    @Test
    fun test_Scenario_One_with_Data(){
        val Feature_Data_glue_object = Feature_Data_glue()

        val objectList1 = listOf<MyClass>(
        MyClass(
            One = "v1",
            Two = "v2",
        ),
        MyClass(
            One = "v1a",
            Two = "v1b",
        ),
            )
        Feature_Data_glue_object.Given_a_step(objectList1)

        }
    @Test
    fun test_Scenario_One_with_Data_Transpose(){
        val Feature_Data_glue_object = Feature_Data_glue()

        val objectList1 = listOf<MyClass>(
        MyClass(
            One = "v1",
            Two = "v2",
        ),
        MyClass(
            One = "v1a",
            Two = "v1b",
        ),
            )
        Feature_Data_glue_object.Given_a_step(objectList1)

        }
    }


