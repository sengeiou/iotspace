package com.iot.service;

import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EtreatmentplantproductServiceImpl.class)
public interface EtreatmentplantproductService {
    @RequestMapping(value = "/treatmentplantproduct/select", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectAll();

    @RequestMapping(value = "/treatmentplantproduct/selectByPage", method = RequestMethod.GET)
    public  Rmap<Etreatmentplantproduct> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectBySql", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantproduct/selectV", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectVAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVAll();

    @RequestMapping(value = "/treatmentplantproduct/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/treatmentplantproduct/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantproduct/insert", method = RequestMethod.POST)
    public Rmap<Etreatmentplantproduct> insert(@RequestBody Etreatmentplantproduct record);

    @RequestMapping(value = "/treatmentplantproduct/update", method = RequestMethod.PUT)
    public Rmap<Etreatmentplantproduct> update(@RequestBody Etreatmentplantproduct record);

    @RequestMapping(value = "/treatmentplantproduct/delete", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantproduct> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/treatmentplantproduct/deletes", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantproduct> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/treatmentplantproduct/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumWeightGroupByCategory(@RequestParam("sql") String sql);
}