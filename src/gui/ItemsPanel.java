package gui;

import javax.swing.JPanel;

import main.Item;
import main.ItemInventory;

/**
 * A template for creating a new panel of items.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class ItemsPanel extends JPanel {

	private int verticalPadding = 20;
	private int horizontalPadding = 20;
	
	/**
	 * Create a new items panel.
	 * @param items the given items
	 * @param actionListener the given actionListener
	 */
	public ItemsPanel(ItemInventory items, int xStart, int yStart, int numColumns) {
		super();
		setAlignmentY(CENTER_ALIGNMENT);
		int xPos = xStart;
		int yPos = yStart;
		int numButton = 1;
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ItemButton itemButton = new ItemButton(item, xPos, yPos);
			add(itemButton);
			if(numButton % numColumns == 0) {
				xPos = xStart;
				yPos += itemButton.getHeight() + verticalPadding;
			}
			else {				
				xPos += itemButton.getWidth() + horizontalPadding;
			}
			numButton += 1;
		}
	}

}
