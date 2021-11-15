/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.KhachHang;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface KhachHangInterface {
    void themKhachHang(KhachHang kh);
    
    void suaKhachHang(KhachHang kh);
    
    List<KhachHang> findAllKhachHang();
    
    KhachHang findKhachHangSDT(String name);
}
