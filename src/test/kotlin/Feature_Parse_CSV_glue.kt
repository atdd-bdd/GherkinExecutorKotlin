package gherkinexecutor
import convertCSVtoTable
import kotlin.test.assertEquals
import gherkinexecutor.*
import transpose

class Feature_Parse_CSV_glue {


    var input = ""
    fun Given( value : String) {
        println("Input is " + value)
        input = value
    } 

    fun When_converted_result_is( value : String) {
        val output = convertCSVtoTable(input)
        println("Expected " + value)
        assertEquals(value, output)
    }
    var inputTable = listOf(listOf(""))
    fun Given_input_table( value : List<List<String>>) {
        println("Input table " + value)
         inputTable = value
    }
    fun When_transposed_result_is( value : List<List<String>>) {
        val result = transpose(inputTable)
        println("result is " + result)
        assertEquals(value, result)
    }




}
