package com.iot.service;

import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtrucktransportcompanyServiceImpl.class)
public interface EtrucktransportcompanyService {
    @RequestMapping(value = "/trucktransportcompany/select", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectAll();

    @RequestMapping(value = "/trucktransportcompany/selectByPage", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectBySql", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktransportcompany/selectV", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectVAll", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> selectVAll();

    @RequestMapping(value = "/trucktransportcompany/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etrucktransportcompanyv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/trucktransportcompany/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktransportcompany/insert", method = RequestMethod.POST)
    public Rmap<Etrucktransportcompany> insert(@RequestBody Etrucktransportcompany record);

    @RequestMapping(value = "/trucktransportcompany/update", method = RequestMethod.PUT)
    public Rmap<Etrucktransportcompany> update(@RequestBody Etrucktransportcompany record);

    @RequestMapping(value = "/trucktransportcompany/delete", method = RequestMethod.DELETE)
    public Rmap<Etrucktransportcompany> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/trucktransportcompany/deletes", method = RequestMethod.DELETE)
    public Rmap<Etrucktransportcompany> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/trucktransportcompany/selectVByStatusAndSqlPage", method = RequestMethod.GET)
    public  Rmap<Etrucktransportcompanyv> selectVByStatusAndSqlPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);
}