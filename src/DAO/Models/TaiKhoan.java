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
public class TaiKhoan {
    private int Id;
    private String TenTK;
    private String MatKhau;
    private boolean VaiTro;
    private boolean TrangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(String TenTK, String MatKhau, boolean VaiTro, boolean TrangThai) {
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.TrangThai = TrangThai;
    }

    public TaiKhoan(int Id, String TenTK, String MatKhau, boolean VaiTro, boolean TrangThai) {
        this.Id = Id;
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
}
