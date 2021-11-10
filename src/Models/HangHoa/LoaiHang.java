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

    public LoaiHang() {
    }

    public LoaiHang(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public LoaiHang(int Id, String TenLoai) {
        this.Id = Id;
        this.TenLoai = TenLoai;
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
    
    
}
