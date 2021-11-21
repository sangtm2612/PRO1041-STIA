/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.ApSuatDAO;
import DAO.Models.ApSuat;
import Service.Interface.ApSuatInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class ApSuatService implements ApSuatInterface{
    ApSuatDAO aDAO = new ApSuatDAO();

    @Override
    public void themApSuat(ApSuat a) {
        aDAO.insert(a);
    }

    @Override
    public void suaApSuat(ApSuat a) {
        aDAO.update(a);
    }

    @Override
    public List<ApSuat> findAllApSuat() {
        return aDAO.selectAll();
    }

    @Override
    public ApSuat findApSuatName(String name) {
        return aDAO.selectByName(name);
    }

    public ApSuat findApSuatId(int id) {
        return aDAO.selectById(id);
    }
    
    
    
    
}
