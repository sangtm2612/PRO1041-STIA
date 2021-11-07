/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPro.Models;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private int ID ;
    private String hoTen;
    private String diaChi;
    private boolean gioiTinh;
    private String diaChiCT;
    private String SDT;
    private String GhiChu;
    private boolean trangThai;

    public KhachHang() {
    }

    public KhachHang(int ID, String hoTen, String diaChi, boolean gioiTinh, String diaChiCT, String SDT, String GhiChu, boolean trangThai) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.diaChiCT = diaChiCT;
        this.SDT = SDT;
        this.GhiChu = GhiChu;
        this.trangThai = trangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChiCT() {
        return diaChiCT;
    }

    public void setDiaChiCT(String diaChiCT) {
        this.diaChiCT = diaChiCT;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "ID=" + ID + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", diaChiCT=" + diaChiCT + ", SDT=" + SDT + ", GhiChu=" + GhiChu + ", trangThai=" + trangThai + '}';
    }
    
}
