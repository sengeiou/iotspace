package com.iot.service;

import com.iot.bean.Eprovince;

import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2019/7/16
 * @Description: 省service
 */
public interface EprovinceService {
    public List<Eprovince> select(String sql);

    public List<Eprovince> selectAll();

    public List<Eprovince> selectBySql(String sql);

    public List<Eprovince> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public Eprovince selectByPrimaryKey(Integer id);

    public int insert(Eprovince record);

    public int update(Eprovince record);

    public int delete(Integer id);

    public int deletes(String ids);

}
