package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

import main.Item;

@SuppressWarnings("serial")
public class ItemButton extends JButton {

	public ItemButton(Item item, int xPos, int yPos) {
		super(String.valueOf(item.getName()));
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(xPos, yPos, 160, 90);
	}
	
}
