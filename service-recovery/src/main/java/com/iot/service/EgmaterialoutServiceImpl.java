package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;
import com.iot.mapper.EgmaterialoutMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("egmaterialoutService")
public class EgmaterialoutServiceImpl implements EgmaterialoutService {  
    @Resource  
    private EgmaterialoutMapper egmaterialoutMapper;

	public List<Egmaterialout> select(String sql){
		return egmaterialoutMapper.select(sql);
	}

	public List<Egmaterialout> selectAll(){
		return egmaterialoutMapper.selectAll();
	}

	public List<Egmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialoutMapper.selectAll();
		else
			return egmaterialoutMapper.selectBySql(sql);
	}

	public List<Egmaterialout> selectBySql(String sql){
		return egmaterialoutMapper.selectBySql(sql);
	}

	public Egmaterialout selectByPrimaryKey(Integer id){
		return egmaterialoutMapper.selectByPrimaryKey(id);
	}

	public List<Egmaterialoutv> selectV(String sql){
		return egmaterialoutMapper.selectV(sql);
	}

	public List<Egmaterialoutv> selectVAll(){
		return egmaterialoutMapper.selectVAll();
	}

	public List<Egmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialoutMapper.selectVAll();
		else
			return egmaterialoutMapper.selectVBySql(sql);
	}

	public List<Egmaterialoutv> selectVBySql(String sql){
		return egmaterialoutMapper.selectVBySql(sql);
	}

	public Egmaterialoutv selectVByPrimaryKey(Integer id){
		return egmaterialoutMapper.selectVByPrimaryKey(id);
	}

	public int insert(Egmaterialout record){
		return egmaterialoutMapper.insert(record);
	}

	public int update(Egmaterialout record){
		return egmaterialoutMapper.update(record);
	}

	public int delete(Integer id){
		return egmaterialoutMapper.delete(id);
	}

	public int deletes(String ids){
		return egmaterialoutMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		return egmaterialoutMapper.sumWeightGroupByCategory(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupBySource(String sql) {
		return egmaterialoutMapper.sumWeightGroupBySource(sql);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year) {
		return egmaterialoutMapper.sumWeightGroupByMonth(treatmentplantid,gmaterialcategoryid,year);
	}
}