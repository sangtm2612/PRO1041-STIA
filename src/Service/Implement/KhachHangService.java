/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.KhachHangDAO;
import DAO.Models.KhachHang;
import Service.Interface.KhachHangInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class KhachHangService implements KhachHangInterface{
    KhachHangDAO khDAO = new KhachHangDAO();

    @Override
    public void themKhachHang(KhachHang kh) {
        khDAO.insert(kh);
    }

    @Override
    public void suaKhachHang(KhachHang kh) {
        khDAO.update(kh);
    }

    @Override
    public List<KhachHang> findAllKhachHang() {
        return khDAO.selectAll();
    }

    @Override
    public KhachHang findKhachHangSDT(String name) {
        return khDAO.selectBySDT(name);
    }
 
}
