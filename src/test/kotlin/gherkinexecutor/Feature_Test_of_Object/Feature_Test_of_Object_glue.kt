package gherkinexecutor.Feature_Test_of_Object
import kotlin.test.fail

class Feature_Test_of_Object_glue {

    fun Given_table_is( value : List<ContainsLibraryClass>) {
        println("---  " + "Given_table_is")
        println(value)
        println(value[0].toContainsLibraryClassInternal())

    }

    }
