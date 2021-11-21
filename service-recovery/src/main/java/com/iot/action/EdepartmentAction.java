package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.bean.Rmap;
import com.iot.service.EdepartmentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class EdepartmentAction {
	@Resource
	private EdepartmentService edepartmentService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Edepartment> select(String sql){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		List<Edepartment> list = edepartmentService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Edepartment> selectAll(){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		List<Edepartment> list = edepartmentService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Edepartment> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Edepartment> list = edepartmentService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Edepartment> pageInfo = new PageInfo<Edepartment>(list);
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Edepartment> selectBySql(String sql){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		List<Edepartment> list = edepartmentService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Edepartment> selectByPrimaryKey(Integer id){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		Edepartment edepartment = edepartmentService.selectByPrimaryKey(id);
		if(null != edepartment)
			map.setObject(edepartment);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Edepartmentv> selectV(String sql){
		Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
		List<Edepartmentv> list = edepartmentService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Edepartmentv>  selectVAll(){
		Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
		List<Edepartmentv> list = edepartmentService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Edepartmentv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Edepartmentv> list = edepartmentService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Edepartmentv> pageInfo = new PageInfo<Edepartmentv>(list);
		Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Edepartmentv> selectVBySql(String sql){
		Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
		List<Edepartmentv> list = edepartmentService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Edepartmentv> selectVByPrimaryKey(Integer id){
		Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
		Edepartmentv edepartmentv = edepartmentService.selectVByPrimaryKey(id);
		if(null != edepartmentv)
			map.setObject(edepartmentv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Edepartment> insert(@RequestBody Edepartment record){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		if(edepartmentService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Edepartment> update(@RequestBody Edepartment record){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		if(edepartmentService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Edepartment> delete(Integer id){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		Edepartment edepartment = edepartmentService.selectByPrimaryKey(id);
		if(edepartmentService.delete(id) > 0)
			map.setObject(edepartment);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Edepartment> deletes(String ids){
		Rmap<Edepartment> map = new Rmap<Edepartment>();
		List<Edepartment> list = edepartmentService.selectBySql("id in "+ids);
		if(edepartmentService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/countByhospitalid", method = RequestMethod.GET)
	public Rmap<Integer> countByhospitalid(Integer hospitalid){
		Rmap<Integer> map = new Rmap<Integer>();
		Integer count = edepartmentService.countByhospitalid(hospitalid);
		map.setObject(count);
		return map;
	}

}