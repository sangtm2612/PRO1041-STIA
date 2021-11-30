/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HoaDonDAO;
import DAO.Models.HoaDon;
import Service.Interface.HoaDonInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class HoaDonService implements HoaDonInterface{
    HoaDonDAO hdDAO = new HoaDonDAO();

    @Override
    public void themHoaDon(HoaDon kh) {
        hdDAO.insert(kh);
    }

    @Override
    public void suaHoaDon(HoaDon kh) {
        hdDAO.update(kh);
    }

    @Override
    public List<HoaDon> findAllHoaDon() {
        return hdDAO.selectAll();
    }
    
    public List<HoaDon> findAllHoaDonDone() {
        return hdDAO.selectAllTT3();
    }
    
    public List<HoaDon> findAllHoaDonDoneTimKiem(String i) {
        return hdDAO.selectBySDTKhachVaTK(i);
    }

    @Override
    public HoaDon findHoaDonId(int id) {
        return hdDAO.selectById(id);
    }
    
    
    
}
