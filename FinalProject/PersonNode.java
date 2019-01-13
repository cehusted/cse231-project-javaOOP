/*
 * Cameron Husted
 * CSE 231 - Final Project
 * April 10, 2017
 * Professor Shital Joshi
 */

package FinalProject;

public class PersonNode {
	PersonNode next, leftChild, rightChild;
	String name, address, number;
	
	public PersonNode(){													//Initializes the fields in PersonNode
		number = null;
		address = null;
		name = null;
		next = null;
	}
	
	public PersonNode(String name, String address, String number){
		this.name = name;													//Each person's record contains all of the information to the 
		this.address = address;												//left of these lines. However, "next" remains null in the BST
		this.number = number;												//portion, and "rightChild" and "leftChild" remain null in
		this.next = null;													//the hash table portion. This way, I can stick with just one 
		this.rightChild = null;												//constructor for both data structures
		this.leftChild = null;
	}
	//----------Returns the person's name----------
	public String getName(){
		return name;
	}
	//----------Returns the person's phone number----------
	public String getTelephoneNumber(){
		return number;
	}
	//----------Returns the person's address----------
	public String getAddress(){
		return address;
	}
	//----------Returns the next person in the hash table----------
	public PersonNode getNext(){
		return next;
	}
	//----------Sets the next person (after this person) in the hash table----------
	public void setNext(PersonNode next){
		this.next = next;
	}
	//----------Sets the person's new address----------
	public void setAddress(String address){
		this.address = address;
	}
	//----------Sets the person's new address----------
	public void setNumber(String number){
		this.number = number;
	}
}