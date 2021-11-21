package com.iot.service;
  
import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;

import java.util.List;

public interface EcmaterialcategoryService {
    public List<Ecmaterialcategory> select(String sql);

    public List<Ecmaterialcategory> selectAll();

    public List<Ecmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialcategory> selectBySql(String sql);

    public Ecmaterialcategory selectByPrimaryKey(Integer id);

    public List<Ecmaterialcategoryv> selectV(String sql);

    public List<Ecmaterialcategoryv> selectVAll();

    public List<Ecmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ecmaterialcategoryv> selectVBySql(String sql);

    public Ecmaterialcategoryv selectVByPrimaryKey(Integer id);

    public int insert(Ecmaterialcategory record);

    public int update(Ecmaterialcategory record);

    public int delete(Integer id);

    public int deletes(String ids);

    public int updateStock(Integer id,Double stock);
}