package gherkinexecutor.Feature_Full_Test
import kotlin.test.fail

class Feature_Full_Test_glue {

    fun Given_a_string( value : String) {
        println("---  " + "Given_a_string")
        println(value)
        fail("Must implement")
    }

    fun Then_a_table( value : List<List<String>>) {
        println("---  " + "Then_a_table")
        println(value)
        fail("Must implement")
    }

    fun Given( value : String) {
        println("---  " + "Given")
        println(value)
        fail("Must implement")
    }

    fun When_converted_result_is( value : String) {
        println("---  " + "When_converted_result_is")
        println(value)
        fail("Must implement")
    }

    fun Given_input_table( value : List<List<String>>) {
        println("---  " + "Given_input_table")
        println(value)
        fail("Must implement")
    }

    fun When_transposed_result_is( value : List<List<String>>) {
        println("---  " + "When_transposed_result_is")
        println(value)
        fail("Must implement")
    }

    fun Given_Background_Function(){
        println("---  " + "Given_Background_Function")
        fail("Must implement")
    }

    fun Given_Cleanup_Function(){
        println("---  " + "Given_Cleanup_Function")
        fail("Must implement")
    }

    fun Given_a_regular_function(){
        println("---  " + "Given_a_regular_function")
        fail("Must implement")
    }

    fun Given_table_is( value : List<ATest>) {
        println("---  " + "Given_table_is")
        println(value)
        fail("Must implement")
    }

    fun Given_a_step( value : List<MyClass>) {
        println("---  " + "Given_a_step")
        println(value)
        fail("Must implement")
    }

    fun Calculation_Convert_F_to_C( value : List<TemperatureCalculation>) {
        println("---  " + "Calculation_Convert_F_to_C")
        println(value)
        fail("Must implement")
    }

    fun Rule_ID_must_have_exactly_5_letters_and_begin_with_Q( value : List<DomainTermID>) {
        println("---  " + "Rule_ID_must_have_exactly_5_letters_and_begin_with_Q")
        println(value)
        fail("Must implement")
    }

    fun Given_list_of_numbers( value : List<LabelValue>) {
        println("---  " + "Given_list_of_numbers")
        println(value)
        fail("Must implement")
    }

    fun When_filtered_by_Label_with_value( value : List<List<String>>) {
        println("---  " + "When_filtered_by_Label_with_value")
        println(value)
        fail("Must implement")
    }

    fun Then_sum_is( value : List<List<String>>) {
        println("---  " + "Then_sum_is")
        println(value)
        fail("Must implement")
    }

    fun Star_A_multiline_string_to_a_string( value : String) {
        println("---  " + "Star_A_multiline_string_to_a_string")
        println(value)
        fail("Must implement")
    }

    fun Star_A_multiline_string_to_a_List_of_String( value : List<String>) {
        println("---  " + "Star_A_multiline_string_to_a_List_of_String")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_List_of_String( value : List<List<String>>) {
        println("---  " + "Star_A_table_to_List_of_List_of_String")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object( value : List<ExampleClass>) {
        println("---  " + "Star_A_table_to_List_of_Object")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object_with_Defaults( value : List<ExampleClass>) {
        println("---  " + "Star_A_table_to_List_of_Object_with_Defaults")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object_with_Blanks_in_Name( value : List<ExampleClassWithBlanks>) {
        println("---  " + "Star_A_table_to_List_of_Object_with_Blanks_in_Name")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_String( value : String) {
        println("---  " + "Star_A_table_to_String")
        println(value)
        fail("Must implement")
    }

}
