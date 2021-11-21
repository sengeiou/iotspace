package com.iot.service;

import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EinfusionbagServiceImpl.class)
public interface EinfusionbagService {
    @RequestMapping(value = "/infusionbag/select", method = RequestMethod.GET)
    public Rmap<Einfusionbag> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/infusionbag/selectAll", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectAll();

    @RequestMapping(value = "/infusionbag/selectByPage", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/infusionbag/selectBySql", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/infusionbag/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/infusionbag/selectV", method = RequestMethod.GET)
    public Rmap<Einfusionbagv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/infusionbag/selectVAll", method = RequestMethod.GET)
    public Rmap<Einfusionbagv> selectVAll();

    @RequestMapping(value = "/infusionbag/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Einfusionbagv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/infusionbag/selectVBySql", method = RequestMethod.GET)
    public Rmap<Einfusionbagv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/infusionbag/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Einfusionbagv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/infusionbag/insert", method = RequestMethod.POST)
    public Rmap<Einfusionbag> insert(@RequestBody Einfusionbag record);

    @RequestMapping(value = "/infusionbag/update", method = RequestMethod.PUT)
    public Rmap<Einfusionbag> update(@RequestBody Einfusionbag record);

    @RequestMapping(value = "/infusionbag/delete", method = RequestMethod.DELETE)
    public Rmap<Einfusionbag> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/infusionbag/deletes", method = RequestMethod.DELETE)
    public Rmap<Einfusionbag> deletes(@RequestParam("ids") String ids);

}