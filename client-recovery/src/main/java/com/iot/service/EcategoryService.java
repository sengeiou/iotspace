package com.iot.service;

import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EcategoryServiceImpl.class)
public interface EcategoryService {
    @RequestMapping(value = "/category/select", method = RequestMethod.GET)
    public Rmap<Ecategory> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectAll", method = RequestMethod.GET)
    public Rmap<Ecategory> selectAll();

    @RequestMapping(value = "/category/selectByPage", method = RequestMethod.GET)
    public  Rmap<Ecategory> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectBySql", method = RequestMethod.GET)
    public Rmap<Ecategory> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecategory> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/category/selectV", method = RequestMethod.GET)
    public Rmap<Ecategoryv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecategoryv> selectVAll();

    @RequestMapping(value = "/category/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ecategoryv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ecategoryv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/category/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecategoryv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    public Rmap<Ecategory> insert(@RequestBody Ecategory record);

    @RequestMapping(value = "/category/update", method = RequestMethod.PUT)
    public Rmap<Ecategory> update(@RequestBody Ecategory record);

    @RequestMapping(value = "/category/delete", method = RequestMethod.DELETE)
    public Rmap<Ecategory> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/category/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecategory> deletes(@RequestParam("ids") String ids);
}