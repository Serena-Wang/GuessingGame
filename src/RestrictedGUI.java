/**
 * This class is the controller of the game.
 * @author Serena Wang
 */
import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import datastructure.*;

public class RestrictedGUI extends JPanel implements ActionListener {
	protected JButton yes;
	protected JButton no;
	protected JButton restart;
	protected JButton begin;
	protected JPanel beginPanel;
	protected JLabel questionLabel;
	private JLabel tPicLabel;
	protected static BinaryTree<String> gameTree;
	protected BinaryTreeNode<String> currentNode;
	protected boolean endGame = false;
	protected File sound;

	/**
	 * Construct a GUI for restricted game
	 * @param filename xml file
	 */
	public RestrictedGUI(String filename) {
		super(new BorderLayout());
		gameTree = FileReader.readFromFile(filename);
		setupStartPage();
		currentNode = gameTree.getRoot();
		sound = new File("resources/sound1.WAV");
	}

	/**
	 * Create the start page.
	 */
	private void setupStartPage(){
		begin = new JButton("Begin the game");
		begin.addActionListener(this);
		beginPanel = new JPanel(new GridLayout(3,1));
		JLabel itemList = new JLabel("<html>Pick one from below:<br> 1837, Abbey, Buckland, Creighton, Ham,<br> Macgregor, Mandelts, Mead, Pearsons, Porter,<br> Rockies, Stafford, Torrey, Wilder, Prospect, Brigham, Dickinson</html>");
		beginPanel.add(itemList);
		ImageIcon beginImage = new ImageIcon("resources/mhc.jpg");
		JLabel bPicLabel = new JLabel("", beginImage, JLabel.CENTER);
		beginPanel.add(bPicLabel);
		beginPanel.add(begin);
		this.add(beginPanel);
	}
	
	/**
	 * Display the questions, buttons and the picture.
	 * @param question current question
	 */
	public void setupQuestion(String question) {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		yes = new JButton("YES");
		no = new JButton("NO");
		restart = new JButton("Restart the Game");
		questionLabel = new JLabel(question);
		yes.addActionListener(this);
		no.addActionListener(this);
		restart.addActionListener(this);
		buttonPanel.add(yes);
		buttonPanel.add(no);
		buttonPanel.add(restart);
		ImageIcon thinking = new ImageIcon("resources/thinking.jpg");
		tPicLabel = new JLabel("", thinking, JLabel.CENTER);
		this.add(tPicLabel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(questionLabel, BorderLayout.NORTH);

	}

	/**
	 * Get the new question.
	 * @param node node which holds the question String
	 * @return question String
	 */
	public String updateQuestion(BinaryTreeNode<String> node) {
		return node.getData();
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
		endGame = false;
	}
	
	/**
	 * Move on to start to ask questions.
	 */
	private void startGuess(){
		this.removeAll();
		this.revalidate();
		this.repaint();
		setupQuestion(updateQuestion(gameTree.getRoot()));
		currentNode = gameTree.getRoot();
		endGame = false;
		
	}

	/**
	 * Display the picture when the make a right guess. 
	 */
	public void displayHaHaPic() {
		ImageIcon haImage = new ImageIcon("resources/haha.jpg");
		JLabel hPicLabel = new JLabel("", haImage, JLabel.CENTER);
		this.add(hPicLabel, BorderLayout.CENTER);
	}

	/**
	 * Display the picture when the make a wrong guess.
	 */
	private void displaySadPic() {
		ImageIcon haImage = new ImageIcon("resources/sad.jpg");
		JLabel hPicLabel = new JLabel("", haImage, JLabel.CENTER);
		this.add(hPicLabel, BorderLayout.CENTER);
	}

	/**
	 * Remove the thinking picture. 
	 */
	public void removeThinkingPic() {
		this.remove(tPicLabel);
	}

	/**
	 * Play the sound.
	 * @param Sound sound file
	 */
	public static void playSound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (Exception e) {

		}

	}

	/**
	 * Do something when the button is clicked
	 * @param event 
	 */
	public void actionPerformed(ActionEvent event) {
		if (!endGame) {
			if (event.getSource() == yes) {
				playSound(sound);
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

			} else if (event.getSource() == no) {
				playSound(sound);
				if (currentNode.getRightChild() != null) {
					currentNode = currentNode.getRightChild();
					String nextQ = updateQuestion(currentNode);
					questionLabel.setText(nextQ);
				} else {
					questionLabel.setText("OH NO :(");
					removeThinkingPic();
					displaySadPic();
					endGame = true;
				}
			}
		}
		if (event.getSource() == restart) {
			playSound(sound);
			restart();
		}
		
		if (event.getSource() == begin){
			playSound(sound);
			startGuess();
			
		}

	}
}
