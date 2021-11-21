package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Egmaterialin;
import com.iot.bean.Egmaterialinv;
import com.iot.bean.Rmap;
import com.iot.service.EgmaterialinService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gmaterialin")
public class EgmaterialinAction {  
    @Resource  
    private EgmaterialinService egmaterialinService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Egmaterialin> select(String sql){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		List<Egmaterialin> list = egmaterialinService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Egmaterialin> selectAll(){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		List<Egmaterialin> list = egmaterialinService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialin> list = egmaterialinService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialin> pageInfo = new PageInfo<Egmaterialin>(list);
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialin> selectBySql(String sql){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		List<Egmaterialin> list = egmaterialinService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialin> selectByPrimaryKey(Integer id){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		Egmaterialin object = egmaterialinService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Egmaterialinv> selectV(String sql){
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		List<Egmaterialinv> list = egmaterialinService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Egmaterialinv>  selectVAll(){
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		List<Egmaterialinv> list = egmaterialinService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialinv> list = egmaterialinService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialinv> pageInfo = new PageInfo<Egmaterialinv>(list);
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialinv> selectVBySql(String sql){
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		List<Egmaterialinv> list = egmaterialinService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialinv> selectVByPrimaryKey(Integer id){
		Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
		Egmaterialinv einfusionbagv = egmaterialinService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Egmaterialin> insert(@RequestBody Egmaterialin record){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		if(egmaterialinService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Egmaterialin> update(@RequestBody Egmaterialin record){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		if(egmaterialinService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Egmaterialin> delete(Integer id){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		Egmaterialin gmaterialin = egmaterialinService.selectByPrimaryKey(id);
		if(egmaterialinService.delete(id) >0)
			map.setObject(gmaterialin);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Egmaterialin> deletes(String ids){
		Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
		List<Egmaterialin> list = egmaterialinService.selectBySql("id in "+ids);
		if(egmaterialinService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}

	/**
	 * 入库分类统计
	 * @param sql
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByCategory", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByCategory(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialinService.sumWeightGroupByCategory(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 入库统计 按照来源分组
	 * @param sql
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupBySource", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupBySource(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialinService.sumWeightGroupBySource(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 某一类别入库重量,按年按月分组
	 * @param treatmentplantid
	 * @param gmaterialcategoryid
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByMonth", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialinService.sumWeightGroupByMonth(treatmentplantid,gmaterialcategoryid,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

}