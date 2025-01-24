Feature FlowGrid 

Scenario: One Run
Given log contains
"""         
Run   
"""
When displayed 
Then grid is               # String
| R   | UC  |
| UC  | UC  |

Scenario: Another Run
Given log contains 
"""
Run    
Commit
"""
When displayed 
Then grid is               # String
| R  | C  |
| C  | C  |

Scenario: One Test that fails 
Given log contains       # String
"""
Test Fail 
Run    
"""
When displayed 
Then grid is             # String
| F  | R  | C  |
| C  | C  | C  |

Scenario: One Test that passes
Given log contains
"""       
Test Pass 
Run    
"""
When displayed 
Then grid is             # String
| P  | R  | C  |
| C  | C  | C  |


Scenario: One Test that passes and one fails 
Given log contains 
"""
Test Pass Fail
Run  
"""
When displayed 
Then grid is              # String 
| F  | R  | C  | 
| P  | R  | C  |
| C  | C  | C  |



