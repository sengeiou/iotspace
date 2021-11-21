package com.iot.service;

import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EhospitalServiceImpl implements EhospitalService {
	@Override
	public Rmap<Ehospital> select(String sql) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospital> selectAll() {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospital> selectBySql(String sql) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospital> selectByPrimaryKey(Integer id) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospitalv> selectV(String sql) {
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospitalv> selectVAll() {
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Ehospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Ehospitalv> map = new  Rmap<Ehospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospitalv> selectVBySql(String sql) {
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospitalv> selectVByPrimaryKey(Integer id) {
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Ehospital> insert(Ehospital record) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospital> update(Ehospital record) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospital> delete(Integer id) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Ehospital> deletes(String ids) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Integer> countryHospitalNum() {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> provinceHospitalNum(String address) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> cityHospitalNum(String address) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> platHospitalNum(String address,String paddress) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countByTransportcompanyGroupByType(Integer transportcompanyid) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countByTransportcompanyAndLevel(Integer transportcompanyid, String level) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> hospitalNumBySql(String sql) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

    @Override
    public Rmap<Ehospitalv> provinceHospital(Integer page, Integer rows, String sort, String order, String address, String sql) {
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ehospitalv> cityHospital(Integer page, Integer rows, String sort, String order, String address, String sql) {
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Ehospitalv> platHospital(Integer page, Integer rows, String sort, String order, String address, String paddress, String sql) {
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        map.setMessage("服务异常");
        return map;
    }

	@Override
	public Rmap<Ehospital> updateLongitudeLatitude(Ehospital record) {
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		map.setMessage("服务异常");
		return map;
	}
}