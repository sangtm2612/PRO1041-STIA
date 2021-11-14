/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import Models.HangHoa.DanhMuc;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface DanhMucInterface {
    
    void themDanhMuc(DanhMuc d);
    
    void suaDanhMuc(DanhMuc d);
    
    List<DanhMuc> findAllDanhMuc();
    
    DanhMuc findDanhMucByName(String name);
}
