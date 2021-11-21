package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Enterprise;
import com.iot.mapper.EnterpriseMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {  
    @Resource  
    private EnterpriseMapper enterpriseMapper;

	public List<Enterprise> select(String sql){
		return enterpriseMapper.select(sql);
	}

	public List<Enterprise> selectAll(){
		return enterpriseMapper.selectAll();
	}

	public List<Enterprise> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return enterpriseMapper.selectAll();
		else
			return enterpriseMapper.selectBySql(sql);
	}

	public List<Enterprise> selectBySql(String sql){
		return enterpriseMapper.selectBySql(sql);
	}

	public Enterprise selectByPrimaryKey(Integer id){
		return enterpriseMapper.selectByPrimaryKey(id);
	}

	public int insert(Enterprise record){
		return enterpriseMapper.insert(record);
	}

	public int update(Enterprise record){
		return enterpriseMapper.update(record);
	}

	public int delete(Integer id){
		return enterpriseMapper.delete(id);
	}

	public int deletes(String ids){
		return enterpriseMapper.deletes(ids);
	}
}