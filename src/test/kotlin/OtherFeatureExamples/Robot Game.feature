Feature: Robots 

Scenario: Movement of Two Bots Not Colliding 
Given map is: # ListOfList
| R, Bot2  |   |                 |                |   |
|          |   |                 |                |   |
|          |   | R, Bot1, South  | R, Bot3, West  |   |
|          |   |                 | B              |   |
|          |   | B               |                |   |
When actions are # ListOfObject IDAndAction
| ID      | Action|
| Bot1    | Move | 
| Bot3    | Move |   
Then map result is 
| R, Bot2  |   |                 |    |   |
|          |   |                 |    |   |
|          |   | R, Bot3, West   |    |   |
|          |   | R, Bot1, South  | B  |   |
|          |   | B               |    |   |

Scenario: Movement of Two Bots Colliding 
Given map is: 
| R, Bot2  |   |                 |                |   |
|          |   |                 |                |   |
|          |   | R, Bot1, South  | R, Bot3, West  |   |
|          |   |                 | B              |   |
|          |   | B               |                |   |
When actions are # ListOfObject IDAndAction
| ID      | Action|
| Bot3    | Move |   
| Bot1    | Move | 
Then map result is
# Bot3 was blocked  
| R, Bot2  |   |                 |                |   |
|          |   |                 |                |   |
|          |   |                 | R, Bot3, West  |   |
|          |   | R, Bot1, South  | B              |   |
|          |   | B               |                |   |


Scenario: Vision Reported 
Given map is: 
| R, Bot2  |   |          |          |   |
|          |   |          |          |   |
|          |   | R, Bot1  | R, Bot3  |   |
|          |   |          | B        |   |
|          |   | B        |          |   |
When vision is computed for
| Bot2| 
Then vision is:
# W is wall (boundary) 
|   |   |   |
|   |   |   |
|   |   |   |
When vision is computed for 
| Bot1| 
Then vision is:
|   |   |    |
|   |   | R  |
|   |   | B  |

Scenario:  Allowed Actions other than Turn 
Given vision for a bot is
| W  | W  | W  |
| B  | X  | R  |
|    |    | B  |
* Allowed actions are: # ListOfObject AllowableActions
# Any Action does not include Turn 
# Any Holding is Yes and No 
| Direction  | Holding  | Action  | Allowed  |
| North      | Any      | Any     | No       |
| West       | Any      | Step    | No       |
| West       | No       | Take    | Yes      |
| West       | Any      | Drop    | No       |
| South      | Any      | Step    | Yes      |
| South      | Yes      | Drop    | Yes      |
| South      | Any      | Take    | No       |
| East       | Any      | Any     | No       |
|            |          |         |          |

Data IDAndAction 
| Name    | Default  | DataType  | Notes  |
| ID      | 0        | Int       |        |
| Action  | ^        | String    |        |

Data AllowableActions
| Name       | Default  |
| Direction  | North    |
| Holding    | Any      |
| Action     | Any      |
| Allowed    | No       |


Scenario:  Aroma Domain Term
* Aromas are 
| 1 |
| 2 | 

Scenario:  Scent  
Given scent area is 
| 

When scent is computed 
Then scent has value 
| 40 | 

Scenario: Convert Vision to String
Given vision for a bot is
| W  | W  | W  |
| B  |    | R  |
|    |    | B  |
When converted to string 
Then result is: 
"""
W,W,W,B, ,R, , ,B,
"""


