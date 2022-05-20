package gui;

import javax.swing.JButton;
import main.Monster;
import main.Purchasable;

/**
 * A template for creating a new monster button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterButton extends JButton {

	/**
	 * Fields
	 */
	private HealthBar healthBar;
	private SpriteLabel spriteLabel;
	private PurchasableLabel purchasableLabel;
	
	/**
	 * Create a new monster button.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public MonsterButton(Monster monster, int xPos, int yPos) {
		super();
		setBounds(xPos, yPos, 160, 120);
		setLayout(null);
		
		healthBar = new HealthBar(monster, 0, 90);
		add(healthBar);
		
		purchasableLabel = new PurchasableLabel((Purchasable) monster, 0, 0);
		add(purchasableLabel);
		
		spriteLabel = new SpriteLabel((Purchasable) monster, 0, 0);
		add(spriteLabel);
	}
	
}
