package com.iot.service;

import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EgarbagebagServiceImpl implements EgarbagebagService {
    @Override
    public Rmap<Egarbagebag> select(String sql) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> selectAll() {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Egarbagebag> selectBySql(String sql) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Egarbagebag> selectByPrimaryKey(Integer id) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagv> selectV(String sql) {
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagv> selectVAll() {
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagv> selectVByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> selectByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Egarbagebagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebagv> map = new  Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagv> selectVBySql(String sql) {
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagv> selectVByPrimaryKey(Integer id) {
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> insert(Egarbagebag record) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> update(Egarbagebag record) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> delete(Integer id) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebag> deletes(String ids) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String,Object>> countGroupByDepartment(Integer hospitalid) {
        Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
        map.setMessage("服务异常");
        return map;
    }
}