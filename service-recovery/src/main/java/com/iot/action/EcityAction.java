package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.bean.Rmap;
import com.iot.service.EcityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
public class EcityAction {
	@Resource
	private EcityService ecityService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ecity> select(String sql){
		Rmap<Ecity> map = new Rmap<Ecity>();
		List<Ecity> list = ecityService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ecity> selectAll(){
		Rmap<Ecity> map = new Rmap<Ecity>();
		List<Ecity> list = ecityService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ecity> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecity> list = ecityService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ecity> pageInfo = new PageInfo<Ecity>(list);
		Rmap<Ecity> map = new Rmap<Ecity>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ecity> selectBySql(String sql){
		Rmap<Ecity> map = new Rmap<Ecity>();
		List<Ecity> list = ecityService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecity> selectByPrimaryKey(Integer id){
		Rmap<Ecity> map = new Rmap<Ecity>();
		Ecity ecity = ecityService.selectByPrimaryKey(id);
		if(null != ecity)
			map.setObject(ecity);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ecityv> selectV(String sql){
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		List<Ecityv> list = ecityService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ecityv> selectVAll(){
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		List<Ecityv> list = ecityService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ecityv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecityv> list = ecityService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ecityv> pageInfo = new PageInfo<Ecityv>(list);
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ecityv> selectVBySql(String sql){
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		List<Ecityv> list = ecityService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecityv> selectVByPrimaryKey(Integer id){
		Rmap<Ecityv> map = new Rmap<Ecityv>();
		Ecityv ecityv = ecityService.selectVByPrimaryKey(id);
		if(null != ecityv)
			map.setObject(ecityv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ecity> insert(@RequestBody Ecity record){
		Rmap<Ecity> map = new Rmap<Ecity>();
		if(ecityService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ecity> update(@RequestBody Ecity record){
		Rmap<Ecity> map = new Rmap<Ecity>();
		if(ecityService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ecity> delete(Integer id){
		Rmap<Ecity> map = new Rmap<Ecity>();
		Ecity ecity = ecityService.selectByPrimaryKey(id);
		if(ecityService.delete(id) > 0)
			map.setObject(ecity);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ecity> deletes(String ids){
		Rmap<Ecity> map = new Rmap<Ecity>();
		List<Ecity> list = ecityService.selectBySql("id in "+ids);
		if(ecityService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}