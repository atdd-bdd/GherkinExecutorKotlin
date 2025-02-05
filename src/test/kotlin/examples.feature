Feature: Examples

Scenario: Filter Data 
# shows data tables 
Given list of numbers # ListOfObject NameValue 
| ID    | Value  |
| a     | 1      |
| b     | 2      |
| a     | 3      |
When filtered by ID value
| a  |
Then sum is 
| 4 | 

Data NameValue 
| Name   | Default  | DataType  | Notes  |
| ID     |          | String    |        |
| Value  | 0        | Int       |        |


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


