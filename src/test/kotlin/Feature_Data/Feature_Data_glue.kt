package gherkinexecutor.Feature_Data
import kotlin.test.assertEquals

class Feature_Data_glue {

    fun Given_table_is( value : List<ATest>) {
        println("*******")
        println(value)
         assertEquals(true, false)
    }

    fun Given_a_step( value : List<MyClass>) {
        println("*******")
        println(value)
         assertEquals(true, false)
    }

    }
