/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.KichThuocDAO;
import DAO.Models.KichThuoc;
import Service.Interface.KichThuocInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class KichThuocService implements KichThuocInterface{
    KichThuocDAO kDAO = new KichThuocDAO();

    @Override
    public void themKichThuoc(KichThuoc k) {
        kDAO.insert(k);
    }

    @Override
    public void suaKichThuoc(KichThuoc k) {
        kDAO.update(k);
    }

    @Override
    public List<KichThuoc> findAllKichThuoc() {
        return kDAO.selectAll();
    }

    @Override
    public KichThuoc findIdKichThuoc(String name) {
        return kDAO.selectByName(name);
    }
    
    
    
    
}
