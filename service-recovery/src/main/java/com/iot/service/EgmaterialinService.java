package com.iot.service;
  
import com.iot.bean.Egmaterialin;
import com.iot.bean.Egmaterialinv;

import java.util.List;
import java.util.Map;

public interface EgmaterialinService {
    public List<Egmaterialin> select(String sql);

    public List<Egmaterialin> selectAll();

    public List<Egmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialin> selectBySql(String sql);

    public Egmaterialin selectByPrimaryKey(Integer id);

    public List<Egmaterialinv> selectV(String sql);

    public List<Egmaterialinv> selectVAll();

    public List<Egmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialinv> selectVBySql(String sql);

    public Egmaterialinv selectVByPrimaryKey(Integer id);

    public int insert(Egmaterialin record);

    public int update(Egmaterialin record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Map<String,Object>> sumWeightGroupByCategory(String sql);

    public List<Map<String,Object>> sumWeightGroupBySource(String sql);

    public List<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year);
}