package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.bean.Rmap;
import com.iot.service.EgarbagebagService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/garbagebag")
public class EgarbagebagAction {
	@Resource
	private EgarbagebagService egarbagebagService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Egarbagebag> select(String sql){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		List<Egarbagebag> list = egarbagebagService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Egarbagebag> selectAll(){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		List<Egarbagebag> list = egarbagebagService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Egarbagebag> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebag> list = egarbagebagService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebag> pageInfo = new PageInfo<Egarbagebag>(list);
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectByHospitalAndPage", method = RequestMethod.GET)
	public Rmap<Egarbagebag> selectByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebag> list = egarbagebagService.selectByHospitalAndPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebag> pageInfo = new PageInfo<Egarbagebag>(list);
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVByHospitalAndPage", method = RequestMethod.GET)
	public Rmap<Egarbagebagv> selectVByHospitalAndPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebagv> list = egarbagebagService.selectVByHospitalAndPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebagv> pageInfo = new PageInfo<Egarbagebagv>(list);
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Egarbagebag> selectBySql(String sql){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		List<Egarbagebag> list = egarbagebagService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egarbagebag> selectByPrimaryKey(Integer id){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		Egarbagebag egarbagebag = egarbagebagService.selectByPrimaryKey(id);
		if(null != egarbagebag)
			map.setObject(egarbagebag);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Egarbagebagv> selectV(String sql){
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		List<Egarbagebagv> list = egarbagebagService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Egarbagebagv>  selectVAll(){
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		List<Egarbagebagv> list = egarbagebagService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Egarbagebagv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebagv> list = egarbagebagService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebagv> pageInfo = new PageInfo<Egarbagebagv>(list);
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Egarbagebagv> selectVBySql(String sql){
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		List<Egarbagebagv> list = egarbagebagService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egarbagebagv> selectVByPrimaryKey(Integer id){
		Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
		Egarbagebagv egarbagebagv = egarbagebagService.selectVByPrimaryKey(id);
		if(null != egarbagebagv)
			map.setObject(egarbagebagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Egarbagebag> insert(@RequestBody Egarbagebag record){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		if(egarbagebagService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Egarbagebag> update(@RequestBody Egarbagebag record){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		if(egarbagebagService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Egarbagebag> delete(Integer id){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		Egarbagebag egarbagebag = egarbagebagService.selectByPrimaryKey(id);
		if(egarbagebagService.delete(id) > 0)
			map.setObject(egarbagebag);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Egarbagebag> deletes(String ids){
		Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
		List<Egarbagebag> list = egarbagebagService.selectBySql("id in "+ids);
		if(egarbagebagService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/countGroupByDepartment", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> countGroupByDepartment(Integer hospitalid){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egarbagebagService.countGroupByDepartment(hospitalid);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}
}