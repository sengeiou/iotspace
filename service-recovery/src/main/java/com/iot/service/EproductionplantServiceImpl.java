package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Eproductionplant;
import com.iot.mapper.EproductionplantMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("eproductionplantService")
public class EproductionplantServiceImpl implements EproductionplantService {
    @Resource  
    private EproductionplantMapper eproductionplantMapper;

	public List<Eproductionplant> select(String sql){
		return eproductionplantMapper.select(sql);
	}

	public List<Eproductionplant> selectAll(){
		return eproductionplantMapper.selectAll();
	}

	public List<Eproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return eproductionplantMapper.selectAll();
		else
			return eproductionplantMapper.selectBySql(sql);
	}

	public List<Eproductionplant> selectBySql(String sql){
		return eproductionplantMapper.selectBySql(sql);
	}

	public Eproductionplant selectByPrimaryKey(Integer id){
		return eproductionplantMapper.selectByPrimaryKey(id);
	}

	public int insert(Eproductionplant record){
		return eproductionplantMapper.insert(record);
	}

	public int update(Eproductionplant record){
		return eproductionplantMapper.update(record);
	}

	public int delete(Integer id){
		return eproductionplantMapper.delete(id);
	}

	public int deletes(String ids){
		return eproductionplantMapper.deletes(ids);
	}
}