package com.iot.service;

import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EgmaterialcategoryServiceImpl implements EgmaterialcategoryService {
	@Override
	public Rmap<Egmaterialcategory> select(String sql) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategory> selectAll() {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategory> selectBySql(String sql) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategory> selectByPrimaryKey(Integer id) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategoryv> selectV(String sql) {
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategoryv> selectVAll() {
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Egmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Egmaterialcategoryv> map = new  Rmap<Egmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategoryv> selectVBySql(String sql) {
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategoryv> selectVByPrimaryKey(Integer id) {
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Egmaterialcategory> insert(Egmaterialcategory record) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategory> update(Egmaterialcategory record) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategory> delete(Integer id) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Egmaterialcategory> deletes(String ids) {
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
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