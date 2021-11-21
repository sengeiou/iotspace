package com.iot.service;

import com.iot.bean.Eoperate;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EoperateServiceImpl.class)
public interface EoperateService {
    @RequestMapping(value = "/operate/select", method = RequestMethod.GET)
    public Rmap<Eoperate> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/operate/selectAll", method = RequestMethod.GET)
    public Rmap<Eoperate> selectAll();

    @RequestMapping(value = "/operate/selectByPage", method = RequestMethod.GET)
    public  Rmap<Eoperate> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/operate/selectBySql", method = RequestMethod.GET)
    public Rmap<Eoperate> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/operate/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eoperate> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/operate/insert", method = RequestMethod.POST)
    public Rmap<Eoperate> insert(@RequestBody Eoperate record);

    @RequestMapping(value = "/operate/update", method = RequestMethod.PUT)
    public Rmap<Eoperate> update(@RequestBody Eoperate record);

    @RequestMapping(value = "/operate/delete", method = RequestMethod.DELETE)
    public Rmap<Eoperate> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/operate/deletes", method = RequestMethod.DELETE)
    public Rmap<Eoperate> deletes(@RequestParam("ids") String ids);
}