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
    
    public static void message(Component c, String message) {
        JOptionPane.showMessageDialog(c, message);
    }
    
    public static int confirm(Component c, String message, String tilte) {
        return JOptionPane.showConfirmDialog(c, message, tilte, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }
}
