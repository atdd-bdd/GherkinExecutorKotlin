Feature: Simple Test

Scenario Simple
Given table is # ListOfObject ATest 
| anInt  | aString    | aDouble  |
| 1      | something  | 1.2      |

Data ATest 
| Name     | Default  | Datatype  | Note  |
| anInt    | 0        | Int       |       |
| aString  | ^        | String    |       |
| aDouble  | 1.2      | Double    |       |
