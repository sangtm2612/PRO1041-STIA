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
public class HangHoa {
    private int Id;
    private String TenHang;
    private String GhiChu;
    private Boolean TrangThai;
    private int Id_NhaCungCap;
    private int Id_DanhMuc;

    public HangHoa() {
    }

    public HangHoa(int Id, String TenHang, String GhiChu, Boolean TrangThai, int Id_NhaCungCap, int Id_DanhMuc) {
        this.Id = Id;
        this.TenHang = TenHang;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
        this.Id_NhaCungCap = Id_NhaCungCap;
        this.Id_DanhMuc = Id_DanhMuc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getId_NhaCungCap() {
        return Id_NhaCungCap;
    }

    public void setId_NhaCungCap(int Id_NhaCungCap) {
        this.Id_NhaCungCap = Id_NhaCungCap;
    }

    public int getId_DanhMuc() {
        return Id_DanhMuc;
    }

    public void setId_DanhMuc(int Id_DanhMuc) {
        this.Id_DanhMuc = Id_DanhMuc;
    }
    
    
}
