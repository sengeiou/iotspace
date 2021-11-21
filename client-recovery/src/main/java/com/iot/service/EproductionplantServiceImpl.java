package com.iot.service;

import com.iot.bean.Eproductionplant;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EproductionplantServiceImpl implements EproductionplantService {
	@Override
	public Rmap<Eproductionplant> select(String sql) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eproductionplant> selectAll() {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eproductionplant> selectBySql(String sql) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> selectByPrimaryKey(Integer id) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> insert(Eproductionplant record) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> update(Eproductionplant record) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> delete(Integer id) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eproductionplant> deletes(String ids) {
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        map.setMessage("服务异常");
        return map;
	}
}