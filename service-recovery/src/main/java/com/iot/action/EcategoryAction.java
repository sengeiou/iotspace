package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.bean.Rmap;
import com.iot.service.EcategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class EcategoryAction {
	@Resource
	private EcategoryService ecategoryService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ecategory> select(String sql){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		List<Ecategory> list = ecategoryService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ecategory> selectAll(){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		List<Ecategory> list = ecategoryService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ecategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecategory> list = ecategoryService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ecategory> pageInfo = new PageInfo<Ecategory>(list);
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ecategory> selectBySql(String sql){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		List<Ecategory> list = ecategoryService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecategory> selectByPrimaryKey(Integer id){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		Ecategory ecategory = ecategoryService.selectByPrimaryKey(id);
		if(null != ecategory)
			map.setObject(ecategory);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ecategoryv> selectV(String sql){
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		List<Ecategoryv> list = ecategoryService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ecategoryv> selectVAll(){
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		List<Ecategoryv> list = ecategoryService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ecategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecategoryv> list = ecategoryService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ecategoryv> pageInfo = new PageInfo<Ecategoryv>(list);
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ecategoryv> selectVBySql(String sql){
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		List<Ecategoryv> list = ecategoryService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecategoryv> selectVByPrimaryKey(Integer id){
		Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
		Ecategoryv ecategoryv = ecategoryService.selectVByPrimaryKey(id);
		if(null != ecategoryv)
			map.setObject(ecategoryv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ecategory> insert(@RequestBody Ecategory record){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		if(ecategoryService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ecategory> update(@RequestBody Ecategory record){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		if(ecategoryService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ecategory> delete(Integer id){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		Ecategory ecategory = ecategoryService.selectByPrimaryKey(id);
		if(ecategoryService.delete(id) > 0)
			map.setObject(ecategory);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ecategory> deletes(String ids){
		Rmap<Ecategory> map = new Rmap<Ecategory>();
		List<Ecategory> list = ecategoryService.selectBySql("id in "+ids);
		if(ecategoryService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}