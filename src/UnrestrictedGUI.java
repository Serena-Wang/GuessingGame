/**
 * This is the controller for the unrestricted game. 
 * @author Serena Wang 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datastructure.BinaryTreeNode;
import datastructure.DefaultBinaryTreeNode;

public class UnrestrictedGUI extends RestrictedGUI {
	private JTextField textF1, textF2;
	private boolean addMode = false;

	/**
	 * Construct a GUI for unrestricted game
	 * 
	 * @param filename
	 *            xml file
	 */
	public UnrestrictedGUI(String filename) {
		super(filename);
		setupStartPage();
	}

	/**
	 * Create the start page.
	 */
	private void setupStartPage() {
		begin = new JButton("Begin the game");
		begin.addActionListener(this);
		beginPanel = new JPanel(new GridLayout(3, 1));
		JLabel itemList = new JLabel("Pick a dorm in Moho.");
		beginPanel.add(itemList);
		ImageIcon beginImage = new ImageIcon("resources/mhc.jpg");
		JLabel bPicLabel = new JLabel("", beginImage, JLabel.CENTER);
		beginPanel.add(bPicLabel);
		beginPanel.add(begin);
		this.add(beginPanel);
	}

	/**
	 * Create text field when the game makes a wrong guess.
	 */
	private void popTextField() {
		JPanel textFPanel = new JPanel(new GridLayout(6, 1));
		JLabel tFQ1 = new JLabel("What's on your mind? Put it here!");
		textF1 = new JTextField();
		JLabel tFQ2 = new JLabel("Giving me a yes/no question. Put it here!");
		textF2 = new JTextField();
		questionLabel.setText("Is the answer to your question yes or no?");
		textFPanel.add(tFQ1);
		textFPanel.add(textF1);
		textFPanel.add(tFQ2);
		textFPanel.add(textF2);
		textFPanel.add(questionLabel);
		this.add(textFPanel);
	}

	/**
	 * Get the answer string
	 * @return answer string
	 */
	private String getNewAnswer() {
		return textF1.getText();
	}

	/**
	 * Get the question string
	 * @return question string
	 */
	private String getNewQuestion() {
		return textF2.getText();
	}

	
	/**
	 * Restart the game.
	 */
	private void restart() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		setupStartPage();
		currentNode = gameTree.getRoot();
		addMode = false;
		endGame = false;
	}

	/**
	 * Move on to start to ask questions.
	 */
	private void startGuess() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		setupQuestion(updateQuestion(gameTree.getRoot()));
		currentNode = gameTree.getRoot();
		endGame = false;
		addMode = false;

	}


	/**
	 * Do something when the button is clicked
	 * @param event 
	 */
	public void actionPerformed(ActionEvent event) {
		if (!endGame) {
			if (event.getSource() == yes) {
				playSound(sound);
				// add the new item to the tree
				if (addMode) {
					if (getNewAnswer().equals("")
							|| getNewQuestion().equals("")) {
						questionLabel
								.setText("Please give me both the question and the answer");
					} else {
						addMode = false;
						BinaryTreeNode<String> newAnswer = new DefaultBinaryTreeNode<String>(
								getNewAnswer());
						String oldAnswer = currentNode.getData();
						BinaryTreeNode<String> oldANode = new DefaultBinaryTreeNode<String>(
								oldAnswer);
						currentNode.setData(getNewQuestion());
						currentNode.setLeftChild(newAnswer);
						currentNode.setRightChild(oldANode);
						questionLabel.setText("Successfully added new item");
						endGame = true;
					}

				} else {
					if (currentNode.getLeftChild() != null) {
						currentNode = currentNode.getLeftChild();
						String nextQ = updateQuestion(currentNode);
						questionLabel.setText(nextQ);
					} else {
						questionLabel.setText("HAHA I'M RIGHT!");
						removeThinkingPic();
						displayHaHaPic();
						endGame = true;

					}
				}

			} else if (event.getSource() == no) {
				playSound(sound);
				// add the new item to the tree
				if (addMode) {

					if (getNewAnswer().equals("")
							|| getNewQuestion().equals("")) {
						questionLabel
								.setText("Please give me both the question and the answer");
					} else {
						addMode = false;
						BinaryTreeNode<String> newAnswer = new DefaultBinaryTreeNode<String>(
								getNewAnswer());
						String oldAnswer = currentNode.getData();
						BinaryTreeNode<String> oldANode = new DefaultBinaryTreeNode<String>(
								oldAnswer);
						currentNode.setData(getNewQuestion());
						currentNode.setRightChild(newAnswer);
						currentNode.setLeftChild(oldANode);
						questionLabel.setText("Successfully added new item");
						endGame = true;
					}

				} else {
					if (currentNode.getRightChild() != null) {
						currentNode = currentNode.getRightChild();
						String nextQ = updateQuestion(currentNode);
						questionLabel.setText(nextQ);
					} else {
						popTextField();
						addMode = true;
					}
				}
			}
		}
		if (event.getSource() == restart) {
			playSound(sound);
			restart();
		}

		if (event.getSource() == begin) {
			playSound(sound);
			startGuess();

		}
	}
}
