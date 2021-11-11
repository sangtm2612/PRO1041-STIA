/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoa.LoaiHangDAO;
import Models.HangHoa.ApSuat;
import Models.HangHoa.LoaiHang;
import Service.Interface.LoaiHangInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class LoaiHangService implements LoaiHangInterface{
    LoaiHangDAO lDAO = new LoaiHangDAO();

    @Override
    public void themLoaiHang(LoaiHang l) {
        lDAO.insert(l);
    }

    @Override
    public void suaLoaiHang(LoaiHang l) {
        lDAO.update(l);
    }

    @Override
    public List<LoaiHang> findAllLoaiHang() {
        return lDAO.selectAll();
    }

    @Override
    public int findIdLoaiHang(String LoaiHang) {
        return -1;
    }
    
    
    
    
}
