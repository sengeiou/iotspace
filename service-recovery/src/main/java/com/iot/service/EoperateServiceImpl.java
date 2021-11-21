package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Eoperate;
import com.iot.mapper.EoperateMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("eoperateService")
public class EoperateServiceImpl implements EoperateService {
	@Resource
	private EoperateMapper eoperateMapper;

	public List<Eoperate> select(String sql){
		return eoperateMapper.select(sql);
	}

	public List<Eoperate> selectAll(){
		return eoperateMapper.selectAll();
	}

	public List<Eoperate> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return eoperateMapper.selectAll();
		else
			return eoperateMapper.selectBySql(sql);
	}


	public List<Eoperate> selectBySql(String sql){
		return eoperateMapper.selectBySql(sql);
	}

	public Eoperate selectByPrimaryKey(Integer id){
		return eoperateMapper.selectByPrimaryKey(id);
	}

	public int insert(Eoperate record){
		return eoperateMapper.insert(record);
	}

	public int update(Eoperate record){
		return eoperateMapper.update(record);
	}

	public int delete(Integer id){
		return eoperateMapper.delete(id);
	}

	public int deletes(String ids){
		return eoperateMapper.deletes(ids);
	}
}