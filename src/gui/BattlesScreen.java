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
import javax.swing.JTextArea;

public class BattlesScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private BattleInventory battles;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeBattlesScreen(this);
	}

	/**
	 * Create the application.
	 */
	public BattlesScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		battles = game.getBattles();
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Battles");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("BATTLES");
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
		
		JPanel battlesPanel = new JPanel();
		battlesPanel.setBounds(110, 160, 580, 260);
		window.getContentPane().add(battlesPanel);
		battlesPanel.setLayout(null);
		
		JButton battle1Button = new JButton("Battle 1");
		battle1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattleScreen(battles.getList().get(0));
				finishedWindow();
			}
		});
		battle1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		battle1Button.setBounds(10, 10, 160, 90);
		battlesPanel.add(battle1Button);
		
		JButton battle2Button = new JButton("Battle 2");
		battle2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattleScreen(battles.getList().get(1));
				finishedWindow();
			}
		});
		battle2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		battle2Button.setBounds(210, 10, 160, 90);
		battlesPanel.add(battle2Button);
		
		JButton battle3Button = new JButton("Battle 3");
		battle3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattleScreen(battles.getList().get(2));
				finishedWindow();
			}
		});
		battle3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		battle3Button.setBounds(410, 10, 160, 90);
		battlesPanel.add(battle3Button);
		
		JButton battle4Button = new JButton("Battle 4");
		battle4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattleScreen(battles.getList().get(3));
				finishedWindow();
			}
		});
		battle4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		battle4Button.setBounds(110, 160, 160, 90);
		battlesPanel.add(battle4Button);
		
		JButton battle5Button = new JButton("Battle 5");
		battle5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattleScreen(battles.getList().get(4));
				finishedWindow();
			}
		});
		battle5Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		battle5Button.setBounds(310, 160, 160, 90);
		battlesPanel.add(battle5Button);
	}
}
