package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etradeuser;
import com.iot.bean.Rmap;
import com.iot.service.EtradeuserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tradeuser")
public class EtradeuserAction {  
    @Resource  
    private EtradeuserService etradeuserService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etradeuser> select(String sql){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		List<Etradeuser> list = etradeuserService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etradeuser> selectAll(){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		List<Etradeuser> list = etradeuserService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etradeuser> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etradeuser> list = etradeuserService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etradeuser> pageInfo = new PageInfo<Etradeuser>(list);
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etradeuser> selectBySql(String sql){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		List<Etradeuser> list = etradeuserService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etradeuser> selectByPrimaryKey(Integer id){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		Etradeuser object = etradeuserService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etradeuser> insert(@RequestBody Etradeuser record){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		if(etradeuserService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etradeuser> update(@RequestBody Etradeuser record){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		if(etradeuserService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etradeuser> delete(Integer id){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		Etradeuser tradeuser = etradeuserService.selectByPrimaryKey(id);
		if(etradeuserService.delete(id) >0)
			map.setObject(tradeuser);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etradeuser> deletes(String ids){
		Rmap<Etradeuser> map = new Rmap<Etradeuser>();
		List<Etradeuser> list = etradeuserService.selectBySql("id in "+ids);
		if(etradeuserService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}