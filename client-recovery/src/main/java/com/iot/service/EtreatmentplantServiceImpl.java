package com.iot.service;

import com.iot.bean.Etreatmentplant;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EtreatmentplantServiceImpl implements EtreatmentplantService {
	@Override
	public Rmap<Etreatmentplant> select(String sql) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplant> selectAll() {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplant> selectBySql(String sql) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> selectByPrimaryKey(Integer id) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> insert(Etreatmentplant record) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> update(Etreatmentplant record) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> delete(Integer id) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> deletes(String ids) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplant> updateProvince(Etreatmentplant record) {
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		map.setMessage("服务异常");
		return map;
	}
}