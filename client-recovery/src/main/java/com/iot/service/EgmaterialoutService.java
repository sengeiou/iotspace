package com.iot.service;

import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EgmaterialoutServiceImpl.class)
public interface EgmaterialoutService {
    @RequestMapping(value = "/gmaterialout/select", method = RequestMethod.GET)
    public Rmap<Egmaterialout> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectAll", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectAll();

    @RequestMapping(value = "/gmaterialout/selectByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialout/selectV", method = RequestMethod.GET)
    public Rmap<Egmaterialoutv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectVAll", method = RequestMethod.GET)
    public Rmap<Egmaterialoutv> selectVAll();

    @RequestMapping(value = "/gmaterialout/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Egmaterialoutv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectVBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialoutv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialoutv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialout/insert", method = RequestMethod.POST)
    public Rmap<Egmaterialout> insert(@RequestBody Egmaterialout record);

    @RequestMapping(value = "/gmaterialout/update", method = RequestMethod.PUT)
    public Rmap<Egmaterialout> update(@RequestBody Egmaterialout record);

    @RequestMapping(value = "/gmaterialout/delete", method = RequestMethod.DELETE)
    public Rmap<Egmaterialout> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialout/deletes", method = RequestMethod.DELETE)
    public Rmap<Egmaterialout> deletes(@RequestParam("ids") String ids);

    /**
     * 出库重量统计 按照类别分组
     * @param sql
     * @return
     */
    @RequestMapping(value = "/gmaterialout/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByCategory(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/sumWeightGroupBySource", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupBySource(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialout/sumWeightGroupByMonth", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByMonth(@RequestParam("treatmentplantid") Integer treatmentplantid, @RequestParam("gmaterialcategoryid") Integer gmaterialcategoryid, @RequestParam("year") Integer year);
}