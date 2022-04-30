package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import main.*;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


public class StartingMonsterScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	private GameEnvironment game;
	private MonsterInventory allMonsters;
	private Player player;
	private Monster selected;
	
	
	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		gui.closeStartingMonsterScreen(this);
	}

	/**
	 * Create the application.
	 */
	public StartingMonsterScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		allMonsters = game.getAllMonsters();
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Starting Monster");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Pick a starting monster");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(202, 35, 376, 50);
		window.getContentPane().add(titleLabel);
		
		JPanel monstersPanel = new JPanel();
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(10, 113, 390, 428);
		window.getContentPane().add(monstersPanel);
				
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setBounds(410, 113, 366, 428);
		window.getContentPane().add(statsPanel);
		
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
					try {
						player.getMonsters().add(selected.clone());
						gui.launchHomeScreen();
						finishedWindow();
					} catch (InventoryFullException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(237, 380, 119, 44);
		statsPanel.add(btnNext);
		
		int yPos = 20;
		int xPos = 10;
		int numButton = 1;
		for(Monster monster: allMonsters.getList()) {
			JButton monsterButton = new JButton(monster.getName());
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selected = monster;
					lblHealthValue.setText(""+ selected.getHealth());
					lblDamageValue.setText(""+ selected.getDamage());
					lblLevelValue.setText(""+ selected.getLevel());
					lblHealValue.setText(""+ selected.getHealAmount());
					lblCritValue.setText(""+ (int)(selected.getCritRate() * 100 )+ "%");
				}
			});
			monsterButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			monsterButton.setBounds(xPos, yPos, 160, 90);
			monstersPanel.add(monsterButton);
			numButton += 1;
			yPos += 140;
			if(numButton == 4) {
				yPos = 20;
				xPos += 200;
			}
		}
		
	}
	
	public static void main(String[]args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.getGame().setupGame();
		new StartingMonsterScreen(gui);
	}
}