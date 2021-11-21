package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.bean.Etruckhospitalv;
import com.iot.mapper.EnoticeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("enoticeService")
public class EnoticeServiceImpl implements EnoticeService {
	@Resource
	private EnoticeMapper enoticeMapper;

	public List<Enotice> select(String sql){
		return enoticeMapper.select(sql);
	}

	public List<Enotice> selectAll(){
		return enoticeMapper.selectAll();
	}

	public List<Enotice> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return enoticeMapper.selectAll();
		else
			return enoticeMapper.selectBySql(sql);
	}


	public List<Enotice> selectBySql(String sql){
		return enoticeMapper.selectBySql(sql);
	}

	public Enotice selectByPrimaryKey(Integer id){
		return enoticeMapper.selectByPrimaryKey(id);
	}

	public int insert(Enotice record){
		return enoticeMapper.insert(record);
	}

	public int update(Enotice record){
		return enoticeMapper.update(record);
	}

	public int delete(Integer id){
		return enoticeMapper.delete(id);
	}

	public int deletes(String ids){
		return enoticeMapper.deletes(ids);
	}

	public List<Enoticev> selectV(String sql){
		return enoticeMapper.selectV(sql);
	}

	public List<Enoticev> selectVAll(){
		return enoticeMapper.selectVAll();
	}

	public List<Enoticev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return enoticeMapper.selectVAll();
		else
			return enoticeMapper.selectVBySql(sql);
	}


	public List<Enoticev> selectVBySql(String sql){
		return enoticeMapper.selectVBySql(sql);
	}

	public Enoticev selectVByPrimaryKey(Integer id){
		return enoticeMapper.selectVByPrimaryKey(id);
	}
}