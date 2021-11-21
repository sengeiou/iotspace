package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;
import com.iot.bean.Rmap;
import com.iot.service.EtransportcompanyService;
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
@RequestMapping("/transportcompany")
public class EtransportcompanyAction {
    @Resource
    private EtransportcompanyService etransportcompanyService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Rmap<Etransportcompany> select(String sql){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        List<Etransportcompany> list = etransportcompanyService.select(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectAll(){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        List<Etransportcompany> list = etransportcompanyService.selectAll();
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public  Rmap<Etransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        List<Etransportcompany> list = etransportcompanyService.selectByPage(page, rows, sort, order, sql);
        PageInfo<Etransportcompany> pageInfo = new PageInfo<Etransportcompany>(list);
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectBySql(String sql){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        List<Etransportcompany> list = etransportcompanyService.selectBySql(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByPrimaryKey(Integer id){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        Etransportcompany object = etransportcompanyService.selectByPrimaryKey(id);
        if(null != object)
            map.setObject(object);
        return map;
    }

    @RequestMapping(value = "/selectV", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectV(String sql){
        Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
        List<Etransportcompanyv> list = etransportcompanyService.selectV(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv>  selectVAll(){
        Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
        List<Etransportcompanyv> list = etransportcompanyService.selectVAll();
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
        List<Etransportcompanyv> list = etransportcompanyService.selectVByPage(page, rows, sort, order, sql);
        PageInfo<Etransportcompanyv> pageInfo = new PageInfo<Etransportcompanyv>(list);
        Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVBySql(String sql){
        Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
        List<Etransportcompanyv> list = etransportcompanyService.selectVBySql(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etransportcompanyv> selectVByPrimaryKey(Integer id){
        Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
        Etransportcompanyv einfusionbagv = etransportcompanyService.selectVByPrimaryKey(id);
        if(null != einfusionbagv)
            map.setObject(einfusionbagv);
        return map;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etransportcompany> insert(@RequestBody Etransportcompany record){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(etransportcompanyService.insert(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public  Rmap<Etransportcompany> update(@RequestBody Etransportcompany record){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(etransportcompanyService.update(record) > 0)
            map.setObject(record);
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> delete(Integer id){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        Etransportcompany etransportcompany = etransportcompanyService.selectByPrimaryKey(id);
        if(etransportcompanyService.delete(id) > 0)
           map.setObject(etransportcompany);
        return map;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> deletes(String ids){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        List<Etransportcompany> list = etransportcompanyService.selectBySql("id in "+ids);
        if(etransportcompanyService.deletes(ids) > 0){
            map.setList(list);
        }
        return map;
    }

    @RequestMapping(value = "/countBySql", method = RequestMethod.GET)
    public Rmap<Integer> countBySql(String sql){
        Rmap<Integer> map = new Rmap<Integer>();
        Integer object = etransportcompanyService.countBySql(sql);
        if(null != object)
            map.setObject(object);
        return map;
    }
}
