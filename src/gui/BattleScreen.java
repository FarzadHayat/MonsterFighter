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

public class BattleScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	
	private GameEnvironment game;
	private Player player;
	private Score score;
	
	private Battle battle;
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeBattleScreen(this);
	}

	/**
	 * Create the application.
	 */
	public BattleScreen(GraphicalUserInterface gui, Battle battle) {
		this.battle = battle;
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
		window.setTitle("MonsterFighter - Battle");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("BATTLE");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchBattlesScreen();
				finishedWindow();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.setBounds(650, 30, 100, 30);
		window.getContentPane().add(backButton);
	}
}
