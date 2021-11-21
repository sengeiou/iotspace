package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.bean.Rmap;
import com.iot.service.EtruckproductionplantService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/truckproductionplant")
public class EtruckproductionplantAction {
	@Resource
	private EtruckproductionplantService etruckproductionplantService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etruckproductionplant> select(String sql){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		List<Etruckproductionplant> list = etruckproductionplantService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etruckproductionplant> selectAll(){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		List<Etruckproductionplant> list = etruckproductionplantService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etruckproductionplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckproductionplant> list = etruckproductionplantService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etruckproductionplant> pageInfo = new PageInfo<Etruckproductionplant>(list);
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etruckproductionplant> selectBySql(String sql){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		List<Etruckproductionplant> list = etruckproductionplantService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruckproductionplant> selectByPrimaryKey(Integer id){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		Etruckproductionplant etreatmentproductionplant = etruckproductionplantService.selectByPrimaryKey(id);
		if(null != etreatmentproductionplant)
			map.setObject(etreatmentproductionplant);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv> selectV(String sql){
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		List<Etruckproductionplantv> list = etruckproductionplantService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv>  selectVAll(){
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		List<Etruckproductionplantv> list = etruckproductionplantService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckproductionplantv> list = etruckproductionplantService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etruckproductionplantv> pageInfo = new PageInfo<Etruckproductionplantv>(list);
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv> selectVBySql(String sql){
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		List<Etruckproductionplantv> list = etruckproductionplantService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv> selectVByPrimaryKey(Integer id){
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		Etruckproductionplantv etreatmentproductionplantv = etruckproductionplantService.selectVByPrimaryKey(id);
		if(null != etreatmentproductionplantv)
			map.setObject(etreatmentproductionplantv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etruckproductionplant> insert(@RequestBody Etruckproductionplant record){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		if(etruckproductionplantService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etruckproductionplant> update(@RequestBody Etruckproductionplant record){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		if(etruckproductionplantService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etruckproductionplant> delete(Integer id){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		Etruckproductionplant etreatmentproductionplant = etruckproductionplantService.selectByPrimaryKey(id);
		if(etruckproductionplantService.delete(id) > 0)
			map.setObject(etreatmentproductionplant);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etruckproductionplant> deletes(String ids){
		Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
		List<Etruckproductionplant> list = etruckproductionplantService.selectBySql("id in "+ids);
		if(etruckproductionplantService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/selectVByStatusAndSqlPage", method = RequestMethod.GET)
	public Rmap<Etruckproductionplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckproductionplantv> list = etruckproductionplantService.selectVByStatusAndSqlPage(page, rows, sort, order, sql);
		PageInfo<Etruckproductionplantv> pageInfo = new PageInfo<Etruckproductionplantv>(list);
		Rmap<Etruckproductionplantv> map = new Rmap<Etruckproductionplantv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

}