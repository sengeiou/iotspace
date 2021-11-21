package com.iot.service;

import com.iot.bean.Eoperate;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EoperateServiceImpl implements EoperateService {
	@Override
	public Rmap<Eoperate> select(String sql) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eoperate> selectAll() {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Eoperate> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eoperate> map = new  Rmap<Eoperate>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eoperate> selectBySql(String sql) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eoperate> selectByPrimaryKey(Integer id) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eoperate> insert(Eoperate record) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eoperate> update(Eoperate record) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eoperate> delete(Integer id) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eoperate> deletes(String ids) {
		Rmap<Eoperate> map = new Rmap<Eoperate>();
        map.setMessage("服务异常");
        return map;
	}
}