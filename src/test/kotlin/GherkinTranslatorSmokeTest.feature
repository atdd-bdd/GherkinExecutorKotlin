Feature: Gherkin Translator Smoke Test
# Uses the testfeature file to see if the created code continues to match expectations

Scenario: Run the Test and check the output 
Given feature file is 
| smoketest.feature |
When translated
Then test file should match expected # ListOfObject FileNames
| Expected                | Actual                 |
| Feature_Smoke_Test\\Feature_Smoke_Test.kt | Feature_Smoke_Test\\Feature_Smoke_Test.exp  |
And data file should match expected # ListOfObject FileNames
| Expected                     | Actual                      |
| Feature_Smoke_Test\\Feature_Smoke_Test_data.exp | Feature_Smoke_Test\\Feature_Smoke_Test_data.tmpl    |
And glue template file should match expected # ListOfObject FileNames
| Expected                     | Actual                        |
| Feature_Smoke_Test\\Feature_Smoke_Test_glue.exp | Feature_Smoke_Test\\Feature_Smoke_Test_glue.tmpl  |

Data FileNames
| Name      | Default     |
| Expected  | NoFileName  |
| Actual    | NoFileName  |





