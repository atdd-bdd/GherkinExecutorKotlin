Feature: Tables and Strings 

Scenario: Here are string options 

* A multiline string to a string 
"""
One line 
Two line 
"""

* A multiline string to a List of String # ListOfString
"""
Three line
Four line 
"""

Scenario: Here are table options 

* A table to List of List of String   
| aa  | bb  |
| cc  | dd  |

* A table to List of Object # ListOfObject ExampleClass 
| FieldA  | FieldB  |
| a       | b       |
| c       | d       |

* A table to List of Object # ListOfObject ExampleClass transpose 
| FieldA  | a  | c  |
| FieldB  | b  | d  |

* A table to List of Object with Defaults # ListOfObject ExampleClass 
| FieldA  |
| a       |
| c       |


* A table to List of List of Object with String Constructor # ListOfList URL
| https://kenpugh.com   |
| https://atdd-bdd.com  |
# Note - the class must be imported into the test and the glue code 

* A table to List of Object with Blanks in Name # ListOfObject ExampleClassWithBlanks
| Field 1  | Field 2  |
| a        | b        |
| c        | d        |

* A table to String # String
| aa  | bb  |
| cc  | dd  |


Data ExampleClass 
| Name    | Default  |
| FieldA  | y        |
| FieldB  | x        | 

Data ExampleClassWithBlanks
| Name     | Default  |
| Field 1  | y        |
| Field 2  | x        |



