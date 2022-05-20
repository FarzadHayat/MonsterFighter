package gui;

import java.awt.Font;
import javax.swing.JButton;

/**
 * A template for creating a new back button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class BackButton extends JButton {

	/**
	 * Fields
	 */
	private int xPos = 650;
	private int yPos = 30;
	
	/**
	 * Create a new back button using xPos and yPos.
	 */
	public BackButton() {
		super("Back");
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 100, 30);
	}
	
}
