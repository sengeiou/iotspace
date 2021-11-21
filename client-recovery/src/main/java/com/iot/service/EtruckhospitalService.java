package com.iot.service;

import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EtruckhospitalServiceImpl.class)
public interface EtruckhospitalService {
    @RequestMapping(value = "/truckhospital/select", method = RequestMethod.GET)
    public Rmap<Etruckhospital> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectAll", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectAll();

    @RequestMapping(value = "/truckhospital/selectByPage", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectBySql", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/selectV", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectVAll", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectVAll();

    @RequestMapping(value = "/truckhospital/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etruckhospitalv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/insert", method = RequestMethod.POST)
    public Rmap<Etruckhospital> insert(@RequestBody Etruckhospital record);

    @RequestMapping(value = "/truckhospital/update", method = RequestMethod.PUT)
    public Rmap<Etruckhospital> update(@RequestBody Etruckhospital record);

    @RequestMapping(value = "/truckhospital/delete", method = RequestMethod.DELETE)
    public Rmap<Etruckhospital> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/deletes", method = RequestMethod.DELETE)
    public Rmap<Etruckhospital> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/truckhospital/statisticProvincePlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticProvincePlasticWeight();

    @RequestMapping(value = "/truckhospital/statisticProvinceGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticProvinceGlassWeight();

    @RequestMapping(value = "/truckhospital/statisticCityPlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticCityPlasticWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/statisticCityGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticCityGlassWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/statisticPlatPlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticPlatPlasticWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/statisticPlatGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticPlatGlassWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/statisticHospitalPlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticHospitalPlasticWeight(@RequestParam("address") String address, @RequestParam("paddress") String paddress);

    @RequestMapping(value = "/truckhospital/statisticHospitalGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticHospitalGlassWeight(@RequestParam("address") String address, @RequestParam("paddress") String paddress);

    @RequestMapping(value = "/truckhospital/countryPlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> countryPlasticWeight();

    @RequestMapping(value = "/truckhospital/countryGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> countryGlassWeight();

    @RequestMapping(value = "/truckhospital/provincePlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> provincePlasticWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/provinceGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> provinceGlassWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/cityPlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> cityPlasticWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/cityGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> cityGlassWeight(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/platPlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> platPlasticWeight(@RequestParam("address") String address, @RequestParam("paddress") String paddress);

    @RequestMapping(value = "/truckhospital/platGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> platGlassWeight(@RequestParam("address") String address, @RequestParam("paddress") String paddress);

    @RequestMapping(value = "/truckhospital/hospitalPlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> hospitalPlasticWeight(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/hospitalGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> hospitalGlassWeight(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/countrySelectLimit", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> countrySelectLimit();

    @RequestMapping(value = "/truckhospital/provinceSelectLimit", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> provinceSelectLimit(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/citySelectLimit", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> citySelectLimit(@RequestParam("address") String address);

    @RequestMapping(value = "/truckhospital/platSelectLimit", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> platSelectLimit(@RequestParam("address") String address,@RequestParam("paddress") String paddress);

    @RequestMapping(value = "/truckhospital/hospitalSelectLimit", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> hospitalSelectLimit(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckhospital/countryYearPlasticWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> countryYearPlasticWeightGroupMonth(@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/countryYearGlassWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> countryYearGlassWeightGroupMonth(@RequestParam("year") Integer year);

    @RequestMapping(value = "/truckhospital/provinceYearPlasticWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> provinceYearPlasticWeightGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/provinceYearGlassWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> provinceYearGlassWeightGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);

    @RequestMapping(value = "/truckhospital/cityYearPlasticWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> cityYearPlasticWeightGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/cityYearGlassWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> cityYearGlassWeightGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);


    @RequestMapping(value = "/truckhospital/platYearPlasticWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> platYearPlasticWeightGroupMonth(@RequestParam("address") String address,@RequestParam("paddress") String paddress,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/platYearGlassWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> platYearGlassWeightGroupMonth(@RequestParam("address") String address,@RequestParam("paddress") String paddress,@RequestParam("year") Integer year);

    @RequestMapping(value = "/truckhospital/hospitalYearPlasticWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> hospitalYearPlasticWeightGroupMonth(@RequestParam("id") Integer id, @RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/hospitalYearGlassWeightGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> hospitalYearGlassWeightGroupMonth(@RequestParam("id") Integer id,@RequestParam("year") Integer year);




    //
    @RequestMapping(value = "/truckhospital/countryYearPlasticNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> countryYearPlasticNumberGroupMonth(@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/countryYearGlassNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> countryYearGlassNumberGroupMonth(@RequestParam("year") Integer year);


    @RequestMapping(value = "/truckhospital/provinceYearPlasticNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> provinceYearPlasticNumberGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/provinceYearGlassNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> provinceYearGlassNumberGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);


    @RequestMapping(value = "/truckhospital/cityYearPlasticNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> cityYearPlasticNumberGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/cityYearGlassNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> cityYearGlassNumberGroupMonth(@RequestParam("address") String address,@RequestParam("year") Integer year);


    @RequestMapping(value = "/truckhospital/platYearPlasticNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> platYearPlasticNumberGroupMonth(@RequestParam("address") String address,@RequestParam("paddress") String paddress,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/platYearGlassNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> platYearGlassNumberGroupMonth(@RequestParam("address") String address,@RequestParam("paddress") String paddress,@RequestParam("year") Integer year);

    @RequestMapping(value = "/truckhospital/hospitalYearPlasticNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> hospitalYearPlasticNumberGroupMonth(@RequestParam("id") Integer id,@RequestParam("year") Integer year);
    @RequestMapping(value = "/truckhospital/hospitalYearGlassNumberGroupMonth", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> hospitalYearGlassNumberGroupMonth(@RequestParam("id") Integer id,@RequestParam("year") Integer year);


    @RequestMapping(value = "/truckhospital/selectVByStatusAndSqlPage", method = RequestMethod.GET)
    public  Rmap<Etruckhospitalv> selectVByStatusAndSqlPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/truckhospital/selectVByTransportcompanyIdAndPage", method = RequestMethod.GET)
    public  Rmap<Etruckhospitalv> selectVByTransportcompanyIdAndPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/sumGlassweightBySql", method = RequestMethod.GET)
    public Rmap<Integer> sumGlassweightBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/sumPlasticweightBySql", method = RequestMethod.GET)
    public Rmap<Integer> sumPlasticweightBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/yearGlassweightGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> yearGlassweightGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/yearPlasticweightGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> yearPlasticweightGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/yearGlassnumberGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> yearGlassnumberGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/yearPlasticnumberGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> yearPlasticnumberGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/sumGlassweightSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumGlassweightSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/sumPlasticweightSql", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumPlasticweightSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/truckhospitalList", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> truckhospitalList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/truckhospitalExport", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> truckhospitalExport(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/treatmentplantTruckhospitalList", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> treatmentplantTruckhospitalList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/treatmentplantTruckhospitalExport", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> treatmentplantTruckhospitalExport(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/treatmentplantTruckhospitalSum", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> treatmentplantTruckhospitalSum(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckhospital/sumByTruckid", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> sumByTruckid(@RequestParam("truckid") Integer truckid);
}