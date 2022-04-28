package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Stats");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(300, 20, 200, 50);
		window.getContentPane().add(titleLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.setBounds(650, 30, 100, 30);
		window.getContentPane().add(backButton);
		
		JLabel balanceLabel = new JLabel("Balance:");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		balanceLabel.setBounds(250, 144, 140, 30);
		window.getContentPane().add(balanceLabel);
		
		JLabel balanceLabelValue = new JLabel(String.format("$%s", player.getBalance()));
		balanceLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		balanceLabelValue.setBounds(410, 144, 140, 30);
		window.getContentPane().add(balanceLabelValue);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(250, 185, 140, 30);
		window.getContentPane().add(nameLabel);
		
		JLabel nameLabelValue = new JLabel(player.getName());
		nameLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabelValue.setBounds(410, 185, 140, 30);
		window.getContentPane().add(nameLabelValue);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayLabel.setBounds(250, 226, 140, 30);
		window.getContentPane().add(dayLabel);

		JLabel dayLabelValue = new JLabel(String.format("%s out of %s", game.getDay(), game.getNumDays()));
		dayLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayLabelValue.setBounds(410, 226, 140, 30);
		window.getContentPane().add(dayLabelValue);
		
		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		difficultyLabel.setBounds(250, 267, 140, 30);
		window.getContentPane().add(difficultyLabel);
		
		JLabel difficultyLabelValue = new JLabel(String.valueOf(game.getDifficulty()));
		difficultyLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		difficultyLabelValue.setBounds(410, 267, 140, 30);
		window.getContentPane().add(difficultyLabelValue);
		
		JLabel todayScoreLabel = new JLabel("Today score:");
		todayScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		todayScoreLabel.setBounds(250, 308, 140, 30);
		window.getContentPane().add(todayScoreLabel);
		
		JLabel todayScoreLabelValue = new JLabel(String.valueOf(score.getDayScore()));
		todayScoreLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		todayScoreLabelValue.setBounds(410, 308, 140, 30);
		window.getContentPane().add(todayScoreLabelValue);
		
		JLabel totalScoreLabel = new JLabel("Total score:");
		totalScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalScoreLabel.setBounds(250, 349, 140, 30);
		window.getContentPane().add(totalScoreLabel);
		
		JLabel totalScoreLabelValue = new JLabel(String.valueOf(score.getTotalScore()));
		totalScoreLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalScoreLabelValue.setBounds(410, 349, 140, 30);
		window.getContentPane().add(totalScoreLabelValue);
		
		JLabel todayBattlesLabel = new JLabel("Today battles:");
		todayBattlesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		todayBattlesLabel.setBounds(250, 390, 140, 30);
		window.getContentPane().add(todayBattlesLabel);
		
		JLabel todayBattlesLabelValue = new JLabel(String.format("%s won out of %s", score.getDayBattlesWon(),
				score.getDayBattlesWon() + score.getDayBattlesLost()));
		todayBattlesLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		todayBattlesLabelValue.setBounds(410, 390, 140, 30);
		window.getContentPane().add(todayBattlesLabelValue);
		
		JLabel totalBattlesLabel = new JLabel("Total battles:");
		totalBattlesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalBattlesLabel.setBounds(250, 431, 140, 30);
		window.getContentPane().add(totalBattlesLabel);
		
		JLabel totalBattlesLabelValue = new JLabel(String.format("%s won out of %s", score.getTotalBattlesWon(),
				score.getTotalBattlesWon() + score.getTotalBattlesLost()));
		totalBattlesLabelValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalBattlesLabelValue.setBounds(410, 431, 140, 30);
		window.getContentPane().add(totalBattlesLabelValue);
	}
	
}
