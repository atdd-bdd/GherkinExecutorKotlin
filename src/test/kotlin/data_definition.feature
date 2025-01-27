Feature: Data 

Data MyClass 
| Name  | Default  | Type    | Notes     |
| One   | aaa      | String  | a string  |
| Two   | 1        | int     | an int    |



Scenario: One with Data
Given a step  # ListOfObject MyClass
| One  | Two  |
| v1   | v2   |
| v1a  | v1b  |
