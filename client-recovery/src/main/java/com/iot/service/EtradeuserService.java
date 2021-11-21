package com.iot.service;

import com.iot.bean.Etradeuser;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtradeuserServiceImpl.class)
public interface EtradeuserService {
    @RequestMapping(value = "/tradeuser/select", method = RequestMethod.GET)
    public Rmap<Etradeuser> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/tradeuser/selectAll", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectAll();

    @RequestMapping(value = "/tradeuser/selectByPage", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/tradeuser/selectBySql", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/tradeuser/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/tradeuser/insert", method = RequestMethod.POST)
    public Rmap<Etradeuser> insert(@RequestBody Etradeuser record);

    @RequestMapping(value = "/tradeuser/update", method = RequestMethod.PUT)
    public Rmap<Etradeuser> update(@RequestBody Etradeuser record);

    @RequestMapping(value = "/tradeuser/delete", method = RequestMethod.DELETE)
    public Rmap<Etradeuser> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/tradeuser/deletes", method = RequestMethod.DELETE)
    public Rmap<Etradeuser> deletes(@RequestParam("ids") String ids);
}