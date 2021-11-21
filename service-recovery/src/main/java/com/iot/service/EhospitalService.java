package com.iot.service;
  
import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;

import java.util.List;
import java.util.Map;

public interface EhospitalService {
    public List<Ehospital> select(String sql);

    public List<Ehospital> selectAll();

    public List<Ehospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql);


    public List<Ehospital> selectBySql(String sql);

    public Ehospital selectByPrimaryKey(Integer id);

    public List<Ehospitalv> selectV(String sql);

    public List<Ehospitalv> selectVAll();

    public List<Ehospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ehospitalv> selectVBySql(String sql);

    public Ehospitalv selectVByPrimaryKey(Integer id);

    public int insert(Ehospital record);

    public int update(Ehospital record);

    public int delete(Integer id);

    public int deletes(String ids);

    public int countryHospitalNum();

    public int provinceHospitalNum(String address);

    public int cityHospitalNum(String address);

    public int platHospitalNum(String address,String paddress);

    public List<Map<String,Object>>countByTransportcompanyGroupByType(Integer transportcompanyid);

    public Map<String,Object> countByTransportcompanyAndLevel(Integer transportcompanyid,String level);

    public int hospitalNumBySql(String sql);

    public List<Ehospitalv> provinceHospital(Integer page, Integer rows, String sort, String order,String address, String sql);

    public List<Ehospitalv> cityHospital(Integer page, Integer rows, String sort, String order,String address, String sql);

    public List<Ehospitalv> platHospital(Integer page, Integer rows, String sort, String order,String address,String paddress, String sql);


    public int updateLongitudeLatitude(Ehospital record);
}