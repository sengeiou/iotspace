package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.mapper.EgarbagebagrecordMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("egarbagebagrecordService")
public class EgarbagebagrecordServiceImpl implements EgarbagebagrecordService {
	@Resource
	private EgarbagebagrecordMapper egarbagebagrecordMapper;

	public List<Egarbagebagrecord> select(String sql){
		return egarbagebagrecordMapper.select(sql);
	}

	public List<Egarbagebagrecord> selectAll(){
		return egarbagebagrecordMapper.selectAll();
	}

	public List<Egarbagebagrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egarbagebagrecordMapper.selectAll();
		else
			return egarbagebagrecordMapper.selectBySql(sql);
	}


	public List<Egarbagebagrecord> selectBySql(String sql){
		return egarbagebagrecordMapper.selectBySql(sql);
	}

	public Egarbagebagrecord selectByPrimaryKey(Integer id){
		return egarbagebagrecordMapper.selectByPrimaryKey(id);
	}

	public List<Egarbagebagrecordv> selectV(String sql){
		return egarbagebagrecordMapper.selectV(sql);
	}

	public List<Egarbagebagrecordv> selectVAll(){
		return egarbagebagrecordMapper.selectVAll();
	}

	public List<Egarbagebagrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egarbagebagrecordMapper.selectVAll();
		else
			return egarbagebagrecordMapper.selectVBySql(sql);
	}

	public List<Egarbagebagrecordv> selectVBySql(String sql){
		return egarbagebagrecordMapper.selectVBySql(sql);
	}

	public Egarbagebagrecordv selectVByPrimaryKey(Integer id){
		return egarbagebagrecordMapper.selectVByPrimaryKey(id);
	}

	public int insert(Egarbagebagrecord record){
		return egarbagebagrecordMapper.insert(record);
	}

	public int update(Egarbagebagrecord record){
		return egarbagebagrecordMapper.update(record);
	}

	public int delete(Integer id){
		return egarbagebagrecordMapper.delete(id);
	}

	public int deletes(String ids){
		return egarbagebagrecordMapper.deletes(ids);
	}
}