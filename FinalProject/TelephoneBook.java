/*
 * Cameron Husted
 * CSE 231 - Final Project
 * April 10, 2017
 * Professor Shital Joshi
 */

package FinalProject;

public class TelephoneBook {
	LinkedList object = new LinkedList();					//Creates a linked list for the PersonNodes. Only utilized in hash table
	BinarySearchTree tree = new BinarySearchTree();			//Creates a BST for the PersonNodes. Only utilized in BST
	
	public TelephoneBook(){
	}
	
	//----------Handles insertion of a record into the hash table----------
	public void insert(String name, String address, String number){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		
		if (object.get(name, index) == null){								//If new entry....
			PersonNode entry = new PersonNode(name, address, number);		//Creates PersonNode object for this person's info
			object.add(entry, index);										//Add the PersonNode object to the hash table in appropriate linked list
			
		} else {															//If entry exists....
			PersonNode updatePerson = object.get(name, index);				//Retrieve existing PersonNode object
			updatePerson.setAddress(address);								//Update the address
			updatePerson.setNumber(number);									//Update the phone number
		}
	}
	
	
	//----------Handles retrieval of a record from the hash table----------
	public PersonNode retrieve(String name){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		return object.get(name, index);										//Searches the appropriate linked list and returns PersonNode with matching name
	}
	
	
	//----------Handles deletion of a record from the hash table----------
	public PersonNode delete(String name){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		return object.remove(name, index);									//Deletes PersonNode object from appropriate linked list, returns their info
	}
	
	
	//----------Displays the contents of the hash table----------
	public void displayBook(){
		for (int index = 0; index <= (FinalMain.hashnum - 1); ++index){		//Traverses all 7 linked lists
			System.out.print("\nTelephoneBook[" + index + "] => ");
			object.display(index);											//Calls to display method to display entire contents of one linked list (at a time)
		}
	}
	
	
	//----------Writes contents of hash table to output file----------
	public void writeOutput() throws Exception{
		object.print();														//Calls to write all phone book info to .csv output file
	}
	
	/*
	 * ---------------------------------Methods for BST Below-----------------------------------------
	 */
	
	//----------Handles insertion of a record into the BST----------
	public void bstInsert(String name, String address, String number){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		
		if (tree.get(name, index) == null){									//If new entry....
			PersonNode entry = new PersonNode(name, address, number);		//Creates PersonNode object for this person's info
			tree.add(entry, index);											//Adds PersonNode object to BST
			
		} else {															//If entry exists....
			PersonNode updatePerson = tree.get(name, index);				//Retrieve PersonNode object of existing entry
			updatePerson.setAddress(address);								//Update their address
			updatePerson.setNumber(number);									//Update their phone number
		}	
	}
	//----------Handles retrieval of a record from the BST----------
	public PersonNode bstRetrieve(String name){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		return tree.get(name, index);										//Searches BST and retrieves the person's info
	}
	//----------Handles deletion of a record from the BST----------
	public PersonNode bstDelete(String name){
		int index = Math.abs(name.hashCode()) % FinalMain.hashnum;			//Hashes the name
		return tree.remove(name, index);									//Searches for and deletes PersonNode from BST, returns their info
	}
	//----------Displays the entire BST contents----------
	public void bstDisplayBook(){
		tree.display(tree.root);											//Calls to display entire BST contents
	}
	//----------Writes contents of BST to output file----------
	public void bstWriteOutput() throws Exception{
		tree.print(tree.root);												//Calls to write entire BST contents to .csv output file
	}
}