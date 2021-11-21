package com.iot.service;

import com.iot.bean.Egmaterialin;
import com.iot.bean.Egmaterialinv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EgmaterialinServiceImpl implements EgmaterialinService {
	@Override
	public Rmap<Egmaterialin> select(String sql) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialin> selectAll() {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialin> selectBySql(String sql) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialin> selectByPrimaryKey(Integer id) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialinv> selectV(String sql) {
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialinv> selectVAll() {
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Egmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialinv> map = new  Rmap<Egmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialinv> selectVBySql(String sql) {
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialinv> selectVByPrimaryKey(Integer id) {
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialin> insert(Egmaterialin record) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialin> update(Egmaterialin record) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialin> delete(Integer id) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialin> deletes(String ids) {
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
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
	public Rmap<Map<String, Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year) {
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		map.setMessage("服务异常");
		return map;
	}
}