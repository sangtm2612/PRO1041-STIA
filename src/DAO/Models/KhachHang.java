/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Models;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private int ID ;
    private String MaSoThue;
    private String Ten;
    private String DiaChi;
    private String Email;
    private String SoDienThoai;
    private String GhiChu;
    private boolean TrangThai;

    public KhachHang() {
    }

    public KhachHang(int ID, String MaSoThue, String Ten, String DiaChi, String Email, String SoDienThoai, String GhiChu, boolean TrangThai) {
        this.ID = ID;
        this.MaSoThue = MaSoThue;
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    public KhachHang(String MaSoThue, String Ten, String DiaChi, String Email, String SoDienThoai, String GhiChu, boolean TrangThai) {
        this.MaSoThue = MaSoThue;
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String MaSoThue) {
        this.MaSoThue = MaSoThue;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
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

    @Override
    public String toString() {
        return SoDienThoai + " " + Ten;
    }
    
}
