package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

import main.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import exceptions.InvalidValueException;

import javax.swing.DefaultComboBoxModel;

/**
 * Displays the setup screen in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class SetupScreen extends Screen {

	/**
	 * Fields
	 */
	private JTextField txtSelectPlayerName;
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player = game.getPlayer();

	
	/**
	 * Create a new SetupScreen object.
	 */
	public SetupScreen() {
		super();
		
		JLabel titleLabel = new JLabel("SETUP");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 20, 300, 50);
		add(titleLabel);
		
		JLabel lblNumDays = new JLabel("Number of days:");
		lblNumDays.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNumDays.setBounds(42, 114, 242, 33);
		add(lblNumDays);
		
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
		add(sliderNumDays);
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblDifficulty.setBounds(42, 239, 242, 33);
		add(lblDifficulty);
		
		JComboBox difficultyBox = new JComboBox();
		difficultyBox.setBackground(Color.WHITE);
		difficultyBox.setModel(new DefaultComboBoxModel(Difficulty.values()));
		difficultyBox.setMaximumRowCount(3);
		difficultyBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		difficultyBox.setBounds(316, 239, 135, 50);
		add(difficultyBox);
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPlayerName.setBounds(42, 385, 242, 33);
		add(lblPlayerName);
		
		txtSelectPlayerName = new JTextField();
		txtSelectPlayerName.setToolTipText("Select a player name (3 - 15 characters containing no numbers or special characters)");
		txtSelectPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSelectPlayerName.setBounds(316, 353, 345, 134);
		add(txtSelectPlayerName);
		txtSelectPlayerName.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setDifficulty((Difficulty)difficultyBox.getSelectedItem());
				try {
					player.setName(txtSelectPlayerName.getText());
					game.setNumDays(sliderNumDays.getValue());
					game.populateGame();
					new StartingMonsterScreen();
					close();
				}
				catch(InvalidValueException error) {
					AlertBox.infoBox(error.getMessage(), "Invalid name!");
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(657, 508, 119, 44);
		add(btnNext);
		
	}

}