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
public class DanhMuc {
    private int Id;
    private String TenDanhMuc; 
    private boolean TrangThai;

    public DanhMuc() {
    }

    public DanhMuc(int Id, String TenDanhMuc, boolean TrangThai) {
        this.Id = Id;
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    public DanhMuc(String TenDanhMuc, boolean TrangThai) {
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

}
