package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.mapper.EcityMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ecityService")
public class EcityServiceImpl implements EcityService {
	@Resource
	private EcityMapper ecityMapper;

	public List<Ecity> select(String sql){
		return ecityMapper.select(sql);
	}

	public List<Ecity> selectAll(){
		return ecityMapper.selectAll();
	}

	public List<Ecity> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecityMapper.selectAll();
		else
			return ecityMapper.selectBySql(sql);
	}

	public List<Ecity> selectBySql(String sql){
		return ecityMapper.selectBySql(sql);
	}

	public Ecity selectByPrimaryKey(Integer id){
		return ecityMapper.selectByPrimaryKey(id);
	}

	public List<Ecityv> selectV(String sql){
		return ecityMapper.selectV(sql);
	}

	public List<Ecityv> selectVAll(){
		return ecityMapper.selectVAll();
	}

	public List<Ecityv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecityMapper.selectVAll();
		else
			return ecityMapper.selectVBySql(sql);
	}

	public List<Ecityv> selectVBySql(String sql){
		return ecityMapper.selectVBySql(sql);
	}

	public Ecityv selectVByPrimaryKey(Integer id){
		return ecityMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ecity record){
		return ecityMapper.insert(record);
	}

	public int update(Ecity record){
		return ecityMapper.update(record);
	}

	public int delete(Integer id){
		return ecityMapper.delete(id);
	}

	public int deletes(String ids){
		return ecityMapper.deletes(ids);
	}
}