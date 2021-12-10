/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package UI;

import DAO.Models.NhanVien;
import DAO.Models.PhongBan;
import DAO.Models.TaiKhoan;
import Service.Implement.NhanVienService;
import Service.Implement.PhongBanService;
import Service.Implement.TaiKhoanService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienJFrame extends javax.swing.JInternalFrame {

    PhongBanJFrame pb = new PhongBanJFrame();
    NhanVienService nvService = new NhanVienService();
    TaiKhoanService tkService = new TaiKhoanService();
    List<NhanVien> nvList;
    NhanVien nvClick;
    TaiKhoan tkClick;
    DefaultTableModel dtm;
    static DefaultComboBoxModel dcm;
    static PhongBanService pbService = new PhongBanService();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, y ");

    /**
     * Creates new form NhanVien
     */
    public NhanVienJFrame() {
        initComponents();
        init();

    }

    public void init() {
        dcm = (DefaultComboBoxModel) cbb_phongban.getModel();
        dtm = (DefaultTableModel) tb_nhanvien.getModel();
        dcm.removeAllElements();
        dtm.setRowCount(0);
        loadCbbPhongBan();
        loadTable();
    }

    public static void loadCbbPhongBan() {
        dcm.removeAllElements();
        List<PhongBan> pbList = pbService.findAllPhongBan();
        for (PhongBan pb : pbList) {
            dcm.addElement(pb);
        }
    }

    public void loadList() {
        nvList = nvService.findAllNhanVien();
    }

    public void loadTable() {
        loadList();
        dtm.setRowCount(0);
        PhongBan pb;
        TaiKhoan tk;
        for (NhanVien nv : nvList) {
            String gioiTinh = nv.isGioiTinh() == true ? "Nam" : "Nữ";
            String trangThai = nv.isTrangThai() == true ? "Làm việc" : "Nghỉ việc";
            String chucVu = nv.isChucVu() == true ? "Trưởng phòng" : "Nhân viên";
            pb = pbService.findPhongBanById(nv.getId_PhongBan());
            tk = tkService.findTaiKhoanId(nv.getId_TaiKhoan());
            dtm.addRow(new Object[]{tk.getTenTK(), nv.getHoTen(), pb.getTenPhongBan(), chucVu, gioiTinh, sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getSDT(), nv.getEmail(), nv.getCCCD(), nv.getGhiChu(), trangThai});
        }
    }

    public void clearForm() {
        NhaCungCapJFrame.clearTextFiel(tf_hoten, tf_cccd, tf_diachi, tf_email, tf_sdt, tf_taikhoan);
        dc_ngaysinh.setDate(null);
        ta_ghichu.setText("");
        rbtn_nam.setSelected(true);
        rbtn_nhanvien.setSelected(true);
        pf_matkhau.setText("");
        pf_xacnhanmk.setText("");
        loadTable();
    }

    public TaiKhoan getTaiKhoan() {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTK(tf_taikhoan.getText());
        if (pf_matkhau.getText().equals(pf_xacnhanmk.getText())) {
            tk.setMatKhau(pf_matkhau.getText());
        }
        tk.setVaiTro(rbtn_truongphong.isSelected());
        tk.setTrangThai(true);
        return tk;
    }

    public NhanVien getNhanVien() {
        NhanVien nv = new NhanVien();
        nv.setHoTen(tf_hoten.getText());
        nv.setCCCD(tf_cccd.getText());
        nv.setDiaChi(tf_diachi.getText());
        nv.setSDT(tf_sdt.getText());
        nv.setEmail(tf_email.getText());
        if (rbtn_nam.isSelected()) {
            nv.setGioiTinh(true);
        } else {
            nv.setGioiTinh(false);
        }
        if (rbtn_truongphong.isSelected()) {
            nv.setChucVu(true);
        } else {
            nv.setChucVu(false);
        }
        nv.setNgaySinh(dc_ngaysinh.getDate());
        nv.setGhiChu(ta_ghichu.getText());
        nv.setTrangThai(true);
        return nv;
    }
    
    public Integer themTK() {
        tkService.themTaiKhoan(getTaiKhoan());
        TaiKhoan nv = tkService.findTaiKhoanName(tf_taikhoan.getText());
        return nv.getId();
    }
    
    public int getIdPhongBan() {
        PhongBan pb = (PhongBan) dcm.getSelectedItem();
        return pb.getId();
    }
    
    public void themNV() {
        Integer idTK = themTK();
        int idPhongBan = getIdPhongBan();
        NhanVien nv = getNhanVien();
        nv.setId_TaiKhoan(idTK);
        nv.setId_PhongBan(idPhongBan);
        nv.setId_TruongPhong(null);
        nvService.themNhanVien(nv);
    }
    
    public void suaNV() {
        NhanVien nv = getNhanVien();
        nv.setId(nvClick.getId());
        nv.setId_TaiKhoan(nvClick.getId_TaiKhoan());
        nv.setId_PhongBan(getIdPhongBan());
        nvService.suaNhanVien(nv);
    }
    
    public void xoaTK() {
        TaiKhoan tk = tkService.findTaiKhoanId(nvClick.getId_TaiKhoan());
        tk.setTrangThai(false);
        tkService.suaTaiKhoan(tk);
    }
    
    public void xoaNV() {
        nvService.xoaNhanVien(nvClick.getId());
    }
    
    //xóa nhân viên kho theo nghiệp vụ chỉ có nhân viên kho mới có tài khoản
    public void xoaNVK() {
        xoaTK();
        xoaNV();
    }

    public void fillForm() {
        int i = tb_nhanvien.getSelectedRow();
        String sdt = tb_nhanvien.getValueAt(i, 7).toString();
        nvClick = nvService.findNhanVienSDT(sdt);
        System.out.println("Id:" + nvClick.getId() + "Id phòng ban: " + nvClick.getId_PhongBan() + " Id tài khoản: " + nvClick.getId_TaiKhoan() + " Id Trưởng phòng: " + nvClick.getId_TruongPhong());
        tf_hoten.setText(nvClick.getHoTen());
        tf_cccd.setText(nvClick.getCCCD());
        tf_diachi.setText(nvClick.getDiaChi());
        tf_sdt.setText(nvClick.getSDT());
        tf_email.setText(nvClick.getEmail());
        if (nvClick.isGioiTinh() == true) {
            rbtn_nam.setSelected(true);
        } else {
            rbtn_nu.setSelected(false);
        }
        if (nvClick.isChucVu() == true) {
            rbtn_truongphong.setSelected(true);
        } else {
            rbtn_nu.setSelected(false);
        }
        dc_ngaysinh.setDate(new Date(nvClick.getNgaySinh().getTime()));
        ta_ghichu.setText(nvClick.getGhiChu());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_gioitinh = new javax.swing.ButtonGroup();
        gbtn_chucvu = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tf_taikhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pf_matkhau = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        pf_xacnhanmk = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_nhanvien = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbb_phongban = new javax.swing.JComboBox<>();
        tf_hoten = new javax.swing.JTextField();
        tf_cccd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_diachi = new javax.swing.JTextField();
        tf_sdt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rbtn_nam = new javax.swing.JRadioButton();
        rbtn_nu = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rbtn_nhanvien = new javax.swing.JRadioButton();
        rbtn_truongphong = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_ghichu = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        dc_ngaysinh = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setIconifiable(true);
        setTitle("QUẢN LÝ NHÂN VIÊN");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 51, 51))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(213, 200));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập:");

        tf_taikhoan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_taikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_taikhoanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        pf_matkhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Xác nhận mật khẩu:");

        pf_xacnhanmk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_taikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(pf_matkhau)
                    .addComponent(pf_xacnhanmk))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_taikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_xacnhanmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tb_nhanvien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tài khoản", "Họ tên", "Phòng ban", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "CCCD", "Ghi chú", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_nhanvien);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Phòng ban:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Họ tên:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("CCCD:");

        cbb_phongban.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbb_phongban.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_phongbanItemStateChanged(evt);
            }
        });

        tf_hoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_hotenActionPerformed(evt);
            }
        });

        tf_cccd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_cccd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cccdActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ:");

        tf_diachi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_sdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Email:");

        tf_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Giới tính:");

        btn_gioitinh.add(rbtn_nam);
        rbtn_nam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtn_nam.setSelected(true);
        rbtn_nam.setText("Nam");

        btn_gioitinh.add(rbtn_nu);
        rbtn_nu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtn_nu.setText("Nữ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Chức vụ:");

        gbtn_chucvu.add(rbtn_nhanvien);
        rbtn_nhanvien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtn_nhanvien.setSelected(true);
        rbtn_nhanvien.setText("Nhân viên");

        gbtn_chucvu.add(rbtn_truongphong);
        rbtn_truongphong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtn_truongphong.setText("Trưởng phòng");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Ghi chú:");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/cleaning.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        btn_them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/add-user.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel2.add(btn_them);

        btn_sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_sua);

        btn_xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/delete-user.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_xoa);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        ta_ghichu.setColumns(20);
        ta_ghichu.setRows(5);
        jScrollPane3.setViewportView(ta_ghichu);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Ngày sinh:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_diachi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_cccd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_hoten, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbb_phongban, javax.swing.GroupLayout.Alignment.LEADING, 0, 201, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(rbtn_nam)
                                        .addGap(36, 36, 36)
                                        .addComponent(rbtn_nu))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_email, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                        .addComponent(tf_sdt)))
                                .addGap(87, 87, 87)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jLabel18)
                                    .addComponent(dc_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel12)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rbtn_nhanvien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtn_truongphong)))
                        .addGap(115, 115, 115)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dc_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbtn_nam)
                                    .addComponent(rbtn_nu)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn_nhanvien)
                            .addComponent(rbtn_truongphong)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_phongban))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(424, 424, 424))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_taikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_taikhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_taikhoanActionPerformed

    private void tf_hotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_hotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_hotenActionPerformed

    private void tf_cccdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cccdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cccdActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        pb.setVisible(true);

    }//GEN-LAST:event_jLabel17MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nhanvienMouseClicked
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_tb_nhanvienMouseClicked

    private void cbb_phongbanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_phongbanItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbb_phongbanItemStateChanged

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        themNV();
        loadTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        suaNV();
        loadTable();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        xoaNVK();
        loadTable();
    }//GEN-LAST:event_btn_xoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_gioitinh;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    public static javax.swing.JComboBox<String> cbb_phongban;
    private com.toedter.calendar.JDateChooser dc_ngaysinh;
    private javax.swing.ButtonGroup gbtn_chucvu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPasswordField pf_matkhau;
    private javax.swing.JPasswordField pf_xacnhanmk;
    private javax.swing.JRadioButton rbtn_nam;
    private javax.swing.JRadioButton rbtn_nhanvien;
    private javax.swing.JRadioButton rbtn_nu;
    private javax.swing.JRadioButton rbtn_truongphong;
    private javax.swing.JTextArea ta_ghichu;
    private javax.swing.JTable tb_nhanvien;
    private javax.swing.JTextField tf_cccd;
    private javax.swing.JTextField tf_diachi;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_hoten;
    private javax.swing.JTextField tf_sdt;
    private javax.swing.JTextField tf_taikhoan;
    // End of variables declaration//GEN-END:variables
}
