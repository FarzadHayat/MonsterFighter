package gui;

import javax.swing.JButton;

import main.Item;
import main.Purchasable;

/**
 * A template for creating a new item button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class ItemButton extends JButton {

	private SpriteLabel spriteLabel;
	protected PurchasableLabel purchasableLabel;
	
	/**
	 * Create a new item button.
	 * @param item the given item
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public ItemButton(Item item, int xPos, int yPos) {
		super();
		setBounds(xPos, yPos, 160, 120);
		setLayout(null);
		
		purchasableLabel = new PurchasableLabel((Purchasable) item, 0, 0);
		add(purchasableLabel);
		
		spriteLabel = new SpriteLabel((Purchasable) item, 0, 30);
		add(spriteLabel);
	}
	
}
