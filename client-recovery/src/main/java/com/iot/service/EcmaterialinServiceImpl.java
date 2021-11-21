package com.iot.service;

import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EcmaterialinServiceImpl implements EcmaterialinService {
	@Override
	public Rmap<Ecmaterialin> select(String sql) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialin> selectAll() {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialin> selectBySql(String sql) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialin> selectByPrimaryKey(Integer id) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialinv> selectV(String sql) {
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialinv> selectVAll() {
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Ecmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialinv> map = new  Rmap<Ecmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialinv> selectVBySql(String sql) {
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialinv> selectVByPrimaryKey(Integer id) {
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialin> insert(Ecmaterialin record) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialin> update(Ecmaterialin record) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialin> delete(Integer id) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialin> deletes(String ids) {
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumWeightGroupBySource(String sql) {
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year) {
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		map.setMessage("服务异常");
		return map;
	}
}