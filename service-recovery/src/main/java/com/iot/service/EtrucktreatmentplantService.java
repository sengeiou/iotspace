package com.iot.service;
  
import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;

import java.util.List;
import java.util.Map;

public interface EtrucktreatmentplantService {
    public List<Etrucktreatmentplant> select(String sql);

    public List<Etrucktreatmentplant> selectAll();

    public List<Etrucktreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktreatmentplant> selectBySql(String sql);

    public Etrucktreatmentplant selectByPrimaryKey(Integer id);

    public List<Etrucktreatmentplantv> selectV(String sql);

    public List<Etrucktreatmentplantv> selectVAll();

    public List<Etrucktreatmentplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktreatmentplantv> selectVBySql(String sql);

    public Etrucktreatmentplantv selectVByPrimaryKey(Integer id);

    public int insert(Etrucktreatmentplant record);

    public int update(Etrucktreatmentplant record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Etrucktreatmentplantv>selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql);

    public Integer sumGlassweightBySql(String sql);

    public Integer sumPlasticweightBySql(String sql);

    public List<Map<String,Object>> yearGlassnumberGroupMonthSql(String sql);

    public List<Map<String,Object>> yearPlasticnumberGroupMonthSql(String sql);

    public List<Map<String,Object>> yearGlassweightGroupMonthSql(String sql);

    public List<Map<String,Object>> yearPlasticweightGroupMonthSql(String sql);

    public List<Map<String, Object>> trucktreatmentplantList(Integer page, Integer rows, String sort, String order, String sql);

    public List<Map<String, Object>> trucktreatmentplantListExport(String sql);

    public Map<String, Object> trucktreatmentplantSum(String sql);



}