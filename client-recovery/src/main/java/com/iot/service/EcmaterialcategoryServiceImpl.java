package com.iot.service;

import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EcmaterialcategoryServiceImpl implements EcmaterialcategoryService {
	@Override
	public Rmap<Ecmaterialcategory> select(String sql) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> selectAll() {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> selectBySql(String sql) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> selectByPrimaryKey(Integer id) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategoryv> selectV(String sql) {
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategoryv> selectVAll() {
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Ecmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecmaterialcategoryv> map = new  Rmap<Ecmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategoryv> selectVBySql(String sql) {
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategoryv> selectVByPrimaryKey(Integer id) {
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> insert(Ecmaterialcategory record) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> update(Ecmaterialcategory record) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> delete(Integer id) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecmaterialcategory> deletes(String ids) {
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Integer> updateStock(Integer id, Double stock) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}
}