package com.iot.service;

import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtruckServiceImpl.class)
public interface EtruckService {
    @RequestMapping(value = "/truck/select", method = RequestMethod.GET)
    public Rmap<Etruck> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectAll", method = RequestMethod.GET)
    public Rmap<Etruck> selectAll();

    @RequestMapping(value = "/truck/selectByPage", method = RequestMethod.GET)
    public Rmap<Etruck> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectBySql", method = RequestMethod.GET)
    public Rmap<Etruck> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruck> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truck/selectV", method = RequestMethod.GET)
    public Rmap<Etruckv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectVAll", method = RequestMethod.GET)
    public Rmap<Etruckv> selectVAll();

    @RequestMapping(value = "/truck/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etruckv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etruckv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truck/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruckv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truck/insert", method = RequestMethod.POST)
    public Rmap<Etruck> insert(@RequestBody Etruck record);

    @RequestMapping(value = "/truck/update", method = RequestMethod.PUT)
    public Rmap<Etruck> update(@RequestBody Etruck record);

    @RequestMapping(value = "/truck/delete", method = RequestMethod.DELETE)
    public Rmap<Etruck> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truck/deletes", method = RequestMethod.DELETE)
    public Rmap<Etruck> deletes(@RequestParam("ids") String ids);
}