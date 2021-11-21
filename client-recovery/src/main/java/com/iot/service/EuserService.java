package com.iot.service;

import com.iot.bean.Euser;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EuserServiceImpl.class)
public interface EuserService {
    @RequestMapping(value = "/user/select", method = RequestMethod.GET)
    public Rmap<Euser> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/user/selectAll", method = RequestMethod.GET)
    public Rmap<Euser> selectAll();

    @RequestMapping(value = "/user/selectByPage", method = RequestMethod.GET)
    public Rmap<Euser> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/user/selectBySql", method = RequestMethod.GET)
    public Rmap<Euser> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/user/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Euser> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/user/insert", method = RequestMethod.POST)
    public Rmap<Euser> insert(@RequestBody Euser record);

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public Rmap<Euser> update(@RequestBody Euser record);

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public Rmap<Euser> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/user/deletes", method = RequestMethod.DELETE)
    public Rmap<Euser> deletes(@RequestParam("ids") String ids);
}