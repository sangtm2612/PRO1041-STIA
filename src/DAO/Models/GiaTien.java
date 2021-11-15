/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Models;

/**
 *
 * @author sangt
 */
public class GiaTien {
    private int Id;
    private int GiaNhap;
    private int GiaBan;

    public GiaTien() {
    }

    public GiaTien(int GiaNhap, int GiaBan) {
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
    }
    
    

    public GiaTien(int Id, int GiaNhap, int GiaBan) {
        this.Id = Id;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }
    
    
}
