package com.iot.service;

import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;

import java.util.List;

public interface EinfusionbagService {
	public List<Einfusionbag> select(String sql);

	public List<Einfusionbag> selectAll();

	public List<Einfusionbag> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Einfusionbag> selectBySql(String sql);

	public Einfusionbag selectByPrimaryKey(Integer id);

	public List<Einfusionbagv> selectV(String sql);

	public List<Einfusionbagv> selectVAll();

	public List<Einfusionbagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Einfusionbagv> selectVBySql(String sql);

	public Einfusionbagv selectVByPrimaryKey(Integer id);

	public int insert(Einfusionbag record);

	public int update(Einfusionbag record);

	public int delete(Integer id);

	public int deletes(String ids);
}