package com.iot.service;

import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;

import java.util.List;
import java.util.Map;

public interface EtreatmentplantproductService {
	public List<Etreatmentplantproduct> select(String sql);

	public List<Etreatmentplantproduct> selectAll();

	public List<Etreatmentplantproduct> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Etreatmentplantproduct> selectBySql(String sql);

	public Etreatmentplantproduct selectByPrimaryKey(Integer id);

	public List<Etreatmentplantproductv> selectV(String sql);

	public List<Etreatmentplantproductv> selectVAll();

	public List<Etreatmentplantproductv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Etreatmentplantproductv> selectVBySql(String sql);

	public Etreatmentplantproductv selectVByPrimaryKey(Integer id);

	public int insert(Etreatmentplantproduct record);

	public int update(Etreatmentplantproduct record);

	public int delete(Integer id);

	public int deletes(String ids);

	public List<Map<String,Object>> sumWeightGroupByCategory(String sql);
}