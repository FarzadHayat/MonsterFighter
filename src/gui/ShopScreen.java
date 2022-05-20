package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;

import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 * Displays the shop screen in a new window.
 * @author Farzad and Daniel
 */
public class ShopScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	
	private MonsterInventory shopMonsters;
	private ItemInventory shopItems;
	
	private JButton selectedShop;
	private int selected = 0; //0 representing monster shop, 1 representing item shop
	
	private JButton selectedMonsterButton;
	private JButton selectedItemButton;
	
	private Monster selectedMonster;
	private Item selectedItem;
	
	
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
		gui.closeShopScreen(this);
	}


	/**
	 * Create a new ShopScreen object.
	 * @param gui the given gui
	 */
	public ShopScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		shopMonsters = game.getShop().getMonsters();
		shopItems = game.getShop().getItems();
		initialize();
		window.setVisible(true);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Shop");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("SHOP");
		titleLabel.setBounds(250, 20, 300, 50);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		window.getContentPane().add(titleLabel);
		
		BackButton backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchHomeScreen();
				finishedWindow();
			}
		});
		window.getContentPane().add(backButton);
		
		MonstersPanel monstersPanel = new MonstersPanel(shopMonsters, 10, 60, 2);
		monstersPanel.setBounds(10, 182, 766, 320);
		window.getContentPane().add(monstersPanel);
		monstersPanel.setLayout(null);
		
		JLabel lblStats = new JLabel("<html><u>Stats</u></html>");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStats.setBounds(513, 0, 151, 40);
		monstersPanel.add(lblStats);
		
		JLabel lblHealth = new JLabel("Health :");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealth.setBounds(453, 37, 121, 40);
		monstersPanel.add(lblHealth);
		
		JLabel lblHealthValue = new JLabel("");
		lblHealthValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealthValue.setBounds(607, 37, 121, 40);
		monstersPanel.add(lblHealthValue);
		
		JLabel lblDamage = new JLabel("Damage :");
		lblDamage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamage.setBounds(453, 88, 121, 40);
		monstersPanel.add(lblDamage);
		
		JLabel lblDamageValue = new JLabel("");
		lblDamageValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamageValue.setBounds(607, 83, 121, 40);
		monstersPanel.add(lblDamageValue);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevel.setBounds(453, 139, 121, 40);
		monstersPanel.add(lblLevel);
		
		JLabel lblHealAmount = new JLabel("Heal amount :");
		lblHealAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealAmount.setBounds(453, 184, 136, 40);
		monstersPanel.add(lblHealAmount);
		
		JLabel lblHealValue = new JLabel("");
		lblHealValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealValue.setBounds(607, 184, 121, 40);
		monstersPanel.add(lblHealValue);
		
		JLabel lblLevelValue = new JLabel("");
		lblLevelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevelValue.setBounds(607, 139, 121, 40);
		monstersPanel.add(lblLevelValue);
		
		JLabel lblCritRate = new JLabel("Critical rate :");
		lblCritRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritRate.setBounds(453, 236, 121, 40);
		monstersPanel.add(lblCritRate);
		
		JLabel lblCritValue = new JLabel("");
		lblCritValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritValue.setBounds(607, 236, 121, 40);
		monstersPanel.add(lblCritValue);
		
		JLabel lblCost = new JLabel("Cost :");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCost.setBounds(453, 281, 121, 40);
		monstersPanel.add(lblCost);
		
		JLabel lblCostValue = new JLabel("");
		lblCostValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostValue.setBounds(607, 281, 121, 40);
		monstersPanel.add(lblCostValue);
		
		JLabel lblBalance = new JLabel("Balance: ");
		lblBalance.setBounds(20, 78, 71, 40);
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		window.getContentPane().add(lblBalance);
		
		JLabel lblBalanceValue = new JLabel("");
		lblBalanceValue.setText("$"+player.getBalance());
		lblBalanceValue.setBounds(101, 78, 71, 40);
		lblBalanceValue.setFont(new Font("Tahoma", Font.PLAIN, 17));
		window.getContentPane().add(lblBalanceValue);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == 0) {
					if(selectedMonster != null) {
						int result = AlertBox.yesNo(String.format("Are you sure you want to buy\n%s for $%s?", selectedMonster.getName(), selectedMonster.getCost()));
						if(result == 0) {
							try {
								AlertBox.infoBox(selectedMonster.buy(), "Monster bought!");
								gui.launchShopScreen();
								finishedWindow();
							} catch (InvalidValueException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InsufficientFundsException | InventoryFullException e1) {
								AlertBox.infoBox(e1.getMessage(), "ERROR, Try again");
							}
						}
					}
				}
				else {
					if(selectedItem != null) {
						int result = AlertBox.yesNo(String.format("Are you sure you want to buy\n%s item for $%s?", selectedItem.getName(), selectedItem.getCost()));
						if(result == 0) {
							try {
								AlertBox.infoBox(selectedItem.buy(), "Item bought!");
								gui.launchShopScreen();
								finishedWindow();
							} catch (InvalidValueException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InsufficientFundsException | InventoryFullException e1) {
								AlertBox.infoBox(e1.getMessage(), "ERROR, Try again");
							}
						}
					}
				}
			}
		});
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBuy.setBounds(631, 508, 119, 44);
		window.getContentPane().add(btnBuy);
		
		ItemsPanel itemsPanel = new ItemsPanel(shopItems, 10, 60, 2);
		itemsPanel.setBounds(10, 182, 766, 320);
		window.getContentPane().add(itemsPanel);
		itemsPanel.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(452, 22, 134, 21);
		itemsPanel.add(lblDescription);
		
		JLabel lblCostItem = new JLabel("Cost:");
		lblCostItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostItem.setBounds(452, 110, 92, 21);
		itemsPanel.add(lblCostItem);
		
		JLabel lblCostItemValue = new JLabel("");
		lblCostItemValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostItemValue.setBounds(452, 138, 92, 21);
		itemsPanel.add(lblCostItemValue);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDescription.setBackground(null);
		txtDescription.setBounds(452, 50, 304, 56);
		itemsPanel.add(txtDescription);

		for (int i = 0; i < shopMonsters.size(); i++) {
			Monster monster = shopMonsters.get(i);
			MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
			monsterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedMonster = monster;
					if(selectedMonsterButton != null) {
						selectedMonsterButton.setBackground(null);
					}
					selectedMonsterButton = monsterButton;
					selectedMonsterButton.setBackground(Color.lightGray);
					lblHealthValue.setText(""+ selectedMonster.getHealth());
					lblDamageValue.setText(""+ selectedMonster.getDamage());
					lblLevelValue.setText(""+ selectedMonster.getLevel());
					lblHealValue.setText(""+ selectedMonster.getHealAmount());
					lblCritValue.setText(""+ (int)(selectedMonster.getCritRate() * 100 )+ "%");
					lblCostValue.setText("$"+selectedMonster.getCost());
				}
			});
		}
		
		for (int i = 0; i < shopItems.size(); i++) {
			Item item = shopItems.get(i);
			ItemButton itemButton = (ItemButton) itemsPanel.getComponent(i);
			itemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedItem = item;
					if(selectedItemButton != null) {
						selectedItemButton.setBackground(null);
					}
					selectedItemButton = itemButton;
					selectedItemButton.setBackground(Color.lightGray);
					txtDescription.setText(selectedItem.getDescription());
					lblCostItemValue.setText("$"+selectedItem.getCost());
				}
			});
		}
		
		JButton btnItemSelection = new JButton("Items");
		btnItemSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedShop != null) {
					selectedShop.setBackground(null);
				}
				selected = 1;
				selectedShop = btnItemSelection;
				selectedShop.setBackground(Color.lightGray);
				itemsPanel.setVisible(true);
				monstersPanel.setVisible(false);
			}
		});
		
		JButton btnMonsterSelection = new JButton("Monsters");
		selectedShop = btnMonsterSelection;
		selectedShop.setBackground(Color.lightGray);
		btnMonsterSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedShop != null) {
					selectedShop.setBackground(null);
				}
				selected = 0;
				selectedShop = btnMonsterSelection;
				selectedShop.setBackground(Color.lightGray);
				itemsPanel.setVisible(false);
				monstersPanel.setVisible(true);
			}
		});
		
		btnMonsterSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMonsterSelection.setBounds(20, 129, 128, 42);
		window.getContentPane().add(btnMonsterSelection);
		
		
		btnItemSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnItemSelection.setBounds(175, 129, 128, 42);
		window.getContentPane().add(btnItemSelection);
	}
	
}
