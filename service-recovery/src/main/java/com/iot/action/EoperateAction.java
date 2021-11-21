package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Eoperate;
import com.iot.bean.Rmap;
import com.iot.service.EoperateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operate")
public class EoperateAction {
	@Resource
	private EoperateService eoperateService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Eoperate> select(String sql){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		List<Eoperate> list = eoperateService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Eoperate> selectAll(){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		List<Eoperate> list = eoperateService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Eoperate> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Eoperate> list = eoperateService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Eoperate> pageInfo = new PageInfo<Eoperate>(list);
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Eoperate> selectBySql(String sql){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		List<Eoperate> list = eoperateService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Eoperate> selectByPrimaryKey(Integer id){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		Eoperate eoperate = eoperateService.selectByPrimaryKey(id);
		if(null != eoperate)
			map.setObject(eoperate);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Eoperate> insert(@RequestBody Eoperate record){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		if(eoperateService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Eoperate> update(@RequestBody Eoperate record){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		if(eoperateService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Eoperate> delete(Integer id){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		Eoperate eoperate = eoperateService.selectByPrimaryKey(id);
		if(eoperateService.delete(id) > 0)
			map.setObject(eoperate);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Eoperate> deletes(String ids){
		Rmap<Eoperate> map = new Rmap<Eoperate>();
		List<Eoperate> list = eoperateService.selectBySql("id in "+ids);
		if(eoperateService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
}