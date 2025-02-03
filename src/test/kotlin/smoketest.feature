Feature: Smoke Test

Scenario:  Steps with all tables
Given a single step with a table
| One | ^^^ |
| Three | Four |

Scenario:  Steps with no tables
Given a single step with no table #a comment
When a step with no table # another comment
Then a step with no table

Scenario: One step with star
* a single step with star

Scenario: Step with a string
Then a step with a string
"""
One line
Two line
"""

Scenario: Step with a string to list
Then a step with a string list # ListOfString
"""
One line separate
Two line separate
"""

Scenario:  Steps with various table forms
Given table to be list of list # ListOfList
| One    | Two   |
| Three  | Four  |


