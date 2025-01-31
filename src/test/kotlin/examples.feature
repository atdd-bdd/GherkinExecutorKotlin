Feature: Examples

Scenario: Filter Data 
# shows data tables 
Given list of numbers # ListOfObject NameValue 
| ID    | Value  |
| a     | 1      |
| b     | 2      |
| a     | 3      |
When filtered by 
| ID | a  |
Then sum is 
| 4 | 

Data NameValue 
| Name   | Default  |
| ID     |          |
| Value  | 0        |


Scenario: Temperature 
# Business rule , Calculation 
* Convert F to C # ListOfObject Temperature 
| F    | C    | Notes       |
| 32   | 0    | Freezing    |
| 212  | 100  | Boiling     |
| -40  | -40  | Below zero  |

Data Temperature
| Name   | Default  |
| F      | 0        |
| C      | 0        |
| Notes  |          |


