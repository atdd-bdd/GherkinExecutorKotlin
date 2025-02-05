package gherkinexecutor.Feature_Examples

import gherkinexecutor.Feature_Examples.TemperatureCalculations
import kotlin.test.assertEquals


class Feature_Examples_glue {

    val solution = SolutionForListOfNumber()

    fun Given_list_of_numbers(value: List<NameValue>) {
        for (element in value) {
            solution.add(element.toNameValueInternal())
        }

    }


    fun When_filtered_by_ID_value( value : List<List<String>>) {
            solution.setFilterValue((value[0][0]))
    }

    fun Then_sum_is(value: List<List<String>>) {
        val sum = solution.sum()
        assertEquals(value[0][0].toInt(), sum)
    }


    fun Star_Convert_F_to_C(value: List<TemperatureComparison>) {
        for (element in value) {
            val temp = element.toTemperatureComparisonInternal()
            assertEquals(
                temp.c,
                TemperatureCalculations.convertFarenheitToCelsius(temp.f),
                temp.notes
            )
        }
    }
}
