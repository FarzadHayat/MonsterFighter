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

public class StatsScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	private Score score;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeStatsScreen(this);
	}

	/**
	 * Create the application.
	 */
	public StatsScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		game = gui.getGame();
		player = game.getPlayer();
		score = game.getScoreSystem();
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Stats");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("PLAYER STATS");
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
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBounds(200, 100, 400, 340);
		window.getContentPane().add(generalPanel);
		generalPanel.setLayout(null);
		
		JLabel balanceLabel = new JLabel("Balance:");
		balanceLabel.setBounds(50, 11, 140, 30);
		generalPanel.add(balanceLabel);
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel balanceLabelValue = new JLabel(String.format("$%s", player.getBalance()));
		balanceLabelValue.setBounds(210, 11, 140, 30);
		generalPanel.add(balanceLabelValue);
		balanceLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(50, 52, 140, 30);
		generalPanel.add(nameLabel);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel nameLabelValue = new JLabel(player.getName());
		nameLabelValue.setBounds(210, 52, 140, 30);
		generalPanel.add(nameLabelValue);
		nameLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setBounds(50, 93, 140, 30);
		generalPanel.add(dayLabel);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel dayLabelValue = new JLabel(String.format("%s out of %s", game.getDay(), game.getNumDays()));
		dayLabelValue.setBounds(210, 93, 140, 30);
		generalPanel.add(dayLabelValue);
		dayLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setBounds(50, 134, 140, 30);
		generalPanel.add(difficultyLabel);
		difficultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel difficultyLabelValue = new JLabel(String.valueOf(game.getDifficulty()));
		difficultyLabelValue.setBounds(210, 134, 140, 30);
		generalPanel.add(difficultyLabelValue);
		difficultyLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel todayScoreLabel = new JLabel("Today score:");
		todayScoreLabel.setBounds(50, 175, 140, 30);
		generalPanel.add(todayScoreLabel);
		todayScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel todayScoreLabelValue = new JLabel(String.valueOf(score.getDayScore()));
		todayScoreLabelValue.setBounds(210, 175, 140, 30);
		generalPanel.add(todayScoreLabelValue);
		todayScoreLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel totalScoreLabel = new JLabel("Total score:");
		totalScoreLabel.setBounds(50, 216, 140, 30);
		generalPanel.add(totalScoreLabel);
		totalScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel totalScoreLabelValue = new JLabel(String.valueOf(score.getTotalScore()));
		totalScoreLabelValue.setBounds(210, 216, 140, 30);
		generalPanel.add(totalScoreLabelValue);
		totalScoreLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel todayBattlesLabel = new JLabel("Today battles:");
		todayBattlesLabel.setBounds(50, 257, 140, 30);
		generalPanel.add(todayBattlesLabel);
		todayBattlesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel todayBattlesLabelValue = new JLabel(String.format("%s won out of %s", score.getDayBattlesWon(),
													score.getDayBattlesWon() + score.getDayBattlesLost()));
		todayBattlesLabelValue.setBounds(210, 257, 140, 30);
		generalPanel.add(todayBattlesLabelValue);
		todayBattlesLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel totalBattlesLabel = new JLabel("Total battles:");
		totalBattlesLabel.setBounds(50, 298, 140, 30);
		generalPanel.add(totalBattlesLabel);
		totalBattlesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel totalBattlesLabelValue = new JLabel(String.format("%s won out of %s", score.getTotalBattlesWon(),
													score.getTotalBattlesWon() + score.getTotalBattlesLost()));
		totalBattlesLabelValue.setBounds(210, 298, 140, 30);
		generalPanel.add(totalBattlesLabelValue);
		totalBattlesLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel finishedPanel = new JPanel();
		finishedPanel.setBounds(200, 440, 400, 90);
		window.getContentPane().add(finishedPanel);
		finishedPanel.setLayout(null);
		
		JLabel finalScoreLabel = new JLabel("Final score:");
		finalScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		finalScoreLabel.setBounds(50, 10, 120, 30);
		finishedPanel.add(finalScoreLabel);
		
		JLabel finalScoreLabelValue = new JLabel(String.format("%s (+%s bonus)", score.finalScore(), score.scoreBonus()));
		finalScoreLabelValue.setHorizontalAlignment(SwingConstants.CENTER);
		finalScoreLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		finalScoreLabelValue.setBounds(160, 10, 220, 30);
		finishedPanel.add(finalScoreLabelValue);
		
		JLabel gameOverLabel = new JLabel("GAME OVER!");
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameOverLabel.setBounds(130, 50, 140, 30);
		finishedPanel.add(gameOverLabel);
		
		generalPanel.setVisible(true);
		finishedPanel.setVisible(false);
		
		if (game.getIsFinished()) {
			backButton.setVisible(false);
			finishedPanel.setVisible(true);
		}
	}
}
