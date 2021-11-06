/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myPro.Models;

import java.util.Date;

/**
 *
 * @author sangt
 */
public class NhanVien {
    private int Id;
    private String HoTen;
    private boolean GioiTinh;
    private Date NgaySinh;
    private String DiaChi;
    private String SDT;
    private String Email;
    private String CCCD;
    private boolean ChucVu;
    private String GhiChu;
    private boolean TrangThai;
    private int PhongBan;
    private int TaiKhoan;

    public NhanVien() {
    }

    public NhanVien(int Id, String HoTen, boolean GioiTinh, Date NgaySinh, String DiaChi, String SDT, String Email, String CCCD, boolean ChucVu, String GhiChu, boolean TrangThai, int PhongBan, int TaiKhoan) {
        this.Id = Id;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.CCCD = CCCD;
        this.ChucVu = ChucVu;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.PhongBan = PhongBan;
        this.TaiKhoan = TaiKhoan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public boolean isChucVu() {
        return ChucVu;
    }

    public void setChucVu(boolean ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(int PhongBan) {
        this.PhongBan = PhongBan;
    }

    public int getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(int TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }
    
}
