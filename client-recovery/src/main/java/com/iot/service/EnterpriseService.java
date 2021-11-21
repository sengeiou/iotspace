package com.iot.service;

import com.iot.bean.Enterprise;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EnterpriseServiceImpl.class)
public interface EnterpriseService {
    @RequestMapping(value = "/nterprise/select", method = RequestMethod.GET)
    public Rmap<Enterprise> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/nterprise/selectAll", method = RequestMethod.GET)
    public Rmap<Enterprise> selectAll();

    @RequestMapping(value = "/nterprise/selectByPage", method = RequestMethod.GET)
    public Rmap<Enterprise> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/nterprise/selectBySql", method = RequestMethod.GET)
    public Rmap<Enterprise> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/nterprise/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Enterprise> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/nterprise/insert", method = RequestMethod.POST)
    public Rmap<Enterprise> insert(@RequestBody Enterprise record);

    @RequestMapping(value = "/nterprise/update", method = RequestMethod.PUT)
    public Rmap<Enterprise> update(@RequestBody Enterprise record);

    @RequestMapping(value = "/nterprise/delete", method = RequestMethod.DELETE)
    public Rmap<Enterprise> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/nterprise/deletes", method = RequestMethod.DELETE)
    public Rmap<Enterprise> deletes(@RequestParam("ids") String ids);
}