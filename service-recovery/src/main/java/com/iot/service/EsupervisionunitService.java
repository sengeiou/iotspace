package com.iot.service;

import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;

import java.util.List;

public interface EsupervisionunitService {
	public List<Esupervisionunit> select(String sql);

	public List<Esupervisionunit> selectAll();

	public List<Esupervisionunit> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Esupervisionunit> selectBySql(String sql);

	public Esupervisionunit selectByPrimaryKey(Integer id);

	public List<Esupervisionunitv> selectV(String sql);

	public List<Esupervisionunitv> selectVAll();

	public List<Esupervisionunitv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Esupervisionunitv> selectVBySql(String sql);

	public Esupervisionunitv selectVByPrimaryKey(Integer id);

	public int insert(Esupervisionunit record);

	public int update(Esupervisionunit record);

	public int delete(Integer id);

	public int deletes(String ids);
}