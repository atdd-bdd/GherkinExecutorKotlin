package gherkinexecutor.Feature_Examples

class TemperatureCalculations {
    companion object {
        fun convertFarenheitToCelsius(input: Int): Int {
            return ((input - 32) * 5) / 9
        }
    }
}


class SolutionForListOfNumber {
    private var values = mutableListOf<NameValueInternal>()
    private var filterValue = ""
    fun add(value: NameValueInternal) {
        values.add(value)
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
