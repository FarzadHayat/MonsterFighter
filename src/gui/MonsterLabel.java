package gui;

import java.awt.Font;
import javax.swing.JLabel;

import main.Monster;

/**
 * A template for creating a new monster button.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterLabel extends JLabel {

	/**
	 * Create a new monster button.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public MonsterLabel(Monster monster, int xPos, int yPos) {
		super(String.valueOf(monster.getName()), JLabel.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 30);
	}
	
}
