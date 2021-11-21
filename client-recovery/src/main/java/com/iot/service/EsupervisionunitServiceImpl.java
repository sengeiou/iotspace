package com.iot.service;

import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EsupervisionunitServiceImpl implements EsupervisionunitService {
    @Override
    public Rmap<Esupervisionunit> select(String sql) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> selectAll() {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Esupervisionunit> selectBySql(String sql) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Esupervisionunit> selectByPrimaryKey(Integer id) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunitv> selectV(String sql) {
        Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunitv> selectVAll() {
        Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Esupervisionunitv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Esupervisionunitv> map = new  Rmap<Esupervisionunitv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunitv> selectVBySql(String sql) {
        Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunitv> selectVByPrimaryKey(Integer id) {
        Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> insert(Esupervisionunit record) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> update(Esupervisionunit record) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> delete(Integer id) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Esupervisionunit> deletes(String ids) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        map.setMessage("服务异常");
        return map;
    }
}