package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class HomeScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	private MonsterInventory monsters;
	private ItemInventory items;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeHomeScreen(this);
	}

	/**
	 * Create the application.
	 */
	public HomeScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		monsters = player.getMonsters();
		items = player.getItems();
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Home");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("HOME");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchSleepAlert();
			}
		});
		sleepButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sleepButton.setBounds(650, 30, 100, 30);
		window.getContentPane().add(sleepButton);
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBounds(300, 100, 200, 300);
		window.getContentPane().add(generalPanel);
		generalPanel.setLayout(null);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchShopScreen();
				finishedWindow();
			}
		});
		shopButton.setBounds(30, 40, 140, 50);
		shopButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(shopButton);
		
		JButton battleButton = new JButton("Battles");
		battleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattlesScreen();
				finishedWindow();
			}
		});
		battleButton.setBounds(30, 120, 140, 50);
		battleButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(battleButton);
		
		JButton statsButton = new JButton("Stats");
		statsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchStatsScreen();
				finishedWindow();
			}
		});
		statsButton.setBounds(30, 200, 140, 50);
		statsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(statsButton);
		
		JPanel monstersPanel = new JPanel();
		monstersPanel.setBounds(0, 20, 180, 410);
		window.getContentPane().add(monstersPanel);
		monstersPanel.setLayout(null);
		
		int yPos = 10;
		for (Monster monster : monsters.getList()) {
			JButton monsterButton = new JButton(monster.getName());
			if (monster.getName().length() > 10) {				
				monsterButton.setText("<html><center>" + monster.getName().replaceFirst(" ", "<br>") + "</centre></html>");
			}
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gui.launchMonsterScreen(monster);
					finishedWindow();
				}
			});
			monsterButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			monsterButton.setBounds(10, yPos, 160, 90);
			monstersPanel.add(monsterButton);
			yPos += 100;
		}
		
		JPanel itemsPanel = new JPanel();
		itemsPanel.setBounds(45, 450, 690, 110);
		window.getContentPane().add(itemsPanel);
		itemsPanel.setLayout(null);
		
		int xPos = 10;
		for (Item item : items.getList()) {
			JButton itemButton = new JButton(item.getName());
			if (item.getName().length() > 10) {
				itemButton.setText("<html><center>" + item.getName().replaceFirst(" ", "<br>") + "</centre></html>");
			}
			itemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gui.launchItemScreen(item);
					finishedWindow();
				}
			});
			itemButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			itemButton.setBounds(xPos, 10, 160, 90);
			itemsPanel.add(itemButton);
			xPos += 170;
		}
	}
}