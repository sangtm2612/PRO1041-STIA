/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.swing.JTextField;

/**
 *
 * @author sangt
 */
public class utilityHelper {
    
    //ép kiểu text
    public static Integer parseInt(JTextField tf) {
        try {
            return Integer.parseInt(tf.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Double parseDouble(JTextField tf) {
        try {
            return Double.parseDouble(tf.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
