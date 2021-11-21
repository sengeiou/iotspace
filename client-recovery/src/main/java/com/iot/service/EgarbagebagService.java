package com.iot.service;

import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EgarbagebagServiceImpl.class)
public interface EgarbagebagService {
    @RequestMapping(value = "/garbagebag/select", method = RequestMethod.GET)
    public Rmap<Egarbagebag> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectAll", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectAll();

    @RequestMapping(value = "/garbagebag/selectByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/garbagebag/selectBySql", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/garbagebag/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebag/selectV", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectVAll", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVAll();

    @RequestMapping(value = "/garbagebag/selectVByHospitalAndPage", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVByHospitalAndPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectByHospitalAndPage", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectByHospitalAndPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Egarbagebagv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectVBySql", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebag/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebag/insert", method = RequestMethod.POST)
    public Rmap<Egarbagebag> insert(@RequestBody Egarbagebag record);

    @RequestMapping(value = "/garbagebag/update", method = RequestMethod.PUT)
    public Rmap<Egarbagebag> update(@RequestBody Egarbagebag record);

    @RequestMapping(value = "/garbagebag/delete", method = RequestMethod.DELETE)
    public Rmap<Egarbagebag> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebag/deletes", method = RequestMethod.DELETE)
    public Rmap<Egarbagebag> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/garbagebag/countGroupByDepartment", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> countGroupByDepartment(@RequestParam("hospitalid") Integer hospitalid);

}