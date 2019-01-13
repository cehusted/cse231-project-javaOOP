# cse231-project-javaOOP

*******************Informational & Notes*******************

During development of this program, I was using Java 8 Update 111 on my computer.

Developed via Eclipse, "Indigo Service Release 2"


The input file to populate the data is "input-data.csv"
	OR "input-data10.csv" for 10 sample records.
	
When exiting program, records are written to "output-file.csv"
	
*NOTE: In class "FinalMain", lines 30 and 110 may need to change relative to where the source data lies.
	Additionally, line 180 in class "BinarySearchTree" and line 87 in class "LinkedList" are the directories where the output file 		will be created.



*************************Description*************************

This program was developed by me during the Winter 2017 term as a final project in CSE
231 at Oakland University.

It is intended to function as a telephone book, which loads 10 (or many, depending on the
input file it's pointed to) records consisting of a name, an address, and a phone number.
Operations that can be performed include:
	* Inserting a new record
	* Retrieving a record given a name
	* Deleting a record
	* Displaying all current records
	
The user has the option of choosing which data structure is used to store the data:
	* Hash Table
	* Binary Search Tree
