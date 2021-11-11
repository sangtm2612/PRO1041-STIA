/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.ChiTietHangHoa;
import Utils.jdbcHelper;
import java.util.List;

/**
 *
 * @author sangt
 */
public class ChiTietHangHoaDAO extends StiaDAO<ChiTietHangHoa, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietHangHoa(SoLuong,TrangThai,Id_KichThuoc,Id_MauSac,Id_HangHoa,Id_ApSuat,Id_DonViTinh,Id_GiaTien,Id_LoaiHang,Id_ChieuDay) VALUES(?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ChiTietHangHoa SET TenApSuat = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM ChiTietHangHoa";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietHangHoa WHERE Id = ?";

    @Override
    public void insert(ChiTietHangHoa entity) {
        jdbcHelper.Update(INSERT_SQL,entity.getSoLuong(),entity. entity.getId_KichThuoc(),entity.getId_MauSac(),entity.getId_HangHoa(),entity.getId_ApSuat(),entity.getId_DonViTinh(),entity.getId_GiaTien(),entity.getId_LoaiHang(),entity.getId_ChieuDay())
    }

    @Override
    public void update(ChiTietHangHoa entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChiTietHangHoa> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChiTietHangHoa selectById(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<ChiTietHangHoa> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
