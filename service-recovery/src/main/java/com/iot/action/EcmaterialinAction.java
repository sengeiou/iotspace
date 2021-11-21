package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;
import com.iot.bean.Rmap;
import com.iot.service.EcmaterialinService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cmaterialin")
public class EcmaterialinAction {  
    @Resource  
    private EcmaterialinService ecmaterialinService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ecmaterialin> select(String sql){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		List<Ecmaterialin> list = ecmaterialinService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialin> selectAll(){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		List<Ecmaterialin> list = ecmaterialinService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialin> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialin> list = ecmaterialinService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialin> pageInfo = new PageInfo<Ecmaterialin>(list);
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialin> selectBySql(String sql){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		List<Ecmaterialin> list = ecmaterialinService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialin> selectByPrimaryKey(Integer id){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		Ecmaterialin object = ecmaterialinService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ecmaterialinv> selectV(String sql){
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		List<Ecmaterialinv> list = ecmaterialinService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialinv>  selectVAll(){
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		List<Ecmaterialinv> list = ecmaterialinService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialinv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialinv> list = ecmaterialinService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialinv> pageInfo = new PageInfo<Ecmaterialinv>(list);
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialinv> selectVBySql(String sql){
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		List<Ecmaterialinv> list = ecmaterialinService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialinv> selectVByPrimaryKey(Integer id){
		Rmap<Ecmaterialinv> map = new Rmap<Ecmaterialinv>();
		Ecmaterialinv einfusionbagv = ecmaterialinService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ecmaterialin> insert(@RequestBody Ecmaterialin record){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		if(ecmaterialinService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ecmaterialin> update(@RequestBody Ecmaterialin record){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		if(ecmaterialinService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialin> delete(Integer id){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		Ecmaterialin cmaterialin = ecmaterialinService.selectByPrimaryKey(id);
		if(ecmaterialinService.delete(id) >0)
			map.setObject(cmaterialin);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialin> deletes(String ids){
		Rmap<Ecmaterialin> map = new Rmap<Ecmaterialin>();
		List<Ecmaterialin> list = ecmaterialinService.selectBySql("id in "+ids);
		if(ecmaterialinService.deletes(ids) > 0) {
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
		List<Map<String,Object>> list = ecmaterialinService.sumWeightGroupByCategory(sql);
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
		List<Map<String,Object>> list = ecmaterialinService.sumWeightGroupBySource(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 某一类别入库重量,按年按月分组
	 * @param treatmentplantid
	 * @param cmaterialcategoryid
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByMonth", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = ecmaterialinService.sumWeightGroupByMonth(treatmentplantid,cmaterialcategoryid,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

}