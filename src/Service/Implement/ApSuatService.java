/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoa.ApSuatDAO;
import Models.HangHoa.ApSuat;
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
    public ApSuat findIdApSuat(String name) {
        return aDAO.selectByName(name);
    }

  
    
    
    
    
}
