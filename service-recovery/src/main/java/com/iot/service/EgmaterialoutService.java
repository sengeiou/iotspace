package com.iot.service;
  
import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;

import java.util.List;
import java.util.Map;

public interface EgmaterialoutService {
    public List<Egmaterialout> select(String sql);

    public List<Egmaterialout> selectAll();

    public List<Egmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialout> selectBySql(String sql);

    public Egmaterialout selectByPrimaryKey(Integer id);

    public List<Egmaterialoutv> selectV(String sql);

    public List<Egmaterialoutv> selectVAll();

    public List<Egmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialoutv> selectVBySql(String sql);

    public Egmaterialoutv selectVByPrimaryKey(Integer id);

    public int insert(Egmaterialout record);

    public int update(Egmaterialout record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Map<String,Object>> sumWeightGroupByCategory(String sql);

    public List<Map<String,Object>> sumWeightGroupBySource(String sql);

    public List<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year);
}