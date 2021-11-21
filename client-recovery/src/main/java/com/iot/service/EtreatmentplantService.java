package com.iot.service;

import com.iot.bean.Etreatmentplant;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtreatmentplantServiceImpl.class)
public interface EtreatmentplantService {
    @RequestMapping(value = "/treatmentplant/select", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplant/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectAll();

    @RequestMapping(value = "/treatmentplant/selectByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplant/selectBySql", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplant/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplant/insert", method = RequestMethod.POST)
    public Rmap<Etreatmentplant> insert(@RequestBody Etreatmentplant record);

    @RequestMapping(value = "/treatmentplant/update", method = RequestMethod.PUT)
    public Rmap<Etreatmentplant> update(@RequestBody Etreatmentplant record);

    @RequestMapping(value = "/treatmentplant/delete", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplant> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplant/deletes", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplant> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/treatmentplant/updateProvince", method = RequestMethod.PUT)
    public Rmap<Etreatmentplant> updateProvince(@RequestBody Etreatmentplant record);
}