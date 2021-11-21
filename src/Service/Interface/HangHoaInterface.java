/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.HangHoa;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface HangHoaInterface {
    void themHangHoa(HangHoa hh);
    
    void suaHangHoa(HangHoa hh);
    
    void xoaHangHoa(int id);
    
    List<HangHoa> findAllHangHoa();
    
    HangHoa findIdHangHoa(int Id);
}
