/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import Models.HangHoa.ApSuat;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface ChieuDayInterface {
    
    void themApSuat(ApSuat a);
    
    void suaApSuat(ApSuat a);
    
    List<ApSuat> findAllApSuat();
    
    int findIdApSuat(String ApSuat);
    
}
