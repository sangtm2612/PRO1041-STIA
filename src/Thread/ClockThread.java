/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sangt
 */
public class ClockThread extends Thread {

    private JLabel lb;

    public ClockThread(JLabel lb) {
        this.lb = lb;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa dd/MM/yyyy");
        while (true) {
            Date d = new Date();
            String now = sdf.format(d);
            lb.setText(now);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
