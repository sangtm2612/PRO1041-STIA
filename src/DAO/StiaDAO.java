/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author sangt
 */
public abstract class StiaDAO<E,K> {
    public abstract void insert(E entity);
    public abstract void update(E entity);
    public abstract void delete(K Key);
    public abstract List<E> selectAll();
    public abstract E selectById(K Key);
    protected abstract List<E> selectBySql(String sql, Object...args);
}
