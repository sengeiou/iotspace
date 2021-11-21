package com.iot.service;
  
import com.iot.bean.Etreatmentplant;

import java.util.List;

public interface EtreatmentplantService {
    public List<Etreatmentplant> select(String sql);

    public List<Etreatmentplant> selectAll();

    public List<Etreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etreatmentplant> selectBySql(String sql);

    public Etreatmentplant selectByPrimaryKey(Integer id);

    public int insert(Etreatmentplant record);

    public int update(Etreatmentplant record);

    public int delete(Integer id);

    public int deletes(String ids);

    public int updateProvince(Etreatmentplant record);
}