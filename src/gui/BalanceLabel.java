package gui;

import java.awt.Font;
import javax.swing.JLabel;

import main.GameEnvironment;

/**
 * A template for creating a new balance label.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class BalanceLabel extends JLabel {
	
	/**
	 * Create a new balance label.
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public BalanceLabel(int xPos, int yPos) {
		super("Balance: $" + GameEnvironment.getInstance().getPlayer().getBalance());
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 40);
	}
	
}
