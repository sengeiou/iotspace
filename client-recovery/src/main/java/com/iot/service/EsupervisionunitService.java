package com.iot.service;

import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EsupervisionunitServiceImpl.class)
public interface EsupervisionunitService {
    @RequestMapping(value = "/supervisionunit/select", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/supervisionunit/selectAll", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectAll();

    @RequestMapping(value = "/supervisionunit/selectByPage", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/supervisionunit/selectBySql", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/supervisionunit/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/supervisionunit/selectV", method = RequestMethod.GET)
    public Rmap<Esupervisionunitv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/supervisionunit/selectVAll", method = RequestMethod.GET)
    public Rmap<Esupervisionunitv> selectVAll();

    @RequestMapping(value = "/supervisionunit/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Esupervisionunitv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/supervisionunit/selectVBySql", method = RequestMethod.GET)
    public Rmap<Esupervisionunitv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/supervisionunit/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Esupervisionunitv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/supervisionunit/insert", method = RequestMethod.POST)
    public Rmap<Esupervisionunit> insert(@RequestBody Esupervisionunit record);

    @RequestMapping(value = "/supervisionunit/update", method = RequestMethod.PUT)
    public Rmap<Esupervisionunit> update(@RequestBody Esupervisionunit record);

    @RequestMapping(value = "/supervisionunit/delete", method = RequestMethod.DELETE)
    public Rmap<Esupervisionunit> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/supervisionunit/deletes", method = RequestMethod.DELETE)
    public Rmap<Esupervisionunit> deletes(@RequestParam("ids") String ids);

}