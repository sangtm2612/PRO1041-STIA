/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.TaiKhoan;
import Thread.ClockThread;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author sangt
 */
public class MainJFrame extends javax.swing.JFrame {

    
    KhachHangJFrame kh = new KhachHangJFrame();
    static TaiKhoan tk;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame(TaiKhoan tk) {
        initComponents();
        this.tk = tk;
        if (tk.isVaiTro() == false) {
            jToolBar1.remove(btn_thongke);
            jToolBar1.remove(sepa_thongke);
            jToolBar1.remove(btn_nhanvien);
            jToolBar1.remove(sape_nhanvien);
        }
        init();
    }

    public void init() {
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com.myPro.Icon/STIA.png")));
        initClock();
    }

    public void initClock() {
        ClockThread c = new ClockThread(lb_dongho);
        c.start();
    }

    public void logOut() {
        this.dispose();
        new LoginJFrame().setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_ncc = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btn_hanghoa = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btn_banhang = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btn_hoadon = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btn_nhanvien = new javax.swing.JButton();
        sape_nhanvien = new javax.swing.JToolBar.Separator();
        btn_khachhang = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_thongke = new javax.swing.JButton();
        sepa_thongke = new javax.swing.JToolBar.Separator();
        btn_doimk = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btn_dangxuat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lb_dongho = new javax.swing.JLabel();
        dp_content = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PH????N M????M QUA??N LY?? BA??N HA??NG STIA");

        jToolBar1.setRollover(true);

        btn_ncc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/nhacungcap.png"))); // NOI18N
        btn_ncc.setText("Nha?? cung c????p");
        btn_ncc.setFocusable(false);
        btn_ncc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ncc.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_ncc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nccActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_ncc);
        jToolBar1.add(jSeparator3);

        btn_hanghoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hanghoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/hanghoa (2).png"))); // NOI18N
        btn_hanghoa.setText("Ha??ng ho??a");
        btn_hanghoa.setFocusable(false);
        btn_hanghoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hanghoa.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_hanghoa.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_hanghoa.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_hanghoa.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_hanghoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_hanghoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hanghoaActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_hanghoa);
        jToolBar1.add(jSeparator4);

        btn_banhang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_banhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/banhang (2).png"))); // NOI18N
        btn_banhang.setText("Ba??n ha??ng");
        btn_banhang.setFocusable(false);
        btn_banhang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_banhang.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_banhang.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_banhang.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_banhang.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_banhang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_banhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_banhangActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_banhang);
        jToolBar1.add(jSeparator7);

        btn_hoadon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/hoadon.png"))); // NOI18N
        btn_hoadon.setText("Ho??a ????n");
        btn_hoadon.setFocusable(false);
        btn_hoadon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hoadon.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_hoadon.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_hoadon.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_hoadon.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_hoadon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_hoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoadonActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_hoadon);
        jToolBar1.add(jSeparator6);

        btn_nhanvien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_nhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/nhanvien (2).png"))); // NOI18N
        btn_nhanvien.setText("Nh??n vi??n");
        btn_nhanvien.setFocusable(false);
        btn_nhanvien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nhanvien.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_nhanvien.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_nhanvien.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_nhanvien.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_nhanvien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nhanvienActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_nhanvien);
        jToolBar1.add(sape_nhanvien);

        btn_khachhang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_khachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/khachhahng.png"))); // NOI18N
        btn_khachhang.setText("Kha??ch ha??ng");
        btn_khachhang.setFocusable(false);
        btn_khachhang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_khachhang.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_khachhang.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_khachhang.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_khachhang.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_khachhang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_khachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khachhangActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_khachhang);
        jToolBar1.add(jSeparator2);

        btn_thongke.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_thongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/thongke (2).png"))); // NOI18N
        btn_thongke.setText("Th????ng k??");
        btn_thongke.setFocusable(false);
        btn_thongke.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_thongke.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_thongke.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_thongke.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_thongke.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_thongke.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_thongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongkeActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_thongke);
        jToolBar1.add(sepa_thongke);

        btn_doimk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_doimk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/doiMK.png"))); // NOI18N
        btn_doimk.setText("??????i m????t kh????u");
        btn_doimk.setFocusable(false);
        btn_doimk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_doimk.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_doimk.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_doimk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_doimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doimkActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_doimk);
        jToolBar1.add(jSeparator8);

        btn_dangxuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/log-out.png"))); // NOI18N
        btn_dangxuat.setText("????ng xu????t");
        btn_dangxuat.setFocusable(false);
        btn_dangxuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_dangxuat.setMargin(new java.awt.Insets(40, 24, 40, 24));
        btn_dangxuat.setMaximumSize(new java.awt.Dimension(139, 137));
        btn_dangxuat.setMinimumSize(new java.awt.Dimension(139, 137));
        btn_dangxuat.setPreferredSize(new java.awt.Dimension(139, 101));
        btn_dangxuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_dangxuat);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/i.png"))); // NOI18N
        jLabel2.setText("C??NG TY ??O KI????M M??I TR??????NG STIA");

        lb_dongho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_dongho.setForeground(new java.awt.Color(153, 0, 0));
        lb_dongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/dongho.png"))); // NOI18N
        lb_dongho.setText("15:00 07/10/2021");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_dongho)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lb_dongho)))
        );

        javax.swing.GroupLayout dp_contentLayout = new javax.swing.GroupLayout(dp_content);
        dp_content.setLayout(dp_contentLayout);
        dp_contentLayout.setHorizontalGroup(
            dp_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dp_contentLayout.setVerticalGroup(
            dp_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dp_content)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dp_content)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hanghoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hanghoaActionPerformed
        // TODO add your handling code here:
        HangHoaJFrame hanghoa = new HangHoaJFrame(tk);
        openX(hanghoa);
    }//GEN-LAST:event_btn_hanghoaActionPerformed

    private void btn_banhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_banhangActionPerformed
        // TODO add your handling code here:
        BanHangJFrame banhang = new BanHangJFrame(tk);
        openX(banhang);

    }//GEN-LAST:event_btn_banhangActionPerformed

    private void btn_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nccActionPerformed
        // TODO add your handling code here:
        NhaCungCapJFrame ncc = new NhaCungCapJFrame(tk);
        ncc.setVisible(true);
    }//GEN-LAST:event_btn_nccActionPerformed

    private void btn_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nhanvienActionPerformed
        // TODO add your handling code here:
        NhanVienJFrame nv = new NhanVienJFrame();
        openX(nv);
    }//GEN-LAST:event_btn_nhanvienActionPerformed

    private void btn_khachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khachhangActionPerformed
        // TODO add your handling code here:
        kh.setVisible(true);
    }//GEN-LAST:event_btn_khachhangActionPerformed

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        // TODO add your handling code here:
        logOut();
    }//GEN-LAST:event_btn_dangxuatActionPerformed

    private void btn_doimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doimkActionPerformed
        // TODO add your handling code here:
        DoiMatKhauJInternal dmk = new DoiMatKhauJInternal(this.tk);
        openX(dmk);
    }//GEN-LAST:event_btn_doimkActionPerformed

    private void btn_hoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoadonActionPerformed
        // TODO add your handling code here:
        HoaDonJInternal hd = new HoaDonJInternal();
        openX(hd);
    }//GEN-LAST:event_btn_hoadonActionPerformed

    private void btn_thongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongkeActionPerformed
        // TODO add your handling code here:
        ThongKeJInternalFrame tk = new ThongKeJInternalFrame();
        openX(tk);
    }//GEN-LAST:event_btn_thongkeActionPerformed

    public void openX(JInternalFrame x) {
        for (JInternalFrame frmChild : dp_content.getAllFrames()) {
            frmChild.dispose();
        }

        x.setLocation(this.getWidth() / 2 - x.getWidth() / 2,
                (this.getHeight() - 20) / 2 - x.getHeight() / 2 - 60);
        dp_content.add(x);
        x.setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame(new TaiKhoan()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_banhang;
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JButton btn_doimk;
    private javax.swing.JButton btn_hanghoa;
    private javax.swing.JButton btn_hoadon;
    private javax.swing.JButton btn_khachhang;
    private javax.swing.JButton btn_ncc;
    private javax.swing.JButton btn_nhanvien;
    private javax.swing.JButton btn_thongke;
    private javax.swing.JDesktopPane dp_content;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb_dongho;
    private javax.swing.JToolBar.Separator sape_nhanvien;
    private javax.swing.JToolBar.Separator sepa_thongke;
    // End of variables declaration//GEN-END:variables
}
