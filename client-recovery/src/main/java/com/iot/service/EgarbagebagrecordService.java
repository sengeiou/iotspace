package com.iot.service;

import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EgarbagebagrecordServiceImpl.class)
public interface EgarbagebagrecordService {
    @RequestMapping(value = "/garbagebagrecord/select", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebagrecord/selectAll", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectAll();

    @RequestMapping(value = "/garbagebagrecord/selectByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/garbagebagrecord/selectBySql", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/garbagebagrecord/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebagrecord/selectV", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebagrecord/selectVAll", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVAll();

    @RequestMapping(value = "/garbagebagrecord/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Egarbagebagrecordv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebagrecord/selectVBySql", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/garbagebagrecord/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebagrecord/insert", method = RequestMethod.POST)
    public Rmap<Egarbagebagrecord> insert(@RequestBody Egarbagebagrecord record);

    @RequestMapping(value = "/garbagebagrecord/update", method = RequestMethod.PUT)
    public Rmap<Egarbagebagrecord> update(@RequestBody Egarbagebagrecord record);

    @RequestMapping(value = "/garbagebagrecord/delete", method = RequestMethod.DELETE)
    public Rmap<Egarbagebagrecord> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/garbagebagrecord/deletes", method = RequestMethod.DELETE)
    public Rmap<Egarbagebagrecord> deletes(@RequestParam("ids") String ids);

}