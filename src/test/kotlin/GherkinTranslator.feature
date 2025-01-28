Feature: Gherkin Translator
# Tests the entire Test 

Scenario: Run the Test and check the output 
Given feature file is 
| testfeature.feature |
When translated
Then test file should match expected # ListOfObject FileNames
| Expected                        | Actual                         |
| Feature_Gherkin_Test.txt  | Feature_Gherkin_Test.kt  |
And data file should match expected # ListOfObject FileNames
| Expected                             | Actual                              |
| Feature_Gherkin_Test_data.txt  | Feature_Gherkin_Test_data.kt  |
And glue template file should match expected # ListOfObject FileNames
| Expected                             | Actual                              |
| Feature_Gherkin_Test_glue_template.txt  | Feature_Gherkin_Test_glue_template.tmpl  |

Data FileNames
| Name      | Default  |
| Expected  | String   |
| Actual    | String   |





