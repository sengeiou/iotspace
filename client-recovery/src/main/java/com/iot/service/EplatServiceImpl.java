package com.iot.service;

import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EplatServiceImpl implements EplatService {
	@Override
	public Rmap<Eplat> select(String sql) {
		Rmap<Eplat> map = new Rmap<Eplat>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eplat> selectAll() {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eplat> map = new Rmap<Eplat>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eplat> selectBySql(String sql) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> selectByPrimaryKey(Integer id) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplatv> selectV(String sql) {
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eplatv> selectVAll() {
		Rmap<Eplatv> map = new Rmap<Eplatv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplatv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Eplatv> selectVBySql(String sql) {
		Rmap<Eplatv> map = new Rmap<Eplatv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplatv> selectVByPrimaryKey(Integer id) {
		Rmap<Eplatv> map = new Rmap<Eplatv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> insert(Eplat record) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> update(Eplat record) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> delete(Integer id) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Eplat> deletes(String ids) {
		Rmap<Eplat> map = new Rmap<Eplat>();
        map.setMessage("服务异常");
        return map;
	}
}