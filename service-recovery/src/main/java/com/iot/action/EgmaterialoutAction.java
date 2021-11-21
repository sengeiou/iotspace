package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;
import com.iot.bean.Rmap;
import com.iot.service.EgmaterialoutService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gmaterialout")
public class EgmaterialoutAction {  
    @Resource  
    private EgmaterialoutService egmaterialoutService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Egmaterialout> select(String sql){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		List<Egmaterialout> list = egmaterialoutService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Egmaterialout> selectAll(){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		List<Egmaterialout> list = egmaterialoutService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialout> list = egmaterialoutService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialout> pageInfo = new PageInfo<Egmaterialout>(list);
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialout> selectBySql(String sql){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		List<Egmaterialout> list = egmaterialoutService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialout> selectByPrimaryKey(Integer id){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		Egmaterialout object = egmaterialoutService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Egmaterialoutv> selectV(String sql){
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		List<Egmaterialoutv> list = egmaterialoutService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Egmaterialoutv>  selectVAll(){
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		List<Egmaterialoutv> list = egmaterialoutService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialoutv> list = egmaterialoutService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialoutv> pageInfo = new PageInfo<Egmaterialoutv>(list);
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialoutv> selectVBySql(String sql){
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		List<Egmaterialoutv> list = egmaterialoutService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialoutv> selectVByPrimaryKey(Integer id){
		Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
		Egmaterialoutv einfusionbagv = egmaterialoutService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Egmaterialout> insert(@RequestBody Egmaterialout record){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		if(egmaterialoutService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Egmaterialout> update(@RequestBody Egmaterialout record){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		if(egmaterialoutService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Egmaterialout> delete(Integer id){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		Egmaterialout cmaterialin = egmaterialoutService.selectByPrimaryKey(id);
		if(egmaterialoutService.delete(id) >0)
			map.setObject(cmaterialin);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Egmaterialout> deletes(String ids){
		Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
		List<Egmaterialout> list = egmaterialoutService.selectBySql("id in "+ids);
		if(egmaterialoutService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}

	/**
	 * 出库分类统计
	 * @param sql
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByCategory", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByCategory(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialoutService.sumWeightGroupByCategory(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 出库统计 按照去向分组
	 * @param sql
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupBySource", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupBySource(String sql){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialoutService.sumWeightGroupBySource(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 某一类别出库重量,按年按月分组
	 * @param treatmentplantid
	 * @param gmaterialcategoryid
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByMonth", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer gmaterialcategoryid, Integer year){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = egmaterialoutService.sumWeightGroupByMonth(treatmentplantid,gmaterialcategoryid,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

}