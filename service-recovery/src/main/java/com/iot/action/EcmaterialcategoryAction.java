package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.bean.Rmap;
import com.iot.service.EcmaterialcategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cmaterialcategory")
public class EcmaterialcategoryAction {  
    @Resource  
    private EcmaterialcategoryService ecmaterialcategoryService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategory> select(String sql){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		List<Ecmaterialcategory> list = ecmaterialcategoryService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategory> selectAll(){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		List<Ecmaterialcategory> list = ecmaterialcategoryService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategory> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialcategory> list = ecmaterialcategoryService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialcategory> pageInfo = new PageInfo<Ecmaterialcategory>(list);
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategory> selectBySql(String sql){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		List<Ecmaterialcategory> list = ecmaterialcategoryService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategory> selectByPrimaryKey(Integer id){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		Ecmaterialcategory object = ecmaterialcategoryService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategoryv> selectV(String sql){
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		List<Ecmaterialcategoryv> list = ecmaterialcategoryService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategoryv>  selectVAll(){
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		List<Ecmaterialcategoryv> list = ecmaterialcategoryService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategoryv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ecmaterialcategoryv> list = ecmaterialcategoryService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ecmaterialcategoryv> pageInfo = new PageInfo<Ecmaterialcategoryv>(list);
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategoryv> selectVBySql(String sql){
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		List<Ecmaterialcategoryv> list = ecmaterialcategoryService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ecmaterialcategoryv> selectVByPrimaryKey(Integer id){
		Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
		Ecmaterialcategoryv einfusionbagv = ecmaterialcategoryService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ecmaterialcategory> insert(@RequestBody Ecmaterialcategory record){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		if(ecmaterialcategoryService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ecmaterialcategory> update(@RequestBody Ecmaterialcategory record){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		if(ecmaterialcategoryService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialcategory> delete(Integer id){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		Ecmaterialcategory cmaterialcategory = ecmaterialcategoryService.selectByPrimaryKey(id);
		if(ecmaterialcategoryService.delete(id) >0)
			map.setObject(cmaterialcategory);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ecmaterialcategory> deletes(String ids){
		Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
		List<Ecmaterialcategory> list = ecmaterialcategoryService.selectBySql("id in "+ids);
		if(ecmaterialcategoryService.deletes(ids) > 0) {
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
		Integer result = ecmaterialcategoryService.updateStock(id,stock);
		map.setObject(result);
		return map;
	}
}