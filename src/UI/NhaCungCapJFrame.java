/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.NhaCungCap;
import DAO.Models.TaiKhoan;
import Service.Implement.NhaCungCapService;
import UI.HangHoaJFrame;
import Utils.validateHelper;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sangt
 */
public class NhaCungCapJFrame extends javax.swing.JFrame {

    public static void clearTextFiel(JTextField tf_diachi, JTextField tf_email, JTextField tf_masothue, JTextField tf_sodienthoai, JTextField tf_ten, JTextField tf_timkiem, JTextArea tf_ghichu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    NhaCungCapService nccService = new NhaCungCapService();
    DefaultTableModel dtm;
    List<NhaCungCap> list;
    NhaCungCap nccClick;
    TaiKhoan tk;

    /**
     * Creates new form NhaCungCapJFrame
     */
    public NhaCungCapJFrame(TaiKhoan tk) {
        initComponents();
        this.tk = tk;
        if (tk.isVaiTro() == false) {
            btn_them.setEnabled(false);
            btn_sua.setEnabled(false);
            btn_xoa.setEnabled(false);
        }
        init();
    }

    //Khởi đầu frame
    public void init() {
        setResizable(false);
        setLocationRelativeTo(null);
        dtm = (DefaultTableModel) tb_nhaCungCap.getModel();
        loadList();
        loadTable();
    }

    public static void clearTextFiel(JTextField... arg) {
        for (JTextField tf : arg) {
            tf.setText("");
        }
    }

    public void loadList() {
        list = nccService.findAllNhaCungCap();
    }

    public void loadTable() {
        dtm.setRowCount(0);
        for (NhaCungCap ncc : list) {
            dtm.addRow(new Object[]{ncc.getTenNCC(), ncc.getSDT(), ncc.getEmail(), ncc.getDiaChi(), ncc.getGhiChu()});
        }
    }

    public void clear() {
        clearTextFiel(tf_diachi, tf_email, tf_nhacungcap, tf_sodienthoai);
        ta_ghichu.setText("");
    }

    public void sua() {
        int i = tb_nhaCungCap.getSelectedRow();
        if (i == -1) {
            validateHelper.message(this, "Vui lòng chọn nhà cung cấp cần sửa");
            return;
        }
        if (validateF()) {
            NhaCungCap ncc = getNhaCungCap();
            ncc.setId(nccClick.getId());
            nccService.suaNhaCungCap(ncc);
            loadList();
            loadTable();
            HangHoaJFrame.loadCbbNhaCungCap();
        }
    }

    public void xoa() {
        int i = tb_nhaCungCap.getSelectedRow();
        if (i == -1) {
            validateHelper.message(this, "Vui lòng chọn nhà cung cấp cần xóa");
            return;
        }
        nccClick.setTrangThai(false);
        nccService.suaNhaCungCap(nccClick);
        loadList();
        loadTable();
        HangHoaJFrame.loadCbbNhaCungCap();
        clear();
    }

    public NhaCungCap getNhaCungCap() {
        String tenNCC = tf_nhacungcap.getText().trim();
        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(tf_diachi.getText());
        ncc.setEmail(tf_email.getText());
        ncc.setSDT(tf_sodienthoai.getText());
        ncc.setGhiChu(ta_ghichu.getText());
        ncc.setTrangThai(true);
        return ncc;
    }

    public boolean validateF() {
        String tenNCC = tf_nhacungcap.getText().trim();
        if (tenNCC.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập nhà cung cấp!");
            return false;
        }
        String diaChi = tf_diachi.getText().trim();
        if (diaChi.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập địa chỉ!");
            return false;
        }
        String sdt = tf_sodienthoai.getText().trim();
        if (sdt.isEmpty()) {
            validateHelper.message(this, "Số điện thoại không hợp lệ!");
            return false;
        }
        String email = tf_email.getText().trim();
        if (!email.matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            validateHelper.message(this, "Email không hợp lệ!");
            return false;
        }
        return true;
    }

    public void them() {
        if (validateF()) {
            nccService.themNhaCungCap(getNhaCungCap());
            loadList();
            loadTable();
            HangHoaJFrame.loadCbbNhaCungCap();
        }
    }

    public void fillForm() {
        int i = tb_nhaCungCap.getSelectedRow();
        String sdt = tb_nhaCungCap.getValueAt(i, 1).toString();
        nccClick = nccService.findNhaCungCapBySDT(sdt);
        tf_nhacungcap.setText(nccClick.getTenNCC());
        tf_diachi.setText(nccClick.getDiaChi());
        tf_email.setText(nccClick.getEmail());
        tf_sodienthoai.setText(nccClick.getSDT());
        ta_ghichu.setText(nccClick.getGhiChu());
        System.out.println("id: " + nccClick.getId());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_ghichu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_nhacungcap = new javax.swing.JTextField();
        tf_sodienthoai = new javax.swing.JTextField();
        tf_email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_diachi = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_lammoi = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tf_timkiem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_nhaCungCap = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ NHÀ CUNG CẤP");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("QUẢN LÝ NHÀ CUNG CẤP");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        ta_ghichu.setColumns(20);
        ta_ghichu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ta_ghichu.setLineWrap(true);
        ta_ghichu.setRows(5);
        jScrollPane1.setViewportView(ta_ghichu);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ghi chú:");

        tf_nhacungcap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_sodienthoai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nhà cung cấp:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        tf_diachi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_diachiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tf_nhacungcap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_diachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(115, 115, 115)))
                    .addComponent(jLabel3))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(tf_sodienthoai, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(tf_email))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_diachi, tf_email, tf_nhacungcap, tf_sodienthoai});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_nhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_diachi, tf_email, tf_nhacungcap, tf_sodienthoai});

        jPanel3.setLayout(new java.awt.GridLayout(1, 4, 3, 10));

        btn_lammoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/cleaning.png"))); // NOI18N
        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });
        jPanel3.add(btn_lammoi);

        btn_them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/them.png"))); // NOI18N
        btn_them.setText("Thêm   ");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel3.add(btn_them);

        btn_sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.setMargin(new java.awt.Insets(2, 2, 2, 14));
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_sua);

        btn_xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/remove.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.setMargin(new java.awt.Insets(2, 3, 2, 14));
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_xoa);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm:");

        tf_timkiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tf_timkiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_timkiem)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng quản lý nhà cung cấp"));

        tb_nhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_nhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_nhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nhaCungCapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tb_nhaCungCapMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tb_nhaCungCap);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_diachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_diachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_diachiActionPerformed

    private void tb_nhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nhaCungCapMouseClicked
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_tb_nhaCungCapMouseClicked

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        clear();
        loadList();
        loadTable();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        them();
    }//GEN-LAST:event_btn_themActionPerformed

    private void tb_nhaCungCapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nhaCungCapMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_nhaCungCapMouseEntered

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        xoa();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void tf_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tf_timkiemCaretUpdate
        // TODO add your handling code here:
        String sdtTimKiem = tf_timkiem.getText().trim();
        System.out.println(sdtTimKiem);
        list = nccService.findListNhaCungCapBySDT(sdtTimKiem);
        loadTable();
    }//GEN-LAST:event_tf_timkiemCaretUpdate

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
            java.util.logging.Logger.getLogger(NhaCungCapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhaCungCapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhaCungCapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhaCungCapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new NhaCungCapJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ta_ghichu;
    private javax.swing.JTable tb_nhaCungCap;
    private javax.swing.JTextField tf_diachi;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_nhacungcap;
    private javax.swing.JTextField tf_sodienthoai;
    private javax.swing.JTextField tf_timkiem;
    // End of variables declaration//GEN-END:variables
}
