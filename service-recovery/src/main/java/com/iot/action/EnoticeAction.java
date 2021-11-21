package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.bean.Rmap;
import com.iot.service.EnoticeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class EnoticeAction {
	@Resource
	private EnoticeService enoticeService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Enotice> select(String sql){
		Rmap<Enotice> map = new Rmap<Enotice>();
		List<Enotice> list = enoticeService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Enotice> selectAll(){
		Rmap<Enotice> map = new Rmap<Enotice>();
		List<Enotice> list = enoticeService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Enotice> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Enotice> list = enoticeService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Enotice> pageInfo = new PageInfo<Enotice>(list);
		Rmap<Enotice> map = new Rmap<Enotice>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Enotice> selectBySql(String sql){
		Rmap<Enotice> map = new Rmap<Enotice>();
		List<Enotice> list = enoticeService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Enotice> selectByPrimaryKey(Integer id){
		Rmap<Enotice> map = new Rmap<Enotice>();
		Enotice enotice = enoticeService.selectByPrimaryKey(id);
		if(null != enotice)
			map.setObject(enotice);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Enotice> insert(@RequestBody Enotice record){
		Rmap<Enotice> map = new Rmap<Enotice>();
		if(enoticeService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Enotice> update(@RequestBody Enotice record){
		Rmap<Enotice> map = new Rmap<Enotice>();
		if(enoticeService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Enotice> delete(Integer id){
		Rmap<Enotice> map = new Rmap<Enotice>();
		Enotice enotice = enoticeService.selectByPrimaryKey(id);
		if(enoticeService.delete(id) > 0)
			map.setObject(enotice);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Enotice> deletes(String ids){
		Rmap<Enotice> map = new Rmap<Enotice>();
		List<Enotice> list = enoticeService.selectBySql("id in "+ids);
		if(enoticeService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Enoticev> selectV(String sql){
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		List<Enoticev> list = enoticeService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Enoticev>  selectVAll(){
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		List<Enoticev> list = enoticeService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Enoticev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Enoticev> list = enoticeService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Enoticev> pageInfo = new PageInfo<Enoticev>(list);
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Enoticev> selectVBySql(String sql){
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		List<Enoticev> list = enoticeService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Enoticev> selectVByPrimaryKey(Integer id){
		Rmap<Enoticev> map = new Rmap<Enoticev>();
		Enoticev etruckhospitalv = enoticeService.selectVByPrimaryKey(id);
		if(null != etruckhospitalv)
			map.setObject(etruckhospitalv);
		return map;
	}

}