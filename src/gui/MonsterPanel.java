package gui;

import javax.swing.JPanel;

import main.Monster;

/**
 * A template for creating a new monster panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterPanel extends JPanel {

	/**
	 * Create a new monster panel.
	 * The panel consists of a monster button and health bar.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public MonsterPanel(Monster monster, int xPos, int yPos) {
		super();
		setBounds(xPos, yPos, 160, 120);
		setLayout(null);
		HealthBar healthBar = new HealthBar(monster, 0, 90);
		add(healthBar);
		MonsterButton monsterButton = new MonsterButton(monster, 0, 0);
		add(monsterButton);
	}
	
}
