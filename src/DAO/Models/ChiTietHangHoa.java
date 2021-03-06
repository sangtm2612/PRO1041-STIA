/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Models;

/**
 *
 * @author sangt
 */
public class ChiTietHangHoa {
    private int Id;
    private int SoLuong;
    private int GiaNhap;
    private int GiaBan;
    private String GhiChu;
    private boolean TrangThai;
    private Integer Id_KichThuoc;
    private Integer Id_MauSac;
    private int Id_HangHoa;
    private Integer Id_ApSuat;
    private int Id_DonViTinh;
    private int Id_LoaiHang;
    private Integer Id_ChieuDay;

    public ChiTietHangHoa(int Id, int SoLuong, int GiaNhap, int GiaBan, String GhiChu, boolean TrangThai, Integer Id_KichThuoc, Integer Id_MauSac, int Id_HangHoa, Integer Id_ApSuat, int Id_DonViTinh, int Id_LoaiHang, Integer Id_ChieuDay) {
        this.Id = Id;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.Id_KichThuoc = Id_KichThuoc;
        this.Id_MauSac = Id_MauSac;
        this.Id_HangHoa = Id_HangHoa;
        this.Id_ApSuat = Id_ApSuat;
        this.Id_DonViTinh = Id_DonViTinh;
        this.Id_LoaiHang = Id_LoaiHang;
        this.Id_ChieuDay = Id_ChieuDay;
    }

    public ChiTietHangHoa(int SoLuong, int GiaNhap, int GiaBan, String GhiChu, boolean TrangThai, Integer Id_KichThuoc, Integer Id_MauSac, int Id_HangHoa, Integer Id_ApSuat, int Id_DonViTinh, int Id_LoaiHang, Integer Id_ChieuDay) {
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.Id_KichThuoc = Id_KichThuoc;
        this.Id_MauSac = Id_MauSac;
        this.Id_HangHoa = Id_HangHoa;
        this.Id_ApSuat = Id_ApSuat;
        this.Id_DonViTinh = Id_DonViTinh;
        this.Id_LoaiHang = Id_LoaiHang;
        this.Id_ChieuDay = Id_ChieuDay;
    }

    public ChiTietHangHoa() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
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

    public Integer getId_KichThuoc() {
        return Id_KichThuoc;
    }

    public void setId_KichThuoc(Integer Id_KichThuoc) {
        this.Id_KichThuoc = Id_KichThuoc;
    }

    public Integer getId_MauSac() {
        return Id_MauSac;
    }

    public void setId_MauSac(Integer Id_MauSac) {
        this.Id_MauSac = Id_MauSac;
    }

    public int getId_HangHoa() {
        return Id_HangHoa;
    }

    public void setId_HangHoa(int Id_HangHoa) {
        this.Id_HangHoa = Id_HangHoa;
    }

    public Integer getId_ApSuat() {
        return Id_ApSuat;
    }

    public void setId_ApSuat(Integer Id_ApSuat) {
        this.Id_ApSuat = Id_ApSuat;
    }

    public int getId_DonViTinh() {
        return Id_DonViTinh;
    }

    public void setId_DonViTinh(int Id_DonViTinh) {
        this.Id_DonViTinh = Id_DonViTinh;
    }

    public int getId_LoaiHang() {
        return Id_LoaiHang;
    }

    public void setId_LoaiHang(int Id_LoaiHang) {
        this.Id_LoaiHang = Id_LoaiHang;
    }

    public Integer getId_ChieuDay() {
        return Id_ChieuDay;
    }

    public void setId_ChieuDay(Integer Id_ChieuDay) {
        this.Id_ChieuDay = Id_ChieuDay;
    }

       

}
