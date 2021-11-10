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
public class KichThuoc {
    private int Id;
    private String TenKichThuoc;

    public KichThuoc() {
    }

    public KichThuoc(int Id, String TenKichThuoc) {
        this.Id = Id;
        this.TenKichThuoc = TenKichThuoc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenKichThuoc() {
        return TenKichThuoc;
    }

    public void setTenKichThuoc(String TenKichThuoc) {
        this.TenKichThuoc = TenKichThuoc;
    }
    
    
}
