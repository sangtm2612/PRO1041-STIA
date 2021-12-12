/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.ChiTietHangHoa;
import DAO.Models.TaiKhoan;
import Utils.utilityHelper;
import Utils.validateHelper;
import javax.swing.JOptionPane;

/**
 *
 * @author sangt
 */
public class SoLuongHuyJFrame extends javax.swing.JFrame {

    BanHangJFrame bh;

    /**
     * Creates new form SoLuongHuyJFrame
     */
    public SoLuongHuyJFrame(BanHangJFrame bh) {
        initComponents();
        this.bh = bh;
        init();
    }

    public void init() {
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void noteLyDoHuy() {
        String soLuongHuy = tf_soLuongHuy.getText().trim();
        int sl = Integer.parseInt(soLuongHuy);
        String lyDo = ta_lyDo.getText().trim();
        bh.setHDClick();
        String tk = bh.tk.getTenTK();
        String note = bh.hdClick.getGhiChu() + tk + " đã hủy " + sl + " " + bh.getTenHangValueTableHDCT() + " lý do: " + lyDo+";";
        bh.hdClick.setGhiChu(note);
        bh.hdService.suaHoaDon(bh.hdClick);
        bh.setValueTBHoaDon(note, bh.iRowHD, 12);
    }

    public void xacNhan() {
        String soLuongHuy = tf_soLuongHuy.getText().trim();
        if (soLuongHuy.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập số lượng hàng cần hủy!");
            return;
        }
        if (utilityHelper.checkFormat("^[0-9]+$", soLuongHuy) == false) {
            validateHelper.message(this, "Sai định dạng số lượng hủy!");
            return;
        }
        if (ta_lyDo.getText().trim().isEmpty()) {
            validateHelper.message(this, "Nhập lý do hủy hàng!");
            return;
        }
        bh.soLuong = Integer.parseInt(soLuongHuy);
        if (bh.soLuong <= 0 || bh.soLuong > 999) {
            validateHelper.message(this, "Số lượng hàng 1-999!");
            return;
        }
        if (bh.soLuong > bh.hdctClick.getSoLuong()) {
            validateHelper.message(this, "Số lượng hủy <= số lượng hàng trong hóa đơn!");
            return;
        }
        if (validateHelper.confirm(this, "Bạn có chắc chắn muốn hủy hàng!", "XÁC NHẬN HỦY HÀNG") == JOptionPane.YES_OPTION) {
            if (bh.soLuong == bh.hdctClick.getSoLuong()) {
                bh.hdctClick.setTrangThai(false);
            }

            bh.hdctClick.setSoLuong(bh.hdctClick.getSoLuong() - bh.soLuong);
            ChiTietHangHoa cthh = bh.cthhService.findIdChiTietHangHoa(bh.hdctClick.getId_CTHangHoa());
            cthh.setSoLuong(cthh.getSoLuong() + bh.soLuong);
            bh.cthhService.suaHangHoaChiTiet(cthh);
            bh.hdctService.suaHoaDonChiTiet(bh.hdctClick);
            noteLyDoHuy();
            bh.setTien();
            bh.loadListChiTietHangHoa();
            bh.loadListHDCT();
            bh.loadTableHangHoa();
            bh.loadTableHDCT();
            bh.setTienThanhToanHD();
            validateHelper.message(bh, "Hủy hàng thành công!");
            this.dispose();
        }

    }

    public void setHoaDon() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_soLuongHuy = new javax.swing.JTextField();
        btn_huy = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_lyDo = new javax.swing.JTextArea();
        btn_thoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Số lượng:");

        tf_soLuongHuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_huy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_huy.setText("Hủy hàng");
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Lý do hủy hàng:");

        ta_lyDo.setColumns(20);
        ta_lyDo.setLineWrap(true);
        ta_lyDo.setRows(5);
        jScrollPane1.setViewportView(ta_lyDo);

        btn_thoat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_soLuongHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_huy, btn_thoat});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_soLuongHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_thoat))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_huy, btn_thoat});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        // TODO add your handling code here:
        xacNhan();
    }//GEN-LAST:event_btn_huyActionPerformed

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
            java.util.logging.Logger.getLogger(SoLuongHuyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SoLuongHuyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SoLuongHuyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SoLuongHuyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new SoLuongHuyJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_lyDo;
    private javax.swing.JTextField tf_soLuongHuy;
    // End of variables declaration//GEN-END:variables
}
