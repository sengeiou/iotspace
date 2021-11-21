package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.bean.Rmap;
import com.iot.service.EgarbagebagrecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/garbagebagrecord")
public class EgarbagebagrecordAction {
	@Resource
	private EgarbagebagrecordService egarbagebagrecordService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecord> select(String sql){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		List<Egarbagebagrecord> list = egarbagebagrecordService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecord> selectAll(){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		List<Egarbagebagrecord> list = egarbagebagrecordService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebagrecord> list = egarbagebagrecordService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebagrecord> pageInfo = new PageInfo<Egarbagebagrecord>(list);
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecord> selectBySql(String sql){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		List<Egarbagebagrecord> list = egarbagebagrecordService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecord> selectByPrimaryKey(Integer id){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		Egarbagebagrecord egarbagebagrecord = egarbagebagrecordService.selectByPrimaryKey(id);
		if(null != egarbagebagrecord)
			map.setObject(egarbagebagrecord);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecordv> selectV(String sql){
		Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
		List<Egarbagebagrecordv> list = egarbagebagrecordService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecordv>  selectVAll(){
		Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
		List<Egarbagebagrecordv> list = egarbagebagrecordService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egarbagebagrecordv> list = egarbagebagrecordService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Egarbagebagrecordv> pageInfo = new PageInfo<Egarbagebagrecordv>(list);
		Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecordv> selectVBySql(String sql){
		Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
		List<Egarbagebagrecordv> list = egarbagebagrecordService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egarbagebagrecordv> selectVByPrimaryKey(Integer id){
		Rmap<Egarbagebagrecordv> map = new Rmap<Egarbagebagrecordv>();
		Egarbagebagrecordv egarbagebagrecordv = egarbagebagrecordService.selectVByPrimaryKey(id);
		if(null != egarbagebagrecordv)
			map.setObject(egarbagebagrecordv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Egarbagebagrecord> insert(@RequestBody Egarbagebagrecord record){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		if(egarbagebagrecordService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Egarbagebagrecord> update(@RequestBody Egarbagebagrecord record){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		if(egarbagebagrecordService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Egarbagebagrecord> delete(Integer id){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		Egarbagebagrecord egarbagebagrecord = egarbagebagrecordService.selectByPrimaryKey(id);
		if(egarbagebagrecordService.delete(id) > 0)
			map.setObject(egarbagebagrecord);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Egarbagebagrecord> deletes(String ids){
		Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
		List<Egarbagebagrecord> list = egarbagebagrecordService.selectBySql("id in "+ids);
		if(egarbagebagrecordService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}