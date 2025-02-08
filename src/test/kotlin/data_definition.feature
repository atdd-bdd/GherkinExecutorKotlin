Feature: Data Definition

# Should used InternalClass as the name 
Data MyClass InternalClass
| Name  | Default  | Type    | Notes     |
| One   | aaa      | String  | a string  |
| Two   | 1        | Int     | an Int    |

Scenario Simple
Given table is # ListOfObject ATest 
| anInt  | aString    | aDouble  |
| 1      | something  | 1.2      |

# will create an internal class name 
Data ATest 
| Name     | Default  | Datatype  | Note  |
| anInt    | 0        | Int       |       |
| aString  | ^        | String    |       |
| aDouble  | 1.2      | Double    |       |

# Should not create an internal class
Data NameValue 
| Name   | Default  |
| ID     |          |
| Value  | 0        |


Scenario: One with Data
Given a step  # ListOfObject MyClass
| One  | Two  |
| v1   | v2   |
| v1a  | v1b  |

Scenario: One with Data Transpose
Given a step  # ListOfObject MyClass transpose
| One  | v1  | v1a  |
| Two  | v2  | v1b  |


