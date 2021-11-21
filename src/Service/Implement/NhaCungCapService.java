/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.NhaCungCapDAO;
import DAO.Models.NhaCungCap;
import Service.Interface.NhaCungCapInterface;
import java.util.List;

/**
 *
 * @author sangt
 */
public class NhaCungCapService implements NhaCungCapInterface{
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();

    @Override
    public void themNhaCungCap(NhaCungCap ncc) {
        nccDAO.insert(ncc);
    }

    @Override
    public void suaNhaCungCap(NhaCungCap ncc) {
        nccDAO.update(ncc);
    }

    @Override
    public List<NhaCungCap> findAllNhaCungCap() {
        return nccDAO.selectAll();
    }

    @Override
    public NhaCungCap findNhaCungCapBySDT(String SDT) {
        return nccDAO.selectBySDT(SDT);
    }

    public NhaCungCap findNhaCungCapById(Integer Id) {
        return nccDAO.selectById(Id);
    }
    
    public NhaCungCap findNhaCungCapByName(String name) {
        return nccDAO.selectByName(name);
    }
    
    
}
