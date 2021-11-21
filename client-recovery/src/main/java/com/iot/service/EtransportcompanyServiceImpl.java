package com.iot.service;

import com.iot.bean.Ehospitalv;
import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EtransportcompanyServiceImpl implements EtransportcompanyService {
	@Override
	public Rmap<Etransportcompany> select(String sql) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etransportcompany> selectAll() {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etransportcompany> selectBySql(String sql) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompany> selectByPrimaryKey(Integer id) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}



	@Override
	public Rmap<Etransportcompany> insert(Etransportcompany record) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompany> update(Etransportcompany record) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompany> delete(Integer id) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompany> deletes(String ids) {
		Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etransportcompanyv> selectVAll() {
		Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etransportcompanyv> map = new  Rmap<Etransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etransportcompanyv> selectVBySql(String sql) {
		Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etransportcompanyv> selectVByPrimaryKey(Integer id) {
		Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> countBySql(String sql) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etransportcompanyv> selectV(String sql) {
		Rmap<Etransportcompanyv> map = new Rmap<Etransportcompanyv>();
		map.setMessage("服务异常");
		return map;
	}
}