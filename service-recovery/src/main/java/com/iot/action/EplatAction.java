package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.bean.Rmap;
import com.iot.service.EplatService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plat")
public class EplatAction {
	@Resource
	private EplatService eplatService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Eplat> select(String sql){
		Rmap<Eplat> map = new Rmap<Eplat>();
		List<Eplat> list = eplatService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Eplat> selectAll(){
		Rmap<Eplat> map = new Rmap<Eplat>();
		List<Eplat> list = eplatService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Eplat> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Eplat> list = eplatService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Eplat> pageInfo = new PageInfo<Eplat>(list);
		Rmap<Eplat> map = new Rmap<Eplat>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Eplat> selectBySql(String sql){
		Rmap<Eplat> map = new Rmap<Eplat>();
		List<Eplat> list = eplatService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Eplat> selectByPrimaryKey(Integer id){
		Rmap<Eplat> map = new Rmap<Eplat>();
		Eplat eplat = eplatService.selectByPrimaryKey(id);
		if(null != eplat)
			map.setObject(eplat);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Eplatv> selectV(String sql){
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		List<Eplatv> list = eplatService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Eplatv> selectVAll(){
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		List<Eplatv> list = eplatService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Eplatv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Eplatv> list = eplatService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Eplatv> pageInfo = new PageInfo<Eplatv>(list);
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Eplatv> selectVBySql(String sql){
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		List<Eplatv> list = eplatService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Eplatv> selectVByPrimaryKey(Integer id){
		Rmap<Eplatv> map = new Rmap<Eplatv>();
		Eplatv object = eplatService.selectVByPrimaryKey(id);
		if(null != object)
			map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Eplat> insert(@RequestBody Eplat record){
		Rmap<Eplat> map = new Rmap<Eplat>();
		if(eplatService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Eplat> update(@RequestBody Eplat record){
		Rmap<Eplat> map = new Rmap<Eplat>();
		if(eplatService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Eplat> delete(Integer id){
		Rmap<Eplat> map = new Rmap<Eplat>();
		Eplat eplat = eplatService.selectByPrimaryKey(id);
		if(eplatService.delete(id) > 0)
			map.setObject(eplat);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Eplat> deletes(String ids){
		Rmap<Eplat> map = new Rmap<Eplat>();
		List<Eplat> list = eplatService.selectBySql("id in "+ids);
		if(eplatService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}