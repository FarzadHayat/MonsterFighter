package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JProgressBar;

import main.Monster;

@SuppressWarnings("serial")
public class HealthBar extends JProgressBar {

	public HealthBar (Monster monster, int xPos, int yPos) {
		super();
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.RED);
		setStringPainted(true);
		setBounds(xPos, yPos, 160, 30);
		setMaximum(monster.getMaxHealth());
		setValue(monster.getHealth());
		
	}
	
}
