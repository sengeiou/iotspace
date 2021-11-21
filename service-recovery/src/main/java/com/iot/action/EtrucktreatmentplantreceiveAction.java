package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;
import com.iot.bean.Rmap;
import com.iot.service.EtrucktreatmentplantreceiveService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trucktreatmentplantreceive")
public class EtrucktreatmentplantreceiveAction {
	@Resource
	private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceive> select(String sql){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		List<Etrucktreatmentplantreceive> list = etrucktreatmentplantreceiveService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceive> selectAll(){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		List<Etrucktreatmentplantreceive> list = etrucktreatmentplantreceiveService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceive> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktreatmentplantreceive> list = etrucktreatmentplantreceiveService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktreatmentplantreceive> pageInfo = new PageInfo<Etrucktreatmentplantreceive>(list);
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceive> selectBySql(String sql){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		List<Etrucktreatmentplantreceive> list = etrucktreatmentplantreceiveService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceive> selectByPrimaryKey(Integer id){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		Etrucktreatmentplantreceive etrucktreatmentplantreceive = etrucktreatmentplantreceiveService.selectByPrimaryKey(id);
		if(null != etrucktreatmentplantreceive)
			map.setObject(etrucktreatmentplantreceive);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceivev> selectV(String sql){
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		List<Etrucktreatmentplantreceivev> list = etrucktreatmentplantreceiveService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceivev>  selectVAll(){
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		List<Etrucktreatmentplantreceivev> list = etrucktreatmentplantreceiveService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceivev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktreatmentplantreceivev> list = etrucktreatmentplantreceiveService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktreatmentplantreceivev> pageInfo = new PageInfo<Etrucktreatmentplantreceivev>(list);
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceivev> selectVBySql(String sql){
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		List<Etrucktreatmentplantreceivev> list = etrucktreatmentplantreceiveService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktreatmentplantreceivev> selectVByPrimaryKey(Integer id){
		Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
		Etrucktreatmentplantreceivev etrucktreatmentplantreceivev = etrucktreatmentplantreceiveService.selectVByPrimaryKey(id);
		if(null != etrucktreatmentplantreceivev)
			map.setObject(etrucktreatmentplantreceivev);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etrucktreatmentplantreceive> insert(@RequestBody Etrucktreatmentplantreceive record){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		if(etrucktreatmentplantreceiveService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etrucktreatmentplantreceive> update(@RequestBody Etrucktreatmentplantreceive record){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		if(etrucktreatmentplantreceiveService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etrucktreatmentplantreceive> delete(Integer id){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		Etrucktreatmentplantreceive etrucktreatmentplantreceive = etrucktreatmentplantreceiveService.selectByPrimaryKey(id);
		if(etrucktreatmentplantreceiveService.delete(id) > 0)
			map.setObject(etrucktreatmentplantreceive);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etrucktreatmentplantreceive> deletes(String ids){
		Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
		List<Etrucktreatmentplantreceive> list = etrucktreatmentplantreceiveService.selectBySql("id in "+ids);
		if(etrucktreatmentplantreceiveService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/selectOutBoundVoByPage", method = RequestMethod.GET)
	public Rmap<EtreatmentplantOutBoundVo> selectOutBoundVoByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<EtreatmentplantOutBoundVo> list = etrucktreatmentplantreceiveService.selectOutBoundVoByPage(page, rows, sort, order, sql);
		PageInfo<EtreatmentplantOutBoundVo> pageInfo = new PageInfo<EtreatmentplantOutBoundVo>(list);
		Rmap<EtreatmentplantOutBoundVo> map = new Rmap<EtreatmentplantOutBoundVo>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}
}