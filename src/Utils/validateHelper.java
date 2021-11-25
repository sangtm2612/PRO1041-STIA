/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author sangt
 */
public class validateHelper {
    
    public static void showMessage(Component c, String message) {
        JOptionPane.showMessageDialog(c, message);
    }

    public static boolean showMessage(JTextField tf, Component c, String message) {
        if (tf.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(c, message);
            return false;
        }
        return true;
    }
    
    public static boolean showMessage(JPasswordField pf, Component c, String message) {
        if (pf.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(c, message);
            return false;
        }
        return true;
    }
}
