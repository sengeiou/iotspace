package com.iot.service;

import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EinfusionbagServiceImpl implements EinfusionbagService {
    @Override
    public Rmap<Einfusionbag> select(String sql) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> selectAll() {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Einfusionbag> selectBySql(String sql) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Einfusionbag> selectByPrimaryKey(Integer id) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbagv> selectV(String sql) {
        Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbagv> selectVAll() {
        Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Einfusionbagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Einfusionbagv> map = new  Rmap<Einfusionbagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbagv> selectVBySql(String sql) {
        Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbagv> selectVByPrimaryKey(Integer id) {
        Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> insert(Einfusionbag record) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> update(Einfusionbag record) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> delete(Integer id) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Einfusionbag> deletes(String ids) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        map.setMessage("服务异常");
        return map;
    }
}