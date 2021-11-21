package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;
import com.iot.mapper.EtrucktransportcompanyMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("etrucktransportcompanyService")
public class EtrucktransportcompanyServiceImpl implements EtrucktransportcompanyService {
    @Resource  
    private EtrucktransportcompanyMapper etrucktransportcompanyMapper;

	public List<Etrucktransportcompany> select(String sql){
		return etrucktransportcompanyMapper.select(sql);
	}

	public List<Etrucktransportcompany> selectAll(){
		return etrucktransportcompanyMapper.selectAll();
	}

	public List<Etrucktransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktransportcompanyMapper.selectAll();
		else
			return etrucktransportcompanyMapper.selectBySql(sql);
	}

	public List<Etrucktransportcompany> selectBySql(String sql){
		return etrucktransportcompanyMapper.selectBySql(sql);
	}

	public Etrucktransportcompany selectByPrimaryKey(Integer id){
		return etrucktransportcompanyMapper.selectByPrimaryKey(id);
	}

	public List<Etrucktransportcompanyv> selectV(String sql){
		return etrucktransportcompanyMapper.selectV(sql);
	}

	public List<Etrucktransportcompanyv> selectVAll(){
		return etrucktransportcompanyMapper.selectVAll();
	}

	public List<Etrucktransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktransportcompanyMapper.selectVAll();
		else
			return etrucktransportcompanyMapper.selectVBySql(sql);
	}

	public List<Etrucktransportcompanyv> selectVBySql(String sql){
		return etrucktransportcompanyMapper.selectVBySql(sql);
	}

	public Etrucktransportcompanyv selectVByPrimaryKey(Integer id){
		return etrucktransportcompanyMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etrucktransportcompany record){
		return etrucktransportcompanyMapper.insert(record);
	}

	public int update(Etrucktransportcompany record){
		return etrucktransportcompanyMapper.update(record);
	}

	public int delete(Integer id){
		return etrucktransportcompanyMapper.delete(id);
	}

	public int deletes(String ids){
		return etrucktransportcompanyMapper.deletes(ids);
	}

	public List<Etrucktransportcompanyv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etrucktransportcompanyMapper.selectVByStatusAndSql(sql);
	}
}