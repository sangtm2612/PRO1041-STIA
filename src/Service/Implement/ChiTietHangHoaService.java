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
        if (cthh.getId_ApSuat() == null || cthh.getId_ApSuat() == 0) {
            cthh.setId_ApSuat(null);
        }
        if (cthh.getId_KichThuoc() == null || cthh.getId_KichThuoc() == 0) {
            cthh.setId_KichThuoc( null);
        }
        if (cthh.getId_MauSac() == null || cthh.getId_MauSac() == 0) {
            cthh.setId_MauSac(null);
        }
        if (cthh.getId_ChieuDay() == null || cthh.getId_ChieuDay() == 0) {
            cthh.setId_ChieuDay(null);
        }
        ctDAO.update(cthh);
    }

    @Override
    public List<ChiTietHangHoa> findAllChiTietHangHoa() {
        return ctDAO.selectAll();
    }
    
    public List<ChiTietHangHoa> findAllChiTietHangHoaByName(String tenHang) {
        return ctDAO.findTenHang(tenHang);
    }

    @Override
    public ChiTietHangHoa findIdChiTietHangHoa(int id) {
        return ctDAO.selectById(id);
    }
    
    
    
}
