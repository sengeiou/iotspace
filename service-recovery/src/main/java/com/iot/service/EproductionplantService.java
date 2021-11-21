package com.iot.service;
  
import com.iot.bean.Eproductionplant;

import java.util.List;

public interface EproductionplantService {
    public List<Eproductionplant> select(String sql);

    public List<Eproductionplant> selectAll();

    public List<Eproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Eproductionplant> selectBySql(String sql);

    public Eproductionplant selectByPrimaryKey(Integer id);

    public int insert(Eproductionplant record);

    public int update(Eproductionplant record);

    public int delete(Integer id);

    public int deletes(String ids);
}