package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import main.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Displays the fight screen in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class FightScreen extends Screen {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Battle battle;
	
	private JTextArea commentaryText;
	private BackButton backButton;
	private JPanel playerPanel;
	private JPanel enemyPanel;


	/**
	 * Create a new FightScreen object.
	 * @param battle the given battle
	 */
	public FightScreen(Battle battle) {
		super();
		this.battle = battle;
		
		JLabel titleLabel = new JLabel("FIGHT!");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(202, 35, 376, 50);
		add(titleLabel);
		
		backButton = new BackButton();
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BattlesScreen();
				close();
			}
		});
		backButton.setVisible(false);
		add(backButton);
		
		refreshMonsters();
		
		JPanel commentaryPanel = new JPanel();
		commentaryPanel.setBounds(93, 215, 600, 150);
		add(commentaryPanel);
		commentaryPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(commentaryText);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 580, 130);
		commentaryPanel.add(scrollPane);
		
		commentaryText = new JTextArea();
		commentaryText.setEditable(false);
		commentaryText.setFont(new Font("Calibri", Font.BOLD, 16));
		commentaryText.setTabSize(4);
		commentaryText.setLineWrap(true);
		scrollPane.setViewportView(commentaryText);
		
		playBattle();
	}
	
	
	/**
	 * Play the battle turn by turn using a play turn button until the battle is over.
	 */
	public void playBattle() {
		// press nextButton to play the next turn in the battle
		JButton nextButton = new JButton("Play Turn");
		nextButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nextButton.setBounds(630, 500, 120, 40);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent key) {				
				if (battle.getWinner() == null) {
					String commentary = battle.playTurn();
					commentary += battle.checkStatus();
					commentaryText.setText(commentary);
					remove(playerPanel);
					remove(enemyPanel);
					refreshMonsters();
				}
				if (battle.getWinner() != null) {
					// disable nextButton and remove Battle from BattleInventory
					game.getBattles().remove(battle);
					backButton.setVisible(true);
					nextButton.setVisible(false);
				}
			}
		});
		add(nextButton);
	}
	

	/**
	 * Refresh the monster buttons to update their health.
	 */
	public void refreshMonsters() {
		playerPanel = new MonstersPanel(game.getPlayer().getMonsters(), 22, 0, 4);
		playerPanel.setBounds(20, 370, 744, 120);
		add(playerPanel);
		playerPanel.setLayout(null);
		
		playerPanel.revalidate();
		playerPanel.repaint();
		
		enemyPanel = new MonstersPanel(battle.getMonsters(), 22, 0, 4);
		enemyPanel.setBounds(20, 90, 744, 120);
		add(enemyPanel);
		enemyPanel.setLayout(null);
		
		enemyPanel.revalidate();
		enemyPanel.repaint();
	}

}