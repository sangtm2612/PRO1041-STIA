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

    public ApSuat() {
    }

    public ApSuat(int Id, String TenApSuat) {
        this.Id = Id;
        this.TenApSuat = TenApSuat;
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
    
    
}
