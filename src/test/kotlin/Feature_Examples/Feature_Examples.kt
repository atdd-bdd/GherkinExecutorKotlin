package gherkinexecutor.Feature_Examples
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Examples{

    @Test
    fun test_Scenario_Filter_Data(){
        val feature_Examples_glue_object = Feature_Examples_glue()

        val objectList1 = listOf<NameValue>(
            NameValue(
                iD = "a",
                value = "1",
                ),
            NameValue(
                iD = "b",
                value = "2",
                ),
            NameValue(
                iD = "a",
                value = "3",
                ),
            )
        feature_Examples_glue_object.Given_list_of_numbers(objectList1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "ID",
            "a",
            ),
            )
        feature_Examples_glue_object.When_filtered_by(stringListList2)

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "4",
            ),
            )
        feature_Examples_glue_object.Then_sum_is(stringListList3)
        }
    @Test
    fun test_Scenario_Temperature(){
        val feature_Examples_glue_object = Feature_Examples_glue()

        val objectList1 = listOf<Temperature>(
            Temperature(
                f = "32",
                c = "0",
                notes = "Freezing",
                ),
            Temperature(
                f = "212",
                c = "100",
                notes = "Boiling",
                ),
            Temperature(
                f = "-40",
                c = "-40",
                notes = "Below zero",
                ),
            )
        feature_Examples_glue_object.Star_Convert_F_to_C(objectList1)
        }
    }

