package com.iot.service;

import com.iot.bean.Egmaterialin;
import com.iot.bean.Egmaterialinv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EgmaterialinServiceImpl.class)
public interface EgmaterialinService {
    @RequestMapping(value = "/gmaterialin/select", method = RequestMethod.GET)
    public Rmap<Egmaterialin> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectAll", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectAll();

    @RequestMapping(value = "/gmaterialin/selectByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialin/selectV", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectVAll", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVAll();

    @RequestMapping(value = "/gmaterialin/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Egmaterialinv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectVBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialin/insert", method = RequestMethod.POST)
    public Rmap<Egmaterialin> insert(@RequestBody Egmaterialin record);

    @RequestMapping(value = "/gmaterialin/update", method = RequestMethod.PUT)
    public Rmap<Egmaterialin> update(@RequestBody Egmaterialin record);

    @RequestMapping(value = "/gmaterialin/delete", method = RequestMethod.DELETE)
    public Rmap<Egmaterialin> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialin/deletes", method = RequestMethod.DELETE)
    public Rmap<Egmaterialin> deletes(@RequestParam("ids") String ids);

    /**
     * 入库重量统计 按照类别分组
     * @param sql
     * @return
     */
    @RequestMapping(value = "/gmaterialin/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByCategory(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/sumWeightGroupBySource", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupBySource(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialin/sumWeightGroupByMonth", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByMonth(@RequestParam("treatmentplantid") Integer treatmentplantid, @RequestParam("gmaterialcategoryid") Integer gmaterialcategoryid, @RequestParam("year") Integer year);
}