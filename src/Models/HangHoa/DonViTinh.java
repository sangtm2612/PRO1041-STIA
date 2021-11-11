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

    public DonViTinh() {
    }

    public DonViTinh(int Id, String TenDonVi) {
        this.Id = Id;
        this.TenDonVi = TenDonVi;
    }

    public DonViTinh(String TenDonVi) {
        this.TenDonVi = TenDonVi;
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
    
    
}
