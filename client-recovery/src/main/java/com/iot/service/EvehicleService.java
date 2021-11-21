package com.iot.service;

import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EvehicleServiceImpl.class)
public interface EvehicleService {
    @RequestMapping(value = "/vehicle/select", method = RequestMethod.GET)
    public Rmap<Evehicle> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/vehicle/selectAll", method = RequestMethod.GET)
    public Rmap<Evehicle> selectAll();

    @RequestMapping(value = "/vehicle/selectByPage", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/vehicle/selectBySql", method = RequestMethod.GET)
    public Rmap<Evehicle> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/vehicle/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/vehicle/selectV", method = RequestMethod.GET)
    public Rmap<Evehiclev> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/vehicle/selectVAll", method = RequestMethod.GET)
    public Rmap<Evehiclev> selectVAll();

    @RequestMapping(value = "/vehicle/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Evehiclev> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/vehicle/selectVBySql", method = RequestMethod.GET)
    public Rmap<Evehiclev> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/vehicle/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Evehiclev> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/vehicle/insert", method = RequestMethod.POST)
    public Rmap<Evehicle> insert(@RequestBody Evehicle record);

    @RequestMapping(value = "/vehicle/update", method = RequestMethod.PUT)
    public Rmap<Evehicle> update(@RequestBody Evehicle record);

    @RequestMapping(value = "/vehicle/delete", method = RequestMethod.DELETE)
    public Rmap<Evehicle> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/vehicle/deletes", method = RequestMethod.DELETE)
    public Rmap<Evehicle> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/vehicle/vehicleNumBySql", method = RequestMethod.GET)
    public Rmap<Integer> vehicleNumBySql(@RequestParam("sql") String sql);

}