package com.iot.service;

import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtreatmentplantoutboundrecordServiceImpl.class)
public interface EtreatmentplantoutboundrecordService {
    @RequestMapping(value = "/treatmentplantoutboundrecord/select", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecord> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecord> selectAll();

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectByPage", method = RequestMethod.GET)
    public  Rmap<Etreatmentplantoutboundrecord> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectBySql", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecord> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecord> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectV", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecordv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectVAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecordv> selectVAll();

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecordv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecordv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantoutboundrecord/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecordv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantoutboundrecord/insert", method = RequestMethod.POST)
    public Rmap<Etreatmentplantoutboundrecord> insert(@RequestBody Etreatmentplantoutboundrecord record);

    @RequestMapping(value = "/treatmentplantoutboundrecord/update", method = RequestMethod.PUT)
    public Rmap<Etreatmentplantoutboundrecord> update(@RequestBody Etreatmentplantoutboundrecord record);

    @RequestMapping(value = "/treatmentplantoutboundrecord/delete", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantoutboundrecord> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantoutboundrecord/deletes", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantoutboundrecord> deletes(@RequestParam("ids") String ids);
}