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
            "a",
            ),
            )
        feature_Examples_glue_object.When_filtered_by_ID_value(stringListList2)

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

        val objectList1 = listOf<TemperatureComparison>(
            TemperatureComparison(
                f = "32",
                c = "0",
                notes = "Freezing",
                ),
            TemperatureComparison(
                f = "212",
                c = "100",
                notes = "Boiling",
                ),
            TemperatureComparison(
                f = "-40",
                c = "-40",
                notes = "Below zero",
                ),
            )
        feature_Examples_glue_object.Star_Convert_F_to_C(objectList1)
        }
    @Test
    fun test_Scenario_Domain_Term_ID(){
        val feature_Examples_glue_object = Feature_Examples_glue()

        val objectList1 = listOf<DomainTermID>(
            DomainTermID(
                value = "Q1234",
                valid = "true",
                notes = "",
                ),
            DomainTermID(
                value = "Q123",
                valid = "false",
                notes = "Too short",
                ),
            DomainTermID(
                value = "Q12345",
                valid = "false",
                notes = "Too long",
                ),
            DomainTermID(
                value = "A1234",
                valid = "false",
                notes = "Must begin with Q",
                ),
            )
        feature_Examples_glue_object.Star_ID_must_have_exactly_5_letters_and_begin_with_Q(objectList1)
        }
    }

