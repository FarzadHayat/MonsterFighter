package gui;

import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BackButton extends JButton {

	public BackButton() {
		super("Back");
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(650, 30, 100, 30);
	}
	
}
