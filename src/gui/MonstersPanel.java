package gui;

import javax.swing.JPanel;

import main.Monster;
import main.MonsterInventory;

/**
 * A template for creating a new panel of monsters.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonstersPanel extends JPanel {

	/**
	 * Fields
	 */
	private int verticalPadding = 20;
	private int horizontalPadding = 20;
	
	/**
	 * Create a new monsters panel.
	 * @param monsters the given monsters
	 * @param xStart the starting x position
	 * @param yStart the starting y position
	 * @param numColumns the number of columns in the panel
	 */
	public MonstersPanel(MonsterInventory monsters, int xStart, int yStart, int numColumns) {
		super();
		setAlignmentY(CENTER_ALIGNMENT);
		int xPos = xStart;
		int yPos = yStart;
		int numButton = 1;
		for(int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			MonsterButton monsterButton = new MonsterButton(monster, xPos, yPos);
			add(monsterButton);
			if(numButton % numColumns == 0) {
				xPos = xStart;
				yPos += monsterButton.getHeight() + verticalPadding;
			}
			else {				
				xPos += monsterButton.getWidth() + horizontalPadding;
			}
			numButton += 1;
		}
	}

}
