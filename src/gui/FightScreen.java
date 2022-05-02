package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import main.*;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


public class FightScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	private GameEnvironment game;
	private Battle battle;
	
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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("FIGHT!");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(202, 35, 376, 50);
		window.getContentPane().add(titleLabel);
	}
	
	public static void main(String[]args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.getGame().setupGame();
		new FightScreen(gui, new Battle(gui.getGame()));
	}
}