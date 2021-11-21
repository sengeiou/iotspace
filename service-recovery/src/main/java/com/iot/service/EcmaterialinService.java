package com.iot.service;
  
import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;

import java.util.List;
import java.util.Map;

public interface EcmaterialinService {
    public List<Ecmaterialin> select(String sql);

    public List<Ecmaterialin> selectAll();

    public List<Ecmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialin> selectBySql(String sql);

    public Ecmaterialin selectByPrimaryKey(Integer id);

    public List<Ecmaterialinv> selectV(String sql);

    public List<Ecmaterialinv> selectVAll();

    public List<Ecmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialinv> selectVBySql(String sql);

    public Ecmaterialinv selectVByPrimaryKey(Integer id);

    public int insert(Ecmaterialin record);

    public int update(Ecmaterialin record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Map<String,Object>> sumWeightGroupByCategory(String sql);

    public List<Map<String,Object>> sumWeightGroupBySource(String sql);

    public List<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid,Integer year);
}