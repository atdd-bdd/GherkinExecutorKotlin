package gherkinexecutor.Feature_Tables_and_Strings
import java.net.URL
import kotlin.test.fail

class Feature_Tables_and_Strings_glue {

    fun Star_A_multiline_string_to_a_string( value : String) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_multiline_string_to_a_List_of_String( value : List<String>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_List_of_String( value : List<List<String>>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object( value : List<ExampleClass>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object_with_Defaults( value : List<ExampleClass>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_List_of_Object_with_String_Constructor( value : List<List<URL>>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_List_of_Object_with_Blanks_in_Name( value : List<ExampleClassWithBlanks>) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    fun Star_A_table_to_String( value : String) {
        println("*******")
        println(value)
        fail("Must implement")
    }

    }
