package com.iot.service;

import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EplatServiceImpl.class)
public interface EplatService {
    @RequestMapping(value = "/plat/select", method = RequestMethod.GET)
    public Rmap<Eplat> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectAll", method = RequestMethod.GET)
    public Rmap<Eplat> selectAll();

    @RequestMapping(value = "/plat/selectByPage", method = RequestMethod.GET)
    public Rmap<Eplat> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectBySql", method = RequestMethod.GET)
    public Rmap<Eplat> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eplat> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/plat/selectV", method = RequestMethod.GET)
    public Rmap<Eplatv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectVAll", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVAll();

    @RequestMapping(value = "/plat/selectVByPage", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectVBySql", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/plat/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/plat/insert", method = RequestMethod.POST)
    public Rmap<Eplat> insert(Eplat record);

    @RequestMapping(value = "/plat/update", method = RequestMethod.PUT)
    public Rmap<Eplat> update(@RequestBody Eplat record);

    @RequestMapping(value = "/plat/delete", method = RequestMethod.DELETE)
    public Rmap<Eplat> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/plat/deletes", method = RequestMethod.DELETE)
    public Rmap<Eplat> deletes(@RequestParam("ids") String ids);
}