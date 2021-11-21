package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ecmaterialout;
import com.iot.bean.Ecmaterialoutv;
import com.iot.bean.Rmap;
import com.iot.service.EcmaterialoutService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cmaterialout")
public class EcmaterialoutAction {  
    @Resource  
    private EcmaterialoutService EcmaterialoutService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ecmaterialout> select(String sql){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		List<Ecmaterialout> list = EcmaterialoutService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialout> selectAll(){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		List<Ecmaterialout> list = EcmaterialoutService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialout> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialout> list = EcmaterialoutService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialout> pageInfo = new PageInfo<Ecmaterialout>(list);
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialout> selectBySql(String sql){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		List<Ecmaterialout> list = EcmaterialoutService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialout> selectByPrimaryKey(Integer id){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		Ecmaterialout object = EcmaterialoutService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ecmaterialoutv> selectV(String sql){
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		List<Ecmaterialoutv> list = EcmaterialoutService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialoutv>  selectVAll(){
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		List<Ecmaterialoutv> list = EcmaterialoutService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialoutv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialoutv> list = EcmaterialoutService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialoutv> pageInfo = new PageInfo<Ecmaterialoutv>(list);
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialoutv> selectVBySql(String sql){
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		List<Ecmaterialoutv> list = EcmaterialoutService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialoutv> selectVByPrimaryKey(Integer id){
		Rmap<Ecmaterialoutv> map = new Rmap<Ecmaterialoutv>();
		Ecmaterialoutv einfusionbagv = EcmaterialoutService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ecmaterialout> insert(@RequestBody Ecmaterialout record){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		if(EcmaterialoutService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ecmaterialout> update(@RequestBody Ecmaterialout record){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		if(EcmaterialoutService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialout> delete(Integer id){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		Ecmaterialout cmaterialin = EcmaterialoutService.selectByPrimaryKey(id);
		if(EcmaterialoutService.delete(id) >0)
			map.setObject(cmaterialin);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialout> deletes(String ids){
		Rmap<Ecmaterialout> map = new Rmap<Ecmaterialout>();
		List<Ecmaterialout> list = EcmaterialoutService.selectBySql("id in "+ids);
		if(EcmaterialoutService.deletes(ids) > 0) {
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
		List<Map<String,Object>> list = EcmaterialoutService.sumWeightGroupByCategory(sql);
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
		List<Map<String,Object>> list = EcmaterialoutService.sumWeightGroupBySource(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * 某一类别出库重量,按年按月分组
	 * @param treatmentplantid
	 * @param cmaterialcategoryid
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/sumWeightGroupByMonth", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> sumWeightGroupByMonth(Integer treatmentplantid, Integer cmaterialcategoryid, Integer year){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String,Object>> list = EcmaterialoutService.sumWeightGroupByMonth(treatmentplantid,cmaterialcategoryid,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

}