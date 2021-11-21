package com.iot.service;

import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;

import java.util.List;
import java.util.Map;

public interface EgarbagebagService {
	public List<Egarbagebag> select(String sql);

	public List<Egarbagebag> selectAll();

	public List<Egarbagebag> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Egarbagebag> selectBySql(String sql);

	public Egarbagebag selectByPrimaryKey(Integer id);

	public List<Egarbagebagv> selectV(String sql);

	public List<Egarbagebagv> selectVAll();

	public List<Egarbagebagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Egarbagebagv> selectVBySql(String sql);

	public Egarbagebagv selectVByPrimaryKey(Integer id);

	public List<Egarbagebagv> selectVByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Egarbagebag> selectByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql);

	public int insert(Egarbagebag record);

	public int update(Egarbagebag record);

	public int delete(Integer id);

	public int deletes(String ids);

	public List<Map<String,Object>> countGroupByDepartment(Integer hospitalid);
}