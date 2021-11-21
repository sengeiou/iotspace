package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etreatmentplant;
import com.iot.mapper.EtreatmentplantMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("etreatmentplantService")
public class EtreatmentplantServiceImpl implements EtreatmentplantService {
    @Resource  
    private EtreatmentplantMapper etreatmentplantMapper;

	public List<Etreatmentplant> select(String sql){
		return etreatmentplantMapper.select(sql);
	}

	public List<Etreatmentplant> selectAll(){
		return etreatmentplantMapper.selectAll();
	}

	public List<Etreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etreatmentplantMapper.selectAll();
		else
			return etreatmentplantMapper.selectBySql(sql);
	}

	public List<Etreatmentplant> selectBySql(String sql){
		return etreatmentplantMapper.selectBySql(sql);
	}

	public Etreatmentplant selectByPrimaryKey(Integer id){
		return etreatmentplantMapper.selectByPrimaryKey(id);
	}

	public int insert(Etreatmentplant record){
		return etreatmentplantMapper.insert(record);
	}

	public int update(Etreatmentplant record){
		return etreatmentplantMapper.update(record);
	}

	public int delete(Integer id){
		return etreatmentplantMapper.delete(id);
	}

	public int deletes(String ids){
		return etreatmentplantMapper.deletes(ids);
	}

	public int updateProvince(Etreatmentplant record){
		return etreatmentplantMapper.updateProvince(record);
	}
}