/*
 * Cameron Husted
 * CSE 231 - Final Project
 * April 10, 2017
 * Professor Shital Joshi
 */

package FinalProject;

import java.io.File;
import java.util.Scanner;

public class FinalMain {
	public static int hashnum = 7;											//Can easily change hash table size just by changing this number
	
	static Scanner scanInput = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		System.out.println("1. Hash Table\n2. Binary Search Tree");
		int input = scanInput.nextInt();									//Main method. Looks for user input and decides
		if (input == 1){													//whether to create a hash table or a BST
			hash();
		} else {
			bst();
		}
	}
	
	//---------Main method for running the hash table----------
	public static void hash() throws Exception {
		TelephoneBook list = new TelephoneBook();															//Creates telephoneBook object
		Scanner scan = new Scanner(new File("C:/eclipse/java-oxygen2/eclipse/workspace/FinalProject/src/input-data10.csv"));		//Find input data here
		while (scan.hasNextLine()){																			//Scans until everything has been scanned
			String str = scan.nextLine();
			String[] array = str.split(",");								//Splits the line by the commas, puts the parts into an array
			list.insert(array[0], array[1], array[2]);						//Inserts the data into the hash table as it is coming in
		}
		int input = 0;
		while (input != 5){
			System.out.println("\nMenu for Hash Table");					//This (and line below) displays all the text for the menu
			System.out.println("1. Insert Telephone Number\n2. Retrieve Telephone Number\n3. Delete Telephone Number\n4. Display Telephone Book\n5. End Program");
			System.out.print("Enter choice (1-5): ");
			input = scanInput.nextInt();									//Takes user input
			switch(input){
			
			
			//Case 1: Insert
			case 1 :
				System.out.println("\nPlease enter your name");
				String insertName = scanInput.next();						//User enters name
				System.out.println("Please enter your address");
				scanInput.nextLine();										//Without this line, the inputs get all screwed up. Had difficulty figuring this out.
				String insertAddress = scanInput.nextLine();				//User enters address
				System.out.println("Please enter your phone number (e.g. 123-456-7890)");
				String insertNumber = scanInput.next();						//User enters phone number
				list.insert(insertName, insertAddress, insertNumber);		//Send's user's data to be inserted
				break;
				
				
			//Case 2: Retrieve
			case 2 :
				System.out.print("\nEnter person's name: ");
				String retrievePerson = scanInput.next();					//User enters name
				
				PersonNode retrieved = list.retrieve(retrievePerson);		//Program returns the person and their information
				
				if (retrieved == null){
					System.out.println("\n" + retrievePerson + " does not exist in the telephone book"); 	//Display message if person not found
				} else {
					System.out.println(retrieved.name + "'s address is: " + retrieved.address);				//Display person's address
					System.out.println(retrieved.name + "'s telephone number is: " + retrieved.number);		//Display person's phone number
				}
				break;
				
				
			//Case 3: Delete
			case 3 :
				System.out.print("\nEnter person's name: ");
				String deletePerson = scanInput.next();						//User enter's name
				//long Dstart = System.nanoTime();
				PersonNode deleted = list.delete(deletePerson);				//Deletes the person, but also returns their information to be displayed
				//long Dend = System.nanoTime();
				//System.out.println("It took " + (Dend - Dstart) + " ns.");
				if (deleted == null){
					System.out.println("\n" + deletePerson + " does not exist in the telephone book");		//Display message if person not found
				} else {
					System.out.println("Deleting: " + deleted.name + " (" + deleted.address + ") " + deleted.number);	//Lets user know person was deleted
				}
				break;
				
				
			//Case 4: Display
			case 4 :
				System.out.print("\nDisplay Table");
				list.displayBook();												//Displays entire phone book
				System.out.println("");
				break;
				
				
			//Case 5: End Program
			case 5 :
				list.writeOutput();												//Writes data to output file
				System.out.println("\nData has been written to excel file.");	//Program will end after this section completes
				break;
			}
		}
	}
	
	//---------Main method for running the BST----------
	public static void bst() throws Exception{
		TelephoneBook bstList = new TelephoneBook();													//Creates TelephoneBook object
		Scanner scan = new Scanner(new File("C:/eclipse/java-oxygen2/eclipse/workspace/FinalProject/src/input-data10.csv"));	//Finds input data here
		while (scan.hasNextLine()){																		//Scans until everything has been scanned
			String str = scan.nextLine();
			String[] array = str.split(",");												//Splits the line by the commas, puts the parts into an array
	        bstList.bstInsert(array[0], array[1], array[2]);								//Inserts the data into the BST as it is coming in
		}
		int input = 0;
		while (input != 5){
			System.out.println("\nMenu for Binary Search Tree");							//This (and line below) displays all the text for the menu
			System.out.println("1. Insert Telephone Number\n2. Retrieve Telephone Number\n3. Delete Telephone Number\n4. Display Telephone Book\n5. End Program");
			System.out.print("Enter choice (1-5): ");
			input = scanInput.nextInt();													//Takes user input
			switch(input){
			
			
			//Case 1: Insert
			case 1 :
				System.out.println("\nPlease enter your name");
				String insertName = scanInput.next();										//User enters name
				System.out.println("Please enter your address");
				scanInput.nextLine();														//Without this line, the inputs get all screwed up. Had difficulty figuring this out.
				String insertAddress = scanInput.nextLine();								//User enters address
				System.out.println("Please enter your phone number (e.g. 123-456-7890)");
				String insertNumber = scanInput.next();										//User enters phone number
				bstList.bstInsert(insertName, insertAddress, insertNumber);					//Sends user's data to be entered into phone book
				System.out.println("\nThe record has been added");
				break;
				
				
			//Case 2: Retrieve
			case 2 :
				System.out.print("\nEnter person's name: ");
				String retrievePerson = scanInput.next();									//User enters name
				PersonNode retrieved = bstList.bstRetrieve(retrievePerson);					//Returns person's information
				if (retrieved == null){
					System.out.println("\n" + retrievePerson + " does not exist in the telephone book");	//Displays message if person not found
				} else {
					System.out.println(retrieved.name + "'s address is: " + retrieved.address);				//Displays person's address
					System.out.println(retrieved.name + "'s telephone number is: " + retrieved.number);		//Displays person's phone number
				}
				break;
				
				
			//Case 3: Delete
			case 3 :
				System.out.print("\nEnter person's name: ");
				String deletePerson = scanInput.next();										//User enters name
				PersonNode deleted = bstList.bstDelete(deletePerson);						//Record is deleted, and person's info is returned
				if (deleted == null){
					System.out.println("\n" + deletePerson + " does not exist in the telephone book");		//Message if person is not found
				} else {
					System.out.println("Deleting: " + deleted.name + " (" + deleted.address + ") " + deleted.number);	//Lets user know person was deleted
				}
				break;
				
				
			//Case 4: Display
			case 4 :
				System.out.print("\nDisplay Table");
				bstList.bstDisplayBook();											//Display's entire phone book
				System.out.println("");
				break;
				
				
			//Case 5: End Program
			case 5 :
				bstList.bstWriteOutput();											//Writes entire phone book to output file
				System.out.println("\nData has been written to excel file.");		//Programs ends after this has been completed
				break;
			}
		}
	}
}