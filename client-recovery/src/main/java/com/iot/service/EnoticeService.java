package com.iot.service;

import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EnoticeServiceImpl.class)
public interface EnoticeService {
    @RequestMapping(value = "/notice/select", method = RequestMethod.GET)
    public Rmap<Enotice> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectAll", method = RequestMethod.GET)
    public Rmap<Enotice> selectAll();

    @RequestMapping(value = "/notice/selectByPage", method = RequestMethod.GET)
    public  Rmap<Enotice> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectBySql", method = RequestMethod.GET)
    public Rmap<Enotice> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Enotice> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/notice/insert", method = RequestMethod.POST)
    public Rmap<Enotice> insert(@RequestBody Enotice record);

    @RequestMapping(value = "/notice/update", method = RequestMethod.PUT)
    public Rmap<Enotice> update(@RequestBody Enotice record);

    @RequestMapping(value = "/notice/delete", method = RequestMethod.DELETE)
    public Rmap<Enotice> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/notice/deletes", method = RequestMethod.DELETE)
    public Rmap<Enotice> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/notice/selectV", method = RequestMethod.GET)
    public Rmap<Enoticev> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectVAll", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVAll();

    @RequestMapping(value = "/notice/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Enoticev> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectVBySql", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/notice/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVByPrimaryKey(@RequestParam("id") Integer id);
}