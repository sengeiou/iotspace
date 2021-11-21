package com.iot.service;

import com.iot.bean.Ecmaterialout;
import com.iot.bean.Ecmaterialoutv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EcmaterialoutServiceImpl implements EcmaterialoutService {
	@Override
	public Rmap<Ecmaterialout> select(String sql) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialout> selectAll() {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialout> selectBySql(String sql) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialout> selectByPrimaryKey(Integer id) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialoutv> selectV(String sql) {
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialoutv> selectVAll() {
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Ecmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialoutv> map = new  Rmap<Ecmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialoutv> selectVBySql(String sql) {
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialoutv> selectVByPrimaryKey(Integer id) {
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialout> insert(Ecmaterialout record) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialout> update(Ecmaterialout record) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialout> delete(Integer id) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialout> deletes(String ids) {
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
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