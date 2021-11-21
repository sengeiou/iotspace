package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;
import com.iot.bean.Rmap;
import com.iot.service.EtrucktransportcompanyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trucktransportcompany")
public class EtrucktransportcompanyAction {
	@Resource
	private EtrucktransportcompanyService etrucktransportcompanyService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompany> select(String sql){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		List<Etrucktransportcompany> list = etrucktransportcompanyService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompany> selectAll(){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		List<Etrucktransportcompany> list = etrucktransportcompanyService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktransportcompany> list = etrucktransportcompanyService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktransportcompany> pageInfo = new PageInfo<Etrucktransportcompany>(list);
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompany> selectBySql(String sql){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		List<Etrucktransportcompany> list = etrucktransportcompanyService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompany> selectByPrimaryKey(Integer id){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		Etrucktransportcompany etrucktransportcompany = etrucktransportcompanyService.selectByPrimaryKey(id);
		if(null != etrucktransportcompany)
			map.setObject(etrucktransportcompany);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv> selectV(String sql){
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		List<Etrucktransportcompanyv> list = etrucktransportcompanyService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv>  selectVAll(){
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		List<Etrucktransportcompanyv> list = etrucktransportcompanyService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktransportcompanyv> list = etrucktransportcompanyService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etrucktransportcompanyv> pageInfo = new PageInfo<Etrucktransportcompanyv>(list);
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv> selectVBySql(String sql){
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		List<Etrucktransportcompanyv> list = etrucktransportcompanyService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv> selectVByPrimaryKey(Integer id){
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		Etrucktransportcompanyv etrucktransportcompanyv = etrucktransportcompanyService.selectVByPrimaryKey(id);
		if(null != etrucktransportcompanyv)
			map.setObject(etrucktransportcompanyv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etrucktransportcompany> insert(@RequestBody Etrucktransportcompany record){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		if(etrucktransportcompanyService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etrucktransportcompany> update(@RequestBody Etrucktransportcompany record){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		if(etrucktransportcompanyService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etrucktransportcompany> delete(Integer id){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		Etrucktransportcompany etrucktransportcompany = etrucktransportcompanyService.selectByPrimaryKey(id);
		if(etrucktransportcompanyService.delete(id) > 0)
			map.setObject(etrucktransportcompany);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etrucktransportcompany> deletes(String ids){
		Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
		List<Etrucktransportcompany> list = etrucktransportcompanyService.selectBySql("id in "+ids);
		if(etrucktransportcompanyService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/selectVByStatusAndSqlPage", method = RequestMethod.GET)
	public Rmap<Etrucktransportcompanyv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etrucktransportcompanyv> list = etrucktransportcompanyService.selectVByStatusAndSqlPage(page, rows, sort, order, sql);
		PageInfo<Etrucktransportcompanyv> pageInfo = new PageInfo<Etrucktransportcompanyv>(list);
		Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}
}