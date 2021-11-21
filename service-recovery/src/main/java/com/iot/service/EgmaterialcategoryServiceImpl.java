package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;
import com.iot.mapper.EgmaterialcategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("EgmaterialcategoryService")
public class EgmaterialcategoryServiceImpl implements EgmaterialcategoryService {  
    @Resource  
    private EgmaterialcategoryMapper egmaterialcategoryMapper;

	public List<Egmaterialcategory> select(String sql){
		return egmaterialcategoryMapper.select(sql);
	}

	public List<Egmaterialcategory> selectAll(){
		return egmaterialcategoryMapper.selectAll();
	}

	public List<Egmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialcategoryMapper.selectAll();
		else
			return egmaterialcategoryMapper.selectBySql(sql);
	}

	public List<Egmaterialcategory> selectBySql(String sql){
		return egmaterialcategoryMapper.selectBySql(sql);
	}

	public Egmaterialcategory selectByPrimaryKey(Integer id){
		return egmaterialcategoryMapper.selectByPrimaryKey(id);
	}

	public List<Egmaterialcategoryv> selectV(String sql){
		return egmaterialcategoryMapper.selectV(sql);
	}

	public List<Egmaterialcategoryv> selectVAll(){
		return egmaterialcategoryMapper.selectVAll();
	}

	public List<Egmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return egmaterialcategoryMapper.selectVAll();
		else
			return egmaterialcategoryMapper.selectVBySql(sql);
	}

	public List<Egmaterialcategoryv> selectVBySql(String sql){
		return egmaterialcategoryMapper.selectVBySql(sql);
	}

	public Egmaterialcategoryv selectVByPrimaryKey(Integer id){
		return egmaterialcategoryMapper.selectVByPrimaryKey(id);
	}

	public int insert(Egmaterialcategory record){
		return egmaterialcategoryMapper.insert(record);
	}

	public int update(Egmaterialcategory record){
		return egmaterialcategoryMapper.update(record);
	}

	public int delete(Integer id){
		return egmaterialcategoryMapper.delete(id);
	}

	public int deletes(String ids){
		return egmaterialcategoryMapper.deletes(ids);
	}

	@Override
	public int updateStock(Integer id, Double stock) {
		return egmaterialcategoryMapper.updateStock(id,stock);
	}
}