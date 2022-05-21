package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Displays the home screen in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class HomeScreen extends Screen {

	/**
	 * Fields
	 */
	private GraphicalUserInterface gui = GraphicalUserInterface.getInstance();
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player = game.getPlayer();
	private MonsterInventory monsters = player.getMonsters();
	private ItemInventory items = player.getItems();
	

	/**
	 * Create a new HomeScreen object.
	 */
	public HomeScreen() {
		super();
		
		JLabel titleLabel = new JLabel("HOME");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		add(titleLabel);
		
		BalanceLabel balanceLabel = new BalanceLabel(180, 25);
		add(balanceLabel);
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBounds(300, 100, 200, 340);
		add(generalPanel);
		generalPanel.setLayout(null);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShopScreen();
				close();
			}
		});
		shopButton.setBounds(30, 40, 140, 50);
		shopButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(shopButton);
		
		JButton battleButton = new JButton("Battles");
		battleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BattlesScreen();
				close();
			}
		});
		battleButton.setBounds(30, 120, 140, 50);
		battleButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(battleButton);
		
		JButton statsButton = new JButton("Stats");
		statsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StatsScreen();
				close();
			}
		});
		statsButton.setBounds(30, 200, 140, 50);
		statsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generalPanel.add(statsButton);

		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();			
				gui.launchSleepAlert();
			}
		});
		sleepButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sleepButton.setBounds(30, 280, 140, 50);
		generalPanel.add(sleepButton);

		MonstersPanel monstersPanel = new MonstersPanel(monsters, 0, 0, 1);
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(0, 10, 160, 540);
		add(monstersPanel);

		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MonsterScreen(monster);
					close();
				}
			});
		}
		
		ItemsPanel itemsPanel = new ItemsPanel(items, 0, 0, 1);
		itemsPanel.setBounds(626, 10, 160, 540);
		add(itemsPanel);
		itemsPanel.setLayout(null);
		
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ItemButton itemButton = (ItemButton) itemsPanel.getComponent(i);
			itemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ItemScreen(item);
					close();
				}
			});
		}
	}
}