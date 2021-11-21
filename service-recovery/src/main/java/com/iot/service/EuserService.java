package com.iot.service;
  
import com.iot.bean.Euser;

import java.util.List;

public interface EuserService {
    public List<Euser> select(String sql);

    public List<Euser> selectAll();

    public List<Euser> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Euser> selectBySql(String sql);

    public Euser selectByPrimaryKey(Integer id);

    public int insert(Euser record);

    public int update(Euser record);

    public int delete(Integer id);

    public int deletes(String ids);
}