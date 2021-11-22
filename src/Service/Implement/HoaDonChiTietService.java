/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HoaDonChiTietDAO;
import DAO.Models.HoaDonChiTiet;
import Service.Interface.HoaDonChiTietInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class HoaDonChiTietService implements HoaDonChiTietInterface{
    HoaDonChiTietDAO hdctDAO = new HoaDonChiTietDAO();
    
    @Override
    public void themHoaDonChiTiet(HoaDonChiTiet hd) {
        hdctDAO.insert(hd);
    }

    @Override
    public void suaHoaDonChiTiet(HoaDonChiTiet hd) {
        hdctDAO.update(hd);
    }

    @Override
    public List<HoaDonChiTiet> findAllHoaDonChiTiet() {
        return  hdctDAO.selectAll();
    }

    @Override
    public HoaDonChiTiet findHoaDonChiTietId(int id) {
        return hdctDAO.selectById(id);
    }
    
    public List<HoaDonChiTiet> findAllHoaDonChiTietByIdHD(int id) {
        return  hdctDAO.selectAllByIdHD(id);
    }
    
}
