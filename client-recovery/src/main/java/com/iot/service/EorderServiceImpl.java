package com.iot.service;

import com.iot.bean.Eorder;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EorderServiceImpl implements EorderService {
	@Override
	public Rmap<Eorder> select(String sql) {
		Rmap<Eorder> map = new Rmap<Eorder>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eorder> selectAll() {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eorder> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eorder> map = new Rmap<Eorder>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eorder> selectBySql(String sql) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eorder> selectByPrimaryKey(Integer id) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}



	@Override
	public Rmap<Eorder> insert(Eorder record) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eorder> update(Eorder record) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eorder> delete(Integer id) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eorder> deletes(String ids) {
		Rmap<Eorder> map = new Rmap<Eorder>();
        map.setMessage("服务异常");
        return map;
	}
}