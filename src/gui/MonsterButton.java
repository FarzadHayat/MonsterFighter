package gui;

import java.awt.Font;
import javax.swing.JButton;

import main.Monster;

@SuppressWarnings("serial")
public class MonsterButton extends JButton {

	public MonsterButton(Monster monster, int xPos, int yPos) {
		super(String.valueOf(monster.getName()));
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 90);
	}
	
}
