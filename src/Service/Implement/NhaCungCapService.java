/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Implement;

import DAO.HangHoa.NhaCungCapDAO;
import Models.HangHoa.NhaCungCap;
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
    public int findIdNhaCungCap(String NhaCungCap) {
        return -1;
    }

  
    
    
    
    
}
