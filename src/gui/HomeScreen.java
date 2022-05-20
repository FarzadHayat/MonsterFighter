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

/**
 * Displays the home screen in a new window.
 * @author Farzad and Daniel
 */
public class HomeScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player;
	private MonsterInventory monsters;
	private ItemInventory items;
	
	
	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		window.dispose();
	}
	
	
	/**
	 * call the gui to close this screen.
	 */
	public void finishedWindow() {
		gui.closeHomeScreen(this);
	}


	/**
	 * Create a new HomeScreen object.
	 * @param gui the given gui
	 */
	public HomeScreen(GraphicalUserInterface gui) {
		this.gui = gui;
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
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("HOME");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		BalanceLabel balanceLabel = new BalanceLabel(180, 25);
		window.getContentPane().add(balanceLabel);
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBounds(300, 100, 200, 340);
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

		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchSleepAlert();
				if (!game.getIsFinished()) {
					gui.launchHomeScreen();
				}
				finishedWindow();				
			}
		});
		sleepButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sleepButton.setBounds(30, 280, 140, 50);
		generalPanel.add(sleepButton);

		MonstersPanel monstersPanel = new MonstersPanel(monsters, 0, 0, 1);
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(0, 10, 160, 540);
		window.getContentPane().add(monstersPanel);

		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gui.launchMonsterScreen(monster);
					finishedWindow();
				}
			});
		}
		
		ItemsPanel itemsPanel = new ItemsPanel(items, 0, 0, 1);
		itemsPanel.setBounds(626, 10, 160, 540);
		window.getContentPane().add(itemsPanel);
		itemsPanel.setLayout(null);
		
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ItemButton itemButton = (ItemButton) itemsPanel.getComponent(i);
			itemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gui.launchItemScreen(item);
					finishedWindow();
				}
			});
		}
	}
}