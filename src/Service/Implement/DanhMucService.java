/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoa.DanhMucDAO;
import Models.HangHoa.DanhMuc;
import Service.Interface.DanhMucInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class DanhMucService implements DanhMucInterface{
    
    DanhMucDAO dDAO = new DanhMucDAO();

    @Override
    public void themDanhMuc(DanhMuc d) {
        dDAO.insert(d);
    }

    @Override
    public void suaDanhMuc(DanhMuc d) {
        
        dDAO.update(d);
    }

    @Override
    public List<DanhMuc> findAllDanhMuc() {
        return dDAO.selectAll();
    }

    @Override
    public void findDanhMucByName(DanhMuc d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
