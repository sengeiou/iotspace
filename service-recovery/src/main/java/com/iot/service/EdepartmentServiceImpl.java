package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.mapper.EdepartmentMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("edepartmentService")
public class EdepartmentServiceImpl implements EdepartmentService {
	@Resource
	private EdepartmentMapper edepartmentMapper;

	public List<Edepartment> select(String sql){
		return edepartmentMapper.select(sql);
	}

	public List<Edepartment> selectAll(){
		return edepartmentMapper.selectAll();
	}

	public List<Edepartment> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return edepartmentMapper.selectAll();
		else
			return edepartmentMapper.selectBySql(sql);
	}

	public List<Edepartment> selectBySql(String sql){
		return edepartmentMapper.selectBySql(sql);
	}

	public Edepartment selectByPrimaryKey(Integer id){
		return edepartmentMapper.selectByPrimaryKey(id);
	}

	public List<Edepartmentv> selectV(String sql){
		return edepartmentMapper.selectV(sql);
	}

	public List<Edepartmentv> selectVAll(){
		return edepartmentMapper.selectVAll();
	}

	public List<Edepartmentv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return edepartmentMapper.selectVAll();
		else
			return edepartmentMapper.selectVBySql(sql);
	}


	public List<Edepartmentv> selectVBySql(String sql){
		return edepartmentMapper.selectVBySql(sql);
	}

	public Edepartmentv selectVByPrimaryKey(Integer id){
		return edepartmentMapper.selectVByPrimaryKey(id);
	}

	public int insert(Edepartment record){
		return edepartmentMapper.insert(record);
	}

	public int update(Edepartment record){
		return edepartmentMapper.update(record);
	}

	public int delete(Integer id){
		return edepartmentMapper.delete(id);
	}

	public int deletes(String ids){
		return edepartmentMapper.deletes(ids);
	}

	@Override
	public int countByhospitalid(Integer hospitalid) {
		return edepartmentMapper.countByhospitalid(hospitalid);
	}
}