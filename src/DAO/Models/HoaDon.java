/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Models;

import java.util.Date;

/**
 *
 * @author sangt
 */
public class HoaDon {
    private int Id;
    private Integer ThanhTien;
    private Integer DatCoc;
    private Integer PhiShip;
    private Date NgayTao;
    private String GhiChu;
    private String DiaChi;
    private int TrangThaiHD;
    private boolean TrangThaiTT;
    private int Id_NhanVien;
    private int Id_KhachHang;

    public HoaDon() {
    }

    public HoaDon(int Id, Integer ThanhTien, Integer DatCoc, Integer PhiShip, Date NgayTao, String GhiChu, String DiaChi, int TrangThaiHD, boolean TrangThaiTT, int Id_NhanVien, int Id_KhachHang) {
        this.Id = Id;
        this.ThanhTien = ThanhTien;
        this.DatCoc = DatCoc;
        this.PhiShip = PhiShip;
        this.NgayTao = NgayTao;
        this.GhiChu = GhiChu;
        this.DiaChi = DiaChi;
        this.TrangThaiHD = TrangThaiHD;
        this.TrangThaiTT = TrangThaiTT;
        this.Id_NhanVien = Id_NhanVien;
        this.Id_KhachHang = Id_KhachHang;
    }

    public HoaDon(Integer ThanhTien, Integer DatCoc, Integer PhiShip, Date NgayTao, String GhiChu, String DiaChi, int TrangThaiHD, boolean TrangThaiTT, int Id_NhanVien, int Id_KhachHang) {
        this.ThanhTien = ThanhTien;
        this.DatCoc = DatCoc;
        this.PhiShip = PhiShip;
        this.NgayTao = NgayTao;
        this.GhiChu = GhiChu;
        this.DiaChi = DiaChi;
        this.TrangThaiHD = TrangThaiHD;
        this.TrangThaiTT = TrangThaiTT;
        this.Id_NhanVien = Id_NhanVien;
        this.Id_KhachHang = Id_KhachHang;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Integer getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Integer ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public Integer getDatCoc() {
        return DatCoc;
    }

    public void setDatCoc(Integer DatCoc) {
        this.DatCoc = DatCoc;
    }

    public Integer getPhiShip() {
        return PhiShip;
    }

    public void setPhiShip(Integer PhiShip) {
        this.PhiShip = PhiShip;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(int TrangThaiHD) {
        this.TrangThaiHD = TrangThaiHD;
    }

    public boolean isTrangThaiTT() {
        return TrangThaiTT;
    }

    public void setTrangThaiTT(boolean TrangThaiTT) {
        this.TrangThaiTT = TrangThaiTT;
    }

    public int getId_NhanVien() {
        return Id_NhanVien;
    }

    public void setId_NhanVien(int Id_NhanVien) {
        this.Id_NhanVien = Id_NhanVien;
    }

    public int getId_KhachHang() {
        return Id_KhachHang;
    }

    public void setId_KhachHang(int Id_KhachHang) {
        this.Id_KhachHang = Id_KhachHang;
    }
    

    
}
