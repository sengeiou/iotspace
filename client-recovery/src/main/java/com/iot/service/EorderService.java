package com.iot.service;

import com.iot.bean.Eorder;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EorderServiceImpl.class)
public interface EorderService {
    @RequestMapping(value = "/order/select", method = RequestMethod.GET)
    public Rmap<Eorder> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/order/selectAll", method = RequestMethod.GET)
    public Rmap<Eorder> selectAll();

    @RequestMapping(value = "/order/selectByPage", method = RequestMethod.GET)
    public Rmap<Eorder> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/order/selectBySql", method = RequestMethod.GET)
    public Rmap<Eorder> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/order/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eorder> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/order/insert", method = RequestMethod.POST)
    public Rmap<Eorder> insert(@RequestBody Eorder record);

    @RequestMapping(value = "/order/update", method = RequestMethod.PUT)
    public Rmap<Eorder> update(@RequestBody Eorder record);

    @RequestMapping(value = "/order/delete", method = RequestMethod.DELETE)
    public Rmap<Eorder> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/order/deletes", method = RequestMethod.DELETE)
    public Rmap<Eorder> deletes(@RequestParam("ids") String ids);
}