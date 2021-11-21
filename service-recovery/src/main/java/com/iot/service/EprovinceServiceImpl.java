package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Eprovince;
import com.iot.mapper.EprovinceMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2019/7/16
 * @Description:
 */
@Service("eprovinceService")
public class EprovinceServiceImpl implements EprovinceService{

    @Resource
    private EprovinceMapper  eprovinceMapper;

    public List<Eprovince> select(String sql){
        return eprovinceMapper.select(sql);
    }

    @Override
    public List<Eprovince> selectAll() {
        return eprovinceMapper.selectAll();
    }

    @Override
    public List<Eprovince> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        if(StringUtils.isBlank(sql))
            return eprovinceMapper.selectAll();
        else
            return eprovinceMapper.selectBySql(sql);
    }

    @Override
    public List<Eprovince> selectBySql(String sql) {
        return eprovinceMapper.selectBySql(sql);
    }

    @Override
    public Eprovince selectByPrimaryKey(Integer id) {
        return eprovinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Eprovince record) {
        return eprovinceMapper.insert(record);
    }

    @Override
    public int update(Eprovince record) {
        return eprovinceMapper.update(record);
    }

    @Override
    public int delete(Integer id) {
        return eprovinceMapper.delete(id);
    }

    @Override
    public int deletes(String ids) {
        return eprovinceMapper.deletes(ids);
    }
}
