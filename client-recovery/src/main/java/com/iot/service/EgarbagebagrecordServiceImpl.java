package com.iot.service;

import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EgarbagebagrecordServiceImpl implements EgarbagebagrecordService {
    @Override
    public Rmap<Egarbagebagrecord> select(String sql) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> selectAll() {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Egarbagebagrecord> selectBySql(String sql) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Egarbagebagrecord> selectByPrimaryKey(Integer id) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecordv> selectV(String sql) {
        Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecordv> selectVAll() {
        Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Egarbagebagrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Egarbagebagrecordv> map = new  Rmap<Egarbagebagrecordv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecordv> selectVBySql(String sql) {
        Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecordv> selectVByPrimaryKey(Integer id) {
        Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> insert(Egarbagebagrecord record) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> update(Egarbagebagrecord record) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> delete(Integer id) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Egarbagebagrecord> deletes(String ids) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        map.setMessage("服务异常");
        return map;
    }
}