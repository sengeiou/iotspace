package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Eorder;
import com.iot.bean.Rmap;
import com.iot.service.EorderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class EorderAction {
    @Resource
    private EorderService eorderService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Rmap<Eorder> select(String sql){
        Rmap<Eorder> map = new Rmap<Eorder>();
        List<Eorder> list = eorderService.select(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eorder> selectAll(){
        Rmap<Eorder> map = new Rmap<Eorder>();
        List<Eorder> list = eorderService.selectAll();
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public  Rmap<Eorder> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        List<Eorder> list = eorderService.selectByPage(page, rows, sort, order, sql);
        PageInfo<Eorder> pageInfo = new PageInfo<Eorder>(list);
        Rmap<Eorder> map = new Rmap<Eorder>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
    public Rmap<Eorder> selectBySql(String sql){
        Rmap<Eorder> map = new Rmap<Eorder>();
        List<Eorder> list = eorderService.selectBySql(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eorder> selectByPrimaryKey(Integer id){
        Rmap<Eorder> map = new Rmap<Eorder>();
        Eorder object = eorderService.selectByPrimaryKey(id);
        if(null != object)
            map.setObject(object);
        return map;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eorder> insert(@RequestBody Eorder record){
        Rmap<Eorder> map = new Rmap<Eorder>();
        if(eorderService.insert(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public  Rmap<Eorder> update(@RequestBody Eorder record){
        Rmap<Eorder> map = new Rmap<Eorder>();
        if(eorderService.update(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Eorder> delete(Integer id){
        Rmap<Eorder> map = new Rmap<Eorder>();
        Eorder Eorder = eorderService.selectByPrimaryKey(id);
        if(eorderService.delete(id) > 0)
           map.setObject(Eorder);
        return map;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Eorder> deletes(String ids){
        Rmap<Eorder> map = new Rmap<Eorder>();
        List<Eorder> list = eorderService.selectBySql("id in "+ids);
        if(eorderService.deletes(ids) > 0){
            map.setList(list);
        }
        return map;
    }
}
