package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;
import com.iot.bean.Rmap;
import com.iot.service.EhospitalService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
public class EhospitalAction {  
    @Resource  
    private EhospitalService ehospitalService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Ehospital> select(String sql){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		List<Ehospital> list = ehospitalService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Ehospital> selectAll(){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		List<Ehospital> list = ehospitalService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Ehospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ehospital> list = ehospitalService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Ehospital> pageInfo = new PageInfo<Ehospital>(list);
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Ehospital> selectBySql(String sql){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		List<Ehospital> list = ehospitalService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ehospital> selectByPrimaryKey(Integer id){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		Ehospital object = ehospitalService.selectByPrimaryKey(id);
		map.setObject(object);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Ehospitalv> selectV(String sql){
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		List<Ehospitalv> list = ehospitalService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Ehospitalv>  selectVAll(){
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		List<Ehospitalv> list = ehospitalService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Ehospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Ehospitalv> list = ehospitalService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Ehospitalv> pageInfo = new PageInfo<Ehospitalv>(list);
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Ehospitalv> selectVBySql(String sql){
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		List<Ehospitalv> list = ehospitalService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Ehospitalv> selectVByPrimaryKey(Integer id){
		Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
		Ehospitalv einfusionbagv = ehospitalService.selectVByPrimaryKey(id);
		if(null != einfusionbagv)
			map.setObject(einfusionbagv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Ehospital> insert(@RequestBody Ehospital record){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		if(ehospitalService.insert(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Ehospital> update(@RequestBody Ehospital record){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		if(ehospitalService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Ehospital> delete(Integer id){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		Ehospital hospital = ehospitalService.selectByPrimaryKey(id);
		if(ehospitalService.delete(id) >0)
			map.setObject(hospital);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Ehospital> deletes(String ids){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		List<Ehospital> list = ehospitalService.selectBySql("id in "+ids);
		if(ehospitalService.deletes(ids) > 0) {
			map.setList(list);
		}
		return map;
	}

	@RequestMapping(value = "/countryHospitalNum", method = RequestMethod.GET)
	public Rmap<Integer> countryHospitalNum(){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(ehospitalService.countryHospitalNum());
		return map;
	}

	@RequestMapping(value = "/provinceHospitalNum", method = RequestMethod.GET)
	public Rmap<Integer> provinceHospitalNum(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(ehospitalService.provinceHospitalNum(address));
		return map;
	}

	@RequestMapping(value = "/cityHospitalNum", method = RequestMethod.GET)
	public Rmap<Integer> cityHospitalNum(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(ehospitalService.cityHospitalNum(address));
		return map;
	}

	@RequestMapping(value = "/platHospitalNum", method = RequestMethod.GET)
	public Rmap<Integer> platHospitalNum(String address,String paddress){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(ehospitalService.platHospitalNum(address,paddress));
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/6/1
	 * @Param: transportcompanyid
	 * @Description: 运输公司下医院各个类型数量
	 */
	@RequestMapping(value = "/countByTransportcompanyGroupByType", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> countByTransportcompanyGroupByType(Integer transportcompanyid){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		List<Map<String, Object>> list = ehospitalService.countByTransportcompanyGroupByType(transportcompanyid);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/6/1
	 * @Param: transportcompanyid
	 * @Param: level
	 * @Description: 查询某个运输公司下的级别医院
	 */
	@RequestMapping(value = "/countByTransportcompanyAndLevel", method = RequestMethod.GET)
	public Rmap<Map<String,Object>> countByTransportcompanyAndLevel(Integer transportcompanyid, String level){
		Rmap<Map<String,Object>> map = new Rmap<Map<String,Object>>();
		map.setObject(ehospitalService.countByTransportcompanyAndLevel(transportcompanyid,level));
		return map;
	}

	@RequestMapping(value = "/hospitalNumBySql", method = RequestMethod.GET)
	public Rmap<Integer> hospitalNumBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(ehospitalService.hospitalNumBySql(sql));
		return map;
	}

    @RequestMapping(value = "/provinceHospital", method = RequestMethod.GET)
    public Rmap<Ehospitalv> provinceHospital(Integer page, Integer rows, String sort, String order,String address, String sql){
        List<Ehospitalv> list = ehospitalService.provinceHospital(page, rows, sort, order,address, sql);
        PageInfo<Ehospitalv> pageInfo = new PageInfo<Ehospitalv>(list);
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/cityHospital", method = RequestMethod.GET)
    public Rmap<Ehospitalv> cityHospital(Integer page, Integer rows, String sort, String order,String address, String sql){
        List<Ehospitalv> list = ehospitalService.cityHospital(page, rows, sort, order,address, sql);
        PageInfo<Ehospitalv> pageInfo = new PageInfo<Ehospitalv>(list);
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/platHospital", method = RequestMethod.GET)
    public Rmap<Ehospitalv> platHospital(Integer page, Integer rows, String sort, String order,String address,String paddress, String sql){
        List<Ehospitalv> list = ehospitalService.platHospital(page, rows, sort, order,address,paddress, sql);
        PageInfo<Ehospitalv> pageInfo = new PageInfo<Ehospitalv>(list);
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

	@RequestMapping(value = "/updateLongitudeLatitude", method = RequestMethod.PUT)
	public Rmap<Ehospital> updateLongitudeLatitude(@RequestBody Ehospital record){
		Rmap<Ehospital> map = new Rmap<Ehospital>();
		if(ehospitalService.updateLongitudeLatitude(record) > 0)
			map.setObject(record);
		return map;
	}

}