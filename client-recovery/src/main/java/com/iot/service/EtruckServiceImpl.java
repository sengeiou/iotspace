package com.iot.service;

import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EtruckServiceImpl implements EtruckService {
	@Override
	public Rmap<Etruck> select(String sql) {
		Rmap<Etruck> map = new Rmap<Etruck>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruck> selectAll() {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruck> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruck> map = new Rmap<Etruck>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruck> selectBySql(String sql) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruck> selectByPrimaryKey(Integer id) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckv> selectV(String sql) {
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckv> selectVAll() {
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etruckv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruckv> map = new  Rmap<Etruckv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckv> selectVBySql(String sql) {
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckv> selectVByPrimaryKey(Integer id) {
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruck> insert(Etruck record) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruck> update(Etruck record) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruck> delete(Integer id) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruck> deletes(String ids) {
		Rmap<Etruck> map = new Rmap<Etruck>();
        map.setMessage("服务异常");
        return map;
	}
}