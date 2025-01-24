package gherkinexecutor
import kotlin.test.assertEquals

class Feature_Gherkin_Test_glue {



    fun Given_a_single_step_with_a_table(){
        assertEquals(true, false)  
    }

    fun When_a_step_with_a_table( table2: List<List<String>>){
        assertEquals(true, false)  
    }

    fun Then_a_step_with_a_string( string3:String ){
        assertEquals(true, false)  
    }
    }
