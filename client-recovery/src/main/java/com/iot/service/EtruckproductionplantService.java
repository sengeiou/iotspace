package com.iot.service;

import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtruckproductionplantServiceImpl.class)
public interface EtruckproductionplantService {
    @RequestMapping(value = "/truckproductionplant/select", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckproductionplant/selectAll", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectAll();

    @RequestMapping(value = "/truckproductionplant/selectByPage", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/truckproductionplant/selectBySql", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/truckproductionplant/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckproductionplant/selectV", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckproductionplant/selectVAll", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> selectVAll();

    @RequestMapping(value = "/truckproductionplant/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etruckproductionplantv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/truckproductionplant/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/truckproductionplant/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckproductionplant/insert", method = RequestMethod.POST)
    public Rmap<Etruckproductionplant> insert(@RequestBody Etruckproductionplant record);

    @RequestMapping(value = "/truckproductionplant/update", method = RequestMethod.PUT)
    public Rmap<Etruckproductionplant> update(@RequestBody Etruckproductionplant record);

    @RequestMapping(value = "/truckproductionplant/delete", method = RequestMethod.DELETE)
    public Rmap<Etruckproductionplant> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/truckproductionplant/deletes", method = RequestMethod.DELETE)
    public Rmap<Etruckproductionplant> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/truckproductionplant/selectVByStatusAndSqlPage", method = RequestMethod.GET)
    public  Rmap<Etruckproductionplantv> selectVByStatusAndSqlPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

}