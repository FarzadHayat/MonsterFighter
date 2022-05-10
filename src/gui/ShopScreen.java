package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.NotFoundException;

import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class ShopScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	private Score score;
	
	private MonsterInventory shopMonsters;
	private ItemInventory shopItems;
	
	private JButton selectedShop;
	private int selected = 0; //0 representing monster shop, 1 representing item shop
	
	private JButton selectedMonsterButton;
	private JButton selectedItemButton;
	
	private Monster selectedMonster;
	private Item selectedItem;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeShopScreen(this);
	}

	/**
	 * Create the application.
	 */
	public ShopScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		score = game.getScoreSystem();
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
		
		JPanel monsterPanel = new JPanel();
		monsterPanel.setBounds(10, 182, 766, 319);
		window.getContentPane().add(monsterPanel);
		monsterPanel.setLayout(null);
		
		JLabel lblStats = new JLabel("<html><u>Stats</u></html>");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStats.setBounds(513, 0, 151, 40);
		monsterPanel.add(lblStats);
		
		JLabel lblHealth = new JLabel("Health :");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealth.setBounds(453, 37, 121, 40);
		monsterPanel.add(lblHealth);
		
		JLabel lblHealthValue = new JLabel("");
		lblHealthValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealthValue.setBounds(607, 37, 121, 40);
		monsterPanel.add(lblHealthValue);
		
		JLabel lblDamage = new JLabel("Damage :");
		lblDamage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamage.setBounds(453, 88, 121, 40);
		monsterPanel.add(lblDamage);
		
		JLabel lblDamageValue = new JLabel("");
		lblDamageValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDamageValue.setBounds(607, 83, 121, 40);
		monsterPanel.add(lblDamageValue);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevel.setBounds(453, 139, 121, 40);
		monsterPanel.add(lblLevel);
		
		JLabel lblHealAmount = new JLabel("Heal amount :");
		lblHealAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealAmount.setBounds(453, 184, 136, 40);
		monsterPanel.add(lblHealAmount);
		
		JLabel lblHealValue = new JLabel("");
		lblHealValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealValue.setBounds(607, 184, 121, 40);
		monsterPanel.add(lblHealValue);
		
		JLabel lblLevelValue = new JLabel("");
		lblLevelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevelValue.setBounds(607, 139, 121, 40);
		monsterPanel.add(lblLevelValue);
		
		JLabel lblCritRate = new JLabel("Critical rate :");
		lblCritRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritRate.setBounds(453, 236, 121, 40);
		monsterPanel.add(lblCritRate);
		
		JLabel lblCritValue = new JLabel("");
		lblCritValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritValue.setBounds(607, 236, 121, 40);
		monsterPanel.add(lblCritValue);
		
		JLabel lblCost = new JLabel("Cost :");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCost.setBounds(453, 281, 121, 40);
		monsterPanel.add(lblCost);
		
		JLabel lblCostValue = new JLabel("");
		lblCostValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostValue.setBounds(607, 281, 121, 40);
		monsterPanel.add(lblCostValue);
		
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
							} catch (NotFoundException| InvalidValueException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InsufficientFundsException e1) {
								AlertBox.infoBox("Insufficient funds in player's balance!", "ERROR, Try again");
							} catch (InventoryFullException e1) {
								AlertBox.infoBox("Inventory is full!", "ERROR, Try again");
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
							} catch (NotFoundException| InvalidValueException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InsufficientFundsException e1) {
								AlertBox.infoBox("Insufficient funds in player's balance!", "ERROR, Try again");
							} catch (InventoryFullException e1) {
								AlertBox.infoBox("Inventory is full!", "ERROR, Try again");
							}
						}
					}
				}
			}
		});
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBuy.setBounds(631, 508, 119, 44);
		window.getContentPane().add(btnBuy);
		
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(10, 182, 766, 319);
		window.getContentPane().add(itemPanel);
		itemPanel.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(452, 22, 134, 21);
		itemPanel.add(lblDescription);
		
		JLabel lblCostItem = new JLabel("Cost:");
		lblCostItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostItem.setBounds(452, 110, 92, 21);
		itemPanel.add(lblCostItem);
		
		JLabel lblCostItemValue = new JLabel("");
		lblCostItemValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCostItemValue.setBounds(452, 138, 92, 21);
		itemPanel.add(lblCostItemValue);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDescription.setBackground(null);
		txtDescription.setBounds(452, 50, 304, 56);
		itemPanel.add(txtDescription);
						
		int xPos = 10;
		int yPos = 60;
		int numButton = 1;
		for(int i = 0; i < shopMonsters.getMaxSize(); i++) {
			Monster monster = shopMonsters.getList().get(i);
			Item item = shopItems.getList().get(i);
			ItemButton itemButton = new ItemButton(item, xPos, yPos);
			MonsterButton monsterButton = new MonsterButton(monster, xPos, yPos);
			if (item.getName().length() > 10) {				
				itemButton.setText("<html><center>" + item.getName().replaceFirst(" ", "<br>") + "</centre></html>");
			}
			else if (monster.getName().length() > 10) {				
				monsterButton.setText("<html><center>" + monster.getName().replaceFirst(" ", "<br>") + "</centre></html>");
			}
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
			monsterPanel.add(monsterButton);
			itemPanel.add(itemButton);
			xPos += 190;
			numButton += 1;
			if(numButton == 3) {
				yPos += 140;
				xPos = 10;
			}
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
				itemPanel.setVisible(true);
				monsterPanel.setVisible(false);
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
				itemPanel.setVisible(false);
				monsterPanel.setVisible(true);
			}
		});
		
		btnMonsterSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMonsterSelection.setBounds(20, 129, 128, 42);
		window.getContentPane().add(btnMonsterSelection);
		
		
		btnItemSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnItemSelection.setBounds(175, 129, 128, 42);
		window.getContentPane().add(btnItemSelection);
	}
	
	public static void main(String[]args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		GameEnvironment game = new GameEnvironment();
		gui.setGame(game);
		gui.getGame().setupGame();
		new ShopScreen(gui);
	}
}
