/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.ApSuatDAO;
import DAO.MauSacDAO;
import DAO.Models.ApSuat;
import DAO.Models.MauSac;
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
    public MauSac findMauSacName(String name) {
        return mDAO.selectByName(name);
    }
    
    public MauSac findMauSacId(int id) {
        return mDAO.selectById(id);
    }
    
    
}
