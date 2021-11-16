/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.Models.KhachHang;
import DAO.Models.NhanVien;
import DAO.NhanVienDAO;
import Service.Interface.NhanVienInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class NhanVienService implements NhanVienInterface{
    NhanVienDAO nvDAO = new NhanVienDAO();

    @Override
    public void themNhanVien(NhanVien nv) {
        nvDAO.insert(nv);
    }

    @Override
    public void suaNhanVien(NhanVien nv) {
        nvDAO.update(nv);
    }

    @Override
    public List<NhanVien> findAllNhanVien() {
        return nvDAO.selectAll();
    }

    @Override
    public NhanVien findIdNhanVien(Integer Id) {
        return nvDAO.selectById(Id);
    }
    
    public NhanVien findNhanVienSDT(String SDT) {
        return nvDAO.selectBySDT(SDT);
    }

    
}
