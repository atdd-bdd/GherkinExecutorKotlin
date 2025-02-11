# Gherkin Translator 

## Summary 
The Gherkin Translator simplifies the creation of test code from a Gherkin feature file. 
It creates collections of objects and values that can be used to test the production code. 
It is designed to work the same way with any implementation language.

## Why Use Gherkin?
Gherkin feature files are readable executable documentation.  If a requirement / story includes buiness rules,
they provide a convenient form to collaborate with non-programmers.   They 
also provide a way to document parts of a domain driven design.
For a developer they provide an alternative way to specify the values used 
to call a parametrized test method.  

The scenarios can be used in user documentation.
For domains which have two-dimensional objects, the tabluar format can be much easier
to comprehend. (See the tic-tac-toe example.)

Here are a few introductory examples. The first shows a calculation from Fahrenheit to
Celsius.  When it is translated, three files are created - one is a unit test file, one 
is glue code which is revised to connect to the production code, and the third is 
a data file which declares the classes used to connect the unit test code to the glue code.
The full code is shown later.
```
Scenario: Temperature Conversion 
Calculation Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |
```
An Excel-style table is a familiar construct to many non-programmers.
(Ward Cunningham introduced this style with FIT).  So this form is easily understandable.
To add another variation, you simply add another row to the table.  The comments
`# ListOfObject TemperatureComparison` are used to create the unit tests.

The Triad (Customer, Developer, Tester perspectives) can collaborate on the detailed behavior.  This
separates the production implementation from the representation of the logic and calculations.  For example,
one of the Triad might ask about the result for this calculation:  
```
| F    | C       | Notes                     |
| 33   | .555    | How many decimal digits?  |
```
The Triad would then discuss how it should appear on an output (e.g. the screen). Note that
the internal representation of F or C is irrelevant - it could be double, Double, BigInteger, or
something else.   

If your code style is to use Abstract Data Types, you can use a table to show allowable values.
```
Scenario: Domain Term ID 
Rule ID must have exactly 5 letters and begin with Q # ListOfObject DomainTermID
| Value   | Valid  | Notes              |
| Q1234   | true   |                    |
| Q123    | false  | Too short          |
| Q12345  | false  | Too long           |
| A1234   | false  | Must begin with Q  |
```
Now this could be used in a help file to show examples.  

Here is domain related example.   A Forecast is a Domain Term that has multiple attributes.
The data type for each attribute is shown, along with a default value.  
```
Data Forecast
| Name        | Default   | Datatype           | Notes               |
| Day         | 1/1/2025  | Date               |                     |
| Time        | 12:00 am  | Time               |                     |
| High        | 100       | Temperature        | High Temperature    |
| Low         | 0         | Temperature        | Low Temperature     |
| Rain        | 0         | Percentage         | Chance of Rain      |
| Wind Speed  | 0         | Speed              |                     |
| Direction   | N         | CompassDirection   |                     |
| Condition   | Clear     | WeatherCondition   | Cloudy, Rain, etc.  |
```
Here's a possible story to search forecasts for those that meet certain conditions:
```
Scenario: Search Forecast
Given forecast is           # ListOfObject Forecast
| Day       | Time      | High  | Low  | Rain  | Wind Speed  | Direction  | Condition  |
| 1/1/2025  | 12:00 am  | 70    | 60   | 0     | 1           | N          | Clear      |
| 1/3/2025  | 12:00 am  | 60    | 40   | 10    | 5           | S          | Cloudy     |
# And many more  (or read from CSV file)
When searching for          # ListOfObject # ForecastSearchCriteria 
| Field        | Relationship  | Value  |
| High         | >             | 65     |
| Wind Speed   | <             | 5      |
Then results are # ListOfObject Forecast 
| Day       | Condition  |
| 1/1/2025  | Clear      |
```
The Given step uses the domain term defined in the previous block. The data could be
listed here, read from a CSV file, or put onto a database. The When step gives the search
criteria.  The Triad would define how these criteria should work.
Finally the Then step shows the expected results.   


## How It Works in Brief

The translator converts a Gherkin feature file into a unit test file.
The unit test file calls a glue file which the developer edits to call the code under 
test.
The translator also creates a template for the glue file.  

The Translator is a single file containing all the necessary components. 
To translate a feature file, you supply the feature files you want converted by either adding them 
to a list in the Translator or adding them as program arguments.  

Unlike other implementations of Gherkin,
each feature file is associated with one unit
test file, its glue file, and its class file. 

## Why Not Use Existing Frameworks? 

I've been using Cucumber, one of the most common applications that uses 
Gherkin for a number of years.  You can have a table after each step.   However you
need to add additional code to use that table as a list of objects.  The means for doing 
so has changed from version to version.  The code for doing so has gotten more complex.     

The Translator converts the Gherkin tables into initialized lists. The developer just needs to 
specify the class of the objects that will be in those lists. It does not depend on 
features of a specific languages, such as introspection. 

The initial version of Translator works for Kotlin.   It is currently being converted
to Java, Python, and C++.    A feature file written for one language should work
in the other languages.  

Since the entire source code is supplied, a developer can alter the translation to
their preferred style.  If methods should not have underscores, that can be changed
in a single line.  

## How It Differs From Other Frameworks 
The Translator passes data to steps only through tables.  There are no values embeeded the
the steps.  The domain term which a value represents can be expressed as the header
of a column contain the values.  

The tables can be passed as a list of objects or a list of lists of strings.  

## How It Works in Detail
Here is a feature file.  
```
Feature: Examples



Scenario: Temperature 
# Business rule , Calculation 
Calculation Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |

Data TemperatureComparison
| Name   | Default  | DataType  | Notes  |
| F      | 0        | Int       |        |
| C      | 0        | Int       |        |
| Notes  |          | String    |        |

Scenario: Domain Term ID 
Rule ID must have exactly 5 letters and begin with Q # ListOfObject DomainTermID
| Value   | Valid  | Notes              |
| Q1234   | true   |                    |
| Q123    | false  | Too short          |
| Q12345  | false  | Too long           |
| A1234   | false  | Must begin with Q  |

Data DomainTermID
| Name   | Default  | DataType  |
| Value  | 0        | String    |
| Valid  | no       | Boolean   |
| Notes  |          | String    |

Scenario: Filter Data 
# filters data 
Given list of numbers # ListOfObject LabelValue 
| Label | Value  |
| a     | 1      |
| b     | 2      |
| a     | 3      |
When filtered by Label with value
| a  |
Then sum is 
| 4 | 

Data LabelValue 
| Name   | Default  | DataType  | Notes  |
| Label  |          | String    |        |
| Value  | 0        | Int       |        |
```
In the test directory, it is named "examples.feature".  The words after the keyword 
`Feature` are combined into the name of the feature.  Let's assume that you are using the translator with Kotlin (language suffix .kt) 
The operation is the same, the output code depends on the language. 

The single step in the `Scenario` ("Convert F to C ") is passed a list of objects of 
`TemperatureComparison`.
A unit test file with the name `Feature_Examples.kt` (with language appropriate suffix) is created in a directory with the same name. 
A another file is created called `Feature_Examples_glue.tmpl` is also created.  This contains code that is called 
from `Feature_Examples.kt`. 

The first time you run the Translator, you should rename that file to the language appropriate suffix
(e.g. rename it from .tmpl to .kt).  You will be making changes in this file to 
call your production code.  If you add new steps to the feature, you can copy a template for the new steps from 
the template file (.tmpl) to the glue source file (.kt).  Alternatively, you can just let the IDE suggest that you need 
a new method in  Feature_Examples_glue.

The two words after the comment sign # denote that data format that is passed to the glue code and the class name. 
For `Feature_Examples`, the table values will be converted to a List of objects of type `TemperatureComparison`. 

```
Feature: Examples

Scenario: Temperature 
# Business rule or Calculation 
Calculation Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |
```
The `Calculation` is a new keyword that is a synonym for `*` in Gherkin.  In the translator, you could also use `Calculation` or `Rule` instead.
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
        feature_Examples_glue_object.Calculation_Convert_F_to_C(objectList1)
        }

```
Now to simplify creation of the objects in a table, you create a data description.   
The `Data TemperatureComparison` portion produces code in the test file that
declares a `TemperatureComparison` class.  Every attribute in this class is `String` type. Since the table also contains the data types for each element
in this class, a second class with the default name `TemperatureConversionInternal` is also created.  You can attempt to
create an instance of this class in the glue code to check that the format of each element in the table is acceptable.

In this file, you can add `import` for any other classes your production code might need. 
Note that the test file only references the class with all string attributes.  The glue code is the place to
do all the conversions.
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

_There is an change that will be implemented in the next version.  There will be a method
that translates a Datatype and a string to the appropriate method to construct an object.
You will be able to add your data types to that method, so it will use the appropriate conversion method
(e.g. `constructor, .parseValue()`, etc. )

Here is what the glue `.tmpl` file looks like: 

```
class Feature_Examples_glue {

    fun Calculation_Convert_F_to_C( value : List<TemperatureComparison>) {
        println("---  " + "Calculation_Convert_F_to_C")
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
    fun Calculation_Convert_F_to_C(value: List<TemperatureComparison>) {
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
The compiler would suggest you create a method such as follows.  The companion object is
equivalent to a class method (e.g. `static`) in other languages.
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
    fun Calculation_Convert_F_to_C(value: List<TemperatureComparison>) {
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

## Domain Term 

In the step method, you implement a call to the method that turns a string into the object.

If you are using a method to perform the validation, the glue code might look like this.   Note
this is just about what a unit test would look like, except for the loop around the list.

```
   fun Rule_ID_must_have_exactly_5_letters_and_begin_with_Q( value : List<DomainTermID>) {
        println("---  " + "Star_ID_must_have_exactly_5_letters_and_begin_with_Q")

        for (element in value) {
            val temp = element.toDomainTermIDInternal()
            assertEquals(
                temp.valid,
                ID(temp.value).isValid(),
                temp.notes
       }
    }

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
If the method throws an exception on errors, you catch and check that in here.
```
   fun Rule_ID_must_have_exactly_5_letters_and_begin_with_Q( value : List<DomainTermID>) {
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
## Inspiration 
This form for expressing shared understanding came from Ward Cunningham's FIT
(Framework for Integrated Testing).  Gherkin came from ???.  Running a program
to create the interface came from 
