/**
 * This class is responsible for controlling only 1 node in the list.
 * 
 * @author Serena Wang
 *
 */
package datastructure;
public class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> next;

	/**
	 * Construct an empty node
	 */
	public LinkedListNode() {
		data = null;
		next = null;
	}

	/**
	 * Construct a node with data
	 * 
	 * @param data
	 */
	public LinkedListNode(T data) {
		this.data = data;
		next = null;
	}

	/**
	 * Construct a node which has data and also connects to the next node
	 * 
	 * @param data1
	 * @param nextNode
	 */
	public LinkedListNode(T data1, LinkedListNode<T> nextNode) {
		data = data1;
		next = nextNode;
	}

	/**
	 * Set the data stored at this node.
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the data stored at this node.
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the next pointer to passed node.
	 * 
	 * @param node
	 */
	public void setNext(LinkedListNode<T> node) {
		next = node;
	}

	/**
	 * Get (pointer to) next node.
	 * 
	 * @return next node
	 */
	public LinkedListNode<T> getNext() {
		return next;
	}

	/**
	 * Returns a String representation of this node.
	 * 
	 * @return String
	 */
	public String toString() {
		if (data != null) {
			return data.toString();
		} else {
			return " ";
		}
	}
}
