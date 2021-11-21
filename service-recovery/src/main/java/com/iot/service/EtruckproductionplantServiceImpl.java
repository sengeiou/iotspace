package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.mapper.EtruckproductionplantMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("etruckproductionplantService")
public class EtruckproductionplantServiceImpl implements EtruckproductionplantService {
	@Resource
	private EtruckproductionplantMapper etruckproductionplantMapper;

	public List<Etruckproductionplant> select(String sql){
		return etruckproductionplantMapper.select(sql);
	}

	public List<Etruckproductionplant> selectAll(){
		return etruckproductionplantMapper.selectAll();
	}

	public List<Etruckproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckproductionplantMapper.selectAll();
		else
			return etruckproductionplantMapper.selectBySql(sql);
	}


	public List<Etruckproductionplant> selectBySql(String sql){
		return etruckproductionplantMapper.selectBySql(sql);
	}

	public Etruckproductionplant selectByPrimaryKey(Integer id){
		return etruckproductionplantMapper.selectByPrimaryKey(id);
	}

	public List<Etruckproductionplantv> selectV(String sql){
		return etruckproductionplantMapper.selectV(sql);
	}

	public List<Etruckproductionplantv> selectVAll(){
		return etruckproductionplantMapper.selectVAll();
	}

	public List<Etruckproductionplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckproductionplantMapper.selectVAll();
		else
			return etruckproductionplantMapper.selectVBySql(sql);
	}


	public List<Etruckproductionplantv> selectVBySql(String sql){
		return etruckproductionplantMapper.selectVBySql(sql);
	}

	public Etruckproductionplantv selectVByPrimaryKey(Integer id){
		return etruckproductionplantMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etruckproductionplant record){
		return etruckproductionplantMapper.insert(record);
	}

	public int update(Etruckproductionplant record){
		return etruckproductionplantMapper.update(record);
	}

	public int delete(Integer id){
		return etruckproductionplantMapper.delete(id);
	}

	public int deletes(String ids){
		return etruckproductionplantMapper.deletes(ids);
	}

	public List<Etruckproductionplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etruckproductionplantMapper.selectVByStatusAndSql(sql);
	}

}