package com.iot.service;

import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EgmaterialoutServiceImpl implements EgmaterialoutService {
	@Override
	public Rmap<Egmaterialout> select(String sql) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialout> selectAll() {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialout> selectBySql(String sql) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialout> selectByPrimaryKey(Integer id) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialoutv> selectV(String sql) {
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialoutv> selectVAll() {
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Egmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialoutv> map = new  Rmap<Egmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialoutv> selectVBySql(String sql) {
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialoutv> selectVByPrimaryKey(Integer id) {
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialout> insert(Egmaterialout record) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialout> update(Egmaterialout record) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialout> delete(Integer id) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialout> deletes(String ids) {
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
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