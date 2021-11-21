package com.iot.service;

import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EtruckproductionplantServiceImpl implements EtruckproductionplantService {
    @Override
    public Rmap<Etruckproductionplant> select(String sql) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> selectAll() {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Etruckproductionplant> selectBySql(String sql) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Etruckproductionplant> selectByPrimaryKey(Integer id) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplantv> selectV(String sql) {
        Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplantv> selectVAll() {
        Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Etruckproductionplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Etruckproductionplantv> map = new  Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplantv> selectVBySql(String sql) {
        Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplantv> selectVByPrimaryKey(Integer id) {
        Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> insert(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> update(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> delete(Integer id) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplant> deletes(String ids) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Etruckproductionplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
        map.setMessage("服务异常");
        return map;
    }
}