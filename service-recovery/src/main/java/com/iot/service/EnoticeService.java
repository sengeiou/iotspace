package com.iot.service;

import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;

import java.util.List;

public interface EnoticeService {
	public List<Enotice> select(String sql);

	public List<Enotice> selectAll();

	public List<Enotice> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Enotice> selectBySql(String sql);

	public Enotice selectByPrimaryKey(Integer id);

	public int insert(Enotice record);

	public int update(Enotice record);

	public int delete(Integer id);

	public int deletes(String ids);

	public List<Enoticev> selectV(String sql);

	public List<Enoticev> selectVAll();

	public List<Enoticev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Enoticev> selectVBySql(String sql);

	public Enoticev selectVByPrimaryKey(Integer id);
}