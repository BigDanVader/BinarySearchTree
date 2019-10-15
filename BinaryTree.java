package TreePackage;
/*
 * file: BinaryTree.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 1/21/18
 * 
 * purpose: This program allows for the traversal of a linked series of nodes. It also includes
 * methods for printing the object in preorder, postorder, and inorder traversals.
 */
public class BinaryTree<T> 
{

	private BinaryNode<T> root;
	
	public BinaryTree()
	{
		root = null;
	}
	
	public BinaryTree (T rootData)
	{
		root = new BinaryNode<>(rootData);
	}
	
	public BinaryTree (T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		 privateSetTree(rootData, leftTree, rightTree);
	}
	
	/*
	 * method: setTree
	 * purpose: Creates a new "root" for the BinaryTree object.
	 */
	public void setTree(T rootData)
	{
		root = new BinaryNode<>(rootData);
	}
	
	/*
	 * method: setTree
	 * purpose: calls the privateSetTree method
	 */
	public void setTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	/*
	 * method: privateSetTree
	 * purpose: Constructs a new "root" for the BinaryTree object and populates it with
	 * children nodes.
	 */
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		root = new BinaryNode<>(rootData);
		
		if ((leftTree != null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root);
		
		if ((rightTree != null) && !rightTree.isEmpty())
		{
			if (rightTree != leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
		}
		
		if ((leftTree != null) && (leftTree != this))
			leftTree.clear();
		
		if ((rightTree != null) && (rightTree != this))
			rightTree.clear();
	}
	
	/*
	 * method: getRootData
	 * purpose: returns the data contained in the root node of the BinaryTree object.
	 */
	public T getRootData()
	{
		T rootData = null;
		
		if (root != null)
			rootData = root.getData();
		
		return rootData;
	}
	
	/*
	 * method: isEmpty
	 * porpose: Returns whether the BinaryTree object is empty.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/*
	 * method: clear
	 * purpose: sets the root node of the BinaryTree to null.
	 */
	public void clear()
	{
		root = null;
	}
	
	/*
	 * method: setRootData
	 * purpose: sets the data member of the root node of the BoinaryTree object.
	 */
	protected void setRootData(T rootData)
	{
		root.setData(rootData);
	}
	
	/*
	 * method: setRootNode
	 * purpose: makes a "shallow" copy of the provided node onto the root node.
	 */
	protected void setRootNode(BinaryNode<T> rootNode)
	{
		root = rootNode;
	}
	
	/*
	 * method: getRootNode
	 * purpose: returns the root node of the BinaryTree object.
	 */
	protected BinaryNode<T> getRootNode()
	{
		return root;
	}
	
	/*
	 * method: printInOrder
	 * purpose: calls the private printInOrder method.
	 */
	public void printInOrder()
	{
		printInOrder(root);
	}
	
	/*
	 * method: printInOrder
	 * purpose: performs an in-order traversal of the BinaryTree object while printing the
	 * elements stored in its nodes on the screen.
	 */
	private void printInOrder(BinaryNode<T> node)
	{
		if (node == null)
			return;
		
		printInOrder(node.getLeftChild());
		System.out.print(" " + node.getData());
		printInOrder(node.getRightChild());
		
	}
	
	/*
	 * method: printPostOrder
	 * purpose: calls the private printPostOrder method.
	 */
	public void printPostOrder()
	{
		printPostOrder(root);
	}
	
	/*
	 * method: printPostOrder
	 * purpose: performs a post-order traversal of the BinaryTree object while printing elements
	 * contained in the nodes.l
	 */
	private void printPostOrder(BinaryNode<T> node)
	{
		if (node == null)
			return;
		
		printPostOrder(node.getLeftChild());
		printPostOrder(node.getRightChild());
		System.out.print(" " + node.getData());
	}
	
	/*
	 * method: printPreOrder
	 * purpose: calls the private printPreOrder method.
	 */
	public void printPreOrder()
	{
		printPreOrder(root);
	}
	
	/*
	 * method: printPreOrder
	 * purpose: performs a pre-order traversal of the BinaryTree object while printing the elements
	 * contained within the bodes.
	 */
	private void printPreOrder(BinaryNode<T> node)
	{
		if (node == null)
			return;
		
		System.out.print(" " + node.getData());
		printPreOrder(node.getLeftChild());
		printPreOrder(node.getRightChild());
	}
}
