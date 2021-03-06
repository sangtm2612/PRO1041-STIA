/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.DonViTinhDAO;
import DAO.Models.DonViTinh;
import Service.Interface.DonViTinhInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class DonViTinhService implements DonViTinhInterface{
    DonViTinhDAO dDAO = new DonViTinhDAO();

    @Override
    public void themDonViTinh(DonViTinh a) {
        dDAO.insert(a);
    }

    @Override
    public void suaDonViTinh(DonViTinh a) {
        dDAO.update(a);
    }

    @Override
    public List<DonViTinh> findAllDonViTinh() {
        return dDAO.selectAll();
    }

    @Override
    public DonViTinh findDonViTinhName(String name) {
        return dDAO.selectByName(name);
    }
    
    public DonViTinh findDonViTinhId(int id) {
        return dDAO.selectById(id);
    }
    
    
}
