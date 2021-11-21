package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.mapper.EgarbagebagMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("egarbagebagService")
public class EgarbagebagServiceImpl implements EgarbagebagService {
	@Resource
	private EgarbagebagMapper egarbagebagMapper;

	public List<Egarbagebag> select(String sql){
		return egarbagebagMapper.select(sql);
	}

	public List<Egarbagebag> selectAll(){
		return egarbagebagMapper.selectAll();
	}

	public List<Egarbagebag> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egarbagebagMapper.selectAll();
		else
			return egarbagebagMapper.selectBySql(sql);
	}

	public List<Egarbagebag> selectBySql(String sql){
		return egarbagebagMapper.selectBySql(sql);
	}

	public Egarbagebag selectByPrimaryKey(Integer id){
		return egarbagebagMapper.selectByPrimaryKey(id);
	}

	public List<Egarbagebagv> selectV(String sql){
		return egarbagebagMapper.selectV(sql);
	}

	public List<Egarbagebagv> selectVAll(){
		return egarbagebagMapper.selectVAll();
	}

	public List<Egarbagebagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egarbagebagMapper.selectVAll();
		else
			return egarbagebagMapper.selectVBySql(sql);
	}

	public List<Egarbagebagv> selectVBySql(String sql){
		return egarbagebagMapper.selectVBySql(sql);
	}

	public Egarbagebagv selectVByPrimaryKey(Integer id){
		return egarbagebagMapper.selectVByPrimaryKey(id);
	}

	@Override
	public List<Egarbagebagv> selectVByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql) {
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return egarbagebagMapper.selectVByHospital(sql);
	}

	@Override
	public List<Egarbagebag> selectByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql) {
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return egarbagebagMapper.selectByHospital(sql);
	}

	public int insert(Egarbagebag record){
		return egarbagebagMapper.insert(record);
	}

	public int update(Egarbagebag record){
		return egarbagebagMapper.update(record);
	}

	public int delete(Integer id){
		return egarbagebagMapper.delete(id);
	}

	public int deletes(String ids){
		return egarbagebagMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> countGroupByDepartment(Integer hospitalid) {
		return egarbagebagMapper.countGroupByDepartment(hospitalid);
	}
}