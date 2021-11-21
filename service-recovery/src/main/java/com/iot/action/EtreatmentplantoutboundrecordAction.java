package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.bean.Rmap;
import com.iot.service.EtreatmentplantoutboundrecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treatmentplantoutboundrecord")
public class EtreatmentplantoutboundrecordAction {
	@Resource
	private EtreatmentplantoutboundrecordService etreatmentplantoutboundrecordService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecord> select(String sql){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		List<Etreatmentplantoutboundrecord> list = etreatmentplantoutboundrecordService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecord> selectAll(){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		List<Etreatmentplantoutboundrecord> list = etreatmentplantoutboundrecordService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etreatmentplantoutboundrecord> list = etreatmentplantoutboundrecordService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etreatmentplantoutboundrecord> pageInfo = new PageInfo<Etreatmentplantoutboundrecord>(list);
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecord> selectBySql(String sql){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		List<Etreatmentplantoutboundrecord> list = etreatmentplantoutboundrecordService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecord> selectByPrimaryKey(Integer id){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		Etreatmentplantoutboundrecord etreatmentplantoutboundrecord = etreatmentplantoutboundrecordService.selectByPrimaryKey(id);
		if(null != etreatmentplantoutboundrecord)
			map.setObject(etreatmentplantoutboundrecord);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecordv> selectV(String sql){
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		List<Etreatmentplantoutboundrecordv> list = etreatmentplantoutboundrecordService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecordv> selectVAll(){
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		List<Etreatmentplantoutboundrecordv> list = etreatmentplantoutboundrecordService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etreatmentplantoutboundrecordv> list = etreatmentplantoutboundrecordService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etreatmentplantoutboundrecordv> pageInfo = new PageInfo<Etreatmentplantoutboundrecordv>(list);
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecordv> selectVBySql(String sql){
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		List<Etreatmentplantoutboundrecordv> list = etreatmentplantoutboundrecordService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etreatmentplantoutboundrecordv> selectVByPrimaryKey(Integer id){
		Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
		Etreatmentplantoutboundrecordv etreatmentplantoutboundrecordv = etreatmentplantoutboundrecordService.selectVByPrimaryKey(id);
		if(null != etreatmentplantoutboundrecordv)
			map.setObject(etreatmentplantoutboundrecordv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etreatmentplantoutboundrecord> insert(@RequestBody Etreatmentplantoutboundrecord record){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		if(etreatmentplantoutboundrecordService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etreatmentplantoutboundrecord> update(@RequestBody Etreatmentplantoutboundrecord record){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		if(etreatmentplantoutboundrecordService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplantoutboundrecord> delete(Integer id){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		Etreatmentplantoutboundrecord etreatmentplantoutboundrecord = etreatmentplantoutboundrecordService.selectByPrimaryKey(id);
		if(etreatmentplantoutboundrecordService.delete(id) > 0)
			map.setObject(etreatmentplantoutboundrecord);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etreatmentplantoutboundrecord> deletes(String ids){
		Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
		List<Etreatmentplantoutboundrecord> list = etreatmentplantoutboundrecordService.selectBySql("id in "+ids);
		if(etreatmentplantoutboundrecordService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}