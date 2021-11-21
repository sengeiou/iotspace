package com.iot.service;

import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EtrucktransportcompanyServiceImpl implements EtrucktransportcompanyService {
	@Override
	public Rmap<Etrucktransportcompany> select(String sql) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> selectAll() {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> selectBySql(String sql) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> selectByPrimaryKey(Integer id) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompanyv> selectV(String sql) {
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompanyv> selectVAll() {
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etrucktransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktransportcompanyv> map = new  Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompanyv> selectVBySql(String sql) {
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompanyv> selectVByPrimaryKey(Integer id) {
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> insert(Etrucktransportcompany record) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> update(Etrucktransportcompany record) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> delete(Integer id) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompany> deletes(String ids) {
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktransportcompanyv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}
}