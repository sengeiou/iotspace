package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;
import com.iot.mapper.EtrucktreatmentplantreceiveMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("etrucktreatmentplantreceiveService")
public class EtrucktreatmentplantreceiveServiceImpl implements EtrucktreatmentplantreceiveService {
    @Resource  
    private EtrucktreatmentplantreceiveMapper etrucktreatmentplantreceiveMapper;

	public List<Etrucktreatmentplantreceive> select(String sql){
		return etrucktreatmentplantreceiveMapper.select(sql);
	}

	public List<Etrucktreatmentplantreceive> selectAll(){
		return etrucktreatmentplantreceiveMapper.selectAll();
	}

	public List<Etrucktreatmentplantreceive> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktreatmentplantreceiveMapper.selectAll();
		else
			return etrucktreatmentplantreceiveMapper.selectBySql(sql);
	}

	public List<Etrucktreatmentplantreceive> selectBySql(String sql){
		return etrucktreatmentplantreceiveMapper.selectBySql(sql);
	}

	public Etrucktreatmentplantreceive selectByPrimaryKey(Integer id){
		return etrucktreatmentplantreceiveMapper.selectByPrimaryKey(id);
	}

	public List<Etrucktreatmentplantreceivev> selectV(String sql){
		return etrucktreatmentplantreceiveMapper.selectV(sql);
	}

	public List<Etrucktreatmentplantreceivev> selectVAll(){
		return etrucktreatmentplantreceiveMapper.selectVAll();
	}

	public List<Etrucktreatmentplantreceivev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktreatmentplantreceiveMapper.selectVAll();
		else
			return etrucktreatmentplantreceiveMapper.selectVBySql(sql);
	}

	public List<Etrucktreatmentplantreceivev> selectVBySql(String sql){
		return etrucktreatmentplantreceiveMapper.selectVBySql(sql);
	}

	public Etrucktreatmentplantreceivev selectVByPrimaryKey(Integer id){
		return etrucktreatmentplantreceiveMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etrucktreatmentplantreceive record){
		return etrucktreatmentplantreceiveMapper.insert(record);
	}

	public int update(Etrucktreatmentplantreceive record){
		return etrucktreatmentplantreceiveMapper.update(record);
	}

	public int delete(Integer id){
		return etrucktreatmentplantreceiveMapper.delete(id);
	}

	public int deletes(String ids){
		return etrucktreatmentplantreceiveMapper.deletes(ids);
	}

	public List<EtreatmentplantOutBoundVo> selectOutBoundVoByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etrucktreatmentplantreceiveMapper.selectOutBoundVoBySql(sql);
	}
}