/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.LoaiHang;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface LoaiHangInterface {
    
    void themLoaiHang(LoaiHang l);
    
    void suaLoaiHang(LoaiHang l);
    
    List<LoaiHang> findAllLoaiHang();
    
    LoaiHang findIdLoaiHang(String name);
    
}
