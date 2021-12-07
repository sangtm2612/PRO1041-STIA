/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.DanhMuc;
import DAO.Models.TaiKhoan;
import Service.Implement.DanhMucService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sangt
 */
public class DanhMucJFrame extends javax.swing.JFrame {
    DanhMucService dmService = new DanhMucService();
    List<DanhMuc> dAL;
    DefaultTableModel dtm;
    DanhMuc dmClick;
    DanhMuc getForm = new DanhMuc();
    TaiKhoan tk;
    /**
     * Creates new form NewJFrame
     */
    public DanhMucJFrame(TaiKhoan tk) {
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
        dtm = (DefaultTableModel) tb_danhmuc.getModel();
        dtm.setRowCount(0);
        for (DanhMuc d : dAL) {
            dtm.addRow(new Object[]{d.getTenDanhMuc()});
        }
    }
    
    public void loadList() {
        dAL = dmService.findAllDanhMuc();
    }
    
    public void add() {
        dmService.themDanhMuc(getDanhMuc());
        loadTable();
        HangHoaJFrame.loadCbbDanhMuc(HangHoaJFrame.cbb_danhmuc);
    }
    
    public void edit() {
        DanhMuc dm = getDanhMuc();
        dm.setId(dmClick.getId());
        dmService.suaDanhMuc(dm);
        loadTable();
        HangHoaJFrame.loadCbbDanhMuc(HangHoaJFrame.cbb_danhmuc);
    }
    
    public void remove() {
        DanhMuc dm = getDanhMuc();
        dm.setId(dmClick.getId());
        dm.setTrangThai(false);
        dmService.suaDanhMuc(dm);
        loadTable();
        HangHoaJFrame.loadCbbDanhMuc(HangHoaJFrame.cbb_danhmuc);
        NhaCungCapJFrame.clearTextFiel(tf_danhmuc);
    }

    public void fillForm() {
        int i = tb_danhmuc.getSelectedRow();
        String tenApSuat = tb_danhmuc.getValueAt(i, 0).toString();
        dmClick = dmService.findDanhMucByName(tenApSuat);
        tf_danhmuc.setText(tenApSuat);
        System.out.println("id: " + dmClick.getId());

    }

    public DanhMuc getDanhMuc() {
        DanhMuc dm = new DanhMuc();
        dm.setTenDanhMuc(tf_danhmuc.getText());
        dm.setTrangThai(true);
        return dm;
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
        tf_danhmuc = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_danhmuc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh mục");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tên danh mục:");

        tf_danhmuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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
        btn_luu.setText("Sưa  ");
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        tb_danhmuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_danhmuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_danhmucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_danhmuc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_danhmuc)
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
                        .addComponent(tf_danhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tb_danhmucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_danhmucMouseClicked
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_tb_danhmucMouseClicked

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_btn_luuActionPerformed

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
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DanhMucJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_danhmuc;
    private javax.swing.JTextField tf_danhmuc;
    // End of variables declaration//GEN-END:variables
}
