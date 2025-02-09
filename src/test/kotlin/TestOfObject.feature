Feature: Test of Object

Scenario Simple One using an external class
Given table is # ListOfObject ContainsLibraryClass
| aURL | aFile | aPattern |
| http://kenpugh.com       | myfile.txt  | ab*|

Data ContainsLibraryClass
| Name      | Default             | Datatype  | Note  |
| aURL      | http://ken-pugh.com  | URL       |       |
| aFile     | anyfile.txt | File      |       |
| aPattern  | a.*b                | Pattern   |       |
