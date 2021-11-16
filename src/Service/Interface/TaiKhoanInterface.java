/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.TaiKhoan;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface TaiKhoanInterface {
    void themTaiKhoan(TaiKhoan tk);
    
    void suaTaiKhoan(TaiKhoan tk);
    
    List<TaiKhoan> findAllTaiKhoan();
    
    TaiKhoan findTaiKhoanName(String name);
}
