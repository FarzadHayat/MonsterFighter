package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import main.*;
import monsters.Raka;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Displays the starting monster screen in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class StartingMonsterScreen extends Screen {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private MonsterInventory allMonsters = game.getAllMonsters();
	private Player player = game.getPlayer();
	
	private Monster selected;
	private MonsterButton selectedButton;

	/**
	 * Create a new StartingMonsterScreen object.
	 */
	public StartingMonsterScreen() {
		super();
		
		JLabel titleLabel = new JLabel("Pick a starting monster");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(202, 35, 376, 50);
		add(titleLabel);
				
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setBounds(410, 113, 366, 428);
		add(statsPanel);
		
		JLabel lblStats = new JLabel("Stats");
		lblStats.setBounds(76, 11, 151, 40);
		statsPanel.add(lblStats);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		String test = "<html><u>Stats</u></html>";
		lblStats.setText(test);
		
		JLabel lblHealth = new JLabel("Health :");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealth.setBounds(10, 76, 121, 40);
		statsPanel.add(lblHealth);
		
		JLabel lblDamage = new JLabel("Damage :");
		lblDamage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamage.setBounds(10, 137, 121, 40);
		statsPanel.add(lblDamage);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevel.setBounds(10, 202, 121, 40);
		statsPanel.add(lblLevel);
		
		JLabel lblHealAmount = new JLabel("Heal amount :");
		lblHealAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealAmount.setBounds(10, 268, 136, 40);
		statsPanel.add(lblHealAmount);
		
		JLabel lblCritRate = new JLabel("Critical rate :");
		lblCritRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritRate.setBounds(10, 329, 121, 40);
		statsPanel.add(lblCritRate);
		
		JLabel lblHealthValue = new JLabel("");
		lblHealthValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealthValue.setBounds(193, 72, 121, 40);
		statsPanel.add(lblHealthValue);
		
		JLabel lblDamageValue = new JLabel("");
		lblDamageValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamageValue.setBounds(193, 135, 121, 40);
		statsPanel.add(lblDamageValue);
		
		JLabel lblLevelValue = new JLabel("");
		lblLevelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevelValue.setBounds(193, 200, 121, 40);
		statsPanel.add(lblLevelValue);
		
		JLabel lblHealValue = new JLabel("");
		lblHealValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealValue.setBounds(193, 268, 121, 40);
		statsPanel.add(lblHealValue);
		
		JLabel lblCritValue = new JLabel("");
		lblCritValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritValue.setBounds(193, 329, 121, 40);
		statsPanel.add(lblCritValue);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected != null) {
					Monster monster = selected.clone();
					player.getMonsters().add(monster);
					if(monster instanceof Raka) {
		    			((Raka) monster).setTeam(player.getMonsters());
		    		}
					new HomeScreen();
					close();
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(237, 380, 119, 44);
		statsPanel.add(btnNext);
		
		MonstersPanel monstersPanel = new MonstersPanel(allMonsters, 20, 20, 2);
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(10, 113, 390, 428);
		add(monstersPanel);
		
		for (int i = 0; i < allMonsters.size(); i++) {
			Monster monster = allMonsters.get(i);
			MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selected = monster;
					if(selectedButton != null) {
						selectedButton.setBackground(null);
					}
					selectedButton = monsterButton ;
					selectedButton.setBackground(Color.lightGray);
					lblHealthValue.setText(""+ selected.getHealth());
					lblDamageValue.setText(""+ selected.getDamage());
					lblLevelValue.setText(""+ selected.getLevel());
					lblHealValue.setText(""+ selected.getHealAmount());
					lblCritValue.setText(""+ (int)(selected.getCritRate() * 100 )+ "%");
				}
			});
		}
	}

}