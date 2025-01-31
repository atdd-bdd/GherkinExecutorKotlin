Feature: Parse CSV 


Scenario:  Convert a CSV file to Gherkin Table
Given 
"""
a,"b,c","c"","
1,2,"3,""b,"",,"
"""
When converted result is 
"""
|a|b,c|c",| 
|1|2|3,"b,",,|
"""

Scenario:  Transpose a table 
Given input table
| a  | b  | c  |
| d  | e  | f  |
When transposed result is 
| a  | d  |
| b  | e  |
| c  | f  |
