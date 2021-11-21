package com.iot.service;

import com.iot.bean.Eorder;

import java.util.List;


public interface EorderService {
    public List<Eorder> select(String sql);

    public List<Eorder> selectAll();

    public List<Eorder> selectBySql(String sql);

    public List<Eorder> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public Eorder selectByPrimaryKey(Integer id);

    public int insert(Eorder record);

    public int update(Eorder record);

    public int delete(Integer id);

    public int deletes(String ids);

}
