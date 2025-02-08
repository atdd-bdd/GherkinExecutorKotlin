package gherkinexecutor.Feature_Full_Test
import kotlin.test.fail

class Feature_Full_Test_glue {

    fun Given_a_string( value : String) {
        println("---  " + "Given_a_string")
        println(value)

    }

    fun Then_a_table( value : List<List<String>>) {
        println("---  " + "Then_a_table")
        println(value)

    }

    fun Given( value : String) {
        println("---  " + "Given")
        println(value)

    }

    fun When_converted_result_is( value : String) {
        println("---  " + "When_converted_result_is")
        println(value)

    }

    fun Given_input_table( value : List<List<String>>) {
        println("---  " + "Given_input_table")
        println(value)

    }

    fun When_transposed_result_is( value : List<List<String>>) {
        println("---  " + "When_transposed_result_is")
        println(value)

    }

    fun Given_Background_Function(){
        println("---  " + "Given_Background_Function")

    }

    fun Given_Cleanup_Function(){
        println("---  " + "Given_Cleanup_Function")

    }

    fun Given_a_regular_function(){
        println("---  " + "Given_a_regular_function")

    }

    fun Given_table_is( value : List<ATest>) {
        println("---  " + "Given_table_is")
        println(value)

    }

    fun Given_a_step( value : List<MyClass>) {
        println("---  " + "Given_a_step")
        println(value)

    }

    fun Given_list_of_numbers( value : List<NameValue>) {
        println("---  " + "Given_list_of_numbers")
        println(value)

    }

    fun When_filtered_by_ID_value( value : List<List<String>>) {
        println("---  " + "When_filtered_by_ID_value")
        println(value)

    }

    fun Then_sum_is( value : List<List<String>>) {
        println("---  " + "Then_sum_is")
        println(value)

    }

    fun Star_Convert_F_to_C( value : List<TemperatureComparison>) {
        println("---  " + "Star_Convert_F_to_C")
        println(value)

    }

    fun Star_A_multiline_string_to_a_string( value : String) {
        println("---  " + "Star_A_multiline_string_to_a_string")
        println(value)

    }

    fun Star_A_multiline_string_to_a_List_of_String( value : List<String>) {
        println("---  " + "Star_A_multiline_string_to_a_List_of_String")
        println(value)

    }

    fun Star_A_table_to_List_of_List_of_String( value : List<List<String>>) {
        println("---  " + "Star_A_table_to_List_of_List_of_String")
        println(value)

    }

    fun Star_A_table_to_List_of_Object( value : List<ExampleClass>) {
        println("---  " + "Star_A_table_to_List_of_Object")
        println(value)

    }

    fun Star_A_table_to_List_of_Object_with_Defaults( value : List<ExampleClass>) {
        println("---  " + "Star_A_table_to_List_of_Object_with_Defaults")
        println(value)

    }

    fun Star_A_table_to_List_of_Object_with_Blanks_in_Name( value : List<ExampleClassWithBlanks>) {
        println("---  " + "Star_A_table_to_List_of_Object_with_Blanks_in_Name")
        println(value)

    }

    fun Star_A_table_to_String( value : String) {
        println("---  " + "Star_A_table_to_String")
        println(value)

    }

}
