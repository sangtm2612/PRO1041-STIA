/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.ChieuDay;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface ChieuDayInterface {
    
    void themChieuDay(ChieuDay a);
    
    void suaChieuDay(ChieuDay a);
    
    List<ChieuDay> findAllChieuDay();
    
    ChieuDay findChieuDayDouble(Double chieuDay);
    
}
