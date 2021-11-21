package com.iot.service;

import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;

import java.util.List;

public interface EgarbagebagrecordService {
	public List<Egarbagebagrecord> select(String sql);

	public List<Egarbagebagrecord> selectAll();

	public List<Egarbagebagrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Egarbagebagrecord> selectBySql(String sql);

	public Egarbagebagrecord selectByPrimaryKey(Integer id);

	public List<Egarbagebagrecordv> selectV(String sql);

	public List<Egarbagebagrecordv> selectVAll();

	public List<Egarbagebagrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Egarbagebagrecordv> selectVBySql(String sql);

	public Egarbagebagrecordv selectVByPrimaryKey(Integer id);

	public int insert(Egarbagebagrecord record);

	public int update(Egarbagebagrecord record);

	public int delete(Integer id);

	public int deletes(String ids);
}