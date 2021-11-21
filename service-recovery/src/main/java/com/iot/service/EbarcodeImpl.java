package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.mapper.EbarcodeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("ebarcodeService")
public class EbarcodeImpl implements EbarcodeService {  
    @Resource  
    private EbarcodeMapper ebarcodeMapper;

	public List<Ebarcode> select(String sql){
		return ebarcodeMapper.select(sql);
	}

	public List<Ebarcode> selectAll(){
		return ebarcodeMapper.selectAll();
	}

	public List<Ebarcode> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ebarcodeMapper.selectAll();
		else
			return ebarcodeMapper.selectBySql(sql);
	}


	public List<Ebarcode> selectBySql(String sql){
		return ebarcodeMapper.selectBySql(sql);
	}

	public Ebarcode selectByPrimaryKey(Integer id){
		return ebarcodeMapper.selectByPrimaryKey(id);
	}

	public List<Ebarcodev> selectV(String sql){
		return ebarcodeMapper.selectV(sql);
	}

	public List<Ebarcodev> selectVAll(){
		return ebarcodeMapper.selectVAll();
	}

	public List<Ebarcodev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ebarcodeMapper.selectVAll();
		else
			return ebarcodeMapper.selectVBySql(sql);
	}

	public List<Ebarcodev> selectVBySql(String sql){
		return ebarcodeMapper.selectVBySql(sql);
	}

	public Ebarcodev selectVByPrimaryKey(Integer id){
		return ebarcodeMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ebarcode record){
		return ebarcodeMapper.insert(record);
	}

	public int update(Ebarcode record){
		return ebarcodeMapper.update(record);
	}

	public int delete(Integer id){
		return ebarcodeMapper.delete(id);
	}

	public int deletes(String ids){
		return ebarcodeMapper.deletes(ids);
	}
}