package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Eproductionplant;
import com.iot.bean.Rmap;
import com.iot.service.EproductionplantService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productionplant")
public class EproductionplantAction {  
    @Resource  
    private EproductionplantService eproductionplantService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Eproductionplant> select(String sql){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		List<Eproductionplant> list = eproductionplantService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Eproductionplant> selectAll(){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		List<Eproductionplant> list = eproductionplantService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Eproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Eproductionplant> list = eproductionplantService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Eproductionplant> pageInfo = new PageInfo<Eproductionplant>(list);
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Eproductionplant> selectBySql(String sql){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		List<Eproductionplant> list = eproductionplantService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Eproductionplant> selectByPrimaryKey(Integer id){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		Eproductionplant object = eproductionplantService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Eproductionplant> insert(@RequestBody Eproductionplant record){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		if(eproductionplantService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Eproductionplant> update(@RequestBody Eproductionplant record){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		if(eproductionplantService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Eproductionplant> delete(Integer id){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		Eproductionplant productionplant = eproductionplantService.selectByPrimaryKey(id);
		if(eproductionplantService.delete(id) >0)
			map.setObject(productionplant);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Eproductionplant> deletes(String ids){
		Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
		List<Eproductionplant> list = eproductionplantService.selectBySql("id in "+ids);
		if(eproductionplantService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}