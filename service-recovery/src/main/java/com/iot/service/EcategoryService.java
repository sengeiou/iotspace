package com.iot.service;

import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;

import java.util.List;

public interface EcategoryService {
	public List<Ecategory> select(String sql);

	public List<Ecategory> selectAll();

	public List<Ecategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Ecategory> selectBySql(String sql);

	public Ecategory selectByPrimaryKey(Integer id);

	public List<Ecategoryv> selectV(String sql);

	public List<Ecategoryv> selectVAll();

	public List<Ecategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Ecategoryv> selectVBySql(String sql);

	public Ecategoryv selectVByPrimaryKey(Integer id);

	public int insert(Ecategory record);

	public int update(Ecategory record);

	public int delete(Integer id);

	public int deletes(String ids);
}