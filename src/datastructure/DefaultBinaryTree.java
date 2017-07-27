/**
 * Create a binary tree and traversals
 *
 * @author Serena Wang
 *
 * @param <T>
 */
package datastructure;
public class DefaultBinaryTree<T> implements BinaryTree<T> {
	private BinaryTreeNode<T> root;

	/**
	 * Set up a binary tree with seven dwarfs
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();
		DefaultBinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>(
				"Happy");
		DefaultBinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>(
				"Doc");
		DefaultBinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>(
				"Bashful");
		DefaultBinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>(
				"Grumpy");
		DefaultBinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>(
				"Sleepy");
		DefaultBinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>(
				"Sneezy");
		DefaultBinaryTreeNode<String> dopey = new DefaultBinaryTreeNode<String>(
				"Dopey");
		grumpy.setLeftChild(dopey);
		doc.setLeftChild(bashful);
		doc.setRightChild(grumpy);
		sleepy.setRightChild(sneezy);
		happy.setLeftChild(doc);
		happy.setRightChild(sleepy);
		tree.setRoot(happy);
	}

	/**
	 * Get the tree root
	 * 
	 * @return BinaryTreeNode<T> root
	 */
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the tree root with the given node
	 * 
	 * @para BinaryTreeNode<T> node
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Check whether the tree is empty
	 * 
	 * @return boolean yes or no
	 */
	public boolean isEmpty() {
		if (root.getData() == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create inorder traversal
	 * 
	 * @return LinkedList<T> the inoder traversal list
	 */
	public LinkedList<T> inorderTraversal() {
		LinkedList<T> list = new LinkedList<T>();
		inorderTraversal(this.getRoot(), list);
		return list;

	}

	/**
	 * Recursive method to create inorder traversal
	 * 
	 * @param node
	 *            the node we are looking at
	 * @param traversal
	 *            the list
	 */
	private void inorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node != null) {
			inorderTraversal(node.getLeftChild(), traversal);
			traversal.insertLast(node.getData());

			inorderTraversal(node.getRightChild(), traversal);
		}

	}

	/**
	 * Create preorder traversal
	 * 
	 * @return LinkedList<T> the preoder traversal list
	 */
	public LinkedList<T> preorderTraversal() {
		LinkedList<T> list = new LinkedList<T>();
		preorderTraversal(this.getRoot(), list);
		return list;
	}

	/**
	 * Recursive method to create preorder traversal
	 * 
	 * @param node
	 *            the node we are looking at
	 * @param traversal
	 *            the list
	 */
	private void preorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node != null) {

			traversal.insertLast(node.getData());

			preorderTraversal(node.getLeftChild(), traversal);
			preorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * Create postorder traversal
	 * 
	 * @return LinkedList<T> the postoder traversal list
	 */
	public LinkedList<T> postorderTraversal() {
		LinkedList<T> list = new LinkedList<T>();
		postorderTraversal(this.getRoot(), list);
		return list;
	}

	/**
	 * Recursive method to create postorder traversal
	 * 
	 * @param node
	 *            the node we are looking at
	 * @param traversal
	 *            the list
	 */
	private void postorderTraversal(BinaryTreeNode<T> node,
			LinkedList<T> traversal) {
		if (node != null) {
			postorderTraversal(node.getLeftChild(), traversal);
			postorderTraversal(node.getRightChild(), traversal);
			traversal.insertLast(node.getData());

		}
	}
}
