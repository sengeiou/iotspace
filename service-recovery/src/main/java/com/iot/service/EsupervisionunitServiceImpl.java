package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;
import com.iot.mapper.EsupervisionunitMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("esupervisionunitService")
public class EsupervisionunitServiceImpl implements EsupervisionunitService {
	@Resource
	private EsupervisionunitMapper esupervisionunitMapper;

	public List<Esupervisionunit> select(String sql){
		return esupervisionunitMapper.select(sql);
	}

	public List<Esupervisionunit> selectAll(){
		return esupervisionunitMapper.selectAll();
	}

	public List<Esupervisionunit> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return esupervisionunitMapper.selectAll();
		else
			return esupervisionunitMapper.selectBySql(sql);
	}


	public List<Esupervisionunit> selectBySql(String sql){
		return esupervisionunitMapper.selectBySql(sql);
	}

	public Esupervisionunit selectByPrimaryKey(Integer id){
		return esupervisionunitMapper.selectByPrimaryKey(id);
	}

	public List<Esupervisionunitv> selectV(String sql){
		return esupervisionunitMapper.selectV(sql);
	}

	public List<Esupervisionunitv> selectVAll(){
		return esupervisionunitMapper.selectVAll();
	}

	public List<Esupervisionunitv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return esupervisionunitMapper.selectVAll();
		else
			return esupervisionunitMapper.selectVBySql(sql);
	}


	public List<Esupervisionunitv> selectVBySql(String sql){
		return esupervisionunitMapper.selectVBySql(sql);
	}

	public Esupervisionunitv selectVByPrimaryKey(Integer id){
		return esupervisionunitMapper.selectVByPrimaryKey(id);
	}

	public int insert(Esupervisionunit record){
		return esupervisionunitMapper.insert(record);
	}

	public int update(Esupervisionunit record){
		return esupervisionunitMapper.update(record);
	}

	public int delete(Integer id){
		return esupervisionunitMapper.delete(id);
	}

	public int deletes(String ids){
		return esupervisionunitMapper.deletes(ids);
	}
}