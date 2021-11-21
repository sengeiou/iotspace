package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ecmaterialout;
import com.iot.bean.Ecmaterialoutv;
import com.iot.mapper.EcmaterialoutMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("ecmaterialoutService")
public class EcmaterialoutServiceImpl implements EcmaterialoutService {  
    @Resource  
    private EcmaterialoutMapper ecmaterialoutMapper;

	public List<Ecmaterialout> select(String sql){
		return ecmaterialoutMapper.select(sql);
	}

	public List<Ecmaterialout> selectAll(){
		return ecmaterialoutMapper.selectAll();
	}

	public List<Ecmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialoutMapper.selectAll();
		else
			return ecmaterialoutMapper.selectBySql(sql);
	}

	public List<Ecmaterialout> selectBySql(String sql){
		return ecmaterialoutMapper.selectBySql(sql);
	}

	public Ecmaterialout selectByPrimaryKey(Integer id){
		return ecmaterialoutMapper.selectByPrimaryKey(id);
	}

	public List<Ecmaterialoutv> selectV(String sql){
		return ecmaterialoutMapper.selectV(sql);
	}

	public List<Ecmaterialoutv> selectVAll(){
		return ecmaterialoutMapper.selectVAll();
	}

	public List<Ecmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialoutMapper.selectVAll();
		else
			return ecmaterialoutMapper.selectVBySql(sql);
	}

	public List<Ecmaterialoutv> selectVBySql(String sql){
		return ecmaterialoutMapper.selectVBySql(sql);
	}

	public Ecmaterialoutv selectVByPrimaryKey(Integer id){
		return ecmaterialoutMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ecmaterialout record){
		return ecmaterialoutMapper.insert(record);
	}

	public int update(Ecmaterialout record){
		return ecmaterialoutMapper.update(record);
	}

	public int delete(Integer id){
		return ecmaterialoutMapper.delete(id);
	}

	public int deletes(String ids){
		return ecmaterialoutMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		return ecmaterialoutMapper.sumWeightGroupByCategory(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupBySource(String sql) {
		return ecmaterialoutMapper.sumWeightGroupBySource(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year) {
		return ecmaterialoutMapper.sumWeightGroupByMonth(treatmentplantid,cmaterialcategoryid,year);
	}
}