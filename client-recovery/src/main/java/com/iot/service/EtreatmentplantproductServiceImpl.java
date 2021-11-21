package com.iot.service;

import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EtreatmentplantproductServiceImpl implements EtreatmentplantproductService {
	@Override
	public Rmap<Etreatmentplantproduct> select(String sql) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> selectAll() {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Etreatmentplantproduct> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etreatmentplantproduct> map = new  Rmap<Etreatmentplantproduct>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> selectBySql(String sql) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> selectByPrimaryKey(Integer id) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproductv> selectV(String sql) {
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantproductv> selectVAll() {
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproductv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantproductv> selectVBySql(String sql) {
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproductv> selectVByPrimaryKey(Integer id) {
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> insert(Etreatmentplantproduct record) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> update(Etreatmentplantproduct record) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> delete(Integer id) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantproduct> deletes(String ids) {
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumWeightGroupByCategory(String sql) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}
}