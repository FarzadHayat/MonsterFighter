package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import exceptions.NotFoundException;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Displays the selected battle in a new window.
 * @author Farzad and Daniel
 */
public class BattleScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private Battle battle;
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
	 * Closes the window.
	 */
	public void closeWindow() {
		window.dispose();
	}
	
	
	/**
	 * Call the gui to close this screen.
	 */
	public void finishedWindow() {
		gui.closeBattleScreen(this);
	}


	/**
	 * Create a new BattleScreen object.
	 * @param gui the given gui
	 * @param battle the given battle
	 */
	public BattleScreen(GraphicalUserInterface gui, Battle battle) {
		this.gui = gui;
		this.battle = battle;
		gui.getGame();
		monsters = battle.getMonsters();
		initialize();
		window.setVisible(true);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Battle");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel(battle.getName().toUpperCase());
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		BackButton backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattlesScreen();
				finishedWindow();
			}
		});
		window.getContentPane().add(backButton);
		
		JPanel monstersPanel = new JPanel();
		monstersPanel.setBounds(20, 105, 360, 440);
		window.getContentPane().add(monstersPanel);
		monstersPanel.setLayout(null);
		
		int yPos = 20;
		for (Monster monster : monsters) {
			MonsterButton monsterButton = new MonsterButton(monster, 100, yPos);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedMonster = monster;
					updateStatsPanel(monsterButton);
				}
			});
			monstersPanel.add(monsterButton);
			yPos += 100;
		}
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBounds(405, 105, 360, 440);
		window.getContentPane().add(statsPanel);
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
					gui.launchFightScreen(battle);
					finishedWindow();
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
