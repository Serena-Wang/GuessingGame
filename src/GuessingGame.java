/**
 * This class is responsible for displaying the restricted game. 
 * @author Serena Wang
 */
import javax.swing.JFrame;

public class GuessingGame {
	/**
	 * Read the file and begin the game
	 * 
	 * @param args
	 *            file name
	 */
	public static void main(String[] args) {
		new GuessingGame(args[0]);
	}

	/**
	 * Construct the setup and call the game controller
	 * 
	 * @param filename
	 */
	public GuessingGame(String filename) {
		JFrame gameFrame = new JFrame("Guessing Game");
		gameFrame.setSize(500, 500);
		gameFrame.add(new RestrictedGUI(filename));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

	}

}
