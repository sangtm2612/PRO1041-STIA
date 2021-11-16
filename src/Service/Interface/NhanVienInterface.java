/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.NhanVien;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface NhanVienInterface {
    void themNhanVien(NhanVien nv);
    
    void suaNhanVien(NhanVien nv);
    
    List<NhanVien> findAllNhanVien();
    
    NhanVien findIdNhanVien(Integer Id);
}
