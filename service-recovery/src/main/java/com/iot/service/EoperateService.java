package com.iot.service;

import com.iot.bean.Eoperate;

import java.util.List;

public interface EoperateService {
	public List<Eoperate> select(String sql);

	public List<Eoperate> selectAll();

	public List<Eoperate> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Eoperate> selectBySql(String sql);

	public Eoperate selectByPrimaryKey(Integer id);

	public int insert(Eoperate record);

	public int update(Eoperate record);

	public int delete(Integer id);

	public int deletes(String ids);
}