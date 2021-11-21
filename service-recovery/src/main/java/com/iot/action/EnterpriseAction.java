package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Enterprise;
import com.iot.bean.Rmap;
import com.iot.service.EnterpriseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nterprise")
public class EnterpriseAction {  
    @Resource  
    private EnterpriseService enterpriseService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Enterprise> select(String sql){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		List<Enterprise> list = enterpriseService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Enterprise> selectAll(){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		List<Enterprise> list = enterpriseService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Enterprise> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Enterprise> list = enterpriseService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>(list);
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Enterprise> selectBySql(String sql){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		List<Enterprise> list = enterpriseService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Enterprise> selectByPrimaryKey(Integer id){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		Enterprise object = enterpriseService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Enterprise> insert(@RequestBody Enterprise record){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		if(enterpriseService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Enterprise> update(@RequestBody Enterprise record){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		if(enterpriseService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Enterprise> delete(Integer id){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		Enterprise nterprise = enterpriseService.selectByPrimaryKey(id);
		if(enterpriseService.delete(id) >0)
			map.setObject(nterprise);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Enterprise> deletes(String ids){
		Rmap<Enterprise> map = new Rmap<Enterprise>();
		List<Enterprise> list = enterpriseService.selectBySql("id in "+ids);
		if(enterpriseService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}