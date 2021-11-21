package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;
import com.iot.mapper.EtransportcompanyMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2019/7/16
 * @Description:
 */
@Service("etransportcompanyService")
public class EtransportcompanyServiceImpl implements EtransportcompanyService{

    @Resource
    private EtransportcompanyMapper  etransportcompanyMapper;

    public List<Etransportcompany> select(String sql){
        return etransportcompanyMapper.select(sql);
    }

    @Override
    public List<Etransportcompany> selectAll() {
        return etransportcompanyMapper.selectAll();
    }

    @Override
    public List<Etransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        if(StringUtils.isBlank(sql))
            return etransportcompanyMapper.selectAll();
        else
            return etransportcompanyMapper.selectBySql(sql);
    }

    @Override
    public List<Etransportcompany> selectBySql(String sql) {
        return etransportcompanyMapper.selectBySql(sql);
    }

    @Override
    public Etransportcompany selectByPrimaryKey(Integer id) {
        return etransportcompanyMapper.selectByPrimaryKey(id);
    }

    public List<Etransportcompanyv> selectV(String sql){
        return etransportcompanyMapper.selectV(sql);
    }

    public List<Etransportcompanyv> selectVAll(){
        return etransportcompanyMapper.selectVAll();
    }

    public List<Etransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        if(StringUtils.isBlank(sql))
            return etransportcompanyMapper.selectVAll();
        else
            return etransportcompanyMapper.selectVBySql(sql);
    }

    public List<Etransportcompanyv> selectVBySql(String sql){
        return etransportcompanyMapper.selectVBySql(sql);
    }

    public Etransportcompanyv selectVByPrimaryKey(Integer id){
        return etransportcompanyMapper.selectVByPrimaryKey(id);
    }

    @Override
    public int insert(Etransportcompany record) {
        return etransportcompanyMapper.insert(record);
    }

    @Override
    public int update(Etransportcompany record) {
        return etransportcompanyMapper.update(record);
    }

    @Override
    public int delete(Integer id) {
        return etransportcompanyMapper.delete(id);
    }

    @Override
    public int deletes(String ids) {
        return etransportcompanyMapper.deletes(ids);
    }

    @Override
    public Integer countBySql(String sql) {
        return etransportcompanyMapper.countBySql(sql);
    }
}
