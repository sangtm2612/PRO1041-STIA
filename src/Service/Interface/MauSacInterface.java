/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Interface;

import DAO.Models.MauSac;
import java.util.List;

/**
 *
 * @author sangt
 */
public interface MauSacInterface {
    
    void themMauSac(MauSac m);
    
    void suaMauSac(MauSac m);
    
    List<MauSac> findAllMauSac();
    
    MauSac findMauSacName(String name);
    
}
