package com.iot.service;

import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EvehicleServiceImpl implements EvehicleService {
    @Override
    public Rmap<Evehicle> select(String sql) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> selectAll() {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Evehicle> selectBySql(String sql) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Evehicle> selectByPrimaryKey(Integer id) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehiclev> selectV(String sql) {
        Rmap<Evehiclev> map = new Rmap<Evehiclev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehiclev> selectVAll() {
        Rmap<Evehiclev> map = new Rmap<Evehiclev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Evehiclev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Evehiclev> map = new  Rmap<Evehiclev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehiclev> selectVBySql(String sql) {
        Rmap<Evehiclev> map = new Rmap<Evehiclev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehiclev> selectVByPrimaryKey(Integer id) {
        Rmap<Evehiclev> map = new Rmap<Evehiclev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> insert(Evehicle record) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> update(Evehicle record) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> delete(Integer id) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Evehicle> deletes(String ids) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Integer> vehicleNumBySql(String sql) {
        Rmap<Integer> map = new Rmap<Integer>();
        map.setMessage("服务异常");
        return map;
    }
}