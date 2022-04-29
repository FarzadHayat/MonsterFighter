package gui;

import javax.swing.JOptionPane;

public class AlertBox {

	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Message: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
