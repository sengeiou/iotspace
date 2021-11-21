package com.iot.service;

import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;

import java.util.List;

public interface EtruckproductionplantService {
	public List<Etruckproductionplant> select(String sql);

	public List<Etruckproductionplant> selectAll();

	public List<Etruckproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Etruckproductionplant> selectBySql(String sql);

	public Etruckproductionplant selectByPrimaryKey(Integer id);

	public List<Etruckproductionplantv> selectV(String sql);

	public List<Etruckproductionplantv> selectVAll();

	public List<Etruckproductionplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Etruckproductionplantv> selectVBySql(String sql);

	public Etruckproductionplantv selectVByPrimaryKey(Integer id);

	public int insert(Etruckproductionplant record);

	public int update(Etruckproductionplant record);

	public int delete(Integer id);

	public int deletes(String ids);

	public List<Etruckproductionplantv>selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql);

}