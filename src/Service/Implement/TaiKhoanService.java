/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.Models.TaiKhoan;
import DAO.TaiKhoanDAO;
import Service.Interface.TaiKhoanInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class TaiKhoanService implements TaiKhoanInterface{
    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    
    @Override
    public void themTaiKhoan(TaiKhoan tk) {
        tkDAO.insert(tk);
    }

    @Override
    public void suaTaiKhoan(TaiKhoan tk) {
        tkDAO.update(tk);
    }

    @Override
    public List<TaiKhoan> findAllTaiKhoan() {
        return tkDAO.selectAll();
    }

    @Override
    public TaiKhoan findTaiKhoanName(String name) {
        return tkDAO.selectByTenTK(name);
    }
    
    public TaiKhoan findTaiKhoanId(int id) {
        return tkDAO.selectById(id);
    }
    
}
