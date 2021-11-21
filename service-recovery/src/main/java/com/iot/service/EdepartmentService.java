package com.iot.service;

import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;

import java.util.List;

public interface EdepartmentService {
	public List<Edepartment> select(String sql);

	public List<Edepartment> selectAll();


	public List<Edepartment> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Edepartment> selectBySql(String sql);

	public Edepartment selectByPrimaryKey(Integer id);

	public List<Edepartmentv> selectV(String sql);

	public List<Edepartmentv> selectVAll();

	public List<Edepartmentv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Edepartmentv> selectVBySql(String sql);

	public Edepartmentv selectVByPrimaryKey(Integer id);

	public int insert(Edepartment record);

	public int update(Edepartment record);

	public int delete(Integer id);

	public int deletes(String ids);

	public int countByhospitalid(Integer hospitalid);
}