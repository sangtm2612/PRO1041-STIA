/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.HoaDon;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface HoaDonInterface {
    void themHoaDon(HoaDon kh);
    
    void suaHoaDon(HoaDon kh);
    
    List<HoaDon> findAllHoaDon();
    
    HoaDon findHoaDonId(int id);
}
