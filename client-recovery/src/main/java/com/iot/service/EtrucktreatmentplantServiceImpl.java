package com.iot.service;

import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EtrucktreatmentplantServiceImpl implements EtrucktreatmentplantService {
	@Override
	public Rmap<Etrucktreatmentplant> select(String sql) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> selectAll() {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> selectBySql(String sql) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> selectByPrimaryKey(Integer id) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantv> selectV(String sql) {
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantv> selectVAll() {
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etrucktreatmentplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktreatmentplantv> map = new  Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantv> selectVBySql(String sql) {
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantv> selectVByPrimaryKey(Integer id) {
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> insert(Etrucktreatmentplant record) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> update(Etrucktreatmentplant record) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> delete(Integer id) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplant> deletes(String ids) {
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etrucktreatmentplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> sumGlassweightBySql(String sql) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> sumPlasticweightBySql(String sql) {
		Rmap<Integer> map = new Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearGlassweightGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

    @Override
    public Rmap<Map<String, Object>> trucktreatmentplantList(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;    }

    @Override
    public Rmap<Map<String, Object>> trucktreatmentplantListExport(String sql) {
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;    }

    @Override
    public Rmap<Map<String, Object>> trucktreatmentplantSum(String sql) {
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;    }
}