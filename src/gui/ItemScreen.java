package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.LevelUp;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class ItemScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	private MonsterInventory monsters;
	private JButton selectedButton;
	private Monster selectedMonster;
	
	private Item item;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeItemScreen(this);
	}

	/**
	 * Create the application.
	 */
	public ItemScreen(GraphicalUserInterface gui, Item item) {
		this.item = item;
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		game.getScoreSystem();
		monsters = player.getMonsters();
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Item");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel(item.getName().toUpperCase());
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		BackButton backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchHomeScreen();
				finishedWindow();
			}
		});
		window.getContentPane().add(backButton);
		
		JButton btnUse = new JButton("Use");
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedMonster != null) {
					try {
						int result = AlertBox.yesNo(String.format("Are you sure you want to use\n%s item on %s?", item.getName(), selectedMonster.getName()));
						if(result == 0) {
							item.use(selectedMonster);
							AlertBox.infoBox(String.format("%s item used on %s.", item.getName(), selectedMonster.getName()),"Item used!");
							gui.launchHomeScreen();
							finishedWindow();
						}
					} catch (StatMaxedOutException e1) {
						AlertBox.infoBox("Monster stat maxed out, choose another monster.", "Try again!");
					}
				}
			}
		});
		btnUse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUse.setBounds(631, 508, 119, 44);
		window.getContentPane().add(btnUse);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = AlertBox.yesNo(String.format("Are you sure you want to sell\n%s item for $%s?", item.getName(), (int) (item.getCost()*item.getRefundAmount())));
				if(result == 0) {
					try {
						AlertBox.infoBox(item.sell(), "Item Sold!");
						gui.launchHomeScreen();
						finishedWindow();
					} catch (InvalidValueException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSell.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSell.setBounds(631, 453, 119, 44);
		window.getContentPane().add(btnSell);
		
		JPanel monstersPanel = new JPanel();
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(10, 87, 766, 465);
		window.getContentPane().add(monstersPanel);
		
		JLabel txtDescription = new JLabel();
		txtDescription.setVerticalAlignment(SwingConstants.TOP);
		txtDescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescription.setBounds(226, 11, 325, 85);
		monstersPanel.add(txtDescription);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescription.setText(item.getDescription());
		txtDescription.setBackground(null);
		
		if(!monsters.getList().isEmpty()) {
			int yPos = 100;
			int xPos = 215;
			int numButton = 1;
			for (Monster monster : monsters.getList()) {
				MonsterButton monsterButton = new MonsterButton(monster, xPos, yPos);
				if (monster.getName().length() > 10) {				
					monsterButton.setText("<html><center>" + monster.getName().replaceFirst(" ", "<br>") + "</centre></html>");
				}
				monsterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedMonster = monster;
						if(selectedButton != null) {
							selectedButton.setBackground(null);
						}
						selectedButton = monsterButton;
						selectedButton.setBackground(Color.lightGray);
					}
				});
				monstersPanel.add(monsterButton);
				xPos += 190;
				numButton++;
				if(numButton == 3) {
					yPos += 120;
					xPos = 215;
				}
			}
		}
		else {
			JLabel txtNoMonster = new JLabel();
			txtNoMonster.setVerticalAlignment(SwingConstants.TOP);
			txtNoMonster.setText((String) null);
			txtNoMonster.setHorizontalAlignment(SwingConstants.CENTER);
			txtNoMonster.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtNoMonster.setBackground((Color) null);
			txtNoMonster.setBounds(226, 107, 325, 85);
			txtNoMonster.setText("No monster in player's inventory!");
			monstersPanel.add(txtNoMonster);
		}
		
	}
	
	public static void main(String[]args) throws InventoryFullException{
		GraphicalUserInterface gui = new GraphicalUserInterface();
		GameEnvironment game = new GameEnvironment();
		gui.setGame(game);
		gui.getGame().setupGame();
		for(int i = 0; i < 4; i++) {
			try {
				gui.getGame().getPlayer().getMonsters().add(gui.getGame().getAllMonsters().getList().get(i).clone());
			} catch (InventoryFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LevelUp test = new LevelUp(game);
		game.getPlayer().getItems().add(test);
		new ItemScreen(gui, test);
	}
}
