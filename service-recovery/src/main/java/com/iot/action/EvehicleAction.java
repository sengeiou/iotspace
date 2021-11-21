package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.bean.Rmap;
import com.iot.service.EvehicleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
public class EvehicleAction {
	@Resource
	private EvehicleService evehicleService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Evehicle> select(String sql){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		List<Evehicle> list = evehicleService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Evehicle> selectAll(){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		List<Evehicle> list = evehicleService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Evehicle> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Evehicle> list = evehicleService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Evehicle> pageInfo = new PageInfo<Evehicle>(list);
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Evehicle> selectBySql(String sql){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		List<Evehicle> list = evehicleService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Evehicle> selectByPrimaryKey(Integer id){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		Evehicle evehicle = evehicleService.selectByPrimaryKey(id);
		if(null != evehicle)
			map.setObject(evehicle);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Evehiclev> selectV(String sql){
		Rmap<Evehiclev> map = new Rmap<Evehiclev>();
		List<Evehiclev> list = evehicleService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Evehiclev>  selectVAll(){
		Rmap<Evehiclev> map = new Rmap<Evehiclev>();
		List<Evehiclev> list = evehicleService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Evehiclev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Evehiclev> list = evehicleService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Evehiclev> pageInfo = new PageInfo<Evehiclev>(list);
		Rmap<Evehiclev> map = new Rmap<Evehiclev>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Evehiclev> selectVBySql(String sql){
		Rmap<Evehiclev> map = new Rmap<Evehiclev>();
		List<Evehiclev> list = evehicleService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Evehiclev> selectVByPrimaryKey(Integer id){
		Rmap<Evehiclev> map = new Rmap<Evehiclev>();
		Evehiclev evehiclev = evehicleService.selectVByPrimaryKey(id);
		if(null != evehiclev)
			map.setObject(evehiclev);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Evehicle> insert(@RequestBody Evehicle record){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		if(evehicleService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Evehicle> update(@RequestBody Evehicle record){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		if(evehicleService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Evehicle> delete(Integer id){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		Evehicle evehicle = evehicleService.selectByPrimaryKey(id);
		if(evehicleService.delete(id) > 0)
			map.setObject(evehicle);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Evehicle> deletes(String ids){
		Rmap<Evehicle> map = new Rmap<Evehicle>();
		List<Evehicle> list = evehicleService.selectBySql("id in "+ids);
		if(evehicleService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/vehicleNumBySql", method = RequestMethod.GET)
	public Rmap<Integer> vehicleNumBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(evehicleService.vehicleNumBySql(sql));
		return map;
	}

}