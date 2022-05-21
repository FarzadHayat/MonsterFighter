package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.InvalidValueException;
import exceptions.StatMaxedOutException;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Displays the selected item in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class ItemScreen extends Screen {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player = game.getPlayer();
	private MonsterInventory monsters = player.getMonsters();
	
	private JButton selectedButton;
	private Monster selectedMonster;
	

	/**
	 * Create a new ItemScreen object.
	 * @param item the given item
	 */
	public ItemScreen(Item item) {
		super();
		
		JLabel titleLabel = new JLabel(item.getName().toUpperCase());
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		add(titleLabel);
		
		BalanceLabel balanceLabel = new BalanceLabel(100, 25);
		add(balanceLabel);
		
		BackButton backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeScreen();
				close();
			}
		});
		add(backButton);
		
		JButton btnUse = new JButton("Use");
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedMonster != null) {
					try {
						int result = AlertBox.yesNo(String.format("Are you sure you want to use\n%s item on %s?", item.getName(), selectedMonster.getName()));
						if(result == 0) {
							item.use(selectedMonster);
							AlertBox.infoBox(String.format("%s item used on %s.", item.getName(), selectedMonster.getName()),"Item used!");
							new HomeScreen();
							close();
						}
					} catch (StatMaxedOutException e1) {
						AlertBox.infoBox("Monster stat maxed out, choose another monster.", "Try again!");
					}
				}
			}
		});
		btnUse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUse.setBounds(631, 508, 119, 44);
		add(btnUse);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = AlertBox.yesNo(String.format("Are you sure you want to sell\n%s item for $%s?", item.getName(), (int) (item.getCost()*item.getRefundAmount())));
				if(result == 0) {
					try {
						AlertBox.infoBox(item.sell(), "Item Sold!");
						new HomeScreen();
						close();
					} catch (InvalidValueException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSell.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSell.setBounds(631, 453, 119, 44);
		add(btnSell);
		
		SpriteLabel spriteLabel = new SpriteLabel(item, 550, 200);
		add(spriteLabel);
		
		MonstersPanel monstersPanel = new MonstersPanel(monsters, 120, 80, 2);
		monstersPanel.setLayout(null);
		monstersPanel.setBounds(10, 87, 766, 465);
		add(monstersPanel);
		
		JLabel txtDescription = new JLabel();
		txtDescription.setVerticalAlignment(SwingConstants.TOP);
		txtDescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescription.setBounds(226, 11, 325, 85);
		monstersPanel.add(txtDescription);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescription.setText(item.getDescription());
		txtDescription.setBackground(null);
		
		if(monsters.isEmpty()) {
			JLabel txtNoMonster = new JLabel();
			txtNoMonster.setVerticalAlignment(SwingConstants.TOP);
			txtNoMonster.setHorizontalAlignment(SwingConstants.CENTER);
			txtNoMonster.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtNoMonster.setBounds(120, 107, 325, 85);
			txtNoMonster.setText("No monster in player's inventory!");
			monstersPanel.add(txtNoMonster);
		}
		else {
			for (int i = 0; i < monsters.size(); i++) {
				Monster monster = monsters.get(i);
				MonsterButton monsterButton = (MonsterButton) monstersPanel.getComponent(i);
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
			}
		}
	}
	
}
