package com.iot.service;

import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtrucktreatmentplantreceiveServiceImpl.class)
public interface EtrucktreatmentplantreceiveService {
    @RequestMapping(value = "/trucktreatmentplantreceive/select", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> selectAll();

    @RequestMapping(value = "/trucktreatmentplantreceive/selectByPage", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectBySql", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectV", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectVAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> selectVAll();

    @RequestMapping(value = "/trucktreatmentplantreceive/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etrucktreatmentplantreceivev> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplantreceive/insert", method = RequestMethod.POST)
    public Rmap<Etrucktreatmentplantreceive> insert(@RequestBody Etrucktreatmentplantreceive record);

    @RequestMapping(value = "/trucktreatmentplantreceive/update", method = RequestMethod.PUT)
    public Rmap<Etrucktreatmentplantreceive> update(@RequestBody Etrucktreatmentplantreceive record);

    @RequestMapping(value = "/trucktreatmentplantreceive/delete", method = RequestMethod.DELETE)
    public Rmap<Etrucktreatmentplantreceive> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktreatmentplantreceive/deletes", method = RequestMethod.DELETE)
    public Rmap<Etrucktreatmentplantreceive> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/trucktreatmentplantreceive/selectOutBoundVoByPage", method = RequestMethod.GET)
    public  Rmap<EtreatmentplantOutBoundVo> selectOutBoundVoByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

}