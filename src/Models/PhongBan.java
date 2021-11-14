/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author sangt
 */
public class PhongBan {
    private int id;
    private String TenPhongBan;
    private boolean TrangThai;

    public PhongBan() {
    }

    public PhongBan(int id, String TenPhongBan, boolean TrangThai) {
        this.id = id;
        this.TenPhongBan = TenPhongBan;
        this.TrangThai = TrangThai;
    }

    public PhongBan(String TenPhongBan, boolean TrangThai) {
        this.TenPhongBan = TenPhongBan;
        this.TrangThai = TrangThai;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return TenPhongBan;
    }

    public void setTenPhongBan(String TenPhongBan) {
        this.TenPhongBan = TenPhongBan;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    

    @Override
    public String toString() {
        return TenPhongBan; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
