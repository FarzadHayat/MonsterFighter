package gui;

import java.awt.Font;
import javax.swing.JButton;

import main.Monster;

/**
 * A template for creating a new monster button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterButton extends JButton {

	/**
	 * Create a new monster button.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public MonsterButton(Monster monster, int xPos, int yPos) {
		super(String.valueOf(monster.getName()));
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 90);
	}
	
}
