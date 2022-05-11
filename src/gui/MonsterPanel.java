package gui;

import javax.swing.JPanel;

import main.Monster;

@SuppressWarnings("serial")
public class MonsterPanel extends JPanel {

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
