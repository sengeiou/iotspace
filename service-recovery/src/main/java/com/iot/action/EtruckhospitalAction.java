package com.iot.action;

import com.github.pagehelper.PageInfo;
import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Rmap;
import com.iot.service.EtruckhospitalService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/truckhospital")
public class EtruckhospitalAction {
	@Resource
	private EtruckhospitalService etruckhospitalService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Rmap<Etruckhospital> select(String sql){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		List<Etruckhospital> list = etruckhospitalService.select(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public Rmap<Etruckhospital> selectAll(){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		List<Etruckhospital> list = etruckhospitalService.selectAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Etruckhospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckhospital> list = etruckhospitalService.selectByPage(page, rows, sort, order, sql);
		PageInfo<Etruckhospital> pageInfo = new PageInfo<Etruckhospital>(list);
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectBySql", method = RequestMethod.GET)
	public Rmap<Etruckhospital> selectBySql(String sql){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		List<Etruckhospital> list = etruckhospitalService.selectBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruckhospital> selectByPrimaryKey(Integer id){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		Etruckhospital etruckhospital = etruckhospitalService.selectByPrimaryKey(id);
		if(null != etruckhospital)
			map.setObject(etruckhospital);
		return map;
	}

	@RequestMapping(value = "/selectV", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectV(String sql){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.selectV(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv>  selectVAll(){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.selectVAll();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckhospitalv> list = etruckhospitalService.selectVByPage(page, rows, sort, order, sql);
		PageInfo<Etruckhospitalv> pageInfo = new PageInfo<Etruckhospitalv>(list);
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVByTransportcompanyIdAndPage", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectVByTransportcompanyIdAndPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckhospitalv> list = etruckhospitalService.selectVByTransportcompanyIdAndPage(page, rows, sort, order, sql);
		PageInfo<Etruckhospitalv> pageInfo = new PageInfo<Etruckhospitalv>(list);
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/selectVBySql", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectVBySql(String sql){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.selectVBySql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectVByPrimaryKey(Integer id){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		Etruckhospitalv etruckhospitalv = etruckhospitalService.selectVByPrimaryKey(id);
		if(null != etruckhospitalv)
			map.setObject(etruckhospitalv);
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Rmap<Etruckhospital> insert(@RequestBody Etruckhospital record){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		if(etruckhospitalService.insert(record) > 0)
			map.setObject(record);
			return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Etruckhospital> update(@RequestBody Etruckhospital record){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		if(etruckhospitalService.update(record) > 0)
			map.setObject(record);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Etruckhospital> delete(Integer id){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		Etruckhospital etruckhospital = etruckhospitalService.selectByPrimaryKey(id);
		if(etruckhospitalService.delete(id) > 0)
			map.setObject(etruckhospital);
		return map;
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Etruckhospital> deletes(String ids){
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		List<Etruckhospital> list = etruckhospitalService.selectBySql("id in "+ids);
		if(etruckhospitalService.deletes(ids) > 0){
			map.setList(list);
		}
		return map;
	}
    //数据统计
	@RequestMapping(value = "/statisticProvincePlasticWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticProvincePlasticWeight(){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticProvincePlasticWeight();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticProvinceGlassWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticProvinceGlassWeight(){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticProvinceGlassWeight();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticCityPlasticWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticCityPlasticWeight(String address){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticCityPlasticWeight(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticCityGlassWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticCityGlassWeight(String address){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticCityGlassWeight(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticPlatPlasticWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticPlatPlasticWeight(String address){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticPlatPlasticWeight(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticPlatGlassWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticPlatGlassWeight(String address){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticPlatGlassWeight(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticHospitalPlasticWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticHospitalPlasticWeight(String address,String paddress){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticHospitalPlasticWeight(address,paddress);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/statisticHospitalGlassWeight", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> statisticHospitalGlassWeight(String address,String paddress){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.statisticHospitalGlassWeight(address,paddress);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/countryPlasticWeight", method = RequestMethod.GET)
	public Rmap<Integer> countryPlasticWeight(){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.countryPlasticWeight());
		return map;
	}

	@RequestMapping(value = "/countryGlassWeight", method = RequestMethod.GET)
	public Rmap<Integer> countryGlassWeight(){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.countryGlassWeight());
		return map;
	}

	@RequestMapping(value = "/provincePlasticWeight", method = RequestMethod.GET)
	public Rmap<Integer> provincePlasticWeight(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.provincePlasticWeight(address));
		return map;
	}

	@RequestMapping(value = "/provinceGlassWeight", method = RequestMethod.GET)
	public Rmap<Integer> provinceGlassWeight(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.provinceGlassWeight(address));
		return map;
	}

	@RequestMapping(value = "/cityPlasticWeight", method = RequestMethod.GET)
	public Rmap<Integer> cityPlasticWeight(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.cityPlasticWeight(address));
		return map;
	}

	@RequestMapping(value = "/cityGlassWeight", method = RequestMethod.GET)
	public Rmap<Integer> cityGlassWeight(String address){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.cityGlassWeight(address));
		return map;
	}

	@RequestMapping(value = "/platPlasticWeight", method = RequestMethod.GET)
	public Rmap<Integer> platPlasticWeight(String address, String paddress){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.platPlasticWeight(address,paddress));
		return map;
	}

	@RequestMapping(value = "/platGlassWeight", method = RequestMethod.GET)
	public Rmap<Integer> platGlassWeight(String address, String paddress){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.platGlassWeight(address, paddress));
		return map;
	}

	@RequestMapping(value = "/hospitalPlasticWeight", method = RequestMethod.GET)
	public Rmap<Integer> hospitalPlasticWeight(Integer id){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.hospitalPlasticWeight(id));
		return map;
	}

	@RequestMapping(value = "/hospitalGlassWeight", method = RequestMethod.GET)
	public Rmap<Integer> hospitalGlassWeight(Integer id){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.hospitalGlassWeight(id));
		return map;
	}

	@RequestMapping(value = "/countrySelectLimit", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> countrySelectLimit(){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.countrySelectLimit();
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/provinceSelectLimit", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> provinceSelectLimit(String address){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.provinceSelectLimit(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/citySelectLimit", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> citySelectLimit(String address){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.citySelectLimit(address);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/platSelectLimit", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> platSelectLimit(String address, String paddress){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.platSelectLimit(address, paddress);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/hospitalSelectLimit", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> hospitalSelectLimit(Integer id){
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		List<Etruckhospitalv> list = etruckhospitalService.hospitalSelectLimit(id);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/countryYearPlasticWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> countryYearPlasticWeightGroupMonth(Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.countryYearPlasticWeightGroupMonth(year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/countryYearGlassWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> countryYearGlassWeightGroupMonth(Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.countryYearGlassWeightGroupMonth(year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/provinceYearPlasticWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> provinceYearPlasticWeightGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.provinceYearPlasticWeightGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/provinceYearGlassWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> provinceYearGlassWeightGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.provinceYearGlassWeightGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}


	@RequestMapping(value = "/cityYearPlasticWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> cityYearPlasticWeightGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.cityYearPlasticWeightGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/cityYearGlassWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> cityYearGlassWeightGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.cityYearGlassWeightGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/platYearPlasticWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> platYearPlasticWeightGroupMonth(String address,String paddress,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.platYearPlasticWeightGroupMonth(address,paddress,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/platYearGlassWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> platYearGlassWeightGroupMonth(String address,String paddress,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.platYearGlassWeightGroupMonth(address,paddress,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/hospitalYearPlasticWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> hospitalYearPlasticWeightGroupMonth(Integer id,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.hospitalYearPlasticWeightGroupMonth(id,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/hospitalYearGlassWeightGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> hospitalYearGlassWeightGroupMonth(Integer id,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService. hospitalYearGlassWeightGroupMonth(id,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/countryYearPlasticNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> countryYearPlasticNumberGroupMonth(Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.countryYearPlasticNumberGroupMonth(year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/countryYearGlassNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> countryYearGlassNumberGroupMonth(Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list = etruckhospitalService.countryYearGlassNumberGroupMonth(year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/provinceYearPlasticNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> provinceYearPlasticNumberGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.provinceYearPlasticNumberGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/provinceYearGlassNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> provinceYearGlassNumberGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.provinceYearGlassNumberGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/cityYearPlasticNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> cityYearPlasticNumberGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.cityYearPlasticNumberGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/cityYearGlassNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> cityYearGlassNumberGroupMonth(String address,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.cityYearGlassNumberGroupMonth(address,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/platYearPlasticNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> platYearPlasticNumberGroupMonth(String address,String paddress,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.platYearPlasticNumberGroupMonth(address,paddress,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/platYearGlassNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> platYearGlassNumberGroupMonth(String address,String paddress,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.platYearGlassNumberGroupMonth(address,paddress,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/hospitalYearPlasticNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> hospitalYearPlasticNumberGroupMonth(Integer id,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.hospitalYearPlasticNumberGroupMonth(id,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/hospitalYearGlassNumberGroupMonth", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> hospitalYearGlassNumberGroupMonth(Integer id,Integer year){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.hospitalYearGlassNumberGroupMonth(id,year);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/selectVByStatusAndSqlPage", method = RequestMethod.GET)
	public Rmap<Etruckhospitalv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		List<Etruckhospitalv> list = etruckhospitalService.selectVByStatusAndSqlPage(page, rows, sort, order, sql);
		PageInfo<Etruckhospitalv> pageInfo = new PageInfo<Etruckhospitalv>(list);
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		if(list.size() > 0)
			map.setList(list);
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("total", pageInfo.getTotal());
		map.setInfo(info);
		return map;
	}

	@RequestMapping(value = "/sumGlassweightBySql", method = RequestMethod.GET)
	public Rmap<Integer> sumGlassweightBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.sumGlassweightBySql(sql));
		return map;
	}

	@RequestMapping(value = "/sumPlasticweightBySql", method = RequestMethod.GET)
	public Rmap<Integer> sumPlasticweightBySql(String sql){
		Rmap<Integer> map = new Rmap<Integer>();
		map.setObject(etruckhospitalService.sumPlasticweightBySql(sql));
		return map;
	}

	@RequestMapping(value = "/yearGlassweightGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> yearGlassweightGroupMonthSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.yearGlassweightGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearPlasticweightGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.yearPlasticweightGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearGlassnumberGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.yearGlassnumberGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/yearPlasticnumberGroupMonthSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.yearPlasticnumberGroupMonthSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/sumGlassweightSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> sumGlassweightSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.sumGlassweightSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

	@RequestMapping(value = "/sumPlasticweightSql", method = RequestMethod.GET)
	public Rmap<Map<String, Object>> sumPlasticweightSql(String sql){
		Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
		List<Map<String, Object>> list =etruckhospitalService.sumPlasticweightSql(sql);
		if(list.size() > 0)
			map.setList(list);
		return map;
	}

    @RequestMapping(value = "/truckhospitalList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> truckhospitalList(Integer page, Integer rows, String sort, String order, String sql){
        List<Map<String, Object>> list = etruckhospitalService.truckhospitalList(page, rows, sort, order, sql);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/truckhospitalExport", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> truckhospitalExport(String sql){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        List<Map<String, Object>> list =etruckhospitalService.truckhospitalExport(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/treatmentplantTruckhospitalList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalList(Integer page, Integer rows, String sort, String order, String sql){
        List<Map<String, Object>> list = etruckhospitalService.treatmentplantTruckhospitalList(page, rows, sort, order, sql);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        if(list.size() > 0)
            map.setList(list);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("total", pageInfo.getTotal());
        map.setInfo(info);
        return map;
    }

    @RequestMapping(value = "/treatmentplantTruckhospitalExport", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalExport(String sql){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        List<Map<String, Object>> list =etruckhospitalService.treatmentplantTruckhospitalExport(sql);
        if(list.size() > 0)
            map.setList(list);
        return map;
    }

    @RequestMapping(value = "/treatmentplantTruckhospitalSum", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalSum(String sql){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        Map<String, Object> result = etruckhospitalService.treatmentplantTruckhospitalSum(sql);
        if(null != result)
            map.setObject(result);
        return map;
    }

    @RequestMapping(value = "/sumByTruckid", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumByTruckid(Integer truckid){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        Map<String, Object> result = etruckhospitalService.sumByTruckid(truckid);
        if(null != result)
            map.setObject(result);
        return map;
    }

}