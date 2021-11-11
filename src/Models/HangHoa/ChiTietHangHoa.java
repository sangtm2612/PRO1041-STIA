/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.HangHoa;

/**
 *
 * @author sangt
 */
public class ChiTietHangHoa {
    private int Id;
    private int SoLuong;
    private int Id_KichThuoc;
    private int Id_MauSac;
    private int Id_HangHoa;
    private int Id_ApSuat;
    private int Id_DonViTinh;
    private int Id_GiaTien;
    private int Id_LoaiHang;
    private int Id_ChieuDay;

    public ChiTietHangHoa() {
    }

    public ChiTietHangHoa(int Id, int SoLuong, int Id_KichThuoc, int Id_MauSac, int Id_HangHoa, int Id_ApSuat, int Id_DonViTinh, int Id_GiaTien, int Id_LoaiHang, int Id_ChieuDay) {
        this.Id = Id;
        this.SoLuong = SoLuong;
        this.Id_KichThuoc = Id_KichThuoc;
        this.Id_MauSac = Id_MauSac;
        this.Id_HangHoa = Id_HangHoa;
        this.Id_ApSuat = Id_ApSuat;
        this.Id_DonViTinh = Id_DonViTinh;
        this.Id_GiaTien = Id_GiaTien;
        this.Id_LoaiHang = Id_LoaiHang;
        this.Id_ChieuDay = Id_ChieuDay;
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

    public int getId_KichThuoc() {
        return Id_KichThuoc;
    }

    public void setId_KichThuoc(int Id_KichThuoc) {
        this.Id_KichThuoc = Id_KichThuoc;
    }

    public int getId_MauSac() {
        return Id_MauSac;
    }

    public void setId_MauSac(int Id_MauSac) {
        this.Id_MauSac = Id_MauSac;
    }

    public int getId_HangHoa() {
        return Id_HangHoa;
    }

    public void setId_HangHoa(int Id_HangHoa) {
        this.Id_HangHoa = Id_HangHoa;
    }

    public int getId_ApSuat() {
        return Id_ApSuat;
    }

    public void setId_ApSuat(int Id_ApSuat) {
        this.Id_ApSuat = Id_ApSuat;
    }

    public int getId_DonViTinh() {
        return Id_DonViTinh;
    }

    public void setId_DonViTinh(int Id_DonViTinh) {
        this.Id_DonViTinh = Id_DonViTinh;
    }

    public int getId_GiaTien() {
        return Id_GiaTien;
    }

    public void setId_GiaTien(int Id_GiaTien) {
        this.Id_GiaTien = Id_GiaTien;
    }

    public int getId_LoaiHang() {
        return Id_LoaiHang;
    }

    public void setId_LoaiHang(int Id_LoaiHang) {
        this.Id_LoaiHang = Id_LoaiHang;
    }

    public int getId_ChieuDay() {
        return Id_ChieuDay;
    }

    public void setId_ChieuDay(int Id_ChieuDay) {
        this.Id_ChieuDay = Id_ChieuDay;
    }

    
    
    
    
}
