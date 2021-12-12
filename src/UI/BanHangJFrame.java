/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import static UI.HangHoaJFrame.asService;
import static UI.HangHoaJFrame.dvtService;
import static UI.HangHoaJFrame.ktService;
import static UI.HangHoaJFrame.msService;
import Utils.utilityHelper;
import Utils.validateHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sangt
 */
public class BanHangJFrame extends javax.swing.JInternalFrame {

    int iRowHD;
    public Integer soLuong;
    public Integer id_HangHoa;
    ChiTietHangHoa cthhClick;
    HoaDonChiTiet hdctClick;
    HoaDon hdClick;
    HoaDonService hdService = new HoaDonService();
    HoaDonChiTietService hdctService = new HoaDonChiTietService();
    HangHoaService hhService = new HangHoaService();
    static List<KhachHang> khList;
    static TaiKhoan tk;
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
        loadListChiTietHangHoa();
        loadTableHangHoa();
        loadTableHD();
        AutoCompleteDecorator.decorate(cbb_khachhang);
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = 100;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(15);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    public static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    public static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {
            int i = tb_hoadon.getSelectedRow();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa dd/MM/yyyy");
            String d = sdf.format(hdClick.getNgayTao());
            String sdtKhach = String.valueOf(tb_hoadon.getValueAt(i, 3));

            int result = Printable.NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    //g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
                    y += yShift + 30;
                    g2d.drawString("---------------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("Hóa đơn: " + hdClick.getId(), 12, y);
                    y += yShift;
                    g2d.drawString("Ngày tạo: " + d, 12, y);
                    y += yShift;
                    g2d.drawString("SDT khách hàng: " + sdtKhach, 12, y);
                    y += yShift;
                    g2d.drawString("Địa chỉ giao hàng: " + hdClick.getDiaChi(), 12, y);
                    y += yShift;
                    g2d.drawString("Nhân viên tạo hóa đơn: " + tk.getTenTK(), 12, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString(" Tên hàng                                    Thành tiền   ", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------", 10, y);
                    y += headerRectHeight;

                    for (int s = 0; s < tb_hoadonchitiet.getRowCount(); s++) {
                        String tenHang = String.valueOf(tb_hoadonchitiet.getValueAt(s, 1));
                        String soLuong = String.valueOf(tb_hoadonchitiet.getValueAt(s, 2));
                        String donGia = String.valueOf(tb_hoadonchitiet.getValueAt(s, 4));
                        String thanhTien = String.valueOf(tb_hoadonchitiet.getValueAt(s, 5));
                        g2d.drawString(" " + tenHang + "                                       ", 10, y);
                        y += yShift;
                        g2d.drawString("      " + soLuong + " * " + setVND(Integer.parseInt(donGia)), 10, y);
                        g2d.drawString(setVND(Integer.parseInt(thanhTien)), 260, y);
                        y += yShift;

                    }
                    g2d.drawString("---------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tổng tiền hàng:                              " + setVND(getTongTienHang()) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" VAT      :                                   " + setVND(getTongTienHang() / 10) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Phí vận chuyển   :                           " + setVND(hdClick.getPhiShip()) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tổng chi phí   :                             " + setVND(hdClick.getTongTienTT()) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString(" Đặt cọc   :                                  " + setVND(hdClick.getDatCoc()) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString(" Số tiền còn phải thanh toán   :              " + setVND(hdClick.getTongTienTT() - hdClick.getThanhTien()) + "   ", 10, y);
                    y += yShift;
                    g2d.drawString(" ", 10, y);
                    y += yShift;
                    g2d.drawString("*********************************************************", 10, y);
                    y += yShift;
                    g2d.drawString("                 THANK YOU COME AGAIN!                   ", 10, y);
                    y += yShift;
                    g2d.drawString("*********************************************************", 10, y);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = Printable.PAGE_EXISTS;
            }
            return result;
        }
    }

    public static void loadListKH() {
        khList = khService.findAllKhachHang();
    }

    public void loadListHD() {
        hdList = hdService.findAllHoaDon();
    }

    public void setCTHH() {
        int i = tb_hanghoa.getSelectedRow();
        int id = Integer.parseInt(tb_hanghoa.getValueAt(i, 0).toString());
        cthhClick = cthhService.findIdChiTietHangHoa(id);
    }

    public void setHDClick() {
        int i = tb_hoadon.getSelectedRow();
        iRowHD = i;
        int id = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        hdClick = hdService.findHoaDonId(id);
    }

    public void loadTableHD() {
        loadListHD();
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : hdList) {
            KhachHang kh = khService.findKhachHangId(hd.getId_KhachHang());
            String TTHoaDon = hd.getTrangThaiHD() == 1 ? "Đang xử lý" : hd.getTrangThaiHD() == 2 ? "Đang giao" : "Hoàn thành";
            String TTThanhToan = hd.getTrangThaiTT() == 1 ? "Chưa cọc" : hd.getTrangThaiTT() == 2 ? "Chưa thanh toán" : "Đã thanh toán";
            dtm.addRow(new Object[]{hd.getId(), hd.getNgayTao(), tk.getTenTK(), kh.getSoDienThoai(), kh.getTen(), hd.getDiaChi(), hd.getPhiShip(), hd.getTongTienTT(), hd.getDatCoc(), hd.getThanhTien(), TTThanhToan, TTHoaDon, hd.getGhiChu()});
        }
    }

    public void loadTableHDNotList() {
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : hdList) {
            KhachHang kh = khService.findKhachHangId(hd.getId_KhachHang());
            String TTHoaDon = hd.getTrangThaiHD() == 1 ? "Đang xử lý" : hd.getTrangThaiHD() == 2 ? "Đang giao" : "Hoàn thành";
            String TTThanhToan = hd.getTrangThaiTT() == 1 ? "Chưa cọc" : hd.getTrangThaiTT() == 2 ? "Chưa thanh toán" : "Đã thanh toán";
            dtm.addRow(new Object[]{hd.getId(), hd.getNgayTao(), tk.getTenTK(), kh.getSoDienThoai(), kh.getTen(), hd.getDiaChi(), hd.getPhiShip(), hd.getTongTienTT(), hd.getDatCoc(), hd.getThanhTien(), TTThanhToan, TTHoaDon, hd.getGhiChu()});
        }
    }

    public void loadListHDCT() {
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        System.out.println(idHD);
        hdctListClick = hdctService.findAllHoaDonChiTietByIdHD(idHD);
    }

    public void setVND(JLabel lb, int tien) {
        Locale l = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(l);
        lb.setText(format.format(tien));
    }

    public String setVND(int tien) {
        Locale l = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(l);
        return format.format(tien);
    }

    public int getTongTienHang() {
        int sum = 0;
        for (int i = 0; i < tb_hoadonchitiet.getRowCount(); i++) {
            int thanhTien = Integer.parseInt(tb_hoadonchitiet.getValueAt(i, 5).toString());
            sum += thanhTien;
        }
        return sum;
    }

    public void loadTableHDCT() {
        loadListHDCT();
        dtm = (DefaultTableModel) tb_hoadonchitiet.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet hdct : hdctListClick) {

            ChiTietHangHoa cthh = cthhService.findIdChiTietHangHoa(hdct.getId_CTHangHoa());
            DonViTinh dvt = dvtService.findDonViTinhId(cthh.getId_DonViTinh());
            int soLuong = hdct.getSoLuong();
            int giaBan = cthh.getGiaBan();
            int thanhTien = soLuong * giaBan;
            dtm.addRow(new Object[]{hdct.getId(), tenHangToString(hdct.getId_CTHangHoa()), soLuong, dvt.getTenDonVi(), giaBan, thanhTien});
        }
        setTien();
    }

    public void setHDCTClick() {
        int i = tb_hoadonchitiet.getSelectedRow();
        int idHDCT = Integer.parseInt(String.valueOf(tb_hoadonchitiet.getValueAt(i, 0)));
        hdctClick = hdctService.findHoaDonChiTietId(idHDCT);
    }

    public void xoaHDCT() {
        setHDCTClick();
        hdctClick.setTrangThai(false);
        hdctService.suaHoaDonChiTiet(hdctClick);
        ChiTietHangHoa cthh = cthhService.findIdChiTietHangHoa(hdctClick.getId_CTHangHoa());
        cthh.setSoLuong(cthh.getSoLuong() + hdctClick.getSoLuong());
        cthhService.suaHangHoaChiTiet(cthh);
        setTienThanhToanHD();
        // setGhiChuHuyHang();
        loadTableHDCT();
        loadListChiTietHangHoa();
        loadTableHangHoa();
        loadTableHD();
    }

    public void setTienThanhToanHD() {
        setHDClick();
        HoaDon hd = hdClick;
        hd.setTongTienTT(getTongTienHang() + getTongTienHang() / 10 + hd.getPhiShip());
        hdService.suaHoaDon(hd);
        int i = tb_hoadon.getSelectedRow();
        setValueTBHoaDon(String.valueOf(getTongTienHang() + getTongTienHang() / 10 + hd.getPhiShip()), i, 7);
    }

    public int getDatCocHD() {
        iRowHD = tb_hoadon.getSelectedRow();
        int phiShip = Integer.parseInt(tb_hoadon.getValueAt(iRowHD, 6).toString());
        return getTongTienHang() * 20 / 100 + phiShip;
    }

    public int getTienThanhToan() {
        setHDClick();
        return hdClick.getPhiShip() + getTongTienHang() / 10 + getTongTienHang();
    }

    //BUG HỦY HÀNG SET LẠI GHI CHÚ TRONG HÓA ĐƠN
    public void setGhiChuHuyHang() {
        int i = tb_hoadonchitiet.getSelectedRow();
        String tenHang = tb_hoadonchitiet.getValueAt(i, 1).toString();
        String ghiChu = tk.getTenTK() + " Hủy " + hdctClick.getSoLuong() + " " + tenHang;
        setHDClick();
        hdClick.setGhiChu(ghiChu);
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        dtm.setValueAt(ghiChu, i, 0);
        hdService.suaHoaDon(hdClick);
    }

    public static void loadCbbKhachHang() {
        loadListKH();
        //new BanHangJFrame(tk);
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        dcm.removeAllElements();
        for (KhachHang kh : khList) {
            dcm.addElement(kh);
        }
    }

    //set Trạng thái hóa đơn
    public void setValueTBHoaDon(String text, int i, int j) {
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        dtm.setValueAt(text, i, j);
    }

    public void setValueTBHoaDonCT(String text, int i, int j) {
        dtm = (DefaultTableModel) tb_hoadonchitiet.getModel();
        dtm.setValueAt(text, i, j);
    }

    public void setTienTrinh(HoaDon hd) {
        if (hd.getTienTrinh() == 0) {
            lb_themHangHoa.setForeground(Color.RED);
            lb_themHangHoa.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_guiMail.setForeground(Color.BLACK);
            lb_guiMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lb_trangThai.setForeground(Color.BLACK);
            lb_trangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lb_luuHoaDon.setForeground(Color.BLACK);
            lb_luuHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        if (hd.getTienTrinh() == 1) {
            lb_themHangHoa.setForeground(Color.RED);
            lb_themHangHoa.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_guiMail.setForeground(Color.RED);
            lb_guiMail.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_trangThai.setForeground(Color.BLACK);
            lb_trangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lb_luuHoaDon.setForeground(Color.BLACK);
            lb_luuHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        if (hd.getTienTrinh() == 2) {
            lb_themHangHoa.setForeground(Color.RED);
            lb_themHangHoa.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_guiMail.setForeground(Color.RED);
            lb_guiMail.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_trangThai.setForeground(Color.RED);
            lb_trangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_luuHoaDon.setForeground(Color.BLACK);
            lb_luuHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        if (hd.getTienTrinh() == 3) {
            lb_themHangHoa.setForeground(Color.RED);
            lb_themHangHoa.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_guiMail.setForeground(Color.RED);
            lb_guiMail.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_trangThai.setForeground(Color.RED);
            lb_trangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
            lb_luuHoaDon.setForeground(Color.RED);
            lb_luuHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
    }

    public void taoHoaDon() {
        String diaChi = tf_diachigiao.getText().trim();
        if (diaChi.isEmpty()) {
            validateHelper.message(this, "Vui lòng nhập địa chỉ giao hàng!");
        }
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        hd.setTongTienTT(0);
        hd.setThanhTien(0);
        hd.setDatCoc(0);
        hd.setPhiShip(0);
        hd.setDiaChi(tf_diachigiao.getText());
        hd.setId_KhachHang(kh.getID());
        hd.setId_NhanVien(tk.getId());
        hd.setTrangThaiHD(1);
        hd.setTrangThaiTT(1);
        hd.setTienTrinh(0);
        hd.setGhiChu("");
        hdService.themHoaDon(hd);
        loadTableHD();
        tb_hoadon.setRowSelectionInterval(tb_hoadon.getRowCount() - 1, tb_hoadon.getRowCount() - 1);
        setTienTrinh(hd);
    }

    public void themHDCT() {
        int i = tb_hoadon.getSelectedRow();
        int j = tb_hanghoa.getSelectedRow();
        int idHH = Integer.parseInt(tb_hanghoa.getValueAt(j, 0).toString());
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoLuong(soLuong);
        hdct.setTrangThai(true);
        hdct.setId_CTHangHoa(idHH);
        hdct.setId_HoaDon(idHD);
        hdctService.themHoaDonChiTiet(hdct);
    }

    public void loadCBB() {
        loadCbbKhachHang();
    }

    public void setKhacHang() {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        if (kh != null) {
            lb_masothue.setText(kh.getMaSoThue());
            lb_ten.setText(kh.getTen());
            tf_diachigiao.setText(kh.getDiaChi());
        }
    }

    public void datCoc(KhachHang kh) {
        final String username = "kiemdinh.stie@gmail.com";
        final String password = "0988615123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kiemdinh.stie@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(kh.getEmail())
            );
            message.setSubject("ĐẶT CỌC HÓA ĐƠN SP" + 1);
            int i = tb_hoadon.getSelectedRow();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa dd/MM/yyyy");
            String d = sdf.format(hdClick.getNgayTao());
            String sdtKhach = String.valueOf(tb_hoadon.getValueAt(i, 3));

            String contents = "<a>---------------------------------------------------------";
            contents += "<br>Hóa đơn: " + hdClick.getId();
            contents += "<br>Ngày tạo: " + d;
            contents += "<br>SDT khách hàng: " + sdtKhach;
            contents += "<br>Địa chỉ giao hàng: " + hdClick.getDiaChi();
            contents += "<br>Nhân viên tạo hóa đơn: " + tk.getTenTK();
            contents += "<br>---------------------------------------------------------";
            contents += "<br> Tên hàng&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Thành tiền   ";
            contents += "<br>---------------------------------------------------------";
            for (int s = 0; s < tb_hoadonchitiet.getRowCount(); s++) {
                String tenHang = String.valueOf(tb_hoadonchitiet.getValueAt(s, 1));
                String soLuong = String.valueOf(tb_hoadonchitiet.getValueAt(s, 2));
                String donGia = String.valueOf(tb_hoadonchitiet.getValueAt(s, 4));
                String thanhTien = String.valueOf(tb_hoadonchitiet.getValueAt(s, 5));
                contents += "<br> " + tenHang + "                                       ";
                contents += "<br>      " + soLuong + " * " + setVND(Integer.parseInt(donGia));
                contents += "<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + setVND(Integer.parseInt(thanhTien));
            }
            contents += "<br>---------------------------------------------------------";
            contents += "<br> Tổng tiền hàng:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; " + setVND(getTongTienHang());
            contents += "<br>---------------------------------------------------------";
            contents += "<br> VAT      :&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + setVND(getTongTienHang() / 10);
            contents += "<br>---------------------------------------------------------";
            contents += "<br> Phí vận chuyển   :&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + setVND(hdClick.getPhiShip());
            contents += "<br>---------------------------------------------------------";
            contents += "<br> Tổng chi phí   :&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + setVND(hdClick.getTongTienTT());
            contents += "<br> Đặt cọc   :&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + setVND(hdClick.getDatCoc());
            contents += "<br>*********************************************************";
            
            message.setContent(contents, "text/html; charset=UTF-8");
            Transport.send(message);

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTien() {
        setHDClick();
        setVND(lb_tongtien, getTongTienHang());
        setVND(lb_vat, getTongTienHang() / 10);
        setVND(lb_thanhToan, getTongTienHang() + getTongTienHang() / 10 + hdClick.getPhiShip());
    }

    //load bảng hàng hóa
    public void loadListChiTietHangHoa() {
        cthhList = cthhService.findAllChiTietHangHoa();
    }

    public String getTenHangValueTableHDCT() {
        int i = tb_hoadonchitiet.getSelectedRow();
        return String.valueOf(tb_hoadonchitiet.getValueAt(i, 1));
    }

    public String tenHangToString(int idCTHH) {
        ChiTietHangHoa cthh = cthhService.findIdChiTietHangHoa(idCTHH);
        HangHoa hh = hhService.findIdHangHoa(cthh.getId_HangHoa());
        String tenHang = hh.getTenHang();
        String mauSac = "";
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

    public void removeTable(JTable t) {
        dtm = (DefaultTableModel) t.getModel();
        int j = t.getRowCount() + 1;
        for (int i = 0; i < j - 1; i++) {
            dtm.removeRow(0);
        }
    }

    public void loadTableHangHoa() {
        dtm = (DefaultTableModel) tb_hanghoa.getModel();
        dtm.setRowCount(0);
        HangHoa hh;
        for (ChiTietHangHoa cthh : cthhList) {
            hh = HangHoaJFrame.hhService.findIdHangHoa(cthh.getId_HangHoa());
            String tenHang = hh.getTenHang();
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
                chieuDay = HangHoaJFrame.cdService.findChieuDayId(cthh.getId_ChieuDay()).getDoDay();
            }
            String donViTinh = dvtService.findDonViTinhId(cthh.getId_DonViTinh()).getTenDonVi();
            dtm.addRow(new Object[]{cthh.getId(), tenHang, kichThuoc, mauSac, chieuDay, apSuat, donViTinh, cthh.getSoLuong()});
        }
    }

    public void findNameHangHoa() {
        String tenHang = tf_timkiem.getText().trim();
        cthhList = cthhService.findAllChiTietHangHoaByName(tenHang);
        loadTableHangHoa();
    }

    //lọc hóa đơn
    public void locHoaDon() {
        if (cbb_locHoaDon.getSelectedIndex() == 0) {
            loadTableHD();
        }
        if (cbb_locHoaDon.getSelectedIndex() == 1) {
            hdList = hdService.findHoaDonTT(1);
            loadTableHDNotList();
        }
        if (cbb_locHoaDon.getSelectedIndex() == 2) {
            hdList = hdService.findHoaDonTT(2);
            loadTableHDNotList();
        }
        if (cbb_locHoaDon.getSelectedIndex() == 3) {
            hdList = hdService.findHoaDonTT(3);
            loadTableHDNotList();
        }
        DefaultTableModel dtm = (DefaultTableModel) tb_hoadonchitiet.getModel();
        dtm.setRowCount(0);
    }

    public String getBill() {
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop_hanghoa = new javax.swing.JPopupMenu();
        mitem_themhang = new javax.swing.JMenuItem();
        pop_hoadon = new javax.swing.JPopupMenu();
        mitem_huyhang = new javax.swing.JMenuItem();
        pop_tthd = new javax.swing.JPopupMenu();
        mitem_huyHoaDon = new javax.swing.JMenuItem();
        mitem_dangGiao = new javax.swing.JMenuItem();
        mitem_luuHoaDon = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mitem_chuaThanhToan = new javax.swing.JMenuItem();
        mitem_daThanhToan = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btn_guiMail = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lb_tongtien = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lb_vat = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_hoadon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_hanghoa = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hoadonchitiet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tf_phigiaohang = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tf_timkiem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lb_thanhToan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lb_ten = new javax.swing.JLabel();
        cbb_khachhang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_masothue = new javax.swing.JLabel();
        btn_taohoadon = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tf_diachigiao = new javax.swing.JTextField();
        tf_datCoc = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lb_guiMail = new javax.swing.JLabel();
        lb_themHangHoa = new javax.swing.JLabel();
        lb_trangThai = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lb_luuHoaDon = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbb_locHoaDon = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tf_timKiemHD = new javax.swing.JTextField();
        btn_inHoaDon = new javax.swing.JButton();

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
        mitem_huyhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_huyhangActionPerformed(evt);
            }
        });
        pop_hoadon.add(mitem_huyhang);

        mitem_huyHoaDon.setText("Hủy hóa đơn");
        mitem_huyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_huyHoaDonActionPerformed(evt);
            }
        });
        pop_tthd.add(mitem_huyHoaDon);

        mitem_dangGiao.setText("Đang giao hàng");
        mitem_dangGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_dangGiaoActionPerformed(evt);
            }
        });
        pop_tthd.add(mitem_dangGiao);

        mitem_luuHoaDon.setText("Lưu hóa đơn");
        mitem_luuHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_luuHoaDonActionPerformed(evt);
            }
        });
        pop_tthd.add(mitem_luuHoaDon);
        pop_tthd.add(jSeparator1);

        mitem_chuaThanhToan.setText("Chưa thanh toán");
        mitem_chuaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_chuaThanhToanActionPerformed(evt);
            }
        });
        pop_tthd.add(mitem_chuaThanhToan);

        mitem_daThanhToan.setText("Đã thanh toán");
        mitem_daThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitem_daThanhToanActionPerformed(evt);
            }
        });
        pop_tthd.add(mitem_daThanhToan);

        setClosable(true);
        setIconifiable(true);
        setTitle("QUẢN LÝ BÁN HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btn_guiMail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_guiMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/sendEmail.png"))); // NOI18N
        btn_guiMail.setText("Gửi mail đặt cọc");
        btn_guiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guiMailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tiền hàng:");

        lb_tongtien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_tongtien.setForeground(new java.awt.Color(204, 0, 0));
        lb_tongtien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tongtien.setText("0 đ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("VAT:");

        lb_vat.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_vat.setForeground(new java.awt.Color(204, 0, 0));
        lb_vat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_vat.setText("0 đ");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tb_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Nhân viên tạo", "SDT khách hàng", "Tên KH", "Địa chỉ giao hàng", "Phí giao hàng", "Tiền phải thanh toán", "Đặt cọc", "Thanh toán", "TT thanh toán", "TT hóa đơn", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoadonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_hoadonMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tb_hoadon);
        if (tb_hoadon.getColumnModel().getColumnCount() > 0) {
            tb_hoadon.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_hoadon.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hàng hóa"));

        tb_hanghoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tên hàng", "Kích thước", "Màu sắc", "Độ dày", "Áp suất", "Đơn vị tính", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
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

            },
            new String [] {
                "Id", "Tên hàng", "Số lượng", "Đơn vị tính", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Phí giao hàng:");

        tf_phigiaohang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_phigiaohang.setForeground(new java.awt.Color(255, 0, 0));
        tf_phigiaohang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_phigiaohang.setSelectedTextColor(new java.awt.Color(240, 240, 240));
        tf_phigiaohang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tf_phigiaohangCaretUpdate(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm hàng hóa:");

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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_timkiem)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tiền phải thanh toán:");

        lb_thanhToan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lb_thanhToan.setForeground(new java.awt.Color(255, 0, 51));
        lb_thanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_thanhToan.setText("0 đ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Đặt cọc:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/add-button.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tên KH:");

        lb_ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_ten.setText("      ");

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mã số thuế:");

        lb_masothue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_masothue.setForeground(new java.awt.Color(255, 102, 0));
        lb_masothue.setText("         ");

        btn_taohoadon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_taohoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/create.png"))); // NOI18N
        btn_taohoadon.setText("Tạo hóa đơn");
        btn_taohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taohoadonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Địa chỉ giao hàng:");

        tf_diachigiao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32)
                        .addComponent(cbb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_diachigiao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(lb_masothue))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(lb_ten)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_taohoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lb_masothue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lb_ten))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_diachigiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_taohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tf_datCoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_datCoc.setForeground(new java.awt.Color(255, 0, 51));
        tf_datCoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiến trình của hóa đơn"));

        lb_guiMail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_guiMail.setText("Gửi email đặt cọc");

        lb_themHangHoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_themHangHoa.setText("Tạo hóa đơn");

        lb_trangThai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_trangThai.setText("Đổi trạng thái");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/arrow-right.png"))); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/arrow-right.png"))); // NOI18N

        lb_luuHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_luuHoaDon.setText("Lưu hóa đơn");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/arrow-right.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_themHangHoa)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(lb_guiMail)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(lb_trangThai)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(lb_luuHoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_trangThai))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(lb_luuHoaDon)
                                    .addGap(1, 1, 1)))
                            .addGap(2, 2, 2)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lb_guiMail)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_themHangHoa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái thanh toán:");

        cbb_locHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbb_locHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa cọc", "Chưa thanh toán", "Đã thanh toán" }));
        cbb_locHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_locHoaDonItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tìm kiếm:");

        tf_timKiemHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_timKiemHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tf_timKiemHDCaretUpdate(evt);
            }
        });

        btn_inHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_inHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/printHD.png"))); // NOI18N
        btn_inHoaDon.setText("In hóa đơn");
        btn_inHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1465, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbb_locHoaDon, 0, 234, Short.MAX_VALUE)
                                    .addComponent(tf_timKiemHD))
                                .addGap(18, 18, 18)
                                .addComponent(btn_inHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 368, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_phigiaohang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(23, 23, 23)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_vat, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_datCoc, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(btn_guiMail)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(cbb_locHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(tf_timKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btn_inHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lb_tongtien)
                                        .addComponent(lb_vat)
                                        .addComponent(lb_thanhToan)
                                        .addComponent(tf_datCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_guiMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(2, 2, 2)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(tf_phigiaohang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
        setCTHH();
    }//GEN-LAST:event_tb_hanghoaMouseReleased

    private void mitem_themhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mitem_themhangMouseClicked

    }//GEN-LAST:event_mitem_themhangMouseClicked

    private void mitem_themhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_themhangActionPerformed
        int i = tb_hoadon.getSelectedRow();
        if (i == -1) {
            validateHelper.message(this, "Vui lòng chọn hóa đơn!");
            return;
        }
        new SoLuongJFrame(this).setVisible(true);

    }//GEN-LAST:event_mitem_themhangActionPerformed

    private void tb_hoadonchitietMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadonchitietMouseReleased
        // TODO add your handling code here:4
        int r = tb_hoadonchitiet.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tb_hoadonchitiet.getRowCount()) {
            tb_hoadonchitiet.setRowSelectionInterval(r, r);
        } else {
            tb_hoadonchitiet.clearSelection();
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            pop_hoadon.show(tb_hoadonchitiet, evt.getX(), evt.getY());
        }
        setHDCTClick();
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
        iRowHD = tb_hoadon.getSelectedRow();
        setHDClick();
        setTienTrinh(hdClick);
        loadTableHDCT();
        tf_datCoc.setText(String.valueOf(hdClick.getDatCoc()));
        tf_phigiaohang.setText(String.valueOf(hdClick.getPhiShip()));
        lb_thanhToan.setText(setVND(hdClick.getTongTienTT()));
        lb_vat.setText(setVND(getTongTienHang() / 10));
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        String sdt = String.valueOf(tb_hoadon.getValueAt(iRowHD, 3));
        KhachHang kh = khService.findKhachHangSDT(sdt);
        dcm.setSelectedItem(kh);
    }//GEN-LAST:event_tb_hoadonMouseClicked

    private void btn_guiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guiMailActionPerformed
        // TODO add your handling code here:
        int i = tb_hoadon.getSelectedRow();
        if (i == -1) {
            validateHelper.message(this, "Vui lòng chọn hóa đơn cần gửi email đặt cọc!");
            return;
        }
        String phiGiaoHang = tf_phigiaohang.getText().trim();
        String datCoc = tf_datCoc.getText().trim();
        if (phiGiaoHang.isEmpty()) {
            validateHelper.message(this, "Bạn chưa nhập phí giao hàng!");
            return;
        }
        if (datCoc.isEmpty()) {
            validateHelper.message(this, "Bạn chưa nhập tiền đặt cọc!");
            return;
        }
        if (utilityHelper.checkFormat("^[0-9]+$", phiGiaoHang) == false) {
            validateHelper.message(this, "Sai định dạng phí giao hàng!");
            return;
        }
        if (utilityHelper.checkFormat("^[0-9]+$", datCoc) == false) {
            validateHelper.message(this, "Sai định dạng tiền đặt cọc!");
            return;
        }
        int tienShip = Integer.parseInt(tf_phigiaohang.getText().trim());
        int tienCoc = Integer.parseInt(tf_datCoc.getText().trim());
        if (tienCoc == 0) {
            validateHelper.message(this, "Vui lòng nhập tiền cọc!");
            return;
        }
        if (tienShip == 0) {
            validateHelper.message(this, "Vui lòng nhập phí giao hàng!");
            return;
        }
        if (tienShip < tienCoc) {
            validateHelper.message(this, "Phí vấn chuyển phải lớn hơn hoặc bằng tiền cọc!");
            return;
        }
        int tienHang = getTienThanhToan();
        String idHDString = tb_hoadon.getValueAt(i, 0).toString();
        int idHD = Integer.parseInt(idHDString);
        HoaDon hd = hdService.findHoaDonId(idHD);
        hd.setDatCoc(tienCoc);
        hd.setPhiShip(tienShip);
        hd.setTongTienTT(hd.getTongTienTT() + hd.getPhiShip());
        hd.setTienTrinh(1);
        setTienTrinh(hd);
        hdService.suaHoaDon(hd);
        setValueTBHoaDon(String.valueOf(tienShip), i, 6);
        setValueTBHoaDon(String.valueOf(tienCoc), i, 8);
        setValueTBHoaDon(String.valueOf(getTienThanhToan()), i, 7);
        lb_thanhToan.setText(String.valueOf(getTongTienHang() + getTongTienHang() / 10 + tienShip));
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbb_khachhang.getModel();
        KhachHang kh = (KhachHang) dcm.getSelectedItem();
        setTien();
        //gửi mail cọc
        datCoc(kh);
        System.out.println(kh.getEmail());
        validateHelper.message(this, "Mail đã được gửi tới khách hàng!");
    }//GEN-LAST:event_btn_guiMailActionPerformed

    private void mitem_huyhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_huyhangActionPerformed
        // TODO add your handling code here:
        new SoLuongHuyJFrame(this).setVisible(true);
    }//GEN-LAST:event_mitem_huyhangActionPerformed

    private void tf_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tf_timkiemCaretUpdate
        // TODO add your handling code here:
        findNameHangHoa();
    }//GEN-LAST:event_tf_timkiemCaretUpdate

    private void tb_hoadonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadonMouseReleased
        // TODO add your handling code here:
        int r = tb_hoadon.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tb_hoadon.getRowCount()) {
            tb_hoadon.setRowSelectionInterval(r, r);
        } else {
            tb_hoadon.clearSelection();
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            pop_tthd.show(tb_hoadon, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_hoadonMouseReleased

    private void mitem_dangGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_dangGiaoActionPerformed
        // TODO add your handling code here:
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDon hd = hdService.findHoaDonId(idHD);
        if (hd.getTongTienTT() == 0) {
            validateHelper.message(this, "Hóa đơn chưa thêm hàng!");
            return;
        }
        if (hd.getTrangThaiTT() == 1) {
            validateHelper.message(this, "Hóa đơn chưa chưa cọc!");
            return;
        }
        if (validateHelper.confirm(this, "Bạn muốn đổi trạng thái hóa đơn thành đang giao?", "XÁC NHẬN ĐỔI TRẠNG THÁI HÓA ĐƠN") == JOptionPane.YES_OPTION) {
            hd.setTrangThaiHD(2);
            hdService.suaHoaDon(hd);
            setValueTBHoaDon("Đang giao", i, 11);
            validateHelper.message(this, "Đổi trạng thái hóa đơn thành công!");
        }
    }//GEN-LAST:event_mitem_dangGiaoActionPerformed

    private void mitem_luuHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_luuHoaDonActionPerformed
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        int i = tb_hoadon.getSelectedRow();
        String idHDString = tb_hoadon.getValueAt(i, 0).toString();
        int idHD = Integer.parseInt(idHDString);
        HoaDon hd = hdService.findHoaDonId(idHD);
        if (hd.getTongTienTT() == 0) {
            validateHelper.message(this, "Hóa đơn chưa thêm hàng!");
            return;
        }
        if (hd.getTrangThaiHD() == 1) {
            validateHelper.message(this, "Hóa đơn đang xử lý không được phép lưu!");
            return;
        }
        if (hd.getTrangThaiTT() == 1 || hd.getTrangThaiTT() == 2) {
            validateHelper.message(this, "Hóa đơn chưa thanh toán không được phép lưu!");
            return;
        }
        if (validateHelper.confirm(this, "Bạn có muốn lưu hóa đơn?", "XÁC NHẬN LƯU HÓA ĐƠN") == JOptionPane.YES_OPTION) {
            String note = hd.getGhiChu() + tk.getTenTK() + " lưu hóa đơn!";
            hd.setGhiChu(note);
            hd.setTrangThaiHD(3);
            hdService.suaHoaDon(hd);
            dtm.removeRow(i);
            removeTable(tb_hoadonchitiet);
            validateHelper.message(this, "Lưu hóa đơn thành công");
        }
    }//GEN-LAST:event_mitem_luuHoaDonActionPerformed

    private void tf_phigiaohangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tf_phigiaohangCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_phigiaohangCaretUpdate

    private void mitem_huyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_huyHoaDonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tb_hoadon.getModel();
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDon hd = hdService.findHoaDonId(idHD);
        int ttThanhToan = hd.getTrangThaiTT();
        int ttHoaDon = hd.getTrangThaiHD();
        if (ttThanhToan != 1) {
            validateHelper.message(this, "Không được phép hủy hóa đơn này!");
            return;
        }
        if (ttHoaDon != 1) {
            validateHelper.message(this, "Không được phép hủy hóa đơn này!");
            return;
        }
        if (validateHelper.confirm(this, "Bạn có muốn hủy hóa đơn này?", "XÁC NHẬN HỦY HÓA ĐƠN") == JOptionPane.YES_OPTION) {
            hd.setTrangThaiHD(0);
            hdService.suaHoaDon(hd);
            dtm.removeRow(i);
            validateHelper.message(this, "Hủy hóa đơn thành công!");
            dtm = (DefaultTableModel) tb_hoadonchitiet.getModel();
            dtm.setRowCount(0);
        }

    }//GEN-LAST:event_mitem_huyHoaDonActionPerformed

    private void mitem_chuaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_chuaThanhToanActionPerformed
        // TODO add your handling code here:

        dtm = (DefaultTableModel) tb_hoadon.getModel();
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDon hd = hdService.findHoaDonId(idHD);
        if (hd.getTrangThaiTT() != 1) {
            validateHelper.message(this, "Hóa đơn chưa cọc mới đổi được trạng thái này!");
            return;
        }
        if (validateHelper.confirm(this, "Đổi trạng thái thanh toán của hóa đơn " + hd.getId() + " thành chưa thanh toán?", "XÁC NHẬN ĐỔI TRẠNG THÁI") == JOptionPane.YES_OPTION) {
            String note = hd.getGhiChu() + tk.getTenTK() + " đổi trạng thái: chưa thanh toán;";
            hd.setGhiChu(note);
            hd.setTrangThaiTT(2);
            hd.setTienTrinh(2);
            hd.setThanhTien(hd.getDatCoc());
            hdService.suaHoaDon(hd);
            setValueTBHoaDon("Chưa thanh toán", i, 10);
            setValueTBHoaDon(note, i, 12);
            setValueTBHoaDon(String.valueOf(hd.getDatCoc()), i, 9);
            setTienTrinh(hd);
            validateHelper.message(this, "Đổi trạng thái thanh toán thành công!");
        }

        //lb_thanhToan.setText(String.valueOf(hd.getThanhTien()-hd.getDatCoc()));
    }//GEN-LAST:event_mitem_chuaThanhToanActionPerformed

    private void mitem_daThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitem_daThanhToanActionPerformed
        // TODO add your handling code here:
        dtm = (DefaultTableModel) tb_hoadon.getModel();
        int i = tb_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tb_hoadon.getValueAt(i, 0).toString());
        HoaDon hd = hdService.findHoaDonId(idHD);
        if (validateHelper.confirm(this, "Đổi trạng thái thanh toán của hóa đơn " + hd.getId() + " thành đã thanh toán?", "XÁC NHẬN ĐỔI TRẠNG THÁI") == JOptionPane.YES_OPTION) {
            String note = hd.getGhiChu() + tk.getTenTK() + " đổi trạng thái: đã thanh toán;";
            hd.setTrangThaiTT(3);
            hd.setTienTrinh(2);
            hd.setThanhTien(hd.getTongTienTT());
            hdService.suaHoaDon(hd);
            setValueTBHoaDon("Đã thanh toán", i, 10);
            setValueTBHoaDon(String.valueOf(hd.getThanhTien()), i, 9);
            setTienTrinh(hd);
            validateHelper.message(this, "Đổi trạng thái thanh toán thành công!");
        }

    }//GEN-LAST:event_mitem_daThanhToanActionPerformed

    private void cbb_locHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_locHoaDonItemStateChanged
        // TODO add your handling code here:
        locHoaDon();
    }//GEN-LAST:event_cbb_locHoaDonItemStateChanged

    private void tf_timKiemHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tf_timKiemHDCaretUpdate
        // TODO add your handling code here:
        String timKiem = tf_timKiemHD.getText().trim();
        hdList = hdService.findHoaDonTreo(timKiem);
        loadTableHDNotList();
    }//GEN-LAST:event_tf_timKiemHDCaretUpdate

    private void btn_inHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inHoaDonActionPerformed
        // TODO add your handling code here:
        int i = tb_hoadon.getSelectedRow();
        if (i == -1) {
            validateHelper.message(this, "Vui lòng chọn hóa đơn cần in");
            return;
        }
        setHDClick();
        if (hdClick.getTrangThaiTT() == 1) {
            validateHelper.message(this, "Hóa đơn chưa đặt cọc không được phép in hóa đơn!");
            return;
        }
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_inHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guiMail;
    private javax.swing.JButton btn_inHoaDon;
    private javax.swing.JButton btn_taohoadon;
    public static javax.swing.JComboBox<String> cbb_khachhang;
    private javax.swing.JComboBox<String> cbb_locHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lb_guiMail;
    private javax.swing.JLabel lb_luuHoaDon;
    private javax.swing.JLabel lb_masothue;
    private javax.swing.JLabel lb_ten;
    private javax.swing.JLabel lb_thanhToan;
    private javax.swing.JLabel lb_themHangHoa;
    private javax.swing.JLabel lb_tongtien;
    private javax.swing.JLabel lb_trangThai;
    private javax.swing.JLabel lb_vat;
    private javax.swing.JMenuItem mitem_chuaThanhToan;
    private javax.swing.JMenuItem mitem_daThanhToan;
    private javax.swing.JMenuItem mitem_dangGiao;
    private javax.swing.JMenuItem mitem_huyHoaDon;
    private javax.swing.JMenuItem mitem_huyhang;
    private javax.swing.JMenuItem mitem_luuHoaDon;
    private javax.swing.JMenuItem mitem_themhang;
    private javax.swing.JPopupMenu pop_hanghoa;
    private javax.swing.JPopupMenu pop_hoadon;
    private javax.swing.JPopupMenu pop_tthd;
    private javax.swing.JTable tb_hanghoa;
    private javax.swing.JTable tb_hoadon;
    private javax.swing.JTable tb_hoadonchitiet;
    private javax.swing.JTextField tf_datCoc;
    private javax.swing.JTextField tf_diachigiao;
    private javax.swing.JTextField tf_phigiaohang;
    private javax.swing.JTextField tf_timKiemHD;
    private javax.swing.JTextField tf_timkiem;
    // End of variables declaration//GEN-END:variables
}
