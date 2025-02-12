package gherkinexecutor.Feature_Examples

import kotlin.test.assertEquals
import kotlin.test.fail

class Feature_Examples_glue {



    fun Calculation_Convert_F_to_C( value : List<TemperatureCalculation>) {
        for (element in value) {
            val temp = element.toTemperatureCalculationInternal()
            assertEquals(
                temp.c,
                TemperatureCalculations.convertFarenheitToCelsius(temp.f),
                temp.notes
            )
        }
    }

    fun Rule_ID_must_have_exactly_5_letters_and_begin_with_Q( value : List<DomainTermID>) {
        println("---  " + "Star_ID_must_have_exactly_5_letters_and_begin_with_Q")

        for (element in value) {
            val temp = element.toDomainTermIDInternal()
            try {
                ID(temp.value)
                if (!temp.valid)
                    fail("Value of " + temp.value + "accepted but should fail")
            } catch (e: Exception) {
                if (temp.valid)
                    fail("Value of " + temp.value + "failed but should be accepted")
                assertEquals(temp.notes, e.message, "Message does not mathc")
            }
//            assertEquals(
//                temp.valid,
//                ID(temp.value).isValid(),
//                temp.notes
//            )
        }
    }
    val solution = SolutionForListOfNumber()

    fun Given_list_of_numbers( value : List<LabelValue>) {
        for (element in value) {
            solution.add(element.toLabelValueInternal())
        }

    }


    fun When_filtered_by_Label_with_value( value : List<List<String>>) {
        solution.setFilterValue((value[0][0]))
    }

    fun Then_sum_is(value: List<List<String>>) {
        val sum = solution.sum()
        assertEquals(value[0][0].toInt(), sum)
    }

}
