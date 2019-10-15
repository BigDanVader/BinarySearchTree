package TreePackage;
/*
 * file: BinaryNode.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 1/21/18
 * 
 * purpose: This program creates the node structure that will be used for all
 * other programs in this package. The nodes can also reference each other, 
 * creating a linked structure.
 */
public class BinaryNode <T>
{
	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	public BinaryNode()
	{
		this(null);
	}
	
	public BinaryNode (T dataPortion)
	{
		this(dataPortion, null, null);
	}
	
	public BinaryNode (T dataPortion, BinaryNode<T> newLC, BinaryNode<T> newRC)
	{
		data = dataPortion;
		leftChild = newLC;
		rightChild = newRC;
	}
	
	/*
	 * method:getData
	 * purpose: returns the data stored in a node object.
	 */
	public T getData()
	{
		return data;
	}
	
	/*
	 * method: setData
	 * purpose: sets the data stored in a node object with the provided value.
	 */
	public void setData (T newData)
	{
		data = newData;
	}
	
	/*
	 * method:getLeftChild
	 * purpose: returns the left child entry of a node object.
	 */
	public BinaryNode<T> getLeftChild()
	{
		return leftChild;
	}
	
	/*
	 * method: getRightChild
	 * purpose: returns the right child of a node object.
	 */
	public BinaryNode<T> getRightChild()
	{
		return rightChild;
	}
	
	/*
	 * method: setLeftChild
	 * purpose: sets the left child of a node object with the provided node.
	 */
	public void setLeftChild (BinaryNode<T> newLC)
	{
		leftChild = newLC;
	}
	
	/*
	 * method: setRightChild
	 * purpose: sets the right child of a node object with the provided node.
	 */
	public void setRightChild (BinaryNode<T> newRC)
	{
		rightChild = newRC;
	}
	
	/*
	 * method: hasLeftChild
	 * purpose: returns whether the left child of a node exists.
	 */
	public boolean hasLeftChild()
	{
		return leftChild != null;
	}
	
	/*
	 * method: hasRightChild
	 * purpose: returns whether the right child of a node exists.
	 */
	public boolean hasRightChild()
	{
		return rightChild != null;
	}
	
	/*
	 * method: isLeaf
	 * purpose: returns whether the given node has no children.
	 */
	public boolean isLeaf()
	{
		return (leftChild == null) && (rightChild == null);
	}
	
	/*
	 * method: copy
	 * purpose: Creates a deep copy of a node.
	 */
	public BinaryNode<T> copy()
	{
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		
		if (leftChild != null)
			newRoot.setLeftChild(leftChild.copy());
		
		if (rightChild != null)
			newRoot.setRightChild(rightChild.copy());
		
		return newRoot;
	}
}
