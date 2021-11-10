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

    public DanhMuc(int Id, String TenDanhMuc) {
        this.Id = Id;
        this.TenDanhMuc = TenDanhMuc;
    }

    public DanhMuc() {
    }

    public DanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
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
    
    
}
