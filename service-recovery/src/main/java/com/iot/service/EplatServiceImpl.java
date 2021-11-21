package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.mapper.EplatMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("eplatService")
public class EplatServiceImpl implements EplatService {
	@Resource
	private EplatMapper eplatMapper;

	public List<Eplat> select(String sql){
		return eplatMapper.select(sql);
	}

	public List<Eplat> selectAll(){
		return eplatMapper.selectAll();
	}

	public List<Eplat> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return eplatMapper.selectAll();
		else
			return eplatMapper.selectBySql(sql);
	}

	public List<Eplat> selectBySql(String sql){
		return eplatMapper.selectBySql(sql);
	}

	public Eplat selectByPrimaryKey(Integer id){
		return eplatMapper.selectByPrimaryKey(id);
	}

	public List<Eplatv> selectV(String sql){
		return eplatMapper.selectV(sql);
	}

	public List<Eplatv> selectVAll(){
		return eplatMapper.selectVAll();
	}

	public List<Eplatv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return eplatMapper.selectVAll();
		else
			return eplatMapper.selectVBySql(sql);
	}

	public List<Eplatv> selectVBySql(String sql){
		return eplatMapper.selectVBySql(sql);
	}

	public Eplatv selectVByPrimaryKey(Integer id){
		return eplatMapper.selectVByPrimaryKey(id);
	}

	public int insert(Eplat record){
		return eplatMapper.insert(record);
	}

	public int update(Eplat record){
		return eplatMapper.update(record);
	}

	public int delete(Integer id){
		return eplatMapper.delete(id);
	}

	public int deletes(String ids){
		return eplatMapper.deletes(ids);
	}
}