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
public class LoaiHang {
    private int Id;
    private String TenLoai;
    private boolean TrangThai;

    public LoaiHang() {
    }

    public LoaiHang(String TenLoai, boolean TrangThai) {
        this.TenLoai = TenLoai;
        this.TrangThai = TrangThai;
    }

    public LoaiHang(int Id, String TenLoai, boolean TrangThai) {
        this.Id = Id;
        this.TenLoai = TenLoai;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
