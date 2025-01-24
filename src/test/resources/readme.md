Summary for Gherkin Translator

The translator converts a Gherkin 
feature file into a unit test file.
The unit test file calls a glue file which
the developer edits to call the code under 
test.   
The translator also creates a template for the 
glue file.   

The translator is a single file containing all 
the necessary components.  To translate a 
feature file, run it from the IDE.   

Unlike other implementations of Gherkin, 
each feature file is associated with one unit
test file and its glue file.  


