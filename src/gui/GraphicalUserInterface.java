package gui;

import javax.swing.JFrame;
import main.*;

/**
 * This class launches the GUI game and manages the screens.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class GraphicalUserInterface extends JFrame {

	/**
	 * Fields
	 */
	private static GraphicalUserInterface instance = null;
	private GameEnvironment game = GameEnvironment.getInstance();
	
	
	/**
	 * Constructors
	 */
	private GraphicalUserInterface() {
		setTitle("MonsterFighter");
		setResizable(false);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
	}
	
	
	/**
	 * Methods
	 */
	
	/**
	 * Quits the game
	 */
	public void quit() {
		dispose();
	}
	
	
	/**
     * @return the singleton instance of GraphicalUserInterface
     */
    public static GraphicalUserInterface getInstance() {
    	if (instance == null) {
    		instance = new GraphicalUserInterface();
    	}
    	return instance;
    }
	
	
	/**
	 * Run the GUI game.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new StartScreen();
	}

	
	/**
	 * Goes to sleep.
	 * If the game is over, then shows game over alert and then the stats screen.
	 * If the game is not over, then shows the overnight events alert.
	 */
	public void launchSleepAlert() {
		// sleep and show overnight commentary
		String events = game.sleep();
		if (game.getIsFinished()) {
			new StatsScreen();
			AlertBox.infoBox("GAME OVER!", "Game over");
		}
		else {
			new HomeScreen();
			AlertBox.infoBox(events.replaceFirst("(?:\n)+", ""), "Good morning!");
		}
	}

}
