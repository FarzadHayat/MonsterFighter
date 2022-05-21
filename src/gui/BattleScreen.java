package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.NotFoundException;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Displays the selected battle in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class BattleScreen extends Screen {

	/**
	 * Fields
	 */	
	private MonsterInventory monsters;
	
	private JButton selectedButton;
	private Monster selectedMonster;
	
	private JPanel labelsPanel;
	private JPanel valuesPanel;
	
	private JLabel healthLabelValue;
	private JLabel damageLabelValue;
	private JLabel levelLabelValue;
	private JLabel healAmountLabelValue;
	private JLabel critRateLabelValue;
	

	/**
	 * Create a new BattleScreen object.
	 * @param battle the given battle
	 */
	public BattleScreen(Battle battle) {
		super();
		monsters = battle.getMonsters();
		
		JLabel titleLabel = new JLabel(battle.getName().toUpperCase());
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		add(titleLabel);
		
		BackButton backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BattlesScreen();
				close();
			}
		});
		add(backButton);
		
		MonstersPanel monstersPanel = new MonstersPanel(monsters, 10, 20, 2);
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(20, 150, 360, 360);
		add(monstersPanel);
		
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedMonster = monster;
					updateStatsPanel(monsterButton);
				}
			});
		}
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBounds(405, 105, 360, 440);
		add(statsPanel);
		statsPanel.setLayout(null);
		
		JLabel statsLabel = new JLabel("<html><u>Monster Stats</u></html>");
		statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		statsLabel.setBounds(80, 10, 200, 40);
		statsPanel.add(statsLabel);

		labelsPanel = new JPanel();
		labelsPanel.setBounds(20, 60, 140, 300);
		statsPanel.add(labelsPanel);
		labelsPanel.setVisible(false);
		labelsPanel.setLayout(null);
		
		JLabel healthLabel = new JLabel("Health:");
		healthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		healthLabel.setBounds(0, 10, 140, 40);
		labelsPanel.add(healthLabel);
		
		JLabel damageLabel = new JLabel("Damage:");
		damageLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		damageLabel.setBounds(0, 70, 140, 40);
		labelsPanel.add(damageLabel);
		
		JLabel levelLabel = new JLabel("Level:");
		levelLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		levelLabel.setBounds(0, 130, 140, 40);
		labelsPanel.add(levelLabel);
		
		JLabel healAmountLabel = new JLabel("Heal amount:");
		healAmountLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		healAmountLabel.setBounds(0, 190, 140, 40);
		labelsPanel.add(healAmountLabel);
		
		JLabel critRateLabel = new JLabel("Critical rate:");
		critRateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		critRateLabel.setBounds(0, 250, 140, 40);
		labelsPanel.add(critRateLabel);
		
		valuesPanel = new JPanel();
		valuesPanel.setBounds(180, 60, 160, 300);
		statsPanel.add(valuesPanel);
		valuesPanel.setLayout(null);
		
		healthLabelValue = new JLabel("");
		healthLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		healthLabelValue.setBounds(0, 10, 160, 40);
		valuesPanel.add(healthLabelValue);
		
		damageLabelValue = new JLabel("");
		damageLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		damageLabelValue.setBounds(0, 70, 160, 40);
		valuesPanel.add(damageLabelValue);
		
		levelLabelValue = new JLabel("");
		levelLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		levelLabelValue.setBounds(0, 130, 160, 40);
		valuesPanel.add(levelLabelValue);
		
		healAmountLabelValue = new JLabel("");
		healAmountLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		healAmountLabelValue.setBounds(0, 190, 160, 40);
		valuesPanel.add(healAmountLabelValue);
		
		critRateLabelValue = new JLabel("");
		critRateLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		critRateLabelValue.setBounds(0, 250, 160, 40);
		valuesPanel.add(critRateLabelValue);
		
		JButton nextButton = new JButton("Fight");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent key) {
				try {
					battle.setup();
					new FightScreen(battle);
					close();
				}
				catch (NotFoundException e) {					
					AlertBox.infoBox(e.getMessage(), "Team empty");
				}
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nextButton.setBounds(220, 380, 120, 40);
		statsPanel.add(nextButton);
	}
	
	
	/**
	 * Update the stats panel and highlight the selected button.
	 * @param button the given button
	 */
	public void updateStatsPanel(JButton button) {
		if(selectedButton != null) {
			selectedButton.setBackground(null);
		}
		
		selectedButton = button;
		selectedButton.setBackground(Color.lightGray);
		if (!labelsPanel.isVisible()) {
			labelsPanel.setVisible(true);
		}
		
		healthLabelValue.setText(String.valueOf(selectedMonster.getHealth()));
		damageLabelValue.setText(String.valueOf(selectedMonster.getDamage()));
		levelLabelValue.setText(String.valueOf(selectedMonster.getLevel()));
		healAmountLabelValue.setText(String.valueOf(selectedMonster.getHealAmount()));
		critRateLabelValue.setText(String.valueOf((int)(selectedMonster.getCritRate() * 100 )+ "%"));
	}
	
}
