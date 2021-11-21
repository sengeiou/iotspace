package com.iot.service;
  
import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;

import java.util.List;

public interface EgmaterialcategoryService {
    public List<Egmaterialcategory> select(String sql);

    public List<Egmaterialcategory> selectAll();

    public List<Egmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialcategory> selectBySql(String sql);

    public Egmaterialcategory selectByPrimaryKey(Integer id);

    public List<Egmaterialcategoryv> selectV(String sql);

    public List<Egmaterialcategoryv> selectVAll();

    public List<Egmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Egmaterialcategoryv> selectVBySql(String sql);

    public Egmaterialcategoryv selectVByPrimaryKey(Integer id);

    public int insert(Egmaterialcategory record);

    public int update(Egmaterialcategory record);

    public int delete(Integer id);

    public int deletes(String ids);

    public int updateStock(Integer id, Double stock);
}