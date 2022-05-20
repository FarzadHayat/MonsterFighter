package gui;

import javax.swing.JButton;
import main.Monster;

/**
 * A template for creating a new monster panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterButton extends JButton {

	private HealthBar healthBar;
	private SpriteLabel spriteLabel;
	private MonsterLabel monsterLabel;
	
	/**
	 * Create a new monster panel.
	 * The panel consists of a monster button and health bar.
	 * @param monster the given monster
	 * @param xPos the given x position
	 * @param yPos the given y position
	 */
	public MonsterButton(Monster monster, int xPos, int yPos) {
		super();
		setBounds(xPos, yPos, 160, 150);
		setLayout(null);
		
		healthBar = new HealthBar(monster, 0, 120);
		add(healthBar);
		
		spriteLabel = new SpriteLabel(monster, 0, 30);
		add(spriteLabel);
		
		monsterLabel = new MonsterLabel(monster, 0, 0);
		add(monsterLabel);
	}
	
}
