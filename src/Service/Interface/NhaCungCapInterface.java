/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import Models.HangHoa.NhaCungCap;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface NhaCungCapInterface {
    
    void themNhaCungCap(NhaCungCap ncc);
    
    void suaNhaCungCap(NhaCungCap a);
    
    List<NhaCungCap> findAllNhaCungCap();
    
    int findIdNhaCungCap(String NhaCungCap);
    
}
