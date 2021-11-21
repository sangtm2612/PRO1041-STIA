/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.ChiTietHangHoa;
import DAO.Models.HangHoa;
import DAO.Models.KhachHang;
import DAO.Models.TaiKhoan;
import Service.Implement.ChiTietHangHoaService;
import Service.Implement.HangHoaService;
import Service.Implement.KhachHangService;
import Thread.ClockThread;
import static UI.HangHoaJFrame.asService;
import static UI.HangHoaJFrame.dmService;
import static UI.HangHoaJFrame.dtm;
import static UI.HangHoaJFrame.dvtService;
import static UI.HangHoaJFrame.ktService;
import static UI.HangHoaJFrame.lhService;
import static UI.HangHoaJFrame.msService;
import static UI.HangHoaJFrame.nccService;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sangt
 */
public class BanHangJFrame extends javax.swing.JInternalFrame {

    static List<KhachHang> khList;
    TaiKhoan tk;
    KhachHangJFrame kh;
    List<ChiTietHangHoa> cthhList;
    ChiTietHangHoaService cthhService = new ChiTietHangHoaService();
    DefaultTableModel dtm;
    static DefaultComboBoxModel dcm;
    static KhachHangService khService = new KhachHangService();
    KhachHang khSelect;

    public BanHangJFrame(TaiKhoan tk) {
        initComponents();
        this.tk = tk;
        init();
    }

    public void init() {
        loadCBB();
        initClock();
        loadTableHangHoa();
        setTK();
        AutoCompleteDecorator.decorate(cbb_khachhang);
    }

    public static void loadListKH() {
        khList = khService.findAllKhachHang();
    }

    public static void loadCbbKhachHang() {
        loadListKH();
        dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        dcm.removeAllElements();
        KhachHang khNull = new KhachHang();
        khNull.setTen("");
        khNull.setSoDienThoai("");
        dcm.addElement(khNull);
        for (KhachHang kh : khList) {
            dcm.addElement(kh);
        }
    }

    public void setTK() {
        lb_taikhoan.setText(tk.getTenTK());
    }

    public void initClock() {
        ClockThread c = new ClockThread(lb_time);
        c.start();
    }

//    public void loadTable() {
//        loadList();
//        dtm = (DefaultTableModel) tb_hanghoa.getModel();
//        dtm.setRowCount(0);
//        for (ChiTietHangHoa cthh : cthhList) {
//            dtm.addRow(new Object[]{cthh.getId_HangHoa(), cthh.getId_KichThuoc(), cthh.getId_MauSac(), cthh.getId_ChieuDay(), 0, cthh.getId_DonViTinh()});
//        }
//    }

    public void loadCBB() {
        HangHoaJFrame.loadCbbDanhMuc(cbb_danhmuc);
        HangHoaJFrame.loadCbbLoaiHang(cbb_loaihang);
        loadCbbKhachHang();
    }

    public void setKhacHang() {
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        lb_masothue.setText(kh.getMaSoThue());
        lb_ten.setText(kh.getTen());
    }
    
    //load bảng hàng hóa
    public void loadListChiTietHangHoa() {
        cthhList = cthhService.findAllChiTietHangHoa();
    }

    public void loadTableHangHoa() {
        loadListChiTietHangHoa();
        dtm = (DefaultTableModel) tb_hanghoa.getModel();
        dtm.setRowCount(0);
        HangHoa hh;
        for (ChiTietHangHoa cthh : cthhList) {
            hh = HangHoaJFrame.hhService.findIdHangHoa(cthh.getId_HangHoa());
            String tenHang = hh.getTenHang();
            String ncc = nccService.findNhaCungCapById(hh.getId_NhaCungCap()).getTenNCC();
            String danhMuc = dmService.findDanhMucById(hh.getId_DanhMuc()).getTenDanhMuc();
            String loaiHang = lhService.findIdLoaiHang(cthh.getId_LoaiHang()).getTenLoai();
            String mauSac = msService.findMauSacId(cthh.getId_MauSac()).getTenMau();
            String kichThuoc = ktService.findKichThuocId(cthh.getId_KichThuoc()).getTenKichThuoc();
            String apSuat = asService.findApSuatId(cthh.getId_ApSuat()).getTenApSuat();
            Double chieuDay = HangHoaJFrame.cdService.findChieuDayId(cthh.getId_ChieuDay()).getDoDay();
            String donViTinh = dvtService.findDonViTinhId(cthh.getId_DonViTinh()).getTenDonVi();
            dtm.addRow(new Object[]{tenHang, kichThuoc, mauSac, chieuDay, apSuat,donViTinh, 0});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop_hanghoa = new javax.swing.JPopupMenu();
        mitem_themhang = new javax.swing.JMenuItem();
        pop_hoadon = new javax.swing.JPopupMenu();
        mitem_huyhang = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        cbb_danhmuc = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbb_loaihang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_masothue = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_time = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lb_ten = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lb_taikhoan = new javax.swing.JLabel();
        cbb_khachhang = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_hoadon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_hanghoa = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hoadonchitiet = new javax.swing.JTable();

        mitem_themhang.setText("Thêm hàng");
        mitem_themhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mitem_themhangMouseClicked(evt);
            }
        });
        mitem_themhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_themhangActionPerformed(evt);
            }
        });
        pop_hanghoa.add(mitem_themhang);

        mitem_huyhang.setText("Hủy hàng");
        pop_hoadon.add(mitem_huyhang);

        setClosable(true);
        setIconifiable(true);
        setTitle("QUẢN LÝ BÁN HÀNG");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Danh mục:");

        cbb_danhmuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Loại hàng:");

        cbb_loaihang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mã số thuế:");

        lb_masothue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_masothue.setForeground(new java.awt.Color(255, 102, 0));
        lb_masothue.setText(" ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Xuất hóa đơn");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tổng tiền:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("30.000.000 đ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Thời gian:");

        lb_time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_time.setForeground(new java.awt.Color(51, 0, 0));
        lb_time.setText("22:40 11/10/2021");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nhân viên:");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/add-button.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("VAT:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("3.000.000 đ");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Tìm kiếm hàng hóa:");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tên:");

        lb_ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_ten.setText(" ");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Tạo hóa đơn");

        lb_taikhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_taikhoan.setForeground(new java.awt.Color(0, 102, 102));
        lb_taikhoan.setText(" ");

        cbb_khachhang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbb_khachhang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_khachhangItemStateChanged(evt);
            }
        });
        cbb_khachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbb_khachhangKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tb_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ngày tạo", "Nhân viên tạo", "SDT khách hàng", "Tên", "Địa chỉ", "Thành tiền", "Đặt cọc", "Trạng thái hóa đơn", "Trạng thái thanh toán"
            }
        ));
        jScrollPane3.setViewportView(tb_hoadon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hàng hóa"));

        tb_hanghoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên hàng", "Kích thước", "Màu sắc", "Độ dày", "Áp suất", "Đơn vị tính", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hanghoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_hanghoaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tb_hanghoa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));
        jPanel5.setToolTipText("Hóa đơn chi tiết");

        tb_hoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên hàng", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hoadonchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_hoadonchitietMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hoadonchitiet);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbb_loaihang, javax.swing.GroupLayout.Alignment.LEADING, 0, 394, Short.MAX_VALUE)
                                    .addComponent(cbb_danhmuc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbb_khachhang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15)
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_taikhoan))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_time))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_ten))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_masothue)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(265, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(112, 112, 112))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(90, 90, 90)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel16)))
                        .addGap(33, 33, 33)
                        .addComponent(jButton5)
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(lb_masothue))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(lb_ten))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(lb_taikhoan))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lb_time)
                                .addComponent(jLabel11))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cbb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_danhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_loaihang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_hanghoaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hanghoaMouseReleased
        // TODO add your handling code here:
        int r = tb_hanghoa.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tb_hanghoa.getRowCount()) {
            tb_hanghoa.setRowSelectionInterval(r, r);
        } else {
            tb_hanghoa.clearSelection();
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            pop_hanghoa.show(tb_hanghoa, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_hanghoaMouseReleased

    private void mitem_themhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mitem_themhangMouseClicked

    }//GEN-LAST:event_mitem_themhangMouseClicked

    private void mitem_themhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_themhangActionPerformed
        System.out.println("click1");
    }//GEN-LAST:event_mitem_themhangActionPerformed

    private void tb_hoadonchitietMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadonchitietMouseReleased
        // TODO add your handling code here:
        int r = tb_hoadonchitiet.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tb_hanghoa.getRowCount()) {
            tb_hoadonchitiet.setRowSelectionInterval(r, r);
        } else {
            tb_hoadonchitiet.clearSelection();
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            pop_hoadon.show(tb_hoadonchitiet, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_hoadonchitietMouseReleased

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        kh = new KhachHangJFrame();
        kh.setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void cbb_khachhangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbb_khachhangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_khachhangKeyPressed

    private void cbb_khachhangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_khachhangItemStateChanged
        // TODO add your handling code here:
        setKhacHang();
    }//GEN-LAST:event_cbb_khachhangItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb_danhmuc;
    public static javax.swing.JComboBox<String> cbb_khachhang;
    private javax.swing.JComboBox<String> cbb_loaihang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lb_masothue;
    private javax.swing.JLabel lb_taikhoan;
    private javax.swing.JLabel lb_ten;
    private javax.swing.JLabel lb_time;
    private javax.swing.JMenuItem mitem_huyhang;
    private javax.swing.JMenuItem mitem_themhang;
    private javax.swing.JPopupMenu pop_hanghoa;
    private javax.swing.JPopupMenu pop_hoadon;
    private javax.swing.JTable tb_hanghoa;
    private javax.swing.JTable tb_hoadon;
    private javax.swing.JTable tb_hoadonchitiet;
    // End of variables declaration//GEN-END:variables
}
