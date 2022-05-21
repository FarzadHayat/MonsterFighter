package gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Displays the start screen in a new panel.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class StartScreen extends Screen {	

	
	/**
	 * Create a new StartScreen object.
	 */
	public StartScreen() {
		super();
		
		JLabel titleLabel = new JLabel("Monster Fighter");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 35, 300, 50);
		add(titleLabel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SetupScreen();
				close();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStart.setBounds(250, 231, 300, 71);
		add(btnStart);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicalUserInterface.getInstance().quit();
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuit.setBounds(250, 371, 300, 71);
		add(btnQuit);

		
	}
}