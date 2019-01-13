/*
 * Cameron Husted
 * CSE 231 - Final Project
 * April 10, 2017
 * Professor Shital Joshi
 */

package FinalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LinkedList{
	PersonNode[] TBArray = new PersonNode[FinalMain.hashnum];
	
	public LinkedList(){
	}
	
	//-------------Handles Insertion for Hash Table-----------------
	public void add(PersonNode entry, int index){
		if (TBArray[index] == null){										//If linked list is empty....
			TBArray[index] = entry;											//Make PersonNode object the first entry
		} else {
			PersonNode firstNode = TBArray[index];							//Else, get the first entry
			entry.setNext(firstNode);										//Makes the first entry the second entry
			TBArray[index] = entry;											//Makes PersonNode object the first entry
		}
	}
	
	
	//-------------Handles Retrieval for Hash Table-----------------
	public PersonNode get(String name, int index){
		if (TBArray[index] == null){										//If linked list is empty, return null
			return null;
		} else {
			PersonNode entry = TBArray[index];								//Get first entry in list
			while (entry != null && !(entry.getName().equals(name))){
				entry = entry.getNext();									//Traverse down the list until "null" or matching name
			}
			if (entry == null){
				return null;												//If entry doesn't exist, return null
			} else {
				return entry;												//If entry does exist, return its PersonNode object
			}
		}
	}
	
	
	//-------------Handles Deletion for Hash Table-----------------
	public PersonNode remove(String name, int index){
		if (TBArray[index] != null){										//If linked list is not empty....
			PersonNode prev = null;											//Keeps track of previous entry
			PersonNode entry = TBArray[index];								//Gets first entry in list
			while (entry.getNext() != null && !(entry.getName().equals(name))){
				prev = entry;												//Traverses the list. Keeps track of previous entry
				entry = entry.getNext();
			}
			if (entry.getName().equals(name)){								//If found....
				if (prev == null){											//If it's the first entry....
					TBArray[index] = entry.getNext();						//Second entry becomes first entry (DELETE!)
				} else {													//If it's not the first entry....
					prev.setNext(entry.getNext());							//Connect previous entry to next entry (DELETE!)
				}
			} else {
				return null;
			}
			return entry;
		}
		return null;
	}
	
	
	//-------------Handles Displaying Hash Table contents-----------------
	public void display(int index){											//Displays contents of one entire linked list
		PersonNode npointer = TBArray[index];								//Gets first node in the list
		while(npointer != null){											//Traverses list until it hits null
			System.out.print(npointer.name + ", " + npointer.address + ", " + npointer.number + " => ");	//Displays current info
			npointer = npointer.getNext();									//Goes to next in list
			}
		System.out.print("null");
		}
	
	
	//-------------Handles printing Hash Table contents to output file-----------------
	public void print() throws IOException{
		FileWriter writer = new FileWriter("C:/eclipse/java-oxygen2/eclipse/workspace/FinalProject/src/output-file.csv");      
		BufferedWriter bwriter = new BufferedWriter(writer);
		
		for (int index = 0; index <= (FinalMain.hashnum - 1); ++index){		//Traverses all 7 linked lists in the table
			PersonNode npointer = TBArray[index];							//Gets first node in the list
			while(npointer != null){										//Traverses list until it hits null
				bwriter.write(npointer.name + "," + npointer.address + "," + npointer.number + "\n");	//Writes info to buffer
				npointer = npointer.getNext();								//Goes to next in list
			}
			bwriter.flush();												//Writes buffer contents to output file
		}
	}
}