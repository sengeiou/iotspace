package com.iot.service;
  
import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;

import java.util.List;
import java.util.Map;

public interface EtruckhospitalService {
    public List<Etruckhospital> select(String sql);

    public List<Etruckhospital> selectAll();

    public List<Etruckhospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etruckhospital> selectBySql(String sql);

    public Etruckhospital selectByPrimaryKey(Integer id);

    public List<Etruckhospitalv> selectV(String sql);

    public List<Etruckhospitalv> selectVAll();

    public List<Etruckhospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etruckhospitalv> selectVBySql(String sql);

    public Etruckhospitalv selectVByPrimaryKey(Integer id);

    public List<Etruckhospitalv> selectVByTransportcompanyIdAndPage(Integer page, Integer rows, String sort, String order, String sql);

    public int insert(Etruckhospital record);

    public int update(Etruckhospital record);

    public int delete(Integer id);

    public int deletes(String ids);
    //数据统计
    public List<Map<String, Object>> statisticProvincePlasticWeight();
    public List<Map<String, Object>> statisticProvinceGlassWeight();

    public List<Map<String, Object>> statisticCityPlasticWeight(String address);
    public List<Map<String, Object>> statisticCityGlassWeight(String address);

    public List<Map<String, Object>> statisticPlatPlasticWeight(String address);
    public List<Map<String, Object>> statisticPlatGlassWeight(String address);

    public List<Map<String, Object>> statisticHospitalPlasticWeight(String address,String paddress);
    public List<Map<String, Object>> statisticHospitalGlassWeight(String address,String paddress);

    public Integer countryPlasticWeight();
    public Integer countryGlassWeight();

    public Integer provincePlasticWeight(String address);
    public Integer provinceGlassWeight(String address);

    public Integer cityPlasticWeight(String address);
    public Integer cityGlassWeight(String address);

    public Integer platPlasticWeight(String address, String paddress);
    public Integer platGlassWeight(String address, String paddress);

    public Integer hospitalPlasticWeight(Integer id);
    public Integer hospitalGlassWeight(Integer id);

    public List<Etruckhospitalv> countrySelectLimit();
    public List<Etruckhospitalv> provinceSelectLimit(String address);
    public List<Etruckhospitalv> citySelectLimit(String address);
    public List<Etruckhospitalv> platSelectLimit(String address,String paddress);
    public List<Etruckhospitalv> hospitalSelectLimit(Integer id);

    public List<Map<String, Object>> countryYearPlasticNumberGroupMonth(Integer year);
    public List<Map<String, Object>> countryYearGlassNumberGroupMonth(Integer year);


    public List<Map<String, Object>>  provinceYearPlasticNumberGroupMonth(String address,Integer year);
    public List<Map<String, Object>>  provinceYearGlassNumberGroupMonth(String address,Integer year);


    public List<Map<String, Object>>  cityYearPlasticNumberGroupMonth(String address,Integer year);
    public List<Map<String, Object>>  cityYearGlassNumberGroupMonth(String address,Integer year);

    public List<Map<String, Object>>  platYearPlasticNumberGroupMonth(String address,String paddress,Integer year);
    public List<Map<String, Object>>  platYearGlassNumberGroupMonth(String address,String paddress,Integer year);


    public List<Map<String, Object>>   hospitalYearPlasticNumberGroupMonth(Integer id,Integer year);
    public List<Map<String, Object>>  hospitalYearGlassNumberGroupMonth(Integer id,Integer year);


    //统计修改
    public List<Map<String, Object>> countryYearPlasticWeightGroupMonth(Integer year);
    public List<Map<String, Object>> countryYearGlassWeightGroupMonth(Integer year);

    //统计修改
    public List<Map<String, Object>> provinceYearPlasticWeightGroupMonth(String address, Integer year);
    public List<Map<String, Object>> provinceYearGlassWeightGroupMonth(String address, Integer year);


    public List<Map<String, Object>>  cityYearPlasticWeightGroupMonth(String address, Integer year);
    public List<Map<String, Object>>  cityYearGlassWeightGroupMonth(String address, Integer year);


    public List<Map<String, Object>>  platYearPlasticWeightGroupMonth(String address,String paddress, Integer year);
    public List<Map<String, Object>>  platYearGlassWeightGroupMonth(String address,String paddress, Integer year);


    public List<Map<String, Object>>   hospitalYearPlasticWeightGroupMonth(Integer id,Integer year);
    public List<Map<String, Object>>  hospitalYearGlassWeightGroupMonth(Integer id,Integer year);

    public List<Etruckhospitalv>selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql);

    public Integer sumGlassweightBySql(String sql);

    public Integer sumPlasticweightBySql(String sql);

    public List<Map<String, Object>> yearGlassweightGroupMonthSql(String sql);

    public List<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql);

    public List<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql);

    public List<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql);

    public List<Map<String, Object>> sumGlassweightSql(String sql);

    public List<Map<String, Object>> sumPlasticweightSql(String sql);

    public List<Map<String, Object>> truckhospitalList(Integer page, Integer rows, String sort, String order, String sql);

    public List<Map<String, Object>> truckhospitalExport(String sql);

    public List<Map<String, Object>> treatmentplantTruckhospitalList(Integer page, Integer rows, String sort, String order, String sql);

    public List<Map<String, Object>> treatmentplantTruckhospitalExport(String sql);

    public Map<String, Object> treatmentplantTruckhospitalSum(String sql);

    public Map<String, Object> sumByTruckid(Integer truckid);

}