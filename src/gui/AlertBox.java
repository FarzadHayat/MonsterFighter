package gui;

import javax.swing.JOptionPane;

public class AlertBox {

	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Message: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static int yesNo(String infoMessage)
    {
		return JOptionPane.showConfirmDialog(null, infoMessage, "Select an Option...", JOptionPane.YES_NO_OPTION);
    }

}
