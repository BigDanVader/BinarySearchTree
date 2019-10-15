package TreePackage;
/*
 * file: BinarySearchTree.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 1
 * date last modified: 1/21/18
 * 
 * purpose: This program allows for the manipulation of the node elements in a BinaryTree object.
 * Also allows for user to add and delete nodes from an already existing BinaryTree object.
 */
public class BinarySearchTree<T extends Comparable<? super T>>
			 extends BinaryTree<T>
{
	public BinarySearchTree()
	{
		super();
	}
	
	public BinarySearchTree(T rootEntry)
	{
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	}
	
	public void setTree(T rootData)
	{
		throw new UnsupportedOperationException();
	}
	
	public void setTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		throw new UnsupportedOperationException();
	}
	
	/*
	 * method: getEntry
	 * purpose: calls the private findEntry method.
	 */
	public T getEntry(T entry)
	{
		return findEntry(getRootNode(), entry);
	}
	
	/*
	 * method: findEntry
	 * purpose: Searches the BinarySearchTree for a given entry. Returns the entry if found, 
	 * or null if it is not found.
	 */
	private T findEntry(BinaryNode<T> rootNode, T entry)
	{
		T result =null;
		
		if (rootNode != null)
		{
			T rootEntry = rootNode.getData();
			
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		}
		
		return result;
	}
	
	/*
	 * method: contains
	 * purpose: calls the getEntry method to test if the BinarySearchTree object contains the
	 * given entry.
	 */
	public boolean contains(T entry)
	{
		return getEntry(entry) != null;
	}
	
	/*
	 * method: add
	 * purpose: If the BinarySearchTree is empty, sets the entry as the root node. Otherwise, calls 
	 * the private addEntry method.
	 */
	public T add(T newEntry)
	{
		T result = null;
		
		if (isEmpty())
			setRootNode(new BinaryNode<>(newEntry));
		else
			result = addEntry(getRootNode(), newEntry);
		
		return result;
	}
	
	/*
	 * method: addEntry
	 * purpose: Adds the given element to the BinarySearchTree. Returns the value if it is
	 * successfully added, otherwise returns null if the entry already exists.
	 */
	private T addEntry(BinaryNode<T> rootNode, T newEntry)
	{
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());
		
		if (comparison == 0)
		{
			result = rootNode.getData();
			rootNode.setData(newEntry);
		}
		else if (comparison <0)
		{
			if (rootNode.hasLeftChild())
				result = addEntry(rootNode.getLeftChild(), newEntry);
			else
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
		}
		else
		{
			assert comparison > 0;
			
			if (rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(),newEntry);
			else
				rootNode.setRightChild(new BinaryNode<>(newEntry));
		}
		
		return result;
	}
	
	/*
	 * class: returnObject
	 * purpose: used with the remove method to help remove an entry from the BinarySearchTree
	 * object.
	 */
	private class ReturnObject
	{
		private T data;
		
		public ReturnObject(T newData)
		{
			set(newData);
		}
		
		public void set(T newData)
		{
			data = newData;
		}
		
		public T get()
		{
			return data;
		}
	}
	
	/*
	 * method: remove
	 * purpose: calls the private removeEntry method.
	 */
	public T remove(T entry)
	{
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		
		setRootNode(newRoot);
		
		return oldEntry.get();
	}
	
	/*
	 * method: removeEntry
	 * purpose: searches the BinarySearchTree object for the given entry, then removes that entry.
	 * Returns the element contained in that entry if found, ortherwise returns null.
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry)
	{
		if (rootNode != null)
		{
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			
			if (comparison == 0)
			{
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}
			else if (comparison < 0)
			{
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			}
			else
			{
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}
		
		return rootNode;
	}
	
	/*
	 * method: removeFromRoot
	 * purpose: allows restructure of BinarySearchTree if root is removed.
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
	{
		if (rootNode.hasLeftChild() && rootNode.hasRightChild())
		{
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		}
		else if (rootNode.hasRightChild())
		{
			rootNode = rootNode.getRightChild();
		}
		else
		{
			rootNode = rootNode.getLeftChild();
		}
		
		return rootNode;
	}
	
	/*
	 * method: findLargest
	 * purpose: finds the largest node of the BinarySearchTree being searched.
	 */
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
	{
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());
		
		return rootNode;
	}
	
	/*
	 * method: removeLargest
	 * purpose: removed the largest entry in a BinarySearchTree.
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
	{
		if (rootNode.hasRightChild())
		{
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		}
		else
			rootNode = rootNode.getLeftChild();
		
		return rootNode;
	}
	
	/*
	 * method: getPredecessor
	 * purpose: calls the private findPredecessor method.
	 */
	public T getPredecessor(T key)
	{
		BinaryNode<T> result = findPredecessor(getRootNode(), key);
		return result.getData();
	}
	
	/*
	 * method: findPredecessor
	 * Purpose: Finds the predecessor of the given search element in an in-order traversal
	 * of the BinarySearchTree object.
	 */
	private BinaryNode<T> findPredecessor(BinaryNode<T> rootNode,T key)
	{
		if (rootNode == null)
			return null;
		
		int comparison = key.compareTo(rootNode.getData());
		if (comparison <= 0)
			return findPredecessor(rootNode.getLeftChild(), key);
		else
		{
			BinaryNode<T> right = findPredecessor(rootNode.getRightChild(), key);
			return (right != null) ? right : rootNode;
		}
	}
	
	/*
	 * method: getSuccessor
	 * purpose: calls the private findSuccessor method.
	 */
	public T getSuccessor(T key)
	{
		return (findSuccessor(getRootNode(), key).getData());
	}
	
	/*
	 * method: findSuccessor
	 * purpose: Finds the successor of the given search element in an in-order traversal
	 * of the BinarySearchTree object.
	 */
	private BinaryNode<T> findSuccessor(BinaryNode<T> rootNode, T key)
	{
		if (rootNode == null)
			return null;
		
		int comparison = key.compareTo(rootNode.getData());
		if (comparison >= 0)
			return findSuccessor(rootNode.getRightChild(), key);
		else
		{
			BinaryNode<T> left = findSuccessor(rootNode.getLeftChild(), key);
			return (left != null) ? left : rootNode;
		}
	}
}
