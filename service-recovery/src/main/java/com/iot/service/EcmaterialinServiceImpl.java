package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;
import com.iot.mapper.EcmaterialinMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("ecmaterialinService")
public class EcmaterialinServiceImpl implements EcmaterialinService {  
    @Resource  
    private EcmaterialinMapper ecmaterialinMapper;

	public List<Ecmaterialin> select(String sql){
		return ecmaterialinMapper.select(sql);
	}

	public List<Ecmaterialin> selectAll(){
		return ecmaterialinMapper.selectAll();
	}

	public List<Ecmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialinMapper.selectAll();
		else
			return ecmaterialinMapper.selectBySql(sql);
	}

	public List<Ecmaterialin> selectBySql(String sql){
		return ecmaterialinMapper.selectBySql(sql);
	}

	public Ecmaterialin selectByPrimaryKey(Integer id){
		return ecmaterialinMapper.selectByPrimaryKey(id);
	}

	public List<Ecmaterialinv> selectV(String sql){
		return ecmaterialinMapper.selectV(sql);
	}

	public List<Ecmaterialinv> selectVAll(){
		return ecmaterialinMapper.selectVAll();
	}

	public List<Ecmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialinMapper.selectVAll();
		else
			return ecmaterialinMapper.selectVBySql(sql);
	}

	public List<Ecmaterialinv> selectVBySql(String sql){
		return ecmaterialinMapper.selectVBySql(sql);
	}

	public Ecmaterialinv selectVByPrimaryKey(Integer id){
		return ecmaterialinMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ecmaterialin record){
		return ecmaterialinMapper.insert(record);
	}

	public int update(Ecmaterialin record){
		return ecmaterialinMapper.update(record);
	}

	public int delete(Integer id){
		return ecmaterialinMapper.delete(id);
	}

	public int deletes(String ids){
		return ecmaterialinMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		return ecmaterialinMapper.sumWeightGroupByCategory(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupBySource(String sql) {
		return ecmaterialinMapper.sumWeightGroupBySource(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year) {
		return ecmaterialinMapper.sumWeightGroupByMonth(treatmentplantid,cmaterialcategoryid,year);
	}
}