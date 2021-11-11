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
public class ApSuat {
    private int Id;
    private String TenApSuat;
    private boolean TrangThai;

    public ApSuat(int Id, String TenApSuat, boolean TrangThai) {
        this.Id = Id;
        this.TenApSuat = TenApSuat;
        this.TrangThai = TrangThai;
    }

    public ApSuat() {
    }

    public ApSuat(String TenApSuat, boolean TrangThai) {
        this.TenApSuat = TenApSuat;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenApSuat() {
        return TenApSuat;
    }

    public void setTenApSuat(String TenApSuat) {
        this.TenApSuat = TenApSuat;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
