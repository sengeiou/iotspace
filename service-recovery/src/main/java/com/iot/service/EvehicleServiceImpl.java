package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.mapper.EvehicleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("evehicleService")
public class EvehicleServiceImpl implements EvehicleService {
	@Resource
	private EvehicleMapper evehicleMapper;

	public List<Evehicle> select(String sql){
		return evehicleMapper.select(sql);
	}

	public List<Evehicle> selectAll(){
		return evehicleMapper.selectAll();
	}

	public List<Evehicle> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return evehicleMapper.selectAll();
		else
			return evehicleMapper.selectBySql(sql);
	}

	public List<Evehicle> selectBySql(String sql){
		return evehicleMapper.selectBySql(sql);
	}

	public Evehicle selectByPrimaryKey(Integer id){
		return evehicleMapper.selectByPrimaryKey(id);
	}

	public List<Evehiclev> selectV(String sql){
		return evehicleMapper.selectV(sql);
	}

	public List<Evehiclev> selectVAll(){
		return evehicleMapper.selectVAll();
	}

	public List<Evehiclev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return evehicleMapper.selectVAll();
		else
			return evehicleMapper.selectVBySql(sql);
	}


	public List<Evehiclev> selectVBySql(String sql){
		return evehicleMapper.selectVBySql(sql);
	}

	public Evehiclev selectVByPrimaryKey(Integer id){
		return evehicleMapper.selectVByPrimaryKey(id);
	}

	public int insert(Evehicle record){
		return evehicleMapper.insert(record);
	}

	public int update(Evehicle record){
		return evehicleMapper.update(record);
	}

	public int delete(Integer id){
		return evehicleMapper.delete(id);
	}

	public int deletes(String ids){
		return evehicleMapper.deletes(ids);
	}

	@Override
	public int vehicleNumBySql(String sql) {
		return evehicleMapper.vehicleNumBySql(sql);
	}

}