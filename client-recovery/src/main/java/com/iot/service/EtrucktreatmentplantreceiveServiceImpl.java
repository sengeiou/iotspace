package com.iot.service;

import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;


@Component
public class EtrucktreatmentplantreceiveServiceImpl implements EtrucktreatmentplantreceiveService {
	@Override
	public Rmap<Etrucktreatmentplantreceive> select(String sql) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> selectAll() {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> selectBySql(String sql) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> selectByPrimaryKey(Integer id) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceivev> selectV(String sql) {
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceivev> selectVAll() {
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etrucktreatmentplantreceivev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktreatmentplantreceivev> map = new  Rmap<Etrucktreatmentplantreceivev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceivev> selectVBySql(String sql) {
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceivev> selectVByPrimaryKey(Integer id) {
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> insert(Etrucktreatmentplantreceive record) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> update(Etrucktreatmentplantreceive record) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> delete(Integer id) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantreceive> deletes(String ids) {
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<EtreatmentplantOutBoundVo> selectOutBoundVoByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<EtreatmentplantOutBoundVo> map = new  Rmap<EtreatmentplantOutBoundVo>();
		map.setMessage("服务异常");
		return map;
	}
}