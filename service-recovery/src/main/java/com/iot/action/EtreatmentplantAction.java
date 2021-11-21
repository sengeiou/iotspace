package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etreatmentplant;
import com.iot.bean.Rmap;
import com.iot.service.EtreatmentplantService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treatmentplant")
public class EtreatmentplantAction {  
    @Resource  
    private EtreatmentplantService etreatmentplantService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etreatmentplant> select(String sql){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		List<Etreatmentplant> list = etreatmentplantService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etreatmentplant> selectAll(){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		List<Etreatmentplant> list = etreatmentplantService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etreatmentplant> list = etreatmentplantService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etreatmentplant> pageInfo = new PageInfo<Etreatmentplant>(list);
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etreatmentplant> selectBySql(String sql){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		List<Etreatmentplant> list = etreatmentplantService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etreatmentplant> selectByPrimaryKey(Integer id){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		Etreatmentplant object = etreatmentplantService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etreatmentplant> insert(@RequestBody Etreatmentplant record){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		if(etreatmentplantService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etreatmentplant> update(@RequestBody Etreatmentplant record){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		if(etreatmentplantService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplant> delete(Integer id){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		Etreatmentplant treatmentplant = etreatmentplantService.selectByPrimaryKey(id);
		if(etreatmentplantService.delete(id) >0)
			map.setObject(treatmentplant);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplant> deletes(String ids){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		List<Etreatmentplant> list = etreatmentplantService.selectBySql("id in "+ids);
		if(etreatmentplantService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/updateProvince", method = RequestMethod.PUT)
	public Rmap<Etreatmentplant> updateProvince(@RequestBody Etreatmentplant record){
		Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
		if(etreatmentplantService.updateProvince(record) > 0)
			map.setObject(record);
		return map;
	}
}