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

    public MauSac() {
    }

    public MauSac(String TenMau) {
        this.TenMau = TenMau;
    }
    
    

    public MauSac(int Id, String TenMau) {
        this.Id = Id;
        this.TenMau = TenMau;
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
    
    
}
