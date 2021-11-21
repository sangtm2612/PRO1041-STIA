/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.KichThuoc;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface KichThuocInterface {
    
    void themKichThuoc(KichThuoc k);
    
    void suaKichThuoc(KichThuoc k);
    
    List<KichThuoc> findAllKichThuoc();
    
    KichThuoc findKichThuocName(String name);
    
}
