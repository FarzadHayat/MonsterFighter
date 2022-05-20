package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Monster;

@SuppressWarnings("serial")
public class SpriteLabel extends JLabel {

	public SpriteLabel(Monster monster, int xPos, int yPos) {
		super();
		ImageIcon imageIcon = monster.getSprite();
		Image image = imageIcon.getImage();
		Image resizedImage = image.getScaledInstance(90, 90,
				java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(resizedImage);
		setIcon(imageIcon);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setBounds(xPos, yPos, 160, 90);
	}
	
}
