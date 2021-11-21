package com.iot.service;

import com.iot.bean.Eprovince;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EprovinceServiceImpl.class)
public interface EprovinceService {
    @RequestMapping(value = "/province/select", method = RequestMethod.GET)
    public Rmap<Eprovince> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/province/selectAll", method = RequestMethod.GET)
    public Rmap<Eprovince> selectAll();

    @RequestMapping(value = "/province/selectByPage", method = RequestMethod.GET)
    public Rmap<Eprovince> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/province/selectBySql", method = RequestMethod.GET)
    public Rmap<Eprovince> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/province/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eprovince> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/province/insert", method = RequestMethod.POST)
    public Rmap<Eprovince> insert(@RequestBody Eprovince record);

    @RequestMapping(value = "/province/update", method = RequestMethod.PUT)
    public Rmap<Eprovince> update(@RequestBody Eprovince record);

    @RequestMapping(value = "/province/delete", method = RequestMethod.DELETE)
    public Rmap<Eprovince> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/province/deletes", method = RequestMethod.DELETE)
    public Rmap<Eprovince> deletes(@RequestParam("ids") String ids);
}