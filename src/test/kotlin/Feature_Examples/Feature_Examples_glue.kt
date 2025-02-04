package gherkinexecutor.Feature_Examples

import kotlin.test.assertEquals


class SolutionForListOfNumber {
    private var values = mutableListOf<NameValueInternal>()
    private var filterValue = ""
    fun add(value: NameValue) {
        values.add(value.toNameValueInternal())
    }

    fun setFilterValue(value: String) {
        filterValue = value
    }

    fun sum(): Int {
        var sum = 0
        for (element in values) {
            if (element.iD == filterValue)
                sum += (element.value)
        }
        return sum
    }
}

fun convertFarenheitToCelsius(input: Int): Int {
    return ((input - 32) * 5) / 9
}

class Feature_Examples_glue {

    val solution = SolutionForListOfNumber()

    fun Given_list_of_numbers(value: List<NameValue>) {
        for (element in value) {
            solution.add(element)
        }

    }

    fun When_filtered_by(value: List<List<String>>) {
        solution.setFilterValue((value[0][1]))
    }

    fun Then_sum_is(value: List<List<String>>) {
        val sum = solution.sum()
        assertEquals(value[0][0].toInt(), sum)
    }


    fun Star_Convert_F_to_C(value: List<Temperature>) {
        for (element in value) {
            assertEquals(
                element.c.toInt(),
                convertFarenheitToCelsius(element.f.toInt()),
                element.notes
            )
        }
    }
}
