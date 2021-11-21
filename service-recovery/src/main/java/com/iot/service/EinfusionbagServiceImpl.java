package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.mapper.EinfusionbagMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("einfusionbagService")
public class EinfusionbagServiceImpl implements EinfusionbagService {
	@Resource
	private EinfusionbagMapper einfusionbagMapper;

	public List<Einfusionbag> select(String sql){
		return einfusionbagMapper.select(sql);
	}

	public List<Einfusionbag> selectAll(){
		return einfusionbagMapper.selectAll();
	}

	public List<Einfusionbag> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return einfusionbagMapper.selectAll();
		else
			return einfusionbagMapper.selectBySql(sql);
	}

	public List<Einfusionbag> selectBySql(String sql){
		return einfusionbagMapper.selectBySql(sql);
	}

	public Einfusionbag selectByPrimaryKey(Integer id){
		return einfusionbagMapper.selectByPrimaryKey(id);
	}

	public List<Einfusionbagv> selectV(String sql){
		return einfusionbagMapper.selectV(sql);
	}

	public List<Einfusionbagv> selectVAll(){
		return einfusionbagMapper.selectVAll();
	}

	public List<Einfusionbagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return einfusionbagMapper.selectVAll();
		else
			return einfusionbagMapper.selectVBySql(sql);
	}


	public List<Einfusionbagv> selectVBySql(String sql){
		return einfusionbagMapper.selectVBySql(sql);
	}

	public Einfusionbagv selectVByPrimaryKey(Integer id){
		return einfusionbagMapper.selectVByPrimaryKey(id);
	}

	public int insert(Einfusionbag record){
		return einfusionbagMapper.insert(record);
	}

	public int update(Einfusionbag record){
		return einfusionbagMapper.update(record);
	}

	public int delete(Integer id){
		return einfusionbagMapper.delete(id);
	}

	public int deletes(String ids){
		return einfusionbagMapper.deletes(ids);
	}
}