package gui;

import java.awt.Font;
import javax.swing.JLabel;

import main.Purchasable;

/**
 * A template for creating a new Purchasable label.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class PurchasableLabel extends JLabel {

	/**
	 * Create a new purchasable label.
	 * @param purchasable the given purchasable
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public PurchasableLabel(Purchasable purchasable, int xPos, int yPos) {
		super(String.valueOf(purchasable.getName()), JLabel.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 30);
	}
	
}
