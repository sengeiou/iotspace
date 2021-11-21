package com.iot.service;

import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EcityServiceImpl implements EcityService {
	@Override
	public Rmap<Ecity> select(String sql) {
		Rmap<Ecity> map = new Rmap<Ecity>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecity> selectAll() {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Ecity> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecity> map = new  Rmap<Ecity>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecity> selectBySql(String sql) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecity> selectByPrimaryKey(Integer id) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecityv> selectV(String sql) {
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecityv> selectVAll() {
		Rmap<Ecityv> map = new Rmap<Ecityv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecityv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ecityv> selectVBySql(String sql) {
		Rmap<Ecityv> map = new Rmap<Ecityv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecityv> selectVByPrimaryKey(Integer id) {
		Rmap<Ecityv> map = new Rmap<Ecityv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecity> insert(Ecity record) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecity> update(Ecity record) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecity> delete(Integer id) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ecity> deletes(String ids) {
		Rmap<Ecity> map = new Rmap<Ecity>();
        map.setMessage("服务异常");
        return map;
	}
}