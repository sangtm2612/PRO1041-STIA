/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoaDAO;
import DAO.Models.HangHoa;
import Service.Interface.HangHoaInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class HangHoaService implements HangHoaInterface{
    HangHoaDAO hhDAO = new HangHoaDAO();

    @Override
    public void themHangHoa(HangHoa hh) {
        hhDAO.insert(hh);
    }

    @Override
    public void suaHangHoa(HangHoa hh) {
        hhDAO.update(hh);
    }

    @Override
    public void xoaHangHoa(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HangHoa> findAllHangHoa() {
        return hhDAO.selectAll();
    }

    @Override
    public HangHoa findIdHangHoa(int Id) {
        return hhDAO.selectById(Id);
    }
    
    public HangHoa findNameHangHoa(String name) {
        return hhDAO.selectByName(name);
    }
    
}
