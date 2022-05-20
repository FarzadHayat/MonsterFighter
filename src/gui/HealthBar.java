package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JProgressBar;

import main.Monster;

/**
 * A template for creating a new health progress bar.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class HealthBar extends JProgressBar {

	/**
	 * Create a new health bar.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public HealthBar (Monster monster, int xPos, int yPos) {
		super();
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.RED);
		setStringPainted(true);
		setBounds(xPos, yPos, 160, 30);
		setMaximum(monster.getMaxHealth());
		setValue(monster.getHealth());
		setString(monster.getHealth() + " / " + monster.getMaxHealth());
	}
	
}
