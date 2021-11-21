package com.iot.service;
  
import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;

import java.util.List;

public interface EtruckService {
    public List<Etruck> select(String sql);

    public List<Etruck> selectAll();

    public List<Etruck> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etruck> selectBySql(String sql);

    public Etruck selectByPrimaryKey(Integer id);

    public List<Etruckv> selectV(String sql);

    public List<Etruckv> selectVAll();

    public List<Etruckv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etruckv> selectVBySql(String sql);

    public Etruckv selectVByPrimaryKey(Integer id);

    public int insert(Etruck record);

    public int update(Etruck record);

    public int delete(Integer id);

    public int deletes(String ids);
}