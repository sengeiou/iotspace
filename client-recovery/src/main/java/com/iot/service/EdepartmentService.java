package com.iot.service;

import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EdepartmentServiceImpl.class)
public interface EdepartmentService {
    @RequestMapping(value = "/department/select", method = RequestMethod.GET)
    public Rmap<Edepartment> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/department/selectAll", method = RequestMethod.GET)
    public Rmap<Edepartment> selectAll();

    @RequestMapping(value = "/department/selectByPage", method = RequestMethod.GET)
    public Rmap<Edepartment> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/department/selectBySql", method = RequestMethod.GET)
    public Rmap<Edepartment> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/department/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Edepartment> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/department/selectV", method = RequestMethod.GET)
    public Rmap<Edepartmentv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/department/selectVAll", method = RequestMethod.GET)
    public Rmap<Edepartmentv> selectVAll();

    @RequestMapping(value = "/department/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Edepartmentv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/department/selectVBySql", method = RequestMethod.GET)
    public Rmap<Edepartmentv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/department/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Edepartmentv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/department/insert", method = RequestMethod.POST)
    public Rmap<Edepartment> insert(@RequestBody Edepartment record);

    @RequestMapping(value = "/department/update", method = RequestMethod.PUT)
    public Rmap<Edepartment> update(@RequestBody Edepartment record);

    @RequestMapping(value = "/department/delete", method = RequestMethod.DELETE)
    public Rmap<Edepartment> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/department/deletes", method = RequestMethod.DELETE)
    public Rmap<Edepartment> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/department/countByhospitalid", method = RequestMethod.GET)
    public Rmap<Integer> countByhospitalid(@RequestParam("hospitalid") Integer hospitalid);

}