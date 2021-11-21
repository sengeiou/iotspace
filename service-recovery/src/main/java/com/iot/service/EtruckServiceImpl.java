package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;
import com.iot.mapper.EtruckMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("etruckService")
public class EtruckServiceImpl implements EtruckService {  
    @Resource  
    private EtruckMapper etruckMapper;

	public List<Etruck> select(String sql){
		return etruckMapper.select(sql);
	}

	public List<Etruck> selectAll(){
		return etruckMapper.selectAll();
	}

	public List<Etruck> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckMapper.selectAll();
		else
			return etruckMapper.selectBySql(sql);
	}

	public List<Etruck> selectBySql(String sql){
		return etruckMapper.selectBySql(sql);
	}

	public Etruck selectByPrimaryKey(Integer id){
		return etruckMapper.selectByPrimaryKey(id);
	}

	public List<Etruckv> selectV(String sql){
		return etruckMapper.selectV(sql);
	}

	public List<Etruckv> selectVAll(){
		return etruckMapper.selectVAll();
	}

	public List<Etruckv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckMapper.selectVAll();
		else
			return etruckMapper.selectVBySql(sql);
	}

	public List<Etruckv> selectVBySql(String sql){
		return etruckMapper.selectVBySql(sql);
	}

	public Etruckv selectVByPrimaryKey(Integer id){
		return etruckMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etruck record){
		return etruckMapper.insert(record);
	}

	public int update(Etruck record){
		return etruckMapper.update(record);
	}

	public int delete(Integer id){
		return etruckMapper.delete(id);
	}

	public int deletes(String ids){
		return etruckMapper.deletes(ids);
	}
}