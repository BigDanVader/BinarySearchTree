package TreePackage;
/*
 * file: BSTUserFile.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 1/21/18
 * 
 * purpose: This program allows the user to create and interact with a BinarySearchTree object.
 * Allows for adding nodes, deleting nodes, and finding the predecessor and successor of a 
 * search key.
 */
import java.util.Scanner;

public class BSTUserFile 
{
	/*
	 * method: printTraversals
	 * purpose: prints an inorder, preorder, and postorder traversal of the BinarySearchTree
	 * object created in main.
	 */
	public static void printTraversals(BinarySearchTree<Integer> input)
	{
		System.out.print("In order:");
		input.printInOrder();
		System.out.println(".");
		System.out.print("PreOrder:");
		input.printPreOrder();
		System.out.println(".");
		System.out.print("PostOrder:");
		input.printPostOrder();
		System.out.println(".");
	}
	
	/*
	 * method: printCommands
	 * purpose: prints the commands available to the user in the main method.
	 */
	private static void printCommands() 
	{
		System.out.println("I: Insert a value");
		System.out.println("D: Delete a value");
		System.out.println("P: Find predecessor");
		System.out.println("S: Find successor");
		System.out.println("E: Exit the program");
		System.out.println("H: Display this message");
	}

	/*
	 * method: insertValue
	 * purpose: calls the add method contained in BinarySearchTree object. Prints an error 
	 * message if the entry already exists, otherwise prints an updated inorder traversal of 
	 * the BinarySearchTree object created in the main method.
	 */
	private static void insertValue(BinarySearchTree<Integer> input, int num)
	{
		Integer result = input.add(num);
		
		if (result != null)
			System.out.println("Sorry, " + num + " already exists.");
		else
		{
			System.out.print("New order:");
			input.printInOrder();
			System.out.println(".");
		}
	}
	
	/*
	 * method: deleteValue
	 * purpose: calls the add method contained in BinarySearchTree object. Prints an error 
	 * message if the entry does not exist, otherwise prints an updated inorder traversal of 
	 * the BinarySearchTree object created in the main method.
	 */
	private static void deleteValue(BinarySearchTree<Integer> input, int key) 
	{
		Integer result = input.remove(key);
		
		if (result == null)
			System.out.println("Sorry, " + key + " does not exist.");
		else
		{
			System.out.print("New order:");
			input.printInOrder();
			System.out.println(".");
		}
	}

	/*
	 * method: findPredecessor
	 * purpose: calls the getPredecessor method from the BinarySearchTree class file. Prints an
	 * error message if the entry cannot be found, otherwise prints the entry's predecessor in 
	 * an inorder traversal.
	 */
	private static void findPredecessor(BinarySearchTree<Integer> input, int key)
	{
		if (!input.contains(key))
			System.out.println("Sorry, " + key + " does not exist.");
		else 
		{
			System.out.println(input.getPredecessor(key));
		}
	}
	
	/*
	 * method: findSuccessor
	 * purpose: calls the getSuccessor method from the BinarySearchTree class file. Prints an
	 * error message if the entry cannot be found, otherwise prints the entry's successor in 
	 * an inorder traversal.
	 * */
	private static void findSuccessor(BinarySearchTree<Integer> input, int key) 
	{
		if (!input.contains(key))
			System.out.println("Sorry, " + key + " does not exist.");
		else 
		{
			System.out.println(input.getSuccessor(key));
		}
	}
	
	/*
	 * method: printError
	 * purpose: prints an error message if the user enters input not supported by the program.
	 */
	private static void printError() 
	{
		System.out.println("Input error. Please try again.");
	}
	
	public static void main(String[] args) 
	{

		BinarySearchTree<Integer> input = new BinarySearchTree<Integer>();
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the initial integers to add to the BST: ");
		String tempInput = in.nextLine();   
		String[] numbers = tempInput.split(" "); 
		
		for (int i = 0; i < numbers.length; i++)
		{
			input.add(Integer.parseInt(numbers[i]));
		}
		
		printTraversals(input);
		
		printCommands();

		Boolean exitProgram = false;
		while(!exitProgram)
		{
			System.out.println("Enter command: ");
			String command = in.next().toUpperCase();
			int comNum = 0;
			
			switch(command)
			{
			case "H": printCommands();
					  break;
			case "I": try
					  {
				      	comNum = in.nextInt();
					  }
					  catch (Exception e)
					  {
						  System.out.println("Input error. Please try again");
						  in.next();
						  continue;
					  }
					  insertValue(input, comNum);
					  break;
			case "D": try
					  {
					  	comNum = in.nextInt();
					  }
					  catch (Exception e)
					  {
						  System.out.println("Input error. Please try again");
						  in.next();
						  continue;
					  }
					  deleteValue(input, comNum);
		              break;
			case "P": try
					  {
					  	comNum = in.nextInt();
					  }
				      catch (Exception e)
					  {
				    	  System.out.println("Input error. Please try again");
				    	  in.next();
				    	  continue;
					  }
				      findPredecessor(input, comNum);
				      break;
			case "S": try
					  {
					  	comNum = in.nextInt();
					  }
					  catch (Exception e)
					  {
						  System.out.println("Input error. Please try again");
						  in.next();
						  continue;
					  }
					  findSuccessor(input, comNum);
				      break;
			case "E": exitProgram = true;
			          break;
			default: printError();
					 break;
			}
		}
		
		System.out.println("Thank you, please come again.");
	}
}


