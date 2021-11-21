package com.iot.service;
  
import com.iot.bean.Ecmaterialout;
import com.iot.bean.Ecmaterialoutv;

import java.util.List;
import java.util.Map;

public interface EcmaterialoutService {
    public List<Ecmaterialout> select(String sql);

    public List<Ecmaterialout> selectAll();

    public List<Ecmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialout> selectBySql(String sql);

    public Ecmaterialout selectByPrimaryKey(Integer id);

    public List<Ecmaterialoutv> selectV(String sql);

    public List<Ecmaterialoutv> selectVAll();

    public List<Ecmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialoutv> selectVBySql(String sql);

    public Ecmaterialoutv selectVByPrimaryKey(Integer id);

    public int insert(Ecmaterialout record);

    public int update(Ecmaterialout record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Map<String,Object>> sumWeightGroupByCategory(String sql);

    public List<Map<String,Object>> sumWeightGroupBySource(String sql);

    public List<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year);
}