package com.iot.service;

import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EcmaterialinServiceImpl.class)
public interface EcmaterialinService {
    @RequestMapping(value = "/cmaterialin/select", method = RequestMethod.GET)
    public Rmap<Ecmaterialin> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialin> selectAll();

    @RequestMapping(value = "/cmaterialin/selectByPage", method = RequestMethod.GET)
    public Rmap<Ecmaterialin> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialin> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialin> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialin/selectV", method = RequestMethod.GET)
    public Rmap<Ecmaterialinv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialinv> selectVAll();

    @RequestMapping(value = "/cmaterialin/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Ecmaterialinv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialinv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialinv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialin/insert", method = RequestMethod.POST)
    public Rmap<Ecmaterialin> insert(@RequestBody Ecmaterialin record);

    @RequestMapping(value = "/cmaterialin/update", method = RequestMethod.PUT)
    public Rmap<Ecmaterialin> update(@RequestBody Ecmaterialin record);

    @RequestMapping(value = "/cmaterialin/delete", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialin> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialin/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialin> deletes(@RequestParam("ids") String ids);

    /**
     * 入库重量统计 按照类别分组
     * @param sql
     * @return
     */
    @RequestMapping(value = "/cmaterialin/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByCategory(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/sumWeightGroupBySource", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupBySource(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialin/sumWeightGroupByMonth", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByMonth(@RequestParam("treatmentplantid") Integer treatmentplantid, @RequestParam("cmaterialcategoryid") Integer cmaterialcategoryid, @RequestParam("year") Integer year);
}