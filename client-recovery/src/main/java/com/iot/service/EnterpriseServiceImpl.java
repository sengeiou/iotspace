package com.iot.service;

import com.iot.bean.Enterprise;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseServiceImpl implements EnterpriseService {
	@Override
	public Rmap<Enterprise> select(String sql) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enterprise> selectAll() {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enterprise> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Enterprise> selectBySql(String sql) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enterprise> selectByPrimaryKey(Integer id) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}



	@Override
	public Rmap<Enterprise> insert(Enterprise record) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enterprise> update(Enterprise record) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enterprise> delete(Integer id) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Enterprise> deletes(String ids) {
		Rmap<Enterprise> map = new Rmap<Enterprise>();
        map.setMessage("服务异常");
        return map;
	}
}