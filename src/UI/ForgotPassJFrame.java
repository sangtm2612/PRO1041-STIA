/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.NhanVien;
import DAO.Models.TaiKhoan;
import Service.Implement.NhanVienService;
import Service.Implement.TaiKhoanService;
import Utils.validateHelper;

/**
 *
 * @author sangt
 */
public class ForgotPassJFrame extends javax.swing.JFrame {
    NhanVien nv;
    NhanVienService nvService = new NhanVienService();
    TaiKhoanService tkService = new TaiKhoanService();
    /**
     * Creates new form ForgotPassJFrame
     */
    public ForgotPassJFrame(NhanVien nv) {
        initComponents();
        init();
        this.nv = nv;
    }
    
    public void init() {
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    public void doiMK() {
        String mkMoi = pf_matKhau.getText();
        String mkXacNhan = pf_xacnhanmk.getText();
        if (mkMoi.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập mật khẩu mới!");
            return;
        }
        if (mkXacNhan.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập mật khẩu xác nhận!");
            return;
        }
        if (!mkMoi.equals(mkXacNhan)) {
            validateHelper.message(this, "Mật khẩu xác nhận không khớp!");
            return;
        }
        if (mkMoi.equals(mkXacNhan)) {
            TaiKhoan tk = tkService.findTaiKhoanId(nv.getId());
            tk.setMatKhau(mkMoi);
            tkService.suaTaiKhoan(tk);
            validateHelper.message(this, "Đổi mật khẩu thành công");
            this.dispose();
            new LoginJFrame().setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pf_matKhau = new javax.swing.JPasswordField();
        pf_xacnhanmk = new javax.swing.JPasswordField();
        btn_xacnhan = new javax.swing.JButton();
        btn_ketthuc = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/forgotmk.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Xác nhận mật khẩu:");

        pf_matKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        pf_xacnhanmk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_xacnhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xacnhan.setText("Xác nhận");

        btn_ketthuc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ketthuc.setText("Kết thúc");
        btn_ketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ketthucActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/back.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_xacnhan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                            .addComponent(btn_ketthuc))
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(pf_matKhau)
                        .addComponent(pf_xacnhanmk))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_xacnhanmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xacnhan)
                    .addComponent(btn_ketthuc))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ketthucActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_ketthucActionPerformed

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
            java.util.logging.Logger.getLogger(ForgotPassJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ForgotPassJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ketthuc;
    private javax.swing.JButton btn_xacnhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pf_matKhau;
    private javax.swing.JPasswordField pf_xacnhanmk;
    // End of variables declaration//GEN-END:variables
}
