package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import exceptions.InvalidValueException;

import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Displays the selected monster in a new window.
 * @author Farzad and Daniel
 */
public class MonsterScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private Monster monster;
	
	
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
		gui.closeMonsterScreen(this);
	}

	
	/**
	 * Create a new MonsterScreen object.
	 * @param gui the given gui
	 * @param monster the given monster
	 */
	public MonsterScreen(GraphicalUserInterface gui, Monster monster) {
		this.monster = monster;
		this.gui = gui;
		initialize();
		window.setVisible(true);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Monster");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel(monster.getName().toUpperCase());
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
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = (String)JOptionPane.showInputDialog(
			               null,
			               "Select monster name", 
			               "Rename monster",            
			               JOptionPane.PLAIN_MESSAGE,
			               null,            
			               null, 
			               ""
			            );
			            if(result != null){
			            	try {
			            		monster.setName(result);
			            		titleLabel.setText(monster.getName().toUpperCase());
			            	}
			            	catch(InvalidValueException error) {
				            	JOptionPane.showMessageDialog(null, "Select a unique monster name (3 - 15 characters)\ncontaining no numbers or special characters", "ERROR: " + "Invalid name!", JOptionPane.INFORMATION_MESSAGE);
			            	}
			            }
			            else if(result == null || (result != null && ("".equals(result)))) {
			            	titleLabel.setText(monster.getName().toUpperCase());
			            }
			}
		});
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(631, 508, 119, 44);
		window.getContentPane().add(btnRename);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = AlertBox.yesNo(String.format("Are you sure you want to sell\n%s for $%s?", monster.getName(), (int) (monster.getCost()*monster.getRefundAmount())));
				if(result == 0) {
					try {
						AlertBox.infoBox(monster.sell(), "Monster Sold!");
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
		
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setBounds(23, 98, 366, 428);
		window.getContentPane().add(statsPanel);
		
		JLabel lblStats = new JLabel("<html><u>Stats</u></html>");
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStats.setBounds(76, 11, 151, 40);
		statsPanel.add(lblStats);
		
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
		lblHealAmount.setBounds(10, 260, 136, 40);
		statsPanel.add(lblHealAmount);
		
		JLabel lblCritRate = new JLabel("Critical rate :");
		lblCritRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritRate.setBounds(10, 320, 121, 40);
		statsPanel.add(lblCritRate);
		
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
		lblHealValue.setBounds(193, 260, 121, 40);
		statsPanel.add(lblHealValue);
		
		JLabel lblCritValue = new JLabel("");
		lblCritValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCritValue.setBounds(193, 320, 121, 40);
		statsPanel.add(lblCritValue);
		
		HealthBar healthBar = new HealthBar(monster, 193, 77);
		statsPanel.add(healthBar);
		
		JLabel lblFainted = new JLabel("Status :");
		lblFainted.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFainted.setBounds(10, 377, 121, 40);
		statsPanel.add(lblFainted);
		
		JLabel lblStatusValue = new JLabel("");
		lblStatusValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatusValue.setBounds(193, 377, 121, 40);
		statsPanel.add(lblStatusValue);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescription.setText(monster.getDescription());
		txtDescription.setBackground(null);
		txtDescription.setBounds(425, 292, 325, 151);
		window.getContentPane().add(txtDescription);
		
		lblDamageValue.setText(""+ monster.getDamage());
		lblLevelValue.setText(""+ monster.getLevel());
		lblHealValue.setText(""+ monster.getHealAmount());
		lblCritValue.setText(""+ (int)(monster.getCritRate() * 100 )+ "%");
		String status;
		if(monster.getIsFainted()) {
			status = "Fainted";
		}
		else {
			status = "Alive";
		}
		lblStatusValue.setText(status);
		
		SpriteLabel spriteLabel = new SpriteLabel(monster, 500, 130);
		window.getContentPane().add(spriteLabel);
	}
}
