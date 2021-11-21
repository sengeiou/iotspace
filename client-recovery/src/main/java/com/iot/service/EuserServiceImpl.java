package com.iot.service;

import com.iot.bean.Euser;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EuserServiceImpl implements EuserService {
	@Override
	public Rmap<Euser> select(String sql) {
		Rmap<Euser> map = new Rmap<Euser>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Euser> selectAll() {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Euser> map = new Rmap<Euser>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Euser> selectBySql(String sql) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> selectByPrimaryKey(Integer id) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> insert(Euser record) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> update(Euser record) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> delete(Integer id) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Euser> deletes(String ids) {
		Rmap<Euser> map = new Rmap<Euser>();
        map.setMessage("服务异常");
        return map;
	}
}