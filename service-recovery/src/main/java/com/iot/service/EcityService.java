package com.iot.service;

import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;

import java.util.List;

public interface EcityService {
	public List<Ecity> select(String sql);

	public List<Ecity> selectAll();

	public List<Ecity> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Ecity> selectBySql(String sql);

	public Ecity selectByPrimaryKey(Integer id);

	public List<Ecityv> selectV(String sql);

	public List<Ecityv> selectVAll();

	public List<Ecityv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Ecityv> selectVBySql(String sql);

	public Ecityv selectVByPrimaryKey(Integer id);

	public int insert(Ecity record);

	public int update(Ecity record);

	public int delete(Integer id);

	public int deletes(String ids);
}