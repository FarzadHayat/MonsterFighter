package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;

import java.awt.Color;
import java.awt.Font;
import main.*;
import monsters.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FightScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	private GameEnvironment game;
	private Battle battle;
	
	private JTextArea commentaryText;
	private BackButton backButton;
	
	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		gui.closeFightScreen(this);
	}

	/**
	 * Create the application.
	 */
	public FightScreen(GraphicalUserInterface gui, Battle battle) {
		this.gui = gui;
		game = gui.getGame();
		this.battle = battle;
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Fight!");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("FIGHT!");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(202, 35, 376, 50);
		window.getContentPane().add(titleLabel);
		
		backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattlesScreen();
				finishedWindow();
			}
		});
		backButton.setVisible(false);
		window.getContentPane().add(backButton);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(20, 105, 744, 100);
		window.getContentPane().add(playerPanel);
		playerPanel.setLayout(null);
		
		int xPos = 7;
		for (Monster monster : game.getPlayer().getMonsters().getList()) {
			MonsterButton monsterButton = new MonsterButton(monster, xPos, 5);
			playerPanel.add(monsterButton);
			xPos += 190;
		}
		
		JPanel enemyPanel = new JPanel();
		enemyPanel.setBounds(20, 365, 744, 100);
		window.getContentPane().add(enemyPanel);
		enemyPanel.setLayout(null);
		
		xPos = 7;
		for (Monster monster : battle.getMonsters().getList()) {
			MonsterButton monsterButton = new MonsterButton(monster, xPos, 5);
			enemyPanel.add(monsterButton);
			xPos += 190;
		}
		
		
		JPanel commentaryPanel = new JPanel();
		commentaryPanel.setBounds(93, 205, 600, 160);
		window.getContentPane().add(commentaryPanel);
		commentaryPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(commentaryText);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 580, 140);
		commentaryPanel.add(scrollPane);
		
		commentaryText = new JTextArea();
		commentaryText.setEditable(false);
		commentaryText.setFont(new Font("Calibri", Font.BOLD, 16));
		commentaryText.setTabSize(4);
		commentaryText.setLineWrap(true);
		scrollPane.setViewportView(commentaryText);
		
		playBattle();
	}
	
	public void playBattle() {
		// Setup the battle
		try {
			battle.setup();
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}

		// press nextButton to play the next turn in the battle
		JButton nextButton = new JButton("Play Turn");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent key) {				
				if (battle.getWinner() == null) {
					commentaryText.setText(battle.playTurn());
					commentaryText.setText(commentaryText.getText() + "\n" + battle.checkStatus());
				}
				if (battle.getWinner() != null) {
					// disable nextButton and remove Battle from BattleInventory
					try {
						game.getBattles().remove(battle);
						backButton.setVisible(true);
						nextButton.setVisible(false);
					}
					catch (NotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nextButton.setBounds(630, 490, 120, 40);
		window.getContentPane().add(nextButton);
	}
	
	public static void main(String[]args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.getGame().setupGame();
		Battle battle = gui.getGame().getBattles().getList().get(0);
		try {
			gui.getGame().getPlayer().getMonsters().add(new Chunky(gui.getGame()));
		} catch (InventoryFullException e) {
			e.printStackTrace();
		}
		new FightScreen(gui, battle);
	}
}