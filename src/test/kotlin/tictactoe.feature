Feature: Tic Tac Toe Game

Scenario:  check the prints
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
When one move is 
| 1,2,x| 
Then board is now # String
|   | X  |   |
|   |    |   |
|   |    |   |

Scenario:  Make a move
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
| Name    | Default  | Notes    |
| Row     | 0        |          |
| Column  | 0        |          |
| Mark    | ^        | A space  |
