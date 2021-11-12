/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import Models.HangHoa.ApSuat;
import Models.HangHoa.GiaTien;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface GiaTienInterface {
    
    void themGiaTien(GiaTien gt);
    
    void suaGiaTien(GiaTien gt);
    
    List<GiaTien> findAllGiaTien();
    
    int findIdGiaTien(String GiaTien);
    
}
