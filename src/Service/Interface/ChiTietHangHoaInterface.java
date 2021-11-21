/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.ChiTietHangHoa;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface ChiTietHangHoaInterface {
    void themHangHoaChiTiet(ChiTietHangHoa cthh);
    
    void suaHangHoaChiTiet(ChiTietHangHoa cthh);
    
    List<ChiTietHangHoa> findAllChiTietHangHoa();
    
    ChiTietHangHoa findIdChiTietHangHoa(int id);
    
}
