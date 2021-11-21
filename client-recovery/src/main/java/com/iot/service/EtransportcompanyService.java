package com.iot.service;

import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EtransportcompanyServiceImpl.class)
public interface EtransportcompanyService {
    @RequestMapping(value = "/transportcompany/select", method = RequestMethod.GET)
    public Rmap<Etransportcompany> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectAll", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectAll();

    @RequestMapping(value = "/transportcompany/selectByPage", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectBySql", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/transportcompany/insert", method = RequestMethod.POST)
    public Rmap<Etransportcompany> insert(@RequestBody Etransportcompany record);

    @RequestMapping(value = "/transportcompany/update", method = RequestMethod.PUT)
    public Rmap<Etransportcompany> update(@RequestBody Etransportcompany record);

    @RequestMapping(value = "/transportcompany/delete", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/transportcompany/deletes", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/transportcompany/selectV", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectVAll", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVAll();

    @RequestMapping(value = "/transportcompany/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Etransportcompanyv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/transportcompany/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/transportcompany/countBySql", method = RequestMethod.GET)
    public Rmap<Integer> countBySql(@RequestParam("sql") String sql);
}