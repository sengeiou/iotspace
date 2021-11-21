package com.iot.service;

import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;

import java.util.List;

public interface EplatService {
	public List<Eplat> select(String sql);

	public List<Eplat> selectAll();

	public List<Eplat> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Eplat> selectBySql(String sql);

	public Eplat selectByPrimaryKey(Integer id);

	public List<Eplatv> selectV(String sql);

	public List<Eplatv> selectVAll();

	public List<Eplatv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Eplatv> selectVBySql(String sql);

	public Eplatv selectVByPrimaryKey(Integer id);

	public int insert(Eplat record);

	public int update(Eplat record);

	public int delete(Integer id);

	public int deletes(String ids);
}