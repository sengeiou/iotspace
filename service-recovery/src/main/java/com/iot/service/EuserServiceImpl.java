package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Euser;
import com.iot.mapper.EuserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("euserService")
public class EuserServiceImpl implements EuserService {  
    @Resource  
    private EuserMapper euserMapper;

	public List<Euser> select(String sql){
		return euserMapper.select(sql);
	}

	public List<Euser> selectAll(){
		return euserMapper.selectAll();
	}

	public List<Euser> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return euserMapper.selectAll();
		else
			return euserMapper.selectBySql(sql);
	}

	public List<Euser> selectBySql(String sql){
		return euserMapper.selectBySql(sql);
	}

	public Euser selectByPrimaryKey(Integer id){
		return euserMapper.selectByPrimaryKey(id);
	}

	public int insert(Euser record){
		return euserMapper.insert(record);
	}

	public int update(Euser record){
		return euserMapper.update(record);
	}

	public int delete(Integer id){
		return euserMapper.delete(id);
	}

	public int deletes(String ids){
		return euserMapper.deletes(ids);
	}
}