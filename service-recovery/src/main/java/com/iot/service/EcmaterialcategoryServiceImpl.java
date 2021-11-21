package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.mapper.EcmaterialcategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("ecmaterialcategoryService")
public class EcmaterialcategoryServiceImpl implements EcmaterialcategoryService {  
    @Resource  
    private EcmaterialcategoryMapper ecmaterialcategoryMapper;

	public List<Ecmaterialcategory> select(String sql){
		return ecmaterialcategoryMapper.select(sql);
	}

	public List<Ecmaterialcategory> selectAll(){
		return ecmaterialcategoryMapper.selectAll();
	}

	public List<Ecmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialcategoryMapper.selectAll();
		else
			return ecmaterialcategoryMapper.selectBySql(sql);
	}

	public List<Ecmaterialcategory> selectBySql(String sql){
		return ecmaterialcategoryMapper.selectBySql(sql);
	}

	public Ecmaterialcategory selectByPrimaryKey(Integer id){
		return ecmaterialcategoryMapper.selectByPrimaryKey(id);
	}

	public List<Ecmaterialcategoryv> selectV(String sql){
		return ecmaterialcategoryMapper.selectV(sql);
	}

	public List<Ecmaterialcategoryv> selectVAll(){
		return ecmaterialcategoryMapper.selectVAll();
	}

	public List<Ecmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ecmaterialcategoryMapper.selectVAll();
		else
			return ecmaterialcategoryMapper.selectVBySql(sql);
	}

	public List<Ecmaterialcategoryv> selectVBySql(String sql){
		return ecmaterialcategoryMapper.selectVBySql(sql);
	}

	public Ecmaterialcategoryv selectVByPrimaryKey(Integer id){
		return ecmaterialcategoryMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ecmaterialcategory record){
		return ecmaterialcategoryMapper.insert(record);
	}

	public int update(Ecmaterialcategory record){
		return ecmaterialcategoryMapper.update(record);
	}

	public int delete(Integer id){
		return ecmaterialcategoryMapper.delete(id);
	}

	public int deletes(String ids){
		return ecmaterialcategoryMapper.deletes(ids);
	}

	@Override
	public int updateStock(Integer id, Double stock) {
		return ecmaterialcategoryMapper.updateStock(id,stock);
	}
}