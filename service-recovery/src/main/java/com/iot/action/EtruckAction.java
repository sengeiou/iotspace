package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;
import com.iot.bean.Rmap;
import com.iot.service.EtruckService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/truck")
public class EtruckAction {  
    @Resource  
    private EtruckService etruckService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etruck> select(String sql){
		Rmap<Etruck> map = new Rmap<Etruck>();
		List<Etruck> list = etruckService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etruck> selectAll(){
		Rmap<Etruck> map = new Rmap<Etruck>();
		List<Etruck> list = etruckService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etruck> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruck> list = etruckService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etruck> pageInfo = new PageInfo<Etruck>(list);
		Rmap<Etruck> map = new Rmap<Etruck>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etruck> selectBySql(String sql){
		Rmap<Etruck> map = new Rmap<Etruck>();
		List<Etruck> list = etruckService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruck> selectByPrimaryKey(Integer id){
		Rmap<Etruck> map = new Rmap<Etruck>();
		Etruck object = etruckService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etruckv> selectV(String sql){
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		List<Etruckv> list = etruckService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etruckv>  selectVAll(){
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		List<Etruckv> list = etruckService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etruckv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckv> list = etruckService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etruckv> pageInfo = new PageInfo<Etruckv>(list);
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etruckv> selectVBySql(String sql){
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		List<Etruckv> list = etruckService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruckv> selectVByPrimaryKey(Integer id){
		Rmap<Etruckv> map = new Rmap<Etruckv>();
		Etruckv einfusionbagv = etruckService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etruck> insert(@RequestBody Etruck record){
		Rmap<Etruck> map = new Rmap<Etruck>();
		if(etruckService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etruck> update(@RequestBody Etruck record){
		Rmap<Etruck> map = new Rmap<Etruck>();
		if(etruckService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etruck> delete(Integer id){
		Rmap<Etruck> map = new Rmap<Etruck>();
		Etruck truck = etruckService.selectByPrimaryKey(id);
		if(etruckService.delete(id) >0)
			map.setObject(truck);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etruck> deletes(String ids){
		Rmap<Etruck> map = new Rmap<Etruck>();
		List<Etruck> list = etruckService.selectBySql("id in "+ids);
		if(etruckService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}