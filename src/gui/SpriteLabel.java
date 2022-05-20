package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Purchasable;

/**
 * A template for creating a new sprite label containing an image icon.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class SpriteLabel extends JLabel {

	/**
	 * Create a new sprite label.
	 * @param purchasable the given purchasable
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public SpriteLabel(Purchasable purchasable, int xPos, int yPos) {
		super();
		ImageIcon imageIcon = purchasable.getSprite();
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
