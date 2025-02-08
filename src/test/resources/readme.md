Summary for Gherkin Translator

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

Let's give an example of how this works.   
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

The two words after the comment sign # denote that data format that is passed to the glue code and the class name
For `Feature_Examples`, the table values will be converted to a List of objects of type `TemperatureComparison`. 

Now to simplify creation of the objects in a table, you create a data description.   
The `Data TemperatureComparison` portion produces code in the test file that 

```
Feature: Examples

Scenario: Temperature 
# Business rule , Calculation 
* Convert F to C # ListOfObject TemperatureComparison 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |

Data TemperatureComparison
| Name   | Default  | DataType  | Notes  |
| F      | 0        | Int       |        |
| C      | 0        | Int       |        |
| Notes  |          | String    |        |
```





