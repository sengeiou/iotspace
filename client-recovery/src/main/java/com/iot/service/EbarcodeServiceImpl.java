package com.iot.service;

import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EbarcodeServiceImpl implements EbarcodeService {
    @Override
    public Rmap<Ebarcode> select(String sql) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> selectAll() {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Ebarcode> selectBySql(String sql) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Ebarcode> selectByPrimaryKey(Integer id) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcodev> selectV(String sql) {
        Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcodev> selectVAll() {
        Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Ebarcodev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Ebarcodev> map = new  Rmap<Ebarcodev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcodev> selectVBySql(String sql) {
        Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcodev> selectVByPrimaryKey(Integer id) {
        Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> insert(Ebarcode record) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> update(Ebarcode record) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> delete(Integer id) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ebarcode> deletes(String ids) {
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Integer> countByhospitalid(Integer hospitalid) {
        Rmap<Integer> map = new Rmap<Integer>();
        map.setMessage("服务异常");
        return map;
    }
}