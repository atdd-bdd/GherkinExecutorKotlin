Feature: Test of Object

# Shows how a data object can use standard data types 
# the data file needs to import those data types 

Scenario Simple One using an external class
Given table is # ListOfObject ContainsLibraryClass
| aURL                | aFile       | aPattern  |
| http://kenpugh.com  | myfile.txt  | ab*       |

Data ContainsLibraryClass
| Name      | Default              | Datatype  | Note  |
| aURL      | http://ken-pugh.com  | URL       |       |
| aFile     | anyfile.txt          | File      |       |
| aPattern  | a.*b                 | Pattern   |       |
