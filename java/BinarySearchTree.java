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

public class BinarySearchTree{
	PersonNode root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	//-------------Handles Insertion for BST-----------------
	public void add(PersonNode entry, int index){						//Adds PersonNode to appropriate spot in BST
		if (root == null){
			root = entry;
		} else {
			PersonNode nptr = root;
			PersonNode nptr_parent = root;
			while(nptr != null){										//If current node is not null....
				int key = Math.abs(nptr.name.hashCode()) % FinalMain.hashnum;
				nptr_parent = nptr;										//Move down the list, and change node_parent
				if(key >= index){										//If new input key <= current node key....
					nptr = nptr.leftChild;								//current node is now the current node's left child
					if(nptr == null){									//If current node is now null (no left child)....
						nptr_parent.leftChild = entry;					//Make the new node its left child
					}		
				} else {												//If new input key > current node key....
					nptr = nptr.rightChild;								//current node is now the current node's right child
					if(nptr == null){									//If current node is now null (no right child)....
						nptr_parent.rightChild = entry;					//Make the new node its right child
					}
				}
			}
		}
	}
	
	
	//-------------Handles Retrieval for BST-----------------
	public PersonNode get(String name, int index){						//Retrieves PersonNode from BST
		if(root == null){
			return null;
		}
		PersonNode nptr = root;
		while(nptr != null){											//While current node is not null...
			if (name.equals(nptr.name)){
				return nptr;
			} else {
				int key = Math.abs(nptr.name.hashCode()) % FinalMain.hashnum;
				if(key >= index){										//If new input key <= current node key....
					nptr = nptr.leftChild;								//current node is now the current node's left child
					if(nptr == null){									//If current node is now null (no left child)....
						return null;									//Make the new node its left child
					}
				} else {
					nptr = nptr.rightChild;								//current node is now the current node's right child
					if(nptr == null){									//If current node is now null (no right child)....
						return null;									//Make the new node its right child
					}
				}
			}
		}
		return null;
	}
	
	
	//-------------Handles Deletion for BST-----------------
	public PersonNode remove(String name, int index){					//Deletes PersonNode from BST
		PersonNode nptr = root;											//Tracks current PersonNode
		PersonNode nptr_parent = root;									//Tracks the parent of the current PersonNode
		int tracker = 0;
		while(!(nptr.getName().equals(name))){							//While the names don't match....
			nptr_parent = nptr;
			int key = Math.abs(nptr.name.hashCode()) % FinalMain.hashnum;	//Hashes the name of "nptr" to figure out where to go
			if(key >= index){
				nptr = nptr.leftChild;									//Current node is a leftChild
				tracker = 1;
			} else {
				nptr = nptr.rightChild;									//Current node is a rightchild
				tracker = 0;
			}
			if(nptr == null){											//Returns null if the PersonNode does not exist
				return null;
			}
		}
		
		
		//--------Case 1: If the node has no child---------
		if(nptr.leftChild == null && nptr.rightChild == null){
			if(nptr == root){											//If it's the only node....
				root = null;											//DELETE
			}
			if(tracker == 1){											//If it's a leftchild....
				nptr_parent.leftChild = null;							//DELETE
			} else {													//If it's a rightchild....
				nptr_parent.rightChild = null;							//DELETE
			}
		}
		
		
		//----------Case 2: If the node has one child-----------
		else if(nptr.leftChild == null){								//If it's child is a right child....
			if(nptr == root){											//If it's the root node....
				root = nptr.rightChild;									//DELETE
			}
			if(tracker == 1){											//If it's a leftchild....
				nptr_parent.leftChild = nptr.rightChild;				//DELETE
			} else {													//If it's a rightchild....
				nptr_parent.rightChild = nptr.rightChild;				//DELETE
			}
		} else if(nptr.rightChild == null){								//If it's child is a left child....
			if(nptr == root){											//If it's the root node....
				root = nptr.leftChild;									//DELETE
			}
			if(tracker == 1){											//If it's a leftchild....
				nptr_parent.leftChild = nptr.leftChild;					//DELETE
			} else {													//If it's a rightchild....
				nptr_parent.rightChild = nptr.leftChild;				//DELETE
			}
		}
		
		
		//----------Case 3: If the node has two children----------
		else {
			PersonNode successor = successor(nptr);						//Retrieves the node's successor
			if(nptr == root){											//If it's the root node
				root = successor;										//Make its successor the root node
			}
			if(tracker == 1){											//If it's a leftchild....
				nptr_parent.leftChild = successor;						//DELETE
			} else {													//If it's a rightchild....
				nptr_parent.rightChild = successor;						//DELETE
			}
			successor.leftChild = nptr.leftChild;
			if(successor != nptr.rightChild){							//If the successor is not immediately succeeding nptr....
				successor.rightChild = nptr.rightChild;					//Then fix it
			}
		}
		return nptr;
	}
	
	
	//-------------Find successor of Node being deleted-----------------
	public PersonNode successor(PersonNode x){
		PersonNode nptr_parent = null;									//Keeps track of current node's parent and
		PersonNode nptr = null;											//curent node
		PersonNode temp = x.rightChild;									//Successor is left-most node in the rightchild
		while(temp != null){
			nptr_parent = nptr;
			nptr = temp;
			temp = temp.leftChild;										//Keeps traversing left until it hits null
		}
		if(nptr != x.rightChild){
			nptr_parent.leftChild = nptr.rightChild;
		}
		return nptr;
	}
	
	
	//-------------Handles displaying BST contents-----------------
	public void display(PersonNode root){
		if(root != null){												//If current node isn't null
			display(root.leftChild);									//Go to the left child
			System.out.print("\n" + root.name + ", " + root.address + ", " + root.number + ", " + Math.abs(root.name.hashCode()) % FinalMain.hashnum);
			display(root.rightChild);									//No left child at this point, so check for right child
		}
	}
	
	
	//-------------Sets up writing BST contents to output file-----------------
	public void print(PersonNode root) throws Exception{
		FileWriter writer = new FileWriter("C:/eclipse/java-oxygen2/eclipse/workspace/FinalProject/src/output-file.csv");      
		BufferedWriter bwriter = new BufferedWriter(writer);
		output(root, writer, bwriter);									//Two separate files to keep from recreating new FileWriter output files
	}
	
	
	//-------------Actually does the writing to output file-----------------
	public void output(PersonNode root, FileWriter writer, BufferedWriter bwriter) throws IOException{
		if(root != null){												//A left-root-right traversal
			output(root.leftChild, writer, bwriter);
			bwriter.write(root.name + "," + root.address + "," + root.number + "\n");		//Writes line to buffer
			//System.out.println("Writing " + root.name + " to file.");
			bwriter.flush();											//Writes buffer contents to output file
			output(root.rightChild, writer, bwriter);
		}
	}
}