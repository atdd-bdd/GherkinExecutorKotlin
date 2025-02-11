package gherkinexecutor.Feature_Examples

class TemperatureCalculations {
    companion object {
        fun convertFarenheitToCelsius(input: Int): Int {
            return ((input - 32) * 5) / 9
        }
    }
}


class SolutionForListOfNumber {
    private var values = mutableListOf<LabelValueInternal>()
    private var filterValue = ""
    fun add(value: LabelValueInternal) {
        values.add(value)
    }

    fun setFilterValue(value: String) {
        filterValue = value
    }

    fun sum(): Int {
        var sum = 0
        for (element in values) {
            if (element.label == filterValue)
                sum += (element.value)
        }
        return sum
    }
}
