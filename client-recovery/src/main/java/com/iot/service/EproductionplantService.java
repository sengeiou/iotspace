package com.iot.service;

import com.iot.bean.Eproductionplant;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EproductionplantServiceImpl.class)
public interface EproductionplantService {
    @RequestMapping(value = "/productionplant/select", method = RequestMethod.GET)
    public Rmap<Eproductionplant> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/productionplant/selectAll", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectAll();

    @RequestMapping(value = "/productionplant/selectByPage", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/productionplant/selectBySql", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/productionplant/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/productionplant/insert", method = RequestMethod.POST)
    public Rmap<Eproductionplant> insert(@RequestBody Eproductionplant record);

    @RequestMapping(value = "/productionplant/update", method = RequestMethod.PUT)
    public Rmap<Eproductionplant> update(@RequestBody Eproductionplant record);

    @RequestMapping(value = "/productionplant/delete", method = RequestMethod.DELETE)
    public Rmap<Eproductionplant> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/productionplant/deletes", method = RequestMethod.DELETE)
    public Rmap<Eproductionplant> deletes(@RequestParam("ids") String ids);
}