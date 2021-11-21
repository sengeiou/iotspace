package com.iot.service;

import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EnoticeServiceImpl implements EnoticeService {
	@Override
	public Rmap<Enotice> select(String sql) {
		Rmap<Enotice> map = new Rmap<Enotice>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enotice> selectAll() {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Enotice> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Enotice> map = new  Rmap<Enotice>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enotice> selectBySql(String sql) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enotice> selectByPrimaryKey(Integer id) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enotice> insert(Enotice record) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enotice> update(Enotice record) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enotice> delete(Integer id) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enotice> deletes(String ids) {
		Rmap<Enotice> map = new Rmap<Enotice>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enoticev> selectV(String sql) {
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enoticev> selectVAll() {
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enoticev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enoticev> selectVBySql(String sql) {
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enoticev> selectVByPrimaryKey(Integer id) {
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		map.setMessage("服务异常");
		return map;
	}
}