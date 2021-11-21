package com.iot.service;

import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EcityServiceImpl.class)
public interface EcityService {
    @RequestMapping(value = "/city/select", method = RequestMethod.GET)
    public Rmap<Ecity> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectAll", method = RequestMethod.GET)
    public Rmap<Ecity> selectAll();

    @RequestMapping(value = "/city/selectByPage", method = RequestMethod.GET)
    public  Rmap<Ecity> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectBySql", method = RequestMethod.GET)
    public Rmap<Ecity> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecity> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/city/selectV", method = RequestMethod.GET)
    public Rmap<Ecityv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVAll();

    @RequestMapping(value = "/city/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/city/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/city/insert", method = RequestMethod.POST)
    public Rmap<Ecity> insert(@RequestBody Ecity record);

    @RequestMapping(value = "/city/update", method = RequestMethod.PUT)
    public Rmap<Ecity> update(@RequestBody Ecity record);

    @RequestMapping(value = "/city/delete", method = RequestMethod.DELETE)
    public Rmap<Ecity> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/city/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecity> deletes(@RequestParam("ids") String ids);
}