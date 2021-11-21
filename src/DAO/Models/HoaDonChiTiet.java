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
public class HoaDonChiTiet {
    private int Id;
    private int SoLuong;
    private boolean TrangThai;
    private int Id_HoaDon;
    private int Id_CTHangHoa;

    public HoaDonChiTiet(int Id, int SoLuong, boolean TrangThai, int Id_HoaDon, int Id_CTHangHoa) {
        this.Id = Id;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
        this.Id_HoaDon = Id_HoaDon;
        this.Id_CTHangHoa = Id_CTHangHoa;
    }

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int SoLuong, boolean TrangThai, int Id_HoaDon, int Id_CTHangHoa) {
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
        this.Id_HoaDon = Id_HoaDon;
        this.Id_CTHangHoa = Id_CTHangHoa;
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

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getId_HoaDon() {
        return Id_HoaDon;
    }

    public void setId_HoaDon(int Id_HoaDon) {
        this.Id_HoaDon = Id_HoaDon;
    }

    public int getId_CTHangHoa() {
        return Id_CTHangHoa;
    }

    public void setId_CTHangHoa(int Id_CTHangHoa) {
        this.Id_CTHangHoa = Id_CTHangHoa;
    }
    
    
}
