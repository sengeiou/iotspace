package com.iot.service;

import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

@Component
public class EtreatmentplantoutboundrecordServiceImpl implements EtreatmentplantoutboundrecordService {
	@Override
	public Rmap<Etreatmentplantoutboundrecord> select(String sql) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> selectAll() {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Etreatmentplantoutboundrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etreatmentplantoutboundrecord> map = new  Rmap<Etreatmentplantoutboundrecord>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> selectBySql(String sql) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> selectByPrimaryKey(Integer id) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecordv> selectV(String sql) {
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecordv> selectVAll() {
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecordv> selectVBySql(String sql) {
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecordv> selectVByPrimaryKey(Integer id) {
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> insert(Etreatmentplantoutboundrecord record) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> update(Etreatmentplantoutboundrecord record) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> delete(Integer id) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etreatmentplantoutboundrecord> deletes(String ids) {
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        map.setMessage("服务异常");
        return map;
	}
}