package com.iot.service;

import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EtrucktreatmentplantServiceImpl.class)
public interface EtrucktreatmentplantService {
    @RequestMapping(value = "/trucktreatmentplant/select", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> selectAll();

    @RequestMapping(value = "/trucktreatmentplant/selectByPage", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectBySql", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplant/selectV", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectVAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> selectVAll();

    @RequestMapping(value = "/trucktreatmentplant/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etrucktreatmentplantv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplant/insert", method = RequestMethod.POST)
    public Rmap<Etrucktreatmentplant> insert(@RequestBody Etrucktreatmentplant record);

    @RequestMapping(value = "/trucktreatmentplant/update", method = RequestMethod.PUT)
    public Rmap<Etrucktreatmentplant> update(@RequestBody Etrucktreatmentplant record);

    @RequestMapping(value = "/trucktreatmentplant/delete", method = RequestMethod.DELETE)
    public Rmap<Etrucktreatmentplant> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplant/deletes", method = RequestMethod.DELETE)
    public Rmap<Etrucktreatmentplant> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/trucktreatmentplant/selectVByStatusAndSqlPage", method = RequestMethod.GET)
    public  Rmap<Etrucktreatmentplantv> selectVByStatusAndSqlPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/sumGlassweightBySql", method = RequestMethod.GET)
    public Rmap<Integer> sumGlassweightBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/sumPlasticweightBySql", method = RequestMethod.GET)
    public Rmap<Integer> sumPlasticweightBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/yearGlassnumberGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> yearGlassnumberGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/yearPlasticnumberGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> yearPlasticnumberGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/yearGlassweightGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> yearGlassweightGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/yearPlasticweightGroupMonthSql", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> yearPlasticweightGroupMonthSql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/trucktreatmentplantList", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> trucktreatmentplantList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/trucktreatmentplantListExport", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> trucktreatmentplantListExport(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplant/trucktreatmentplantSum", method = RequestMethod.GET)
    public  Rmap<Map<String, Object>> trucktreatmentplantSum(@RequestParam("sql") String sql);
}