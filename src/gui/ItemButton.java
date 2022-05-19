package gui;

import java.awt.Font;
import javax.swing.JButton;

import main.Item;

/**
 * A template for creating a new item button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class ItemButton extends JButton {

	/**
	 * Create a new item button.
	 * @param item the given item
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public ItemButton(Item item, int xPos, int yPos) {
		super(String.valueOf(item.getName()));
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 90);
	}
	
}
