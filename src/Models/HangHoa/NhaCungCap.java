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
public class NhaCungCap {
    private int Id;
    private String TenNCC;
    private String DiaChi;
    private String SDT;
    private String Email;
    private String GhiChu;
    private boolean TrangThai;

    public NhaCungCap() {
    }

    public NhaCungCap(int Id, String TenNCC, String DiaChi, String SDT, String Email, String GhiChu, boolean TrangThai) {
        this.Id = Id;
        this.TenNCC = TenNCC;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    public NhaCungCap(String TenNCC, String DiaChi, String SDT, String Email, String GhiChu, boolean TrangThai) {
        this.TenNCC = TenNCC;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }   

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
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

    
    
}
