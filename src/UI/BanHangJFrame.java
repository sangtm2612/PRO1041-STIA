/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import DAO.Models.ChiTietHangHoa;
import DAO.Models.DonViTinh;
import DAO.Models.HangHoa;
import DAO.Models.HoaDon;
import DAO.Models.HoaDonChiTiet;
import DAO.Models.KhachHang;
import DAO.Models.TaiKhoan;
import Service.Implement.ChiTietHangHoaService;
import Service.Implement.HangHoaService;
import Service.Implement.HoaDonChiTietService;
import Service.Implement.HoaDonService;
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
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sangt
 */
public class BanHangJFrame extends javax.swing.JInternalFrame {

    HoaDonService hdService = new HoaDonService();
    HoaDonChiTietService hdctService = new HoaDonChiTietService();
    static List<KhachHang> khList;
    TaiKhoan tk;
    KhachHangJFrame kh;
    List<ChiTietHangHoa> cthhList;
    List<HoaDon> hdList;
    List<HoaDonChiTiet> hdctListClick;
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
        //initClock();
        loadTableHangHoa();
        loadTableHD();
        setTK();
        AutoCompleteDecorator.decorate(cbb_khachhang);
    }

    public static void loadListKH() {
        khList = khService.findAllKhachHang();
    }

    public void loadListHD() {
        hdList = hdService.findAllHoaDon();
    }

    public void loadTableHD() {
        loadListHD();
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : hdList) {
            KhachHang kh = khService.findKhachHangId(hd.getId_KhachHang());
            dtm.addRow(new Object[]{hd.getId(), hd.getNgayTao(), tk.getTenTK(), kh.getSoDienThoai(), kh.getTen(), hd.getDiaChi(), hd.getThanhTien(), hd.getDatCoc(), hd.isTrangThaiHD(), hd.isTrangThaiTT(), hd.getGhiChu()});
        }
    }
    
    public void loadListHDCT() {
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        hdctListClick = hdctService.findAllHoaDonChiTietByIdHD(idHD);
    }
    
    public int getTongTien() {
        int sum = 0;
        for (int i = 0; i<tb_hoadonchitiet.getRowCount(); i++) {
            int thanhTien = Integer.parseInt(tb_hoadonchitiet.getValueAt(i, 4).toString());
            sum += thanhTien;
        }
        return sum;
    }
    
    public void loadTableHDCT() {
        loadListHDCT();
        dtm = (DefaultTableModel) tb_hoadonchitiet.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet hdct : hdctListClick) {
            hdct.getId_CTHangHoa();
            ChiTietHangHoa cthh = cthhService.findIdChiTietHangHoa(hdct.getId_CTHangHoa());
            DonViTinh dvt = dvtService.findDonViTinhId(cthh.getId_DonViTinh());
            int soLuong = hdct.getSoLuong();
            int giaBan = cthh.getGiaBan();
            int thanhTien = soLuong * giaBan;
            dtm.addRow(new Object[]{tenHangToString(hdct.getId_CTHangHoa()),soLuong, dvt.getTenDonVi(),giaBan,thanhTien,hdct.isTrangThai()});
        }
    }

    public static void loadCbbKhachHang() {
        loadListKH();
        dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        dcm.removeAllElements();
//        KhachHang khNull = new KhachHang();
//        khNull.setTen("");
//        khNull.setSoDienThoai("");
//        dcm.addElement(khNull);
        for (KhachHang kh : khList) {
            dcm.addElement(kh);
        }
    }

    public void setTK() {
        //lb_taikhoan.setText(tk.getTenTK());
    }

    //đồng hồ
//    public void initClock() {
//        ClockThread c = new ClockThread(lb_time);
//        c.start();
//    }
    public void taoHoaDon() {
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        hd.setThanhTien(0);
        hd.setDatCoc(0);
        hd.setDiaChi(tf_diachigiao.getText().trim());
        hd.setId_KhachHang(kh.getID());
        hd.setId_NhanVien(tk.getId());
        hd.setTrangThaiHD(false);
        hd.setTrangThaiTT(false);
        hd.setGhiChu(ta_ghichu.getText().trim());
        hdService.themHoaDon(hd);
        loadTableHD();
       
    }
    
    public void themHDCT() {
        int i = tb_hoadon.getSelectedRow();
        int j = tb_hanghoa.getSelectedRow();
        int idHH = Integer.parseInt(tb_hanghoa.getValueAt(j, 0).toString());
        int soLuong = Integer.parseInt(tb_hanghoa.getValueAt(j, 7).toString());
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoLuong(soLuong);
        hdct.setTrangThai(true);
        hdct.setId_CTHangHoa(idHH);
        hdct.setId_HoaDon(idHD);
        hdctService.themHoaDonChiTiet(hdct);
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        int k = tb_hoadon.getSelectedRow();
        dtm.setValueAt(getTongTien(), k, 6);
    }

    public void loadCBB() {
        HangHoaJFrame.loadCbbDanhMuc(cbb_danhmuc);
        HangHoaJFrame.loadCbbLoaiHang(cbb_loaihang);
        loadCbbKhachHang();
    }

    public void setKhacHang() {
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        if (kh != null) {
            lb_masothue.setText(kh.getMaSoThue());
            lb_ten.setText(kh.getTen());
        }
    }

    //load bảng hàng hóa
    public void loadListChiTietHangHoa() {
        cthhList = cthhService.findAllChiTietHangHoa();
    }
    
    public String tenHangToString(int idCTHH) {
        ChiTietHangHoa cthh = cthhService.findIdChiTietHangHoa(idCTHH);
        HangHoa hh = HangHoaJFrame.hhService.findIdHangHoa(cthh.getId_HangHoa());
        String tenHang = hh.getTenHang();
        String mauSac = "";
            System.out.println(cthh.getId_MauSac());
            if (cthh.getId_MauSac() != 0) {
                mauSac = "_Màu:" + msService.findMauSacId(cthh.getId_MauSac()).getTenMau();
            }
            tenHang += mauSac;
            String kichThuoc = "";
            if (cthh.getId_KichThuoc() != 0) {
                kichThuoc = "_Kích thước:" + ktService.findKichThuocId(cthh.getId_KichThuoc()).getTenKichThuoc();
            }
            tenHang += kichThuoc;
            String apSuat = "";
            if (cthh.getId_ApSuat() != 0) {
                apSuat = "_Áp suất:" + asService.findApSuatId(cthh.getId_ApSuat()).getTenApSuat();
            }
            tenHang += apSuat;
            String chieuDay = "";
            if (cthh.getId_ChieuDay() != 0) {
                double cd = HangHoaJFrame.cdService.findChieuDayId(cthh.getId_ChieuDay()).getDoDay();
                chieuDay = "_Độ dày:" + cd;
            }
            tenHang += chieuDay;
        
        return tenHang;
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
            String mauSac = "";
            System.out.println(cthh.getId_MauSac());
            if (cthh.getId_MauSac() != 0) {
                mauSac = msService.findMauSacId(cthh.getId_MauSac()).getTenMau();
            }
            String kichThuoc = "";
            if (cthh.getId_KichThuoc() != 0) {
                kichThuoc = ktService.findKichThuocId(cthh.getId_KichThuoc()).getTenKichThuoc();
            }
            String apSuat = asService.findApSuatId(cthh.getId_ApSuat()).getTenApSuat();
            Double chieuDay = HangHoaJFrame.cdService.findChieuDayId(cthh.getId_ChieuDay()).getDoDay();
            String donViTinh = dvtService.findDonViTinhId(cthh.getId_DonViTinh()).getTenDonVi();
            dtm.addRow(new Object[]{cthh.getId(), tenHang, kichThuoc, mauSac, chieuDay, apSuat, donViTinh, 0});
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
        lb_tongtien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lb_vat = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lb_ten = new javax.swing.JLabel();
        btn_taohoadon = new javax.swing.JButton();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        ta_ghichu = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tf_diachigiao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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
        lb_masothue.setText("   ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Xuất hóa đơn");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tổng tiền:");

        lb_tongtien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_tongtien.setForeground(new java.awt.Color(204, 0, 0));
        lb_tongtien.setText("30.000.000 đ");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/add-button.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("VAT:");

        lb_vat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_vat.setText("3.000.000 đ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tên:");

        lb_ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_ten.setText("   ");

        btn_taohoadon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_taohoadon.setText("Tạo hóa đơn");
        btn_taohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taohoadonActionPerformed(evt);
            }
        });

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
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Nhân viên tạo", "SDT khách hàng", "Tên", "Địa chỉ giao hàng", "Thanh toán", "Đặt cọc", "Trạng thái hóa đơn", "Trạng thái thanh toán", "Ghi chú"
            }
        ));
        tb_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoadonMouseClicked(evt);
            }
        });
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
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HDCT", "Tên hàng", "Kích thước", "Màu sắc", "Độ dày", "Áp suất", "Đơn vị tính", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, false, true
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));
        jPanel5.setToolTipText("Hóa đơn chi tiết");

        tb_hoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên hàng", "Số lượng", "Đơn vị tính", "Đơn giá", "Thành tiền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        ta_ghichu.setColumns(20);
        ta_ghichu.setRows(5);
        jScrollPane4.setViewportView(ta_ghichu);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Địa chỉ giao hàng:");

        tf_diachigiao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ghi chú:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbb_danhmuc, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                                            .addComponent(cbb_khachhang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(cbb_loaihang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lb_ten))
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_diachigiao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lb_masothue)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(69, 69, 69)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btn_taohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_tongtien)
                        .addGap(90, 90, 90)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_vat)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel16)))
                .addGap(36, 36, 36)
                .addComponent(jButton5)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_taohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_danhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_loaihang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cbb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(lb_masothue)
                                        .addComponent(jLabel4))
                                    .addGap(13, 13, 13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(lb_ten))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(tf_diachigiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_tongtien)
                            .addComponent(lb_vat)))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
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
        themHDCT();
        loadTableHDCT();
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

    private void btn_taohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taohoadonActionPerformed
        // TODO add your handling code here:
        taoHoaDon();
    }//GEN-LAST:event_btn_taohoadonActionPerformed

    private void tb_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadonMouseClicked
        // TODO add your handling code here:
        loadTableHDCT();
    }//GEN-LAST:event_tb_hoadonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_taohoadon;
    private javax.swing.JComboBox<String> cbb_danhmuc;
    public static javax.swing.JComboBox<String> cbb_khachhang;
    private javax.swing.JComboBox<String> cbb_loaihang;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lb_masothue;
    private javax.swing.JLabel lb_ten;
    private javax.swing.JLabel lb_tongtien;
    private javax.swing.JLabel lb_vat;
    private javax.swing.JMenuItem mitem_huyhang;
    private javax.swing.JMenuItem mitem_themhang;
    private javax.swing.JPopupMenu pop_hanghoa;
    private javax.swing.JPopupMenu pop_hoadon;
    private javax.swing.JTextArea ta_ghichu;
    private javax.swing.JTable tb_hanghoa;
    private javax.swing.JTable tb_hoadon;
    private javax.swing.JTable tb_hoadonchitiet;
    private javax.swing.JTextField tf_diachigiao;
    // End of variables declaration//GEN-END:variables
}
