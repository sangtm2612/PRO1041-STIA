/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoa.ApSuatDAO;
import DAO.HangHoa.MauSacDAO;
import Models.HangHoa.ApSuat;
import Models.HangHoa.MauSac;
import Service.Interface.MauSacInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class MauSacService implements MauSacInterface{
    MauSacDAO mDAO = new MauSacDAO();

    @Override
    public void themMauSac(MauSac m) {
        mDAO.insert(m);
    }

    @Override
    public void suaMauSac(MauSac m) {
        mDAO.update(m);
    }

    @Override
    public List<MauSac> findAllMauSac() {
        return mDAO.selectAll();
    }

    @Override
    public MauSac findIdMauSac(String name) {
        return mDAO.selectByName(name);
    }
    
    
    
    
}
