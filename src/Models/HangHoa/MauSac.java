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
public class MauSac {
    private int Id;
    private String TenMau;
    private boolean TrangThai;

    public MauSac() {
    }

    public MauSac(int Id, String TenMau, boolean TrangThai) {
        this.Id = Id;
        this.TenMau = TenMau;
        this.TrangThai = TrangThai;
    }

    public MauSac(String TenMau, boolean TrangThai) {
        this.TenMau = TenMau;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
    
}
