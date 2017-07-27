/**
 * This class is responsible for constructing a node and getting its children
 * @author Serena Wang 
 *
 * @param <T>
 */
package datastructure;
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	private T data;

	/**
	 * Construct a binary tree node with children
	 * 
	 * @param data
	 *            data
	 * @param left
	 *            left child
	 * @param right
	 *            right child
	 */
	public DefaultBinaryTreeNode(T data, DefaultBinaryTreeNode<T> left,
			DefaultBinaryTreeNode<T> right) {
		this.data = data;
		this.right = right;
		this.left = left;
	}

	/**
	 * Construct a binary tree node with no children
	 * 
	 * @param data
	 *            data
	 */
	public DefaultBinaryTreeNode(T data) {
		this.data = data;
		right = null;
		left = null;
	}

	/**
	 * Get the data stored at this node.
	 * 
	 * @return Object data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data stored at this node.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the left child.
	 * 
	 * @return BinaryTreeNode that is left child, or null if no child.
	 */
	public BinaryTreeNode<T> getLeftChild() {
		return left;
	}

	/**
	 * Get the right child.
	 * 
	 * @return BinaryTreeNode that is right child, or null if no child.
	 */
	public BinaryTreeNode<T> getRightChild() {
		return right;
	}

	/**
	 * Set the left child.
	 */
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.left = (DefaultBinaryTreeNode<T>) left;
	}

	/**
	 * Set the right child.
	 */
	public void setRightChild(BinaryTreeNode<T> right) {
		this.right = (DefaultBinaryTreeNode<T>) right;
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * 
	 * @return true if leaf node.
	 */
	public boolean isLeaf() {
		if (getRightChild() == null && getLeftChild() == null) {
			return true;
		} else {
			return false;
		}

	}
}
