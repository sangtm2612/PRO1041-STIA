/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.PhongBanDAO;
import DAO.Models.PhongBan;
import Service.Interface.PhongBanInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class PhongBanService implements PhongBanInterface{
    PhongBanDAO pbDAO = new PhongBanDAO();

    @Override
    public void themPhongBan(PhongBan a) {
        pbDAO.insert(a);
    }

    @Override
    public void suaPhongBan(PhongBan a) {
        pbDAO.update(a);
    }

    @Override
    public List<PhongBan> findAllPhongBan() {
        return pbDAO.selectAll();
    }

    @Override
    public PhongBan findPhongBanByName(String name) {
        return pbDAO.selectByName(name);
    }

  
    
    
    
    
}
