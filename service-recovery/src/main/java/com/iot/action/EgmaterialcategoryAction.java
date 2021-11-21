package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;
import com.iot.bean.Rmap;
import com.iot.service.EgmaterialcategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gmaterialcategory")
public class EgmaterialcategoryAction {  
    @Resource  
    private EgmaterialcategoryService egmaterialcategoryService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Egmaterialcategory> select(String sql){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		List<Egmaterialcategory> list = egmaterialcategoryService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Egmaterialcategory> selectAll(){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		List<Egmaterialcategory> list = egmaterialcategoryService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialcategory> list = egmaterialcategoryService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialcategory> pageInfo = new PageInfo<Egmaterialcategory>(list);
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialcategory> selectBySql(String sql){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		List<Egmaterialcategory> list = egmaterialcategoryService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialcategory> selectByPrimaryKey(Integer id){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		Egmaterialcategory object = egmaterialcategoryService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Egmaterialcategoryv> selectV(String sql){
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		List<Egmaterialcategoryv> list = egmaterialcategoryService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Egmaterialcategoryv>  selectVAll(){
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		List<Egmaterialcategoryv> list = egmaterialcategoryService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Egmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Egmaterialcategoryv> list = egmaterialcategoryService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Egmaterialcategoryv> pageInfo = new PageInfo<Egmaterialcategoryv>(list);
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Egmaterialcategoryv> selectVBySql(String sql){
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		List<Egmaterialcategoryv> list = egmaterialcategoryService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Egmaterialcategoryv> selectVByPrimaryKey(Integer id){
		Rmap<Egmaterialcategoryv> map = new Rmap<Egmaterialcategoryv>();
		Egmaterialcategoryv einfusionbagv = egmaterialcategoryService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Egmaterialcategory> insert(@RequestBody Egmaterialcategory record){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		if(egmaterialcategoryService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Egmaterialcategory> update(@RequestBody Egmaterialcategory record){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		if(egmaterialcategoryService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Egmaterialcategory> delete(Integer id){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		Egmaterialcategory gmaterialcategory = egmaterialcategoryService.selectByPrimaryKey(id);
		if(egmaterialcategoryService.delete(id) >0)
			map.setObject(gmaterialcategory);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Egmaterialcategory> deletes(String ids){
		Rmap<Egmaterialcategory> map = new Rmap<Egmaterialcategory>();
		List<Egmaterialcategory> list = egmaterialcategoryService.selectBySql("id in "+ids);
		if(egmaterialcategoryService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}

	/**
	 * 更新库存 添加或减少库存
	 * @param id
	 * @param stock
	 * @return
	 */
	@RequestMapping(value = "/updateStock", method = RequestMethod.PUT)
	public Rmap<Integer> updateStock(Integer id, Double stock){
		Rmap<Integer> map = new Rmap<Integer>();
		Integer result = egmaterialcategoryService.updateStock(id,stock);
		map.setObject(result);
		return map;
	}
}