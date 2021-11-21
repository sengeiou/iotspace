package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.bean.Rmap;
import com.iot.service.EinfusionbagService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/infusionbag")
public class EinfusionbagAction {
	@Resource
	private EinfusionbagService einfusionbagService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Einfusionbag> select(String sql){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		List<Einfusionbag> list = einfusionbagService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Einfusionbag> selectAll(){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		List<Einfusionbag> list = einfusionbagService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Einfusionbag> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Einfusionbag> list = einfusionbagService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Einfusionbag> pageInfo = new PageInfo<Einfusionbag>(list);
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Einfusionbag> selectBySql(String sql){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		List<Einfusionbag> list = einfusionbagService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Einfusionbag> selectByPrimaryKey(Integer id){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		Einfusionbag einfusionbag = einfusionbagService.selectByPrimaryKey(id);
		if(null != einfusionbag)
			map.setObject(einfusionbag);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Einfusionbagv> selectV(String sql){
		Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
		List<Einfusionbagv> list = einfusionbagService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Einfusionbagv>  selectVAll(){
		Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
		List<Einfusionbagv> list = einfusionbagService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Einfusionbagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Einfusionbagv> list = einfusionbagService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Einfusionbagv> pageInfo = new PageInfo<Einfusionbagv>(list);
		Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Einfusionbagv> selectVBySql(String sql){
		Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
		List<Einfusionbagv> list = einfusionbagService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Einfusionbagv> selectVByPrimaryKey(Integer id){
		Rmap<Einfusionbagv> map = new Rmap<Einfusionbagv>();
		Einfusionbagv einfusionbagv = einfusionbagService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Einfusionbag> insert(@RequestBody Einfusionbag record){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		if(einfusionbagService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Einfusionbag> update(@RequestBody Einfusionbag record){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		if(einfusionbagService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Einfusionbag> delete(Integer id){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		Einfusionbag einfusionbag = einfusionbagService.selectByPrimaryKey(id);
		if(einfusionbagService.delete(id) > 0)
			map.setObject(einfusionbag);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Einfusionbag> deletes(String ids){
		Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
		List<Einfusionbag> list = einfusionbagService.selectBySql("id in "+ids);
		if(einfusionbagService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}