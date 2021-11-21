package com.iot.service;

import com.iot.bean.Ecmaterialout;
import com.iot.bean.Ecmaterialoutv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EcmaterialoutServiceImpl.class)
public interface EcmaterialoutService {
    @RequestMapping(value = "/cmaterialout/select", method = RequestMethod.GET)
    public Rmap<Ecmaterialout> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialout> selectAll();

    @RequestMapping(value = "/cmaterialout/selectByPage", method = RequestMethod.GET)
    public Rmap<Ecmaterialout> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialout> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialout> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialout/selectV", method = RequestMethod.GET)
    public Rmap<Ecmaterialoutv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialoutv> selectVAll();

    @RequestMapping(value = "/cmaterialout/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Ecmaterialoutv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialoutv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialoutv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialout/insert", method = RequestMethod.POST)
    public Rmap<Ecmaterialout> insert(@RequestBody Ecmaterialout record);

    @RequestMapping(value = "/cmaterialout/update", method = RequestMethod.PUT)
    public Rmap<Ecmaterialout> update(@RequestBody Ecmaterialout record);

    @RequestMapping(value = "/cmaterialout/delete", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialout> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialout/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialout> deletes(@RequestParam("ids") String ids);

    /**
     * 出库重量统计 按照类别分组
     * @param sql
     * @return
     */
    @RequestMapping(value = "/cmaterialout/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByCategory(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/sumWeightGroupBySource", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupBySource(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialout/sumWeightGroupByMonth", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByMonth(@RequestParam("treatmentplantid") Integer treatmentplantid, @RequestParam("cmaterialcategoryid") Integer cmaterialcategoryid, @RequestParam("year") Integer year);
}