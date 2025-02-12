Feature: Tic Tac Toe Game

# This passes the table in the Then as a string, rather than a List<list<String>>
# If the current status of the game is converted to a string, it can be matched to this
# The differences, if any, will be shown as a diff.   
# You could pass the table as a List<List<String>>.  Then assertEquals would print out
# the values as [[,X,,], [,,,], [,,,] ]


Scenario:  Make a move
Given board is
|   |   |   |
|   |   |   |
|   |   |   |
When move is # ListOfObject Move transpose
| Row     | 1  |
| Column  | 2  |
| Mark    | X  |
Then board is now # String
|   | X  |   |
|   |    |   |
|   |    |   |

Scenario:  Make a move using single element
Given board is
|   |   |   |
|   |   |   |
|   |   |   |
When one move is # ListOfList Move
| 1,2,X| 
Then board is now # String
|   | X  |   |
|   |    |   |
|   |    |   |

Scenario:  Make multiple moves
Given board is
|   |   |   |
|   |   |   |
|   |   |   |
When moves are # ListOfObject Move transpose
| Row     | 1  | 2  |
| Column  | 2  | 3  |
| Mark    | X  | O  |
Then board is now # String
|   | X  |    |
|   |    | O  |
|   |    |    |

Data Move
| Name    | Default  | DataType  | Notes    |
| Row     | 0        | Int       |          |
| Column  | 0        | Int       |          |
| Mark    | ^        | Char      | A space  |


Scenario:  check the prints to see how it works
Given board is
| 0  | x  | 0  |
| x  | 0  | x  |
| 0  | x  | 0  |
When move is # ListOfObject Move transpose
| Row     | 1  |
| Column  | 2  |
| Mark    | X  |
Then board is now # String
| 0  | X  | 0  |
| x  | 0  | x  |
| 0  | x  | 0  |


