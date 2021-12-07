/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Models.ApSuat;
import DAO.Models.ChiTietHangHoa;
import DAO.Models.ChieuDay;
import DAO.Models.DanhMuc;
import DAO.Models.DonViTinh;
import DAO.Models.HangHoa;
import DAO.Models.KichThuoc;
import DAO.Models.LoaiHang;
import DAO.Models.MauSac;
import DAO.Models.NhaCungCap;
import DAO.Models.TaiKhoan;
import Service.Implement.ApSuatService;
import Service.Implement.ChiTietHangHoaService;
import Service.Implement.ChieuDayService;
import Service.Implement.DanhMucService;
import Service.Implement.DonViTinhService;
import Service.Implement.HangHoaService;
import Service.Implement.KichThuocService;
import Service.Implement.LoaiHangService;
import Service.Implement.MauSacService;
import Service.Implement.NhaCungCapService;
import Utils.utilityHelper;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sangt
 */
public class HangHoaJFrame extends javax.swing.JInternalFrame {

    static HangHoaService hhService = new HangHoaService();
    ChiTietHangHoaService cthhService = new ChiTietHangHoaService();
    static ChieuDayService cdService = new ChieuDayService();
    List<ChiTietHangHoa> cthhList;
    static DanhMucService dmService = new DanhMucService();
    static LoaiHangService lhService = new LoaiHangService();
    static MauSacService msService = new MauSacService();
    static KichThuocService ktService = new KichThuocService();
    static ApSuatService asService = new ApSuatService();
    static DonViTinhService dvtService = new DonViTinhService();
    static NhaCungCapService nccService = new NhaCungCapService();
    static DefaultComboBoxModel dcm;
    static DefaultTableModel dtm;
    TaiKhoan tk;

    public HangHoaJFrame(TaiKhoan tk) {
        initComponents();
        this.tk = tk;
        if (tk.isVaiTro() == false) {
            btn_sua.setEnabled(false);
            btn_them.setEnabled(false);
            btn_xoa.setEnabled(false);
        }
        init();
    }

    public void init() {
        loadCbbForm();
        loadTable();
    }

    public void loadListChiTietHangHoa() {
        cthhList = cthhService.findAllChiTietHangHoa();
    }

    public void loadTable() {
        loadListChiTietHangHoa();
        dtm = (DefaultTableModel) tb_hanghoa.getModel();
        dtm.setRowCount(0);
        HangHoa hh;
        for (ChiTietHangHoa cthh : cthhList) {
            hh = hhService.findIdHangHoa(cthh.getId_HangHoa());
            String tenHang = hh.getTenHang();
            String ncc = nccService.findNhaCungCapById(hh.getId_NhaCungCap()).getTenNCC();
            String danhMuc = dmService.findDanhMucById(hh.getId_DanhMuc()).getTenDanhMuc();
            String loaiHang = lhService.findIdLoaiHang(cthh.getId_LoaiHang()).getTenLoai();
            String mauSac = "";
            if (cthh.getId_MauSac() != 0) {
                mauSac = msService.findMauSacId(cthh.getId_MauSac()).getTenMau();
            }
            String kichThuoc = "";
            if (cthh.getId_KichThuoc() != 0) {
                kichThuoc = ktService.findKichThuocId(cthh.getId_KichThuoc()).getTenKichThuoc();
            }
            String apSuat = "";
            if (cthh.getId_ApSuat() != 0) {
                apSuat = asService.findApSuatId(cthh.getId_ApSuat()).getTenApSuat();
            }
            Double chieuDay = null;
            if (cthh.getId_ChieuDay() != 0) {
                chieuDay = cdService.findChieuDayId(cthh.getId_ChieuDay()).getDoDay();
            }
            String donViTinh = dvtService.findDonViTinhId(cthh.getId_DonViTinh()).getTenDonVi();
            dtm.addRow(new Object[]{tenHang, ncc, danhMuc, loaiHang, mauSac, kichThuoc, apSuat, chieuDay, donViTinh, cthh.getSoLuong(), cthh.getGiaNhap(), cthh.getGiaBan(), cthh.getGhiChu()});
        }
    }

    public static void loadCbbForm() {
        loadCbbNhaCungCap();
        loadCbbDanhMuc(cbb_danhmuc);
        loadCbbLoaiHang(cbb_loaihang);
        loadCbbMauSac();
        loadCbbKichThuoc();
        loadCbbApSuat();
        loadCbbDonViTinh();
    }

    //load object lên combobox
    public static void loadCbbDanhMuc(JComboBox cbb) {
        dcm = (DefaultComboBoxModel) cbb.getModel();
        dcm.removeAllElements();
        List<DanhMuc> dmList = dmService.findAllDanhMuc();
        for (DanhMuc d : dmList) {
            dcm.addElement(d);
        }
    }

    public static void loadCbbLoaiHang(JComboBox cbb) {
        dcm = (DefaultComboBoxModel) cbb.getModel();
        dcm.removeAllElements();
        List<LoaiHang> lhList = lhService.findAllLoaiHang();
        for (LoaiHang l : lhList) {
            dcm.addElement(l);
        }
    }

    public static void loadCbbMauSac() {
        dcm = (DefaultComboBoxModel) cbb_mausac.getModel();
        dcm.removeAllElements();
        dcm.addElement(addMauSacNull());
        List<MauSac> msList = msService.findAllMauSac();
        for (MauSac m : msList) {
            dcm.addElement(m);
        }
    }

    public static MauSac addMauSacNull() {
        MauSac ms = new MauSac("", true);
        return ms;
    }

    public static void loadCbbKichThuoc() {
        dcm = (DefaultComboBoxModel) cbb_kichthuoc.getModel();
        dcm.removeAllElements();
        dcm.addElement(addKichThuocNull());
        List<KichThuoc> ktList = ktService.findAllKichThuoc();
        for (KichThuoc k : ktList) {
            dcm.addElement(k);
        }
    }

    public static KichThuoc addKichThuocNull() {
        KichThuoc kt = new KichThuoc("", true);
        return kt;
    }

    public static void loadCbbApSuat() {
        dcm = (DefaultComboBoxModel) cbb_apsuat.getModel();
        dcm.removeAllElements();
        dcm.addElement(addApSuatNull());
        List<ApSuat> asList = asService.findAllApSuat();
        for (ApSuat a : asList) {
            dcm.addElement(a);
        }
    }

    public static ApSuat addApSuatNull() {
        ApSuat as = new ApSuat("", true);
        return as;
    }

    public static void loadCbbDonViTinh() {
        dcm = (DefaultComboBoxModel) cbb_donvitinh.getModel();
        dcm.removeAllElements();
        List<DonViTinh> dvtList = dvtService.findAllDonViTinh();
        for (DonViTinh d : dvtList) {
            dcm.addElement(d);
        }
    }

    public static void loadCbbNhaCungCap() {
        dcm = (DefaultComboBoxModel) cbb_nhacungcap.getModel();
        dcm.removeAllElements();
        List<NhaCungCap> nccList = nccService.findAllNhaCungCap();
        for (NhaCungCap ncc : nccList) {
            dcm.addElement(ncc);
        }
    }

    //Lấy các đối tượng cbb đang chọn
    public NhaCungCap getNCC() {
        dcm = (DefaultComboBoxModel) cbb_nhacungcap.getModel();
        return (NhaCungCap) dcm.getSelectedItem();
    }

    public DanhMuc getDanhMuc() {
        dcm = (DefaultComboBoxModel) cbb_danhmuc.getModel();
        return (DanhMuc) dcm.getSelectedItem();
    }

    public LoaiHang getLoaiHang() {
        dcm = (DefaultComboBoxModel) cbb_loaihang.getModel();
        return (LoaiHang) dcm.getSelectedItem();
    }

    public MauSac getMauSac() {
        dcm = (DefaultComboBoxModel) cbb_mausac.getModel();
        return (MauSac) dcm.getSelectedItem();
    }

    public KichThuoc getKichThuoc() {
        dcm = (DefaultComboBoxModel) cbb_kichthuoc.getModel();
        return (KichThuoc) dcm.getSelectedItem();
    }

    public ApSuat getApSuat() {
        dcm = (DefaultComboBoxModel) cbb_apsuat.getModel();
        return (ApSuat) dcm.getSelectedItem();
    }

    public DonViTinh getDonViTinh() {
        dcm = (DefaultComboBoxModel) cbb_donvitinh.getModel();
        return (DonViTinh) dcm.getSelectedItem();
    }

    public void clearForm() {
        loadCbbForm();
        NhaCungCapJFrame.clearTextFiel(tf_tenhang, tf_chieuday, tf_giaban, tf_gianhap, tf_soluong);
        ta_ghichu.setText("");
    }

    public HangHoa getHangHoa() {
        int Id_NhaCungCap = getNCC().getId();
        int Id_DanhMuc = getDanhMuc().getId();
        String tenHangHoa = tf_tenhang.getText();
        HangHoa hh = new HangHoa(tenHangHoa, Id_NhaCungCap, Id_DanhMuc);
        return hh;
    }

    public ChieuDay timChieuDayTheoTen() {
        return cdService.findChieuDayDouble(utilityHelper.parseDouble(tf_chieuday));
    }

    public HangHoa timHangTheoTen(String name) {
        return hhService.findNameHangHoa(name);
    }

    public ChiTietHangHoa getCTHH() {
        int soLuong = utilityHelper.parseInt(tf_soluong);
        int giaNhap = utilityHelper.parseInt(tf_gianhap);
        int giaBan = utilityHelper.parseInt(tf_giaban);
        String ghiChu = ta_ghichu.getText().trim();
        Integer Id_KichThuoc = getKichThuoc().getId();
        Integer Id_MauSac = getMauSac().getId();
        int Id_HangHoa = timHangTheoTen(tf_tenhang.getText()).getId();
        Integer Id_ApSuat = getApSuat().getId();
        int Id_DonViTinh = getDonViTinh().getId();
        int Id_LoaiHang = getLoaiHang().getId();
        Integer Id_ChieuDay = timChieuDayTheoTen().getId();
        if (Id_ChieuDay == 0) {
            Id_ChieuDay = null;
        }
        ChiTietHangHoa cthh = new ChiTietHangHoa(soLuong, giaNhap, giaBan, ghiChu, true, Id_KichThuoc, Id_MauSac, Id_HangHoa, Id_ApSuat, Id_DonViTinh, Id_LoaiHang, Id_ChieuDay);
        return cthh;
    }

    public void themHangHoa() {
        hhService.themHangHoa(getHangHoa());
    }

    public void fillForm() {
        int i = tb_hanghoa.getSelectedRow();
        fillCbbApSuat(i);
        fillCbbDanhMuc(i);
        fillCbbDonViTinh(i);
        fillCbbKichThuoc(i);
        fillCbbMauSac(i);
        fillCbbNCC(i);
        fillCbbLoaiHang(i);
        fillHangHoa(i);
    }

    public void fillHangHoa(int i) {
        String hangHoa = tb_hanghoa.getValueAt(i, 0).toString();
        tf_tenhang.setText(hangHoa);
        String chieuDay = tb_hanghoa.getValueAt(i, 7).toString();
        tf_chieuday.setText(chieuDay);
        String soLuong = tb_hanghoa.getValueAt(i, 9).toString();
        tf_soluong.setText(soLuong);
        String giaNhap = tb_hanghoa.getValueAt(i, 10).toString();
        tf_gianhap.setText(giaNhap);
        String giaBan = tb_hanghoa.getValueAt(i, 11).toString();
        tf_giaban.setText(giaBan);
        String ghiChu = tb_hanghoa.getValueAt(i, 12).toString();
        ta_ghichu.setText(ghiChu);
    }

    public void fillCbbNCC(int i) {
        String tenNCC = tb_hanghoa.getValueAt(i, 1).toString();
        NhaCungCap ncc = nccService.findNhaCungCapByName(tenNCC);
        dcm = (DefaultComboBoxModel) cbb_nhacungcap.getModel();
        dcm.setSelectedItem(ncc);
    }

    public void fillCbbDanhMuc(int i) {
        String tenDanhMuc = tb_hanghoa.getValueAt(i, 2).toString();
        DanhMuc dm = dmService.findDanhMucByName(tenDanhMuc);
        dcm = (DefaultComboBoxModel) cbb_danhmuc.getModel();
        dcm.setSelectedItem(dm);
    }

    public void fillCbbMauSac(int i) {
        String tenMau = tb_hanghoa.getValueAt(i, 4).toString();
        MauSac ms = msService.findMauSacName(tenMau);
        dcm = (DefaultComboBoxModel) cbb_mausac.getModel();
        dcm.setSelectedItem(ms);
    }

    public void fillCbbKichThuoc(int i) {
        String tenKichThuoc = tb_hanghoa.getValueAt(i, 5).toString();
        KichThuoc kt = ktService.findKichThuocName(tenKichThuoc);
        dcm = (DefaultComboBoxModel) cbb_kichthuoc.getModel();
        dcm.setSelectedItem(kt);
    }

    public void fillCbbApSuat(int i) {
        String tenApSuat = tb_hanghoa.getValueAt(i, 6).toString();
        ApSuat as = asService.findApSuatName(tenApSuat);
        dcm = (DefaultComboBoxModel) cbb_apsuat.getModel();
        dcm.setSelectedItem(as);
    }

    public void fillCbbDonViTinh(int i) {
        String tenDonVi = tb_hanghoa.getValueAt(i, 8).toString();
        DonViTinh dvt = dvtService.findDonViTinhName(tenDonVi);
        dcm = (DefaultComboBoxModel) cbb_donvitinh.getModel();
        dcm.setSelectedItem(dvt);
    }

    public void fillCbbLoaiHang(int i) {
        String tenLoai = tb_hanghoa.getValueAt(i, 3).toString();
        LoaiHang lh = lhService.findNameLoaiHang(tenLoai);
        dcm = (DefaultComboBoxModel) cbb_loaihang.getModel();
        dcm.setSelectedItem(lh);
    }

    public void themChieuDay() {
        cdService.themChieuDay(new ChieuDay(utilityHelper.parseDouble(tf_chieuday)));
    }

    public void themCTHH() {
        if (tf_chieuday.getText().trim().isEmpty()) {
            themHangHoa();
            cthhService.themHangHoaChiTiet(getCTHH());
            loadTable();
        } else {
            themHangHoa();
            themChieuDay();
            cthhService.themHangHoaChiTiet(getCTHH());
            loadTable();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pn_iinput = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbb_nhacungcap = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbb_danhmuc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tf_tenhang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbb_loaihang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbb_mausac = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbb_kichthuoc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbb_apsuat = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tf_chieuday = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_ghichu = new javax.swing.JTextArea();
        cbb_donvitinh = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tf_soluong = new javax.swing.JTextField();
        tf_gianhap = new javax.swing.JTextField();
        tf_giaban = new javax.swing.JTextField();
        btn_QLNhaCungCap = new javax.swing.JLabel();
        btn_QLMauSac = new javax.swing.JLabel();
        btn_QLKichThuoc = new javax.swing.JLabel();
        btn_QLApSuat = new javax.swing.JLabel();
        btn_QLDonViTinh = new javax.swing.JLabel();
        btn_themLoaiHang = new javax.swing.JLabel();
        btn_QLDanhMuc = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_lammoi = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tf_timkiem = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("QUẢN LÝ HÀNG HÓA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("QUẢN LÝ HÀNG HÓA");

        pn_iinput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Danh mục:");

        cbb_nhacungcap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbb_nhacungcap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_nhacungcapItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nhà cung cấp:");

        cbb_danhmuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tên hàng:");

        tf_tenhang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Loại vật tư:");

        cbb_loaihang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Màu sắc:");

        cbb_mausac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Kích thước:");

        cbb_kichthuoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Áp suất:");

        cbb_apsuat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Chiều dày:");

        tf_chieuday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Đơn vị tính:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Ghi chú:");

        ta_ghichu.setColumns(20);
        ta_ghichu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ta_ghichu.setRows(5);
        jScrollPane1.setViewportView(ta_ghichu);

        cbb_donvitinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Số lượng:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Giá nhập:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Giá bán:");

        tf_soluong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_gianhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_giaban.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_giaban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_giabanActionPerformed(evt);
            }
        });

        btn_QLNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLNhaCungCapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_QLNhaCungCapMouseEntered(evt);
            }
        });

        btn_QLMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLMauSacMouseClicked(evt);
            }
        });

        btn_QLKichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLKichThuocMouseClicked(evt);
            }
        });

        btn_QLApSuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLApSuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLApSuatMouseClicked(evt);
            }
        });

        btn_QLDonViTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLDonViTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLDonViTinhMouseClicked(evt);
            }
        });

        btn_themLoaiHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_themLoaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themLoaiHangMouseClicked(evt);
            }
        });

        btn_QLDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/suaHangHoa.png"))); // NOI18N
        btn_QLDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QLDanhMucMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_iinputLayout = new javax.swing.GroupLayout(pn_iinput);
        pn_iinput.setLayout(pn_iinputLayout);
        pn_iinputLayout.setHorizontalGroup(
            pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_iinputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_nhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_danhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tenhang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cbb_loaihang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_themLoaiHang)
                    .addComponent(btn_QLDanhMuc)
                    .addComponent(btn_QLNhaCungCap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(cbb_mausac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(cbb_kichthuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(cbb_apsuat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_chieuday, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_QLMauSac)
                    .addComponent(btn_QLKichThuoc)
                    .addComponent(btn_QLApSuat))
                .addGap(61, 61, 61)
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(cbb_donvitinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(tf_soluong)
                    .addComponent(tf_gianhap)
                    .addComponent(tf_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_QLDonViTinh)
                .addGap(87, 87, 87)
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pn_iinputLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbb_apsuat, cbb_danhmuc, cbb_donvitinh, cbb_kichthuoc, cbb_loaihang, cbb_mausac, cbb_nhacungcap, tf_chieuday, tf_giaban, tf_gianhap, tf_soluong, tf_tenhang});

        pn_iinputLayout.setVerticalGroup(
            pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_iinputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_iinputLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pn_iinputLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(8, 8, 8)
                            .addComponent(tf_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pn_iinputLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_QLDonViTinh)
                                .addComponent(cbb_donvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(126, 126, 126)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_iinputLayout.createSequentialGroup()
                        .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_iinputLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(pn_iinputLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_mausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_QLMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_QLKichThuoc)
                                    .addComponent(cbb_kichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)))
                        .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_QLApSuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_apsuat))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_chieuday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_iinputLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbb_nhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_QLNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_QLDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_danhmuc))
                        .addGap(18, 18, 18)
                        .addGroup(pn_iinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_iinputLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_tenhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_loaihang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_themLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btn_lammoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/cleaning.png"))); // NOI18N
        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });
        jPanel1.add(btn_lammoi);

        btn_them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/them.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel1.add(btn_them);

        btn_sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_sua);

        btn_xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/remove.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        jPanel1.add(btn_xoa);

        tb_hanghoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_hanghoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hàng hóa", "Nhà cung cấp", "Danh mục", "Loại hàng", "Màu sắc", "Kích thước", "Áp suất", "Chiều dày", "Đơn vị tính", "Số lượng", "Giá nhập", "Giá bán", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hanghoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hanghoaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_hanghoa);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Tìm kiếm:");

        tf_timkiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tf_timkiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_timkiem)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tf_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(412, 412, 412))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_iinput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_iinput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_giabanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_giabanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_giabanActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        themCTHH();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_QLNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLNhaCungCapMouseClicked
        // TODO add your handling code here:
        NhaCungCapJFrame ncc = new NhaCungCapJFrame(tk);
        ncc.setVisible(true);
    }//GEN-LAST:event_btn_QLNhaCungCapMouseClicked

    private void btn_themLoaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themLoaiHangMouseClicked
        // TODO add your handling code here:
        LoaiHangJFrame lh = new LoaiHangJFrame(tk);
        lh.setVisible(true);
    }//GEN-LAST:event_btn_themLoaiHangMouseClicked

    private void btn_QLMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLMauSacMouseClicked
        // TODO add your handling code here:
        MauSacJFrame ms = new MauSacJFrame(tk);
        ms.setVisible(true);
    }//GEN-LAST:event_btn_QLMauSacMouseClicked

    private void btn_QLApSuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLApSuatMouseClicked
        // TODO add your handling code here:
        ApSuatJFrame as = new ApSuatJFrame(tk);
        as.setVisible(true);
    }//GEN-LAST:event_btn_QLApSuatMouseClicked

    private void btn_QLDonViTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLDonViTinhMouseClicked
        // TODO add your handling code here:
        DonViTinhJFrame dvt = new DonViTinhJFrame(tk);
        dvt.setVisible(true);
    }//GEN-LAST:event_btn_QLDonViTinhMouseClicked

    private void btn_QLKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLKichThuocMouseClicked
        // TODO add your handling code here:
        KichThuocJFrame kt = new KichThuocJFrame(tk);
        kt.setVisible(true);
    }//GEN-LAST:event_btn_QLKichThuocMouseClicked

    private void btn_QLDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLDanhMucMouseClicked
        // TODO add your handling code here:
        DanhMucJFrame dm = new DanhMucJFrame(tk);
        dm.setVisible(true);
    }//GEN-LAST:event_btn_QLDanhMucMouseClicked

    private void btn_QLNhaCungCapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QLNhaCungCapMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_QLNhaCungCapMouseEntered

    private void cbb_nhacungcapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_nhacungcapItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_nhacungcapItemStateChanged

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        themHangHoa();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void tb_hanghoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hanghoaMouseClicked
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_tb_hanghoaMouseClicked

    private void tf_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tf_timkiemCaretUpdate
        // TODO add your handling code here:
        String timkiem = tf_timkiem.getText().trim();

    }//GEN-LAST:event_tf_timkiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_QLApSuat;
    private javax.swing.JLabel btn_QLDanhMuc;
    private javax.swing.JLabel btn_QLDonViTinh;
    private javax.swing.JLabel btn_QLKichThuoc;
    private javax.swing.JLabel btn_QLMauSac;
    private javax.swing.JLabel btn_QLNhaCungCap;
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JLabel btn_themLoaiHang;
    private javax.swing.JButton btn_xoa;
    public static javax.swing.JComboBox<String> cbb_apsuat;
    public static javax.swing.JComboBox<String> cbb_danhmuc;
    public static javax.swing.JComboBox<String> cbb_donvitinh;
    public static javax.swing.JComboBox<String> cbb_kichthuoc;
    public static javax.swing.JComboBox<String> cbb_loaihang;
    public static javax.swing.JComboBox<String> cbb_mausac;
    public static javax.swing.JComboBox<String> cbb_nhacungcap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pn_iinput;
    private javax.swing.JTextArea ta_ghichu;
    public final javax.swing.JTable tb_hanghoa = new javax.swing.JTable();
    private javax.swing.JTextField tf_chieuday;
    private javax.swing.JTextField tf_giaban;
    private javax.swing.JTextField tf_gianhap;
    private javax.swing.JTextField tf_soluong;
    private javax.swing.JTextField tf_tenhang;
    private javax.swing.JTextField tf_timkiem;
    // End of variables declaration//GEN-END:variables
}
