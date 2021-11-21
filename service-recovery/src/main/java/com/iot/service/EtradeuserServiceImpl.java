package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etradeuser;
import com.iot.mapper.EtradeuserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("etradeuserService")
public class EtradeuserServiceImpl implements EtradeuserService {  
    @Resource  
    private EtradeuserMapper etradeuserMapper;

	public List<Etradeuser> select(String sql){
		return etradeuserMapper.select(sql);
	}

	public List<Etradeuser> selectAll(){
		return etradeuserMapper.selectAll();
	}

	public List<Etradeuser> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etradeuserMapper.selectAll();
		else
			return etradeuserMapper.selectBySql(sql);
	}

	public List<Etradeuser> selectBySql(String sql){
		return etradeuserMapper.selectBySql(sql);
	}

	public Etradeuser selectByPrimaryKey(Integer id){
		return etradeuserMapper.selectByPrimaryKey(id);
	}

	public int insert(Etradeuser record){
		return etradeuserMapper.insert(record);
	}

	public int update(Etradeuser record){
		return etradeuserMapper.update(record);
	}

	public int delete(Integer id){
		return etradeuserMapper.delete(id);
	}

	public int deletes(String ids){
		return etradeuserMapper.deletes(ids);
	}
}