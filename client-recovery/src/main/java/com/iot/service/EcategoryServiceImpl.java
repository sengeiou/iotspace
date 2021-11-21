package com.iot.service;

import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EcategoryServiceImpl implements EcategoryService {
	@Override
	public Rmap<Ecategory> select(String sql) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecategory> selectAll() {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Ecategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecategory> map = new  Rmap<Ecategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecategory> selectBySql(String sql) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategory> selectByPrimaryKey(Integer id) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategoryv> selectV(String sql) {
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecategoryv> selectVAll() {
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecategoryv> selectVBySql(String sql) {
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategoryv> selectVByPrimaryKey(Integer id) {
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategory> insert(Ecategory record) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategory> update(Ecategory record) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategory> delete(Integer id) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecategory> deletes(String ids) {
		Rmap<Ecategory> map = new Rmap<Ecategory>();
        map.setMessage("服务异常");
        return map;
	}
}