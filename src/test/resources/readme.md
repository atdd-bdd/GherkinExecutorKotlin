# Gherkin Translator

The translator converts a Gherkin 
feature file into a unit test file.
The unit test file calls a glue file which
the developer edits to call the code under 
test. 
The translator also creates a template for the 
glue file.  

The translator is a single file containing all
the necessary components.  To translate a
feature file, run it from the IDE.

Unlike other implementations of Gherkin,
each feature file is associated with one unit
test file and its glue file.

## Why Use Gherkin?

Gherkin feature files are readable executable documentation.  If a requirement / story includes buiness rules, 
they provide a convenient form to collaborate with non-programmers.  For a developer they provide an alternative way to specify the values used to call a parametrized test method.  


## Why Not Use Existing Frameworks? 

I've been using Cucumber, one of the most common applications that uses 
Gherkin for a number of years.  You can have a table after each step.   However you
need to add additional code to use that table as a list of objects.  The means for doing 
so has changed from version to version.  The code for doing so has gotten more complex.     

## Examples 
Let's give a couple of simple examples of calculations/business rules and domain terms 
that can be expressed with tables.  

The first scenario is conversion from Fahrenheit to Celsius. 
You can add as many variations in the calculation without having to write additional code.
This scenario represents the data to the method which does the calculation and returns the result. 
It does not change, regardless of the underlying implementation or language.
```
Scenario: Temperature Conversion 
# Business rule , Calculation 
Calculation Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |
```

The second scenario is validation of a domain term. This validation could be performed 
by a constructor, a parse method, or some other means.  
```
Scenario: Domain Term ID 
Rule ID must have exactly 5 letters and begin with Q # ListOfObject DomainTermID
| Value   | Valid  | Notes              |
| Q1234   | true   |                    |
| Q123    | false  | Too short          |
| Q12345  | false  | Too long           |
| A1234   | false  | Must begin with Q  |

```

## How Does It work 
Here is a feature file.  In the test directory, it is named "examples.feature".  The words after the keyword 
`Feature` are combined into the name of the feature.  Let's assume that you are using the translator with Kotlin (language suffix .kt) 
The operation is the same, the output code depends on the language. 

The single step in the `Scenario` ("Convert F to C ") is passed a list of objects of 
`TemperatureComparison`.
A unit test file with the name `Feature_Examples.kt` (with language appropriate suffix) is created in a directory with the same name. 
A another file is created called `Feature_Examples_glue.impl` is also created.  This contains code that is called 
from `Feature_Examples.kt`. 

The first time you run the Translator, you should rename that file to the language appropriate suffix
(e.g. rename it from .tmpl to .kt).  You will be making changes in this file to 
call your production code.  If you add new steps to the feature, you can copy a template for the new steps from 
the template file (.impl) to the glue source file (.kt).  Alternatively, you can just let the IDE suggest that you need 
a new method in  Feature_Examples_glue.

The two words after the comment sign # denote that data format that is passed to the glue code and the class name. 
For `Feature_Examples`, the table values will be converted to a List of objects of type `TemperatureComparison`. 

```
Feature: Examples

Scenario: Temperature 
# Business rule , Calculation 
* Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |
```
The `*` is a keyword in Gherkin.  In the translator, you could also use `Calculation` or `Rule` instead.
This turns into code in  `Feature_Examples.kt`:
```
class Feature_Examples{
    @Test
    fun test_Scenario_Temperature(){
        val feature_Examples_glue_object = Feature_Examples_glue()

        val objectList1 = listOf<TemperatureComparison>(
            TemperatureComparison(
                f = "32",
                c = "0",
                notes = "Freezing",
                ),
            TemperatureComparison(
                f = "212",
                c = "100",
                notes = "Boiling",
                ),
            TemperatureComparison(
                f = "-40",
                c = "-40",
                notes = "Below zero",
                ),
            )
        feature_Examples_glue_object.Star_Convert_F_to_C(objectList1)
        }

```
Now to simplify creation of the objects in a table, you create a data description.   
The `Data TemperatureComparison` portion produces code in the test file that
declares a `TemperatureComparison` class.  Every attribute in this class is `String` type. Since the table also contains the data types for each element
in this class, a second class with the default name `TemperatureConversionInternal` is also created.  You can attempt to
create an instance of this class in the glue code to check that the format of each element in the table is acceptable.b 

```
Data TemperatureComparison
| Name   | Default  | DataType  | Notes  |
| F      | 0        | Int       |        |
| C      | 0        | Int       |        |
| Notes  |          | String    |        |
```
turns into code in `Feature_Examples_data.tmpl` that looks like 

```
data class TemperatureComparison(
    val f: String = "0",
    val c: String = "0",
    val notes: String = "", ) {
    fun toTemperatureComparisonInternal() : TemperatureComparisonInternal{
        return TemperatureComparisonInternal(
            f.toInt(),
            c.toInt(),
            notes,) 
    }
        
data class TemperatureComparisonInternal(
    val f: Int= "0".toInt(),
    val c: Int= "0".toInt(),
    val notes: String= "",)
```
The first time you run the Translator, rename this file 
The other file that is created is `Feature_Exmaples_glue.tmpl"
Again, just rename this file to language suffix.  
```
class Feature_Examples_glue {

    fun Star_Convert_F_to_C( value : List<TemperatureComparison>) {
        println("---  " + "Star_Convert_F_to_C")
        println(value)
        fail("Must implement")
    }

```
Now comes your part.  Add the appropriate code to this glue function
to call the code you create.  If you only have one row,
then you might just code that one. The string values are converted into the
internal values.  The `F` value is passed to the `TemperatureCalculations.convertFarenheitToCelsius()`
method and the return value is compared to the `C` value.

```
    fun Star_Convert_F_to_C(value: List<TemperatureComparison>) {
        element = value[0]
        val temp = element.toTemperatureComparisonInternal()
        assertEquals(
                temp.c,
                TemperatureCalculations.convertFarenheitToCelsius(temp.f),
                temp.notes
            )
        }
    }

```
The compiler would suggest you create a method such as:
```
class TemperatureCalculations {
    companion object {
        fun convertFarenheitToCelsius(input: Int): Int {
            return ((input - 32) * 5) / 9
        }
    }
}
```
Now you could change it to use every row in the table:
```
    fun Star_Convert_F_to_C(value: List<TemperatureComparison>) {
        for (element in value) {
            val temp = element.toTemperatureComparisonInternal()
            assertEquals(
                temp.c,
                TemperatureCalculations.convertFarenheitToCelsius(temp.f),
                temp.notes
            )
        }
    }
```
Note you can have as many columns and rows in the table as you need. 
The form in the glue code looks the same - iterate around each row.  

Domain Term 

```
   fun Star_ID_must_have_exactly_5_letters_and_begin_with_Q( value : List<DomainTermID>) {
        println("---  " + "Star_ID_must_have_exactly_5_letters_and_begin_with_Q")

        for (element in value) {
            val temp = element.toDomainTermIDInternal()
            try {
                ID(temp.value)
                if (!temp.valid)
                    fail("Value of " + temp.value + "accepted but should fail")
            }
            catch(e: Exception){
                if (temp.valid)
                    fail("Value of " + temp.value + "failed but should be accepted")
                assertEquals(temp.notes, e.message, "Message does not mathc")
            }

       }

    }
}
```

This is what the constructor might look like

```
data class ID(val value: String) {

    init {
        if (this.value.length < 5 )
            throw Exception("Too short")
        if (this.value.length > 5)
            throw Exception("Too long")
        if (this.value.get(0) != 'Q')
            throw Exception("Must begin with Q")
    }

```

If you are using a method to perform the validation, the glue code might look like this

```
            assertEquals(
                temp.valid,
                ID(temp.value).isValid(),
                temp.notes
            )
```

and the method might look like this

```
    fun isValid(): Boolean {
        if (this.value.length < 5 )
            return false
        if (this.value.length > 5)
            return false
        if (this.value.get(0) != 'Q')
            return false
        return true
    }
```

'''
'
## Inspiration 

