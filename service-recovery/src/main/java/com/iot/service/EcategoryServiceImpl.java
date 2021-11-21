package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.mapper.EcategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ecategoryService")
public class EcategoryServiceImpl implements EcategoryService {
	@Resource
	private EcategoryMapper ecategoryMapper;

	public List<Ecategory> select(String sql){
		return ecategoryMapper.select(sql);
	}

	public List<Ecategory> selectAll(){
		return ecategoryMapper.selectAll();
	}

	public List<Ecategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecategoryMapper.selectAll();
		else
			return ecategoryMapper.selectBySql(sql);
	}

	public List<Ecategory> selectBySql(String sql){
		return ecategoryMapper.selectBySql(sql);
	}

	public Ecategory selectByPrimaryKey(Integer id){
		return ecategoryMapper.selectByPrimaryKey(id);
	}

	public List<Ecategoryv> selectV(String sql){
		return ecategoryMapper.selectV(sql);
	}

	public List<Ecategoryv> selectVAll(){
		return ecategoryMapper.selectVAll();
	}

	public List<Ecategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecategoryMapper.selectVAll();
		else
			return ecategoryMapper.selectVBySql(sql);
	}

	public List<Ecategoryv> selectVBySql(String sql){
		return ecategoryMapper.selectVBySql(sql);
	}

	public Ecategoryv selectVByPrimaryKey(Integer id){
		return ecategoryMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ecategory record){
		return ecategoryMapper.insert(record);
	}

	public int update(Ecategory record){
		return ecategoryMapper.update(record);
	}

	public int delete(Integer id){
		return ecategoryMapper.delete(id);
	}

	public int deletes(String ids){
		return ecategoryMapper.deletes(ids);
	}
}