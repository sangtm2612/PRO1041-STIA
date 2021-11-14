/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import Models.PhongBan;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface PhongBanInterface {
    void themPhongBan(PhongBan pb);
    
    void suaPhongBan(PhongBan a);
    
    List<PhongBan> findAllPhongBan();
    
    PhongBan findPhongBanByName(String name);
}
