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
    private Date NgayTao;
    private String GhiChu;
    private int Id_NhanVien;
    private int Id_KhachHang;

    public HoaDon() {
    }

    public HoaDon(Integer ThanhTien, Date NgayTao, String GhiChu, int Id_NhanVien, int Id_KhachHang) {
        this.ThanhTien = ThanhTien;
        this.NgayTao = NgayTao;
        this.GhiChu = GhiChu;
        this.Id_NhanVien = Id_NhanVien;
        this.Id_KhachHang = Id_KhachHang;
    }

    public HoaDon(int Id, Integer ThanhTien, Date NgayTao, String GhiChu, int Id_NhanVien, int Id_KhachHang) {
        this.Id = Id;
        this.ThanhTien = ThanhTien;
        this.NgayTao = NgayTao;
        this.GhiChu = GhiChu;
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
