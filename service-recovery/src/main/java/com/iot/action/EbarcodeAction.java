package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.bean.Rmap;
import com.iot.service.EbarcodeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/barcode")
public class EbarcodeAction {  
    @Resource  
    private EbarcodeService ebarcodeService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ebarcode> select(String sql){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		List<Ebarcode> list = ebarcodeService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ebarcode> selectAll(){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		List<Ebarcode> list = ebarcodeService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ebarcode> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ebarcode> list = ebarcodeService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ebarcode> pageInfo = new PageInfo<Ebarcode>(list);
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ebarcode> selectBySql(String sql){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		List<Ebarcode> list = ebarcodeService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ebarcode> selectByPrimaryKey(Integer id){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		Ebarcode object = ebarcodeService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ebarcodev> selectV(String sql){
		Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
		List<Ebarcodev> list = ebarcodeService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ebarcodev>  selectVAll(){
		Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
		List<Ebarcodev> list = ebarcodeService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ebarcodev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ebarcodev> list = ebarcodeService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ebarcodev> pageInfo = new PageInfo<Ebarcodev>(list);
		Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ebarcodev> selectVBySql(String sql){
		Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
		List<Ebarcodev> list = ebarcodeService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ebarcodev> selectVByPrimaryKey(Integer id){
		Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
		Ebarcodev einfusionbagv = ebarcodeService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ebarcode> insert(@RequestBody Ebarcode record){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		if(ebarcodeService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ebarcode> update(@RequestBody Ebarcode record){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		if(ebarcodeService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ebarcode> delete(Integer id){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		Ebarcode barcode = ebarcodeService.selectByPrimaryKey(id);
		if(ebarcodeService.delete(id) >0)
			map.setObject(barcode);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ebarcode> deletes(String ids){
		Rmap<Ebarcode> map = new Rmap<Ebarcode>();
		List<Ebarcode> list = ebarcodeService.selectBySql("id in "+ids);
		if(ebarcodeService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}
}