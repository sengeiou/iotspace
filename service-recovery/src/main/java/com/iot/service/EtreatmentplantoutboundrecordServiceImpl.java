package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.mapper.EtreatmentplantoutboundrecordMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("etreatmentplantoutboundrecordService")
public class EtreatmentplantoutboundrecordServiceImpl implements EtreatmentplantoutboundrecordService {
	@Resource
	private EtreatmentplantoutboundrecordMapper etreatmentplantoutboundrecordMapper;

	public List<Etreatmentplantoutboundrecord> select(String sql){
		return etreatmentplantoutboundrecordMapper.select(sql);
	}

	public List<Etreatmentplantoutboundrecord> selectAll(){
		return etreatmentplantoutboundrecordMapper.selectAll();
	}

	public List<Etreatmentplantoutboundrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etreatmentplantoutboundrecordMapper.selectAll();
		else
			return etreatmentplantoutboundrecordMapper.selectBySql(sql);
	}

	public List<Etreatmentplantoutboundrecord> selectBySql(String sql){
		return etreatmentplantoutboundrecordMapper.selectBySql(sql);
	}

	public Etreatmentplantoutboundrecord selectByPrimaryKey(Integer id){
		return etreatmentplantoutboundrecordMapper.selectByPrimaryKey(id);
	}

	public List<Etreatmentplantoutboundrecordv> selectV(String sql){
		return etreatmentplantoutboundrecordMapper.selectV(sql);
	}

	public List<Etreatmentplantoutboundrecordv> selectVAll(){
		return etreatmentplantoutboundrecordMapper.selectVAll();
	}

	public List<Etreatmentplantoutboundrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
        if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etreatmentplantoutboundrecordMapper.selectVAll();
		else
			return etreatmentplantoutboundrecordMapper.selectVBySql(sql);
	}

	public List<Etreatmentplantoutboundrecordv> selectVBySql(String sql){
		return etreatmentplantoutboundrecordMapper.selectVBySql(sql);
	}

	public Etreatmentplantoutboundrecordv selectVByPrimaryKey(Integer id){
		return etreatmentplantoutboundrecordMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etreatmentplantoutboundrecord record){
		return etreatmentplantoutboundrecordMapper.insert(record);
	}

	public int update(Etreatmentplantoutboundrecord record){
		return etreatmentplantoutboundrecordMapper.update(record);
	}

	public int delete(Integer id){
		return etreatmentplantoutboundrecordMapper.delete(id);
	}

	public int deletes(String ids){
		return etreatmentplantoutboundrecordMapper.deletes(ids);
	}
}