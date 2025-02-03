Feature: Gherkin Translator Smoke Test
# Uses the testfeature file to see if the created code continues to match expectations

Scenario: Run the Test and check the output 
Given feature file is 
| smoketest.feature |
When translated
Then test file should match expected # ListOfObject FileNames
| Expected                | Actual                 |
| Feature_Smoke_Test\\Feature_Smoke_Test.exp| Feature_Smoke_Test\\Feature_Smoke_Test.kt  |
And data file should match expected # ListOfObject FileNames
| Expected                     | Actual                      |
| Feature_Smoke_Test\\Feature_Smoke_Test_data.kt | Feature_Smoke_Test\\Feature_Smoke_Test_data.exp    |
And glue template file should match expected # ListOfObject FileNames
| Expected                     | Actual                        |
| Feature_Smoke_Test\\Feature_Smoke_Test_glue.tmpl | Feature_Smoke_Test\\Feature_Smoke_Test_glue.exp  |

Data FileNames
| Name      | Default     |
| Expected  | NoFileName  |
| Actual    | NoFileName  |





