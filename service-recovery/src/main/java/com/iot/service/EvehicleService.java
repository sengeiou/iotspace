package com.iot.service;

import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;

import java.util.List;

public interface EvehicleService {
	public List<Evehicle> select(String sql);

	public List<Evehicle> selectAll();


	public List<Evehicle> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Evehicle> selectBySql(String sql);

	public Evehicle selectByPrimaryKey(Integer id);

	public List<Evehiclev> selectV(String sql);

	public List<Evehiclev> selectVAll();

	public List<Evehiclev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);


	public List<Evehiclev> selectVBySql(String sql);

	public Evehiclev selectVByPrimaryKey(Integer id);

	public int insert(Evehicle record);

	public int update(Evehicle record);

	public int delete(Integer id);

	public int deletes(String ids);

	public int vehicleNumBySql(String sql);

}