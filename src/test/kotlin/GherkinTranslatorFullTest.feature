Feature: Gherkin Translator Full Test
# Uses the testfeature file to see if the created code continues to match expectations

Scenario: Run the Test and check the output 
Given feature file is 
| fulltest.feature |
When translated
Then test file should match expected # ListOfObject FileNames
| Expected                        | Actual                         |
| Feature_Full_Test\\Feature_Full_Test.exp  | Feature_Full_Test\\Feature_Full_Test.kt  |
And data file should match expected # ListOfObject FileNames
| Expected                             | Actual                              |
| Feature_Full_Test\\Feature_Full_Test_data.exp  | Feature_Full_Test\\Feature_Full_Test_data.kt  |
And glue template file should match expected # ListOfObject FileNames
| Expected                             | Actual                              |
| Feature_Full_Test\\Feature_Full_Test_glue.exp  | Feature_Full_Test\\Feature_Full_Test_glue.tmpl  |

Data FileNames
| Name      | Default     |
| Expected  | NoFileName  |
| Actual    | NoFileName  |





