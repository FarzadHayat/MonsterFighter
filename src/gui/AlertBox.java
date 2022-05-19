package gui;

import javax.swing.JOptionPane;

/**
 * Launches a new pop up dialog.
 * @author Farzad and Daniel
 */
public class AlertBox {

	/**
	 * Create a new AlertBox object to display a message.
	 * @param infoMessage the given infoMessage
	 * @param titleBar the given titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Message: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	
	/**
	 * Create a new AlertBox object with a yes or no option.
	 * @param infoMessage the given infoMessage.
	 * @return the result: 0 or 1
	 */
	public static int yesNo(String infoMessage)
    {
		return JOptionPane.showConfirmDialog(null, infoMessage, "Select an Option...", JOptionPane.YES_NO_OPTION);
    }

}
