Feature: Gherkin Test


Scenario:  Steps with all tables
Given a single step with a table
# comment begin
| One | Two |
| Three | Four |
When a step with a table # another comment
| A | B|
# comment intermixed
| C | D|
Then a step with a table
|1|2|
|3|4|
# comment end

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



