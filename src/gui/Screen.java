package gui;

import java.awt.Container;

import javax.swing.JPanel;

/**
 * Screen template for all screens to use.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class Screen extends JPanel {

	/**
	 * Fields
	 */
	private Container window = GraphicalUserInterface.getInstance().getContentPane();
	
	/**
	 * Create a new Screen object.
	 */
	public Screen() {
		super();
		setLayout(null);
		setBounds(0, 0, 800, 600);
		window.add(this);
		window.repaint();
	}
	
	public void close() {
		window.remove(this);
	}

}
