package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.bean.Rmap;
import com.iot.service.EtreatmentplantproductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treatmentplantproduct")
public class EtreatmentplantproductAction {
	@Resource
	private EtreatmentplantproductService etreatmentplantproductService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproduct> select(String sql){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		List<Etreatmentplantproduct> list = etreatmentplantproductService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproduct> selectAll(){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		List<Etreatmentplantproduct> list = etreatmentplantproductService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproduct> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etreatmentplantproduct> list = etreatmentplantproductService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etreatmentplantproduct> pageInfo = new PageInfo<Etreatmentplantproduct>(list);
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproduct> selectBySql(String sql){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		List<Etreatmentplantproduct> list = etreatmentplantproductService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproduct> selectByPrimaryKey(Integer id){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		Etreatmentplantproduct etreatmentplantproduct = etreatmentplantproductService.selectByPrimaryKey(id);
		if(null != etreatmentplantproduct)
			map.setObject(etreatmentplantproduct);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproductv> selectV(String sql){
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		List<Etreatmentplantproductv> list = etreatmentplantproductService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproductv> selectVAll(){
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		List<Etreatmentplantproductv> list = etreatmentplantproductService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproductv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etreatmentplantproductv> list = etreatmentplantproductService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etreatmentplantproductv> pageInfo = new PageInfo<Etreatmentplantproductv>(list);
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproductv> selectVBySql(String sql){
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		List<Etreatmentplantproductv> list = etreatmentplantproductService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etreatmentplantproductv> selectVByPrimaryKey(Integer id){
		Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
		Etreatmentplantproductv etreatmentplantproductv = etreatmentplantproductService.selectVByPrimaryKey(id);
		if(null != etreatmentplantproductv)
			map.setObject(etreatmentplantproductv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etreatmentplantproduct> insert(@RequestBody Etreatmentplantproduct record){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		if(etreatmentplantproductService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etreatmentplantproduct> update(@RequestBody Etreatmentplantproduct record){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		if(etreatmentplantproductService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplantproduct> delete(Integer id){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		Etreatmentplantproduct etreatmentplantproduct = etreatmentplantproductService.selectByPrimaryKey(id);
		if(etreatmentplantproductService.delete(id) > 0)
			map.setObject(etreatmentplantproduct);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplantproduct> deletes(String ids){
		Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
		List<Etreatmentplantproduct> list = etreatmentplantproductService.selectBySql("id in "+ids);
		if(etreatmentplantproductService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/sumWeightGroupByCategory", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> sumWeightGroupByCategory(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etreatmentplantproductService.sumWeightGroupByCategory(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}
}