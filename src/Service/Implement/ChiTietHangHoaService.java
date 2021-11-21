/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.ChiTietHangHoaDAO;
import DAO.Models.ChiTietHangHoa;
import Service.Interface.ChiTietHangHoaInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class ChiTietHangHoaService implements ChiTietHangHoaInterface{
    ChiTietHangHoaDAO ctDAO = new ChiTietHangHoaDAO();

    @Override
    public void themHangHoaChiTiet(ChiTietHangHoa cthh) {
        ctDAO.insert(cthh);
    }

    @Override
    public void suaHangHoaChiTiet(ChiTietHangHoa cthh) {
        ctDAO.update(cthh);
    }

    @Override
    public List<ChiTietHangHoa> findAllChiTietHangHoa() {
        return ctDAO.selectAll();
    }

    @Override
    public ChiTietHangHoa findIdChiTietHangHoa(int id) {
        return ctDAO.selectById(id);
    }
    
    
    
}
