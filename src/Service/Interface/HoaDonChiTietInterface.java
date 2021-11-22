/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface HoaDonChiTietInterface {
    void themHoaDonChiTiet(HoaDonChiTiet hd);
    
    void suaHoaDonChiTiet(HoaDonChiTiet hd);
    
    List<HoaDonChiTiet> findAllHoaDonChiTiet();
    
    HoaDonChiTiet findHoaDonChiTietId(int id);
}
