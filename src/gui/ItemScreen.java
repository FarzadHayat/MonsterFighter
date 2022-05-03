package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import items.IncreaseCritRate;
import items.IncreaseDamage;
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
	private Score score;
	
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
		score = game.getScoreSystem();
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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel(item.getName().toUpperCase());
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchHomeScreen();
				finishedWindow();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.setBounds(650, 30, 100, 30);
		window.getContentPane().add(backButton);
		
		JButton btnUse = new JButton("Use");
		btnUse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUse.setBounds(631, 508, 119, 44);
		window.getContentPane().add(btnUse);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to sell\n%s item for %s gold?", item.getName(), (int)(item.getCost()*item.getRefundAmount())));
				if(result == 0) {
					try {
						AlertBox.infoBox(item.sell(), "Item Sold!");
						gui.launchHomeScreen();
						finishedWindow();
					} catch (NotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		
		int yPos = 100;
		int xPos = 215;
		int numButton = 1;
		for (Monster monster : monsters.getList()) {
			JButton monsterButton = new JButton(monster.getName());
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
			monsterButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			monsterButton.setBounds(xPos, yPos, 160, 90);
			monstersPanel.add(monsterButton);
			xPos += 190;
			numButton++;
			if(numButton == 3) {
				yPos += 120;
				xPos = 215;
			}
		}
	}
	
	public static void main(String[]args){
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.getGame().setupGame();
		for(int i = 0; i < 4; i++) {
			try {
				gui.getGame().getPlayer().getMonsters().add(gui.getGame().getAllMonsters().getList().get(i).clone());
			} catch (InventoryFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new ItemScreen(gui, new IncreaseDamage(gui.getGame()));
	}
}
