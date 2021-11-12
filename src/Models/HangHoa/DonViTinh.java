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
public class DonViTinh {
    private int Id;
    private String TenDonVi;
    private boolean TrangThai;

    public DonViTinh() {
    }

    public DonViTinh(int Id, String TenDonVi, boolean TrangThai) {
        this.Id = Id;
        this.TenDonVi = TenDonVi;
        this.TrangThai = TrangThai;
    }

    public DonViTinh(String TenDonVi, boolean TrangThai) {
        this.TenDonVi = TenDonVi;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenDonVi() {
        return TenDonVi;
    }

    public void setTenDonVi(String TenDonVi) {
        this.TenDonVi = TenDonVi;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenDonVi; //To change body of generated methods, choose Tools | Templates.
    }  
    
}
