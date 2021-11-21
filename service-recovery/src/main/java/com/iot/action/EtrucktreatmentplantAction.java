package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.bean.Rmap;
import com.iot.service.EtrucktreatmentplantService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trucktreatmentplant")
public class EtrucktreatmentplantAction {
	@Resource
	private EtrucktreatmentplantService etrucktreatmentplantService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplant> select(String sql){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		List<Etrucktreatmentplant> list = etrucktreatmentplantService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplant> selectAll(){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		List<Etrucktreatmentplant> list = etrucktreatmentplantService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktreatmentplant> list = etrucktreatmentplantService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktreatmentplant> pageInfo = new PageInfo<Etrucktreatmentplant>(list);
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplant> selectBySql(String sql){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		List<Etrucktreatmentplant> list = etrucktreatmentplantService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplant> selectByPrimaryKey(Integer id){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		Etrucktreatmentplant etrucktreatmentplant = etrucktreatmentplantService.selectByPrimaryKey(id);
		if(null != etrucktreatmentplant)
			map.setObject(etrucktreatmentplant);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv> selectV(String sql){
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		List<Etrucktreatmentplantv> list = etrucktreatmentplantService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv>  selectVAll(){
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		List<Etrucktreatmentplantv> list = etrucktreatmentplantService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktreatmentplantv> list = etrucktreatmentplantService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktreatmentplantv> pageInfo = new PageInfo<Etrucktreatmentplantv>(list);
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv> selectVBySql(String sql){
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		List<Etrucktreatmentplantv> list = etrucktreatmentplantService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv> selectVByPrimaryKey(Integer id){
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		Etrucktreatmentplantv etrucktreatmentplantv = etrucktreatmentplantService.selectVByPrimaryKey(id);
		if(null != etrucktreatmentplantv)
			map.setObject(etrucktreatmentplantv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etrucktreatmentplant> insert(@RequestBody Etrucktreatmentplant record){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		if(etrucktreatmentplantService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etrucktreatmentplant> update(@RequestBody Etrucktreatmentplant record){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		if(etrucktreatmentplantService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etrucktreatmentplant> delete(Integer id){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		Etrucktreatmentplant etrucktreatmentplant = etrucktreatmentplantService.selectByPrimaryKey(id);
		if(etrucktreatmentplantService.delete(id) > 0)
			map.setObject(etrucktreatmentplant);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etrucktreatmentplant> deletes(String ids){
		Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
		List<Etrucktreatmentplant> list = etrucktreatmentplantService.selectBySql("id in "+ids);
		if(etrucktreatmentplantService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/selectVByStatusAndSqlPage", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktreatmentplantv> list = etrucktreatmentplantService.selectVByStatusAndSqlPage(page, rows, sort, order, sql);
		PageInfo<Etrucktreatmentplantv> pageInfo = new PageInfo<Etrucktreatmentplantv>(list);
		Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/sumGlassweightBySql", method = RequestMethod.GET)
	public Rmap<Integer> sumGlassweightBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		Integer object = etrucktreatmentplantService.sumGlassweightBySql(sql);
		if(null != object)
			map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/sumPlasticweightBySql", method = RequestMethod.GET)
	public Rmap<Integer> sumPlasticweightBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		Integer object = etrucktreatmentplantService.sumPlasticweightBySql(sql);
		if(null != object)
			map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/yearGlassnumberGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> yearGlassnumberGroupMonthSql(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = etrucktreatmentplantService.yearGlassnumberGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearPlasticnumberGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> yearPlasticnumberGroupMonthSql(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = etrucktreatmentplantService.yearPlasticnumberGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearGlassweightGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> yearGlassweightGroupMonthSql(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = etrucktreatmentplantService.yearGlassweightGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearPlasticweightGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> yearPlasticweightGroupMonthSql(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = etrucktreatmentplantService.yearPlasticweightGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

    @RequestMapping(value = "/trucktreatmentplantList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> trucktreatmentplantList(Integer page, Integer rows, String sort, String order, String sql){
        List<Map<String, Object>> list = etrucktreatmentplantService.trucktreatmentplantList(page, rows, sort, order, sql);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/trucktreatmentplantListExport", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> trucktreatmentplantListExport(String sql){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        List<Map<String, Object>> list =etrucktreatmentplantService.trucktreatmentplantListExport(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/trucktreatmentplantSum", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> trucktreatmentplantSum(String sql){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        Map<String, Object> result = etrucktreatmentplantService.trucktreatmentplantSum(sql);
        if(null != result)
            map.setObject(result);
        return map;
    }
}