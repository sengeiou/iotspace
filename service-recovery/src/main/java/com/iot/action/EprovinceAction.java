package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Eprovince;
import com.iot.bean.Rmap;
import com.iot.service.EprovinceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Barry
 * @Date: 2019/7/16
 * @Description:
 */
@RestController
@RequestMapping("/province")
public class EprovinceAction {
    @Resource
    private EprovinceService eprovinceService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Rmap<Eprovince> select(String sql){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        List<Eprovince> list = eprovinceService.select(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eprovince> selectAll(){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        List<Eprovince> list = eprovinceService.selectAll();
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public  Rmap<Eprovince> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        List<Eprovince> list = eprovinceService.selectByPage(page, rows, sort, order, sql);
        PageInfo<Eprovince> pageInfo = new PageInfo<Eprovince>(list);
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
    public Rmap<Eprovince> selectBySql(String sql){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        List<Eprovince> list = eprovinceService.selectBySql(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eprovince> selectByPrimaryKey(Integer id){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        Eprovince object = eprovinceService.selectByPrimaryKey(id);
        if(null != object)
            map.setObject(object);
        return map;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eprovince> insert(@RequestBody Eprovince record){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        if(eprovinceService.insert(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public  Rmap<Eprovince> update(@RequestBody Eprovince record){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        if(eprovinceService.update(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Eprovince> delete(Integer id){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        Eprovince eprovince = eprovinceService.selectByPrimaryKey(id);
        if(eprovinceService.delete(id) > 0)
           map.setObject(eprovince);
        return map;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Eprovince> deletes(String ids){
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        List<Eprovince> list = eprovinceService.selectBySql("id in "+ids);
        if(eprovinceService.deletes(ids) > 0){
            map.setList(list);
        }
        return map;
    }
}
