/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.TaiKhoan;
import Service.Implement.TaiKhoanService;
import Utils.validateHelper;
import java.awt.Cursor;
import java.util.List;

/**
 *
 * @author sangt
 */
public class LoginJFrame extends javax.swing.JFrame {

    List<TaiKhoan> tkList;
    TaiKhoanService tkService = new TaiKhoanService();
    TaiKhoan tk;

    public LoginJFrame() {
        initComponents();
        init();
    }

    public void init() {
        setLocationRelativeTo(null);
        setResizable(false);
        tkList = tkService.findAllTaiKhoan();
    }

    public void openQuenMK() {

    }

    public void checkLogin() {
        String tenTK = tf_tenTK.getText().trim();
        if (tenTK.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập tên tài khoản");
            return;
        }
        String matKhau = pf_matkhau.getText();
        if (matKhau.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập mật khẩu");
            return;
        }
        boolean check = false;
        for (TaiKhoan tk : tkList) {
            if (tenTK.equals(tk.getTenTK()) && matKhau.equals(tk.getMatKhau())) {
                new MainJFrame(tk).setVisible(true);
                check = true;
                this.dispose();
            }
        }
        if (check == false) {
            validateHelper.message(this, "Tài khoản không tồn tại!");
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_tenTK = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pf_matkhau = new javax.swing.JPasswordField();
        btn_dangnhap = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lb_quenmk = new javax.swing.JLabel();
        btn_ketthuc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/login.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập:");

        tf_tenTK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        pf_matkhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_dangnhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_dangnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/dangNhap.png"))); // NOI18N
        btn_dangnhap.setText("Đăng nhập");
        btn_dangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangnhapActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("ĐĂNG NHẬP");

        lb_quenmk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_quenmk.setText("Quên mật khẩu?");
        lb_quenmk.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lb_quenmkMouseMoved(evt);
            }
        });
        lb_quenmk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_quenmkMouseClicked(evt);
            }
        });

        btn_ketthuc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ketthuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/thoat.png"))); // NOI18N
        btn_ketthuc.setText("Kết thúc");
        btn_ketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ketthucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_dangnhap)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_ketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(pf_matkhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_tenTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_quenmk, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_dangnhap, btn_ketthuc});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_tenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_quenmk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dangnhap)
                    .addComponent(btn_ketthuc))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ketthucActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_ketthucActionPerformed

    private void btn_dangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangnhapActionPerformed
        // TODO add your handling code here:
        checkLogin();
    }//GEN-LAST:event_btn_dangnhapActionPerformed

    private void lb_quenmkMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_quenmkMouseMoved
        // TODO add your handling code here:
        lb_quenmk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lb_quenmkMouseMoved

    private void lb_quenmkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_quenmkMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ForgotJFrame().setVisible(true);
    }//GEN-LAST:event_lb_quenmkMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dangnhap;
    private javax.swing.JButton btn_ketthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lb_quenmk;
    private javax.swing.JPasswordField pf_matkhau;
    private javax.swing.JTextField tf_tenTK;
    // End of variables declaration//GEN-END:variables
}
