package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Euser;
import com.iot.bean.Rmap;
import com.iot.service.EuserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class EuserAction {  
    @Resource  
    private EuserService euserService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Euser> select(String sql){
		Rmap<Euser> map = new Rmap<Euser>();
		List<Euser> list = euserService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Euser> selectAll(){
		Rmap<Euser> map = new Rmap<Euser>();
		List<Euser> list = euserService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Euser> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Euser> list = euserService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Euser> pageInfo = new PageInfo<Euser>(list);
		Rmap<Euser> map = new Rmap<Euser>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Euser> selectBySql(String sql){
		Rmap<Euser> map = new Rmap<Euser>();
		List<Euser> list = euserService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Euser> selectByPrimaryKey(Integer id){
		Rmap<Euser> map = new Rmap<Euser>();
		Euser object = euserService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Euser> insert(@RequestBody Euser record){
		Rmap<Euser> map = new Rmap<Euser>();
		if(euserService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Euser> update(@RequestBody Euser record){
		Rmap<Euser> map = new Rmap<Euser>();
		if(euserService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Euser> delete(Integer id){
		Rmap<Euser> map = new Rmap<Euser>();
		Euser user = euserService.selectByPrimaryKey(id);
		if(euserService.delete(id) >0)
			map.setObject(user);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Euser> deletes(String ids){
		Rmap<Euser> map = new Rmap<Euser>();
		List<Euser> list = euserService.selectBySql("id in "+ids);
		if(euserService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}