package com.iot.service;
  
import com.iot.bean.Etradeuser;

import java.util.List;

public interface EtradeuserService {
    public List<Etradeuser> select(String sql);

    public List<Etradeuser> selectAll();

    public List<Etradeuser> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etradeuser> selectBySql(String sql);

    public Etradeuser selectByPrimaryKey(Integer id);

    public int insert(Etradeuser record);

    public int update(Etradeuser record);

    public int delete(Integer id);

    public int deletes(String ids);
}