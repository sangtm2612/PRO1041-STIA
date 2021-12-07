/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import DAO.Models.MauSac;
import DAO.Models.TaiKhoan;
import Service.Implement.MauSacService;
import UI.HangHoaJFrame;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sangt
 */
public class MauSacJFrame extends javax.swing.JFrame {
    MauSacService msService = new MauSacService();
    List<MauSac> mAL;
    DefaultTableModel dtm;
    MauSac msClick;
    TaiKhoan tk;
    
    /**
     * Creates new form NewJFrame
     */
    public MauSacJFrame(TaiKhoan tk) {
        initComponents();
        init();
        this.tk = tk;
        if (tk.isVaiTro() == false) {
            btn_luu.setEnabled(false);
            btn_them.setEnabled(false);
            btn_xoa.setEnabled(false);
        }
    }
    
    public void init(){
        setResizable(false);
        setLocationRelativeTo(null);
        loadTable();
    }
    
    public void loadTable() {
        loadList();
        dtm = (DefaultTableModel) tb_mausac.getModel();
        dtm.setRowCount(0);
        for (MauSac m : mAL) {
            dtm.addRow(new Object[]{m.getTenMau()});
        }
    }
    
    public void loadList() {
        mAL = msService.findAllMauSac();
    }
    
    public void add() {
        msService.themMauSac(getMauSac());
        loadTable();
        HangHoaJFrame.loadCbbMauSac();
    }
    
    public void edit() {
        MauSac ms = getMauSac();
        ms.setId(msClick.getId());
        msService.suaMauSac(ms);
        loadTable();
        HangHoaJFrame.loadCbbMauSac();
    }
    
    public void remove() {
        MauSac ms = getMauSac();
        ms.setId(msClick.getId());
        ms.setTrangThai(false);
        msService.suaMauSac(ms);
        loadTable();
        HangHoaJFrame.loadCbbMauSac();
        NhaCungCapJFrame.clearTextFiel(tf_mausac);
    }

    public void fillForm() {
        int i = tb_mausac.getSelectedRow();
        String tenMauSac = tb_mausac.getValueAt(i, 0).toString();
        msClick = msService.findMauSacName(tenMauSac);
        tf_mausac.setText(tenMauSac);
        System.out.println("id: " + msClick.getId());

    }

    public MauSac getMauSac() {
        MauSac ms = new MauSac();
        ms.setTenMau(tf_mausac.getText());
        ms.setTrangThai(true);
        return ms;
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
        tf_mausac = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_mausac = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Màu Sắc");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Màu sắc:");

        tf_mausac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/them.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/remove.png"))); // NOI18N
        btn_xoa.setText("Xóa  ");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/luu.png"))); // NOI18N
        btn_luu.setText("Lưu  ");
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        tb_mausac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Màu sắc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_mausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_mausacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_mausac);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_mausac)
                    .addComponent(btn_luu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_mausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_btn_luuActionPerformed

    private void tb_mausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_mausacMouseClicked
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_tb_mausacMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        remove();
    }//GEN-LAST:event_btn_xoaActionPerformed

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
            java.util.logging.Logger.getLogger(MauSacJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MauSacJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MauSacJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MauSacJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MauSacJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_mausac;
    private javax.swing.JTextField tf_mausac;
    // End of variables declaration//GEN-END:variables
}
