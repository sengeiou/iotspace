package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Eorder;
import com.iot.mapper.EorderMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("eorderService")
public class EorderServiceImpl implements EorderService{

    @Resource
    private EorderMapper eorderMapper;

    public List<Eorder> select(String sql){
        return eorderMapper.select(sql);
    }

    @Override
    public List<Eorder> selectAll() {
        return eorderMapper.selectAll();
    }

    @Override
    public List<Eorder> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        if(StringUtils.isBlank(sql))
            return eorderMapper.selectAll();
        else
            return eorderMapper.selectBySql(sql);
    }

    @Override
    public List<Eorder> selectBySql(String sql) {
        return eorderMapper.selectBySql(sql);
    }

    @Override
    public Eorder selectByPrimaryKey(Integer id) {
        return eorderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Eorder record) {
        return eorderMapper.insert(record);
    }

    @Override
    public int update(Eorder record) {
        return eorderMapper.update(record);
    }

    @Override
    public int delete(Integer id) {
        return eorderMapper.delete(id);
    }

    @Override
    public int deletes(String ids) {
        return eorderMapper.deletes(ids);
    }
}
