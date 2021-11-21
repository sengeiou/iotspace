package com.iot.service;
  
import com.iot.bean.Enterprise;

import java.util.List;

public interface EnterpriseService {
    public List<Enterprise> select(String sql);

    public List<Enterprise> selectAll();

    public List<Enterprise> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Enterprise> selectBySql(String sql);

    public Enterprise selectByPrimaryKey(Integer id);

    public int insert(Enterprise record);

    public int update(Enterprise record);

    public int delete(Integer id);

    public int deletes(String ids);
}