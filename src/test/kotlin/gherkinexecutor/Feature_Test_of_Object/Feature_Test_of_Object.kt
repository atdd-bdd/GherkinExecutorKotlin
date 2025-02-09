package gherkinexecutor.Feature_Test_of_Object
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Test_of_Object{

    @Test
    fun test_Scenario_Simple_One_using_an_external_class(){
        val feature_Test_of_Object_glue_object = Feature_Test_of_Object_glue()

        val objectList1 = listOf<ContainsLibraryClass>(
            ContainsLibraryClass(
                aURL = "http://kenpugh.com",
                aFile = "myfile.txt",
                aPattern = "ab*",
                ),
            )
        feature_Test_of_Object_glue_object.Given_table_is(objectList1)
        }
    }

