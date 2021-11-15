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
public class KichThuoc {
    private int Id;
    private String TenKichThuoc;
    private boolean TrangThai;

    public KichThuoc() {
    }

    public KichThuoc(int Id, String TenKichThuoc, boolean TrangThai) {
        this.Id = Id;
        this.TenKichThuoc = TenKichThuoc;
        this.TrangThai = TrangThai;
    }

    public KichThuoc(String TenKichThuoc, boolean TrangThai) {
        this.TenKichThuoc = TenKichThuoc;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenKichThuoc() {
        return TenKichThuoc;
    }

    public void setTenKichThuoc(String TenKichThuoc) {
        this.TenKichThuoc = TenKichThuoc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenKichThuoc; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
