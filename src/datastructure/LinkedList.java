/**
 * This class is responsible for manipulating the nodes in the list.
 * 
 * @author Serena Wang
 *
 */
package datastructure;
public class LinkedList<T> {
	private LinkedListNode<T> head;

	/**
	 * Constructor to create a null list( an empty list)
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Constructor to create a list which has only one node
	 */
	public LinkedList(LinkedListNode<T> aNode) {
		head = aNode;

	}

	/**
	 * Get data stored in head node of list.
	 * 
	 * @return data in the first node
	 */
	public T getFirst() {
		if (head != null) {
			return head.getData();
		} else {
			return null;
		}
	}

	/**
	 * Get the head node of the list.
	 * 
	 * @return the first node
	 */
	public LinkedListNode<T> getFirstNode() {
		return head;
	}

	/**
	 * Get data stored in last node of list.
	 * 
	 * @return data in the last node
	 */
	public T getLast() {
		if (this.getLastNode() != null) {
			return this.getLastNode().getData();
		} else {
			return null;
		}
	}

	/**
	 * Get the tail node of the list.
	 * 
	 * @return the last node
	 */
	public LinkedListNode<T> getLastNode() {
		LinkedListNode<T> currentNode = head;
		if (currentNode == null) {
			return null;
		} else {
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			return currentNode;
		}
	}

	/**
	 * Insert a new node with data at the head of the list.
	 * 
	 * @param data
	 */
	public void insertFirst(T data) {
		// if it's an empty list
		if (head == null) {
			head = new LinkedListNode<T>();
			head.setData(data);
		}
		// otherwise
		else {
			LinkedListNode<T> tempNode = new LinkedListNode<T>(data);
			tempNode.setNext(head);
			head = tempNode;
		}
	}

	/**
	 * Insert a new node with data after currentNode
	 * 
	 * @param node
	 * @param data
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data) {
		// create the new node
		LinkedListNode<T> newNode = new LinkedListNode<T>(data);
		if (currentNode == null) {
			return;
		} else {
			if (this.getLastNode() != null) {
				// insert after the last one
				if (currentNode.equals(this.getLastNode())) {
					this.getLastNode().setNext(newNode);
				}
				// insert in the middle
				else {
					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);
				}
			}
		}
	}

	/**
	 * Insert a new node with data at the end of the list.
	 * 
	 * @param data
	 */
	public void insertLast(T data) {
		LinkedListNode<T> newNode = new LinkedListNode<T>(data);
		if (this.getLastNode() != null) {
			this.getLastNode().setNext(newNode);
		} else {
			// return;
			insertFirst(data);
		}
	}

	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		// if it's not an empty list
		if (head != null) {
			// multiple nodes
			if (head.getNext() != null) {
				head = head.getNext();
			}
			// if there's only one node in this list
			else {
				head = null;
			}
		}
		// if it's an empty list
		else {
			return;
		}
	}

	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		// if it's not an empty list
		if (head != null) {
			// only one node in this list
			if (head.getNext() == null) {
				head = null;
			}
			// multiple nodes
			else {
				LinkedListNode<T> currentNode = head;
				while (currentNode != null && currentNode.getNext() != null) {
					if (currentNode.getNext() == this.getLastNode()) {
						currentNode.setNext(null);
					}
					currentNode = currentNode.getNext();
				}
			}
		}
		// if it's an empty list
		else {
			return;
		}
	}

	/**
	 * Remove node following currentNode If no node exists (i.e., currentNode is
	 * the tail), do nothing
	 * 
	 * @param node
	 */
	public void deleteNext(LinkedListNode<T> currentNode) {
		if (currentNode == null || currentNode.getNext() == null) {
			return;
		} else {
			LinkedListNode<T> nextNode = currentNode.getNext();
			// if current node's next node is the last node
			if (nextNode == this.getLastNode()) {
				currentNode.setNext(null);
			} else {
				currentNode.setNext(nextNode.getNext());
			}
		}
	}

	/**
	 * Return the number of nodes in this list.
	 * 
	 * @return size counter
	 */
	public int size() {
		int counter = 0;
		LinkedListNode<T> currentNode = head;
		if (currentNode != null) {
			counter = 1;
			if (currentNode.getNext() != null) {
				while (currentNode.getNext() != null) {
					currentNode = currentNode.getNext();
					counter++;
				}
			}
		}

		return counter;
	}

	/**
	 * Check if list is empty.
	 * 
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Return a String representation of the list.
	 * 
	 * @return dataList
	 */
	public String toString() {
		if (head != null) {
			LinkedListNode<T> currentNode = head;
			String dataList = head.toString();
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
				dataList = dataList + " -> " + currentNode.toString();
			}
			return dataList;
		} else {
			return null;
		}
	}

	/**
	 * Search for the following node
	 * 
	 * @param target
	 * @return the following node
	 */
	public LinkedListNode<T> searchForNext(T target) {
		return searchForNext(this.getFirstNode(), target);
	}

	/**
	 * Recursive method for searching for the following node
	 * 
	 * @param currentNode
	 * @param target
	 * @return the following node
	 */
	public LinkedListNode<T> searchForNext(LinkedListNode<T> currentNode,
			T target) {
		if (currentNode.getData().equals(target)) {
			return currentNode.getNext();
		} else {
			return searchForNext(currentNode.getNext(), target);
		}
	}

	/**
	 * Search for the node before it
	 * 
	 * @param target
	 * @return the node before
	 */
	public LinkedListNode<T> searchForPre(T target) {
		return searchForPre(this.getFirstNode(), target);
	}

	/**
	 * Recursive method for searching for the node before it
	 * 
	 * @param currentNode
	 * @param target
	 * @return the node before
	 */
	public LinkedListNode<T> searchForPre(LinkedListNode<T> currentNode,
			T target) {
		if (currentNode.getNext().getData().equals(target)) {
			return currentNode;
		} else {
			return searchForPre(currentNode.getNext(), target);
		}
	}

}
