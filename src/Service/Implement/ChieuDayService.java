/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.ChieuDayDAO;
import DAO.Models.ChieuDay;
import Service.Interface.ChieuDayInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class ChieuDayService implements ChieuDayInterface{
    ChieuDayDAO cdDAO = new ChieuDayDAO();

    @Override
    public void themChieuDay(ChieuDay a) {
        cdDAO.insert(a);
    }

    @Override
    public void suaChieuDay(ChieuDay a) {
        cdDAO.update(a);
    }

    @Override
    public List<ChieuDay> findAllChieuDay() {
        return cdDAO.selectAll();
    }

    @Override
    public ChieuDay findChieuDayDouble(Double chieuDay) {
        return cdDAO.selectByDoDay(chieuDay);
    }
    
    public ChieuDay findChieuDayId(int id) {
        return cdDAO.selectById(id);
    }
 
    
}
