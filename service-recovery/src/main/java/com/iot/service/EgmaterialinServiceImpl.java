package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Egmaterialin;
import com.iot.bean.Egmaterialinv;
import com.iot.mapper.EgmaterialinMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("egmaterialinService")
public class EgmaterialinServiceImpl implements EgmaterialinService {  
    @Resource  
    private EgmaterialinMapper egmaterialinMapper;

	public List<Egmaterialin> select(String sql){
		return egmaterialinMapper.select(sql);
	}

	public List<Egmaterialin> selectAll(){
		return egmaterialinMapper.selectAll();
	}

	public List<Egmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialinMapper.selectAll();
		else
			return egmaterialinMapper.selectBySql(sql);
	}

	public List<Egmaterialin> selectBySql(String sql){
		return egmaterialinMapper.selectBySql(sql);
	}

	public Egmaterialin selectByPrimaryKey(Integer id){
		return egmaterialinMapper.selectByPrimaryKey(id);
	}

	public List<Egmaterialinv> selectV(String sql){
		return egmaterialinMapper.selectV(sql);
	}

	public List<Egmaterialinv> selectVAll(){
		return egmaterialinMapper.selectVAll();
	}

	public List<Egmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialinMapper.selectVAll();
		else
			return egmaterialinMapper.selectVBySql(sql);
	}

	public List<Egmaterialinv> selectVBySql(String sql){
		return egmaterialinMapper.selectVBySql(sql);
	}

	public Egmaterialinv selectVByPrimaryKey(Integer id){
		return egmaterialinMapper.selectVByPrimaryKey(id);
	}

	public int insert(Egmaterialin record){
		return egmaterialinMapper.insert(record);
	}

	public int update(Egmaterialin record){
		return egmaterialinMapper.update(record);
	}

	public int delete(Integer id){
		return egmaterialinMapper.delete(id);
	}

	public int deletes(String ids){
		return egmaterialinMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		return egmaterialinMapper.sumWeightGroupByCategory(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupBySource(String sql) {
		return egmaterialinMapper.sumWeightGroupBySource(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year) {
		return egmaterialinMapper.sumWeightGroupByMonth(treatmentplantid,gmaterialcategoryid,year);
	}
}