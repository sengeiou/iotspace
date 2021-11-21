package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;
import com.iot.bean.Rmap;
import com.iot.service.EsupervisionunitService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supervisionunit")
public class EsupervisionunitAction {
	@Resource
	private EsupervisionunitService esupervisionunitService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Esupervisionunit> select(String sql){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		List<Esupervisionunit> list = esupervisionunitService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Esupervisionunit> selectAll(){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		List<Esupervisionunit> list = esupervisionunitService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Esupervisionunit> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Esupervisionunit> list = esupervisionunitService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Esupervisionunit> pageInfo = new PageInfo<Esupervisionunit>(list);
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Esupervisionunit> selectBySql(String sql){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		List<Esupervisionunit> list = esupervisionunitService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Esupervisionunit> selectByPrimaryKey(Integer id){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		Esupervisionunit esupervisionunit = esupervisionunitService.selectByPrimaryKey(id);
		if(null != esupervisionunit)
			map.setObject(esupervisionunit);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Esupervisionunitv> selectV(String sql){
		Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
		List<Esupervisionunitv> list = esupervisionunitService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Esupervisionunitv>  selectVAll(){
		Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
		List<Esupervisionunitv> list = esupervisionunitService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Esupervisionunitv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Esupervisionunitv> list = esupervisionunitService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Esupervisionunitv> pageInfo = new PageInfo<Esupervisionunitv>(list);
		Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Esupervisionunitv> selectVBySql(String sql){
		Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
		List<Esupervisionunitv> list = esupervisionunitService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Esupervisionunitv> selectVByPrimaryKey(Integer id){
		Rmap<Esupervisionunitv> map = new Rmap<Esupervisionunitv>();
		Esupervisionunitv esupervisionunitv = esupervisionunitService.selectVByPrimaryKey(id);
		if(null != esupervisionunitv)
			map.setObject(esupervisionunitv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Esupervisionunit> insert(@RequestBody Esupervisionunit record){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		if(esupervisionunitService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Esupervisionunit> update(@RequestBody Esupervisionunit record){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		if(esupervisionunitService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Esupervisionunit> delete(Integer id){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		Esupervisionunit esupervisionunit = esupervisionunitService.selectByPrimaryKey(id);
		if(esupervisionunitService.delete(id) > 0)
			map.setObject(esupervisionunit);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Esupervisionunit> deletes(String ids){
		Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
		List<Esupervisionunit> list = esupervisionunitService.selectBySql("id in "+ids);
		if(esupervisionunitService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}