/**
 * This class is responsible for displaying the unrestricted game. 
 * @author Serena Wang
 */
import javax.swing.JFrame;

public class UnrestrictedGuessingGame {
	/**
	 * Read the file and begin the game
	 * 
	 * @param args
	 *            file name
	 */
	public static void main(String[] args) {
		new UnrestrictedGuessingGame(args[0]);
	}

	/**
	 * Construct the setup and call the game controller
	 * @param filename
	 */
	public UnrestrictedGuessingGame(String filename) {
		JFrame gameFrame = new JFrame("Unrestricted Guessing Game");
		gameFrame.setSize(500, 500);
		gameFrame.add(new UnrestrictedGUI(filename));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

	}

}
