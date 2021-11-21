package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.mapper.EtreatmentplantproductMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("etreatmentplantproductService")
public class EtreatmentplantproductServiceImpl implements EtreatmentplantproductService {
	@Resource
	private EtreatmentplantproductMapper etreatmentplantproductMapper;

	public List<Etreatmentplantproduct> select(String sql){
		return etreatmentplantproductMapper.select(sql);
	}

	public List<Etreatmentplantproduct> selectAll(){
		return etreatmentplantproductMapper.selectAll();
	}

	public List<Etreatmentplantproduct> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etreatmentplantproductMapper.selectAll();
		else
			return etreatmentplantproductMapper.selectBySql(sql);
	}

	public List<Etreatmentplantproduct> selectBySql(String sql){
		return etreatmentplantproductMapper.selectBySql(sql);
	}

	public Etreatmentplantproduct selectByPrimaryKey(Integer id){
		return etreatmentplantproductMapper.selectByPrimaryKey(id);
	}

	public List<Etreatmentplantproductv> selectV(String sql){
		return etreatmentplantproductMapper.selectV(sql);
	}

	public List<Etreatmentplantproductv> selectVAll(){
		return etreatmentplantproductMapper.selectVAll();
	}

	public List<Etreatmentplantproductv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etreatmentplantproductMapper.selectVAll();
		else
			return etreatmentplantproductMapper.selectVBySql(sql);
	}

	public List<Etreatmentplantproductv> selectVBySql(String sql){
		return etreatmentplantproductMapper.selectVBySql(sql);
	}

	public Etreatmentplantproductv selectVByPrimaryKey(Integer id){
		return etreatmentplantproductMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etreatmentplantproduct record){
		return etreatmentplantproductMapper.insert(record);
	}

	public int update(Etreatmentplantproduct record){
		return etreatmentplantproductMapper.update(record);
	}

	public int delete(Integer id){
		return etreatmentplantproductMapper.delete(id);
	}

	public int deletes(String ids){
		return etreatmentplantproductMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		return etreatmentplantproductMapper.sumWeightGroupByCategory(sql);
	}
}