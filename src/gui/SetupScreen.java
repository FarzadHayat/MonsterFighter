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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import exceptions.InvalidValueException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;

public class SetupScreen {

	private JFrame window;
	private GraphicalUserInterface gui;
	private JTextField txtSelectPlayerName;
	private Player player;
	private GameEnvironment game;
	
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		gui.closeSetupScreen(this);
	}

	/**
	 * Create the application.
	 */
	public SetupScreen(GraphicalUserInterface gui) {
		this.gui = gui;
		initialize();
		game = gui.getGame();
		player = game.getPlayer();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("MonsterFighter - Setup");
		window.setResizable(false);
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("SETUP");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		window.getContentPane().add(titleLabel);
		
		JLabel lblNumDays = new JLabel("Number of days:");
		lblNumDays.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNumDays.setBounds(42, 114, 242, 33);
		window.getContentPane().add(lblNumDays);
		
		JSlider sliderNumDays = new JSlider();
		sliderNumDays.setValue(5);
		sliderNumDays.setPaintTicks(true);
		sliderNumDays.setSnapToTicks(true);
		sliderNumDays.setPaintLabels(true);
		sliderNumDays.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sliderNumDays.setMajorTickSpacing(1);
		sliderNumDays.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sliderNumDays.setMaximum(15);
		sliderNumDays.setMinimum(5);
		sliderNumDays.setBounds(316, 104, 345, 56);
		window.getContentPane().add(sliderNumDays);
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblDifficulty.setBounds(42, 239, 242, 33);
		window.getContentPane().add(lblDifficulty);
		
		JComboBox difficultyBox = new JComboBox();
		difficultyBox.setBackground(Color.WHITE);
		difficultyBox.setModel(new DefaultComboBoxModel(Difficulty.values()));
		difficultyBox.setMaximumRowCount(3);
		difficultyBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		difficultyBox.setBounds(316, 239, 135, 50);
		window.getContentPane().add(difficultyBox);
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPlayerName.setBounds(42, 385, 242, 33);
		window.getContentPane().add(lblPlayerName);
		
		txtSelectPlayerName = new JTextField();
		txtSelectPlayerName.setToolTipText("Select a player name (3 - 15 characters containing no numbers or special characters)");
		txtSelectPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSelectPlayerName.setBounds(316, 353, 345, 134);
		window.getContentPane().add(txtSelectPlayerName);
		txtSelectPlayerName.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setDifficulty((Difficulty)difficultyBox.getSelectedItem());
				try {
					player.SetName(txtSelectPlayerName.getText());
					game.setNumDays(sliderNumDays.getValue());
					game.setupGame();
					gui.launchStartingMonsterScreen();
					finishedWindow();
				}
				catch(InvalidValueException error) {
					AlertBox.infoBox(error.getMessage(), "Invalid name!");
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(657, 508, 119, 44);
		window.getContentPane().add(btnNext);
		
	}

}