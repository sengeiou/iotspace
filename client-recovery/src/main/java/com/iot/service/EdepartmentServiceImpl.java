package com.iot.service;

import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EdepartmentServiceImpl implements EdepartmentService {
    @Override
    public Rmap<Edepartment> select(String sql) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> selectAll() {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Edepartment> selectBySql(String sql) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }


    @Override
    public Rmap<Edepartment> selectByPrimaryKey(Integer id) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartmentv> selectV(String sql) {
        Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartmentv> selectVAll() {
        Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public  Rmap<Edepartmentv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Edepartmentv> map = new  Rmap<Edepartmentv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartmentv> selectVBySql(String sql) {
        Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartmentv> selectVByPrimaryKey(Integer id) {
        Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> insert(Edepartment record) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> update(Edepartment record) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> delete(Integer id) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Edepartment> deletes(String ids) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
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