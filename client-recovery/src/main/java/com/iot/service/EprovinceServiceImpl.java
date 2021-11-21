package com.iot.service;

import com.iot.bean.Eprovince;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EprovinceServiceImpl implements EprovinceService {
	@Override
	public Rmap<Eprovince> select(String sql) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eprovince> selectAll() {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eprovince> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eprovince> selectBySql(String sql) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eprovince> selectByPrimaryKey(Integer id) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}



	@Override
	public Rmap<Eprovince> insert(Eprovince record) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eprovince> update(Eprovince record) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eprovince> delete(Integer id) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eprovince> deletes(String ids) {
		Rmap<Eprovince> map = new Rmap<Eprovince>();
        map.setMessage("服务异常");
        return map;
	}
}