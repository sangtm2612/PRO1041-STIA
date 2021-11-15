/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.DonViTinh;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface DonViTinhInterface {
    
    void themDonViTinh(DonViTinh a);
    
    void suaDonViTinh(DonViTinh a);
    
    List<DonViTinh> findAllDonViTinh();
    
    DonViTinh findIdDonViTinh(String name);
    
}
