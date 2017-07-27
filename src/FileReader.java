/**
 * This class is responsible for parsing the xml file and put it in a Binary Tree.
 * @author Serena Wang
 */
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import datastructure.*;

public class FileReader {

	/**
	 * Parse the file
	 * @param filename xml file name
	 * @return Binary Tree
	 */
	public static BinaryTree<String> readFromFile(String filename) {
		Document document = null;
		BinaryTree<String> tree = new DefaultBinaryTree<String>();
		try {
			// Setup XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File xmlFile = new File(filename);
			document = builder.parse(xmlFile);
			tree.setRoot(parseQuestion(document.getDocumentElement()));
		} catch (ParserConfigurationException pce) {
			System.out.println("There is a parser configuration exception.");
		} catch (SAXException saxe) {
			System.out.println("There is an SAXE exception.");
		} catch (IOException ioe) {
			System.out.println("There is an IOE exception.");
		}

		return tree;
		
	}
	
	/**
	 * Parse the element which contains a question and put it in the tree.
	 * @param e an element in the file
	 * @return a BinaryTreeNode with the information
	 */

	public static BinaryTreeNode<String> parseQuestion(Element e) {
		BinaryTreeNode<String> treeQNode = new DefaultBinaryTreeNode<String>(
				e.getAttribute("question"));
		
		if (e.getNodeName().equals("Q") /* && e.hasChildNodes() */) {
			NodeList answerList = e.getChildNodes();

			for (int i = 0; i < answerList.getLength(); i++) {
				if (answerList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element answerNode = (Element) answerList.item(i);
					if (answerNode.getAttribute("answer").equals("Y")) {
						treeQNode.setLeftChild(parseAnswer(answerNode));

					} else if (answerNode.getAttribute("answer").equals("N")) {
						treeQNode.setRightChild(parseAnswer(answerNode));

					}
				}
			}
		}
		return treeQNode;

	}

	/**
	 * Parse the element which contains an answer
	 * @param e element which contains an answer
	 * @return a BinaryTreeNode with the information
	 */
	public static BinaryTreeNode<String> parseAnswer(Element e) {
		if (e.getNodeName().equals("A") && e.hasChildNodes()) {

			NodeList answerList = e.getChildNodes();

			for (int i = 0; i < answerList.getLength(); i++) {
				if (answerList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element answerNode = (Element) answerList.item(i);
					if (answerNode.getNodeName().equals("Q")) {
						return parseQuestion(answerNode);
					} else if (answerNode.getNodeName().equals("dorm")) {
						BinaryTreeNode<String> answerTNode = new DefaultBinaryTreeNode<String>(
								answerNode.getAttribute("guess"));
						return answerTNode;
					}
				}

			}
		}
		return null;

	}
}
