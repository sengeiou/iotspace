package com.iot.service;

import com.iot.bean.Etradeuser;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EtradeuserServiceImpl implements EtradeuserService {
	@Override
	public Rmap<Etradeuser> select(String sql) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etradeuser> selectAll() {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etradeuser> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etradeuser> selectBySql(String sql) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etradeuser> selectByPrimaryKey(Integer id) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}



	@Override
	public Rmap<Etradeuser> insert(Etradeuser record) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etradeuser> update(Etradeuser record) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etradeuser> delete(Integer id) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etradeuser> deletes(String ids) {
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        map.setMessage("服务异常");
        return map;
	}
}