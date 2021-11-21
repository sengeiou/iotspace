package com.iot.service;

import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Rmap;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EtruckhospitalServiceImpl implements EtruckhospitalService {
	@Override
	public Rmap<Etruckhospital> select(String sql) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospital> selectAll() {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospital> selectBySql(String sql) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospital> selectByPrimaryKey(Integer id) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectV(String sql) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectVAll() {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Etruckhospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruckhospitalv> map = new  Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectVBySql(String sql) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectVByPrimaryKey(Integer id) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospital> insert(Etruckhospital record) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospital> update(Etruckhospital record) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospital> delete(Integer id) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public Rmap<Etruckhospital> deletes(String ids) {
		Rmap<Etruckhospital> map = new Rmap<Etruckhospital>();
        map.setMessage("服务异常");
        return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticProvincePlasticWeight() {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticProvinceGlassWeight() {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticCityPlasticWeight(String address) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticCityGlassWeight(String address) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticPlatPlasticWeight(String address) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticPlatGlassWeight(String address) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticHospitalPlasticWeight(String address,String paddress) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public  Rmap<Map<String, Object>> statisticHospitalGlassWeight(String address,String paddress) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> countryPlasticWeight() {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> countryGlassWeight() {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> provincePlasticWeight(String address) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> provinceGlassWeight(String address) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> cityPlasticWeight(String address) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> cityGlassWeight(String address) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> platPlasticWeight(String address,String paddress) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> platGlassWeight(String address,String paddress) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> hospitalPlasticWeight(Integer id) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> hospitalGlassWeight(Integer id) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> countrySelectLimit() {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> provinceSelectLimit(String address) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> citySelectLimit(String address) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> platSelectLimit(String address,String paddress) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> hospitalSelectLimit(Integer id) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countryYearPlasticWeightGroupMonth(Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countryYearGlassWeightGroupMonth(Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> provinceYearGlassWeightGroupMonth(String address, Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> provinceYearPlasticWeightGroupMonth(String address, Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> cityYearPlasticWeightGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> cityYearGlassWeightGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}


	@Override
	public Rmap<Map<String, Object>> platYearPlasticWeightGroupMonth(String address,String paddress,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> platYearGlassWeightGroupMonth(String address,String paddress,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}


	@Override
	public Rmap<Map<String, Object>> hospitalYearGlassWeightGroupMonth(Integer id,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> hospitalYearPlasticWeightGroupMonth(Integer id,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countryYearPlasticNumberGroupMonth(Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> countryYearGlassNumberGroupMonth(Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}



	@Override
	public Rmap<Map<String, Object>> provinceYearPlasticNumberGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> provinceYearGlassNumberGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> cityYearPlasticNumberGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> cityYearGlassNumberGroupMonth(String address,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}


	@Override
	public Rmap<Map<String, Object>> platYearPlasticNumberGroupMonth(String address,String paddress,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> platYearGlassNumberGroupMonth(String address,String paddress,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> hospitalYearPlasticNumberGroupMonth(Integer id,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> hospitalYearGlassNumberGroupMonth(Integer id,Integer year) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Etruckhospitalv> selectVByTransportcompanyIdAndPage(Integer page, Integer rows, String sort, String order, String sql) {
		Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> sumGlassweightBySql(String sql) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Integer> sumPlasticweightBySql(String sql) {
		Rmap<Integer> map = new  Rmap<Integer>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearGlassweightGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumGlassweightSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

	@Override
	public Rmap<Map<String, Object>> sumPlasticweightSql(String sql) {
		Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
		map.setMessage("服务异常");
		return map;
	}

    @Override
    public Rmap<Map<String, Object>> truckhospitalList(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String, Object>> truckhospitalExport(String sql) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalList(Integer page, Integer rows, String sort, String order, String sql) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalExport(String sql) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalSum(String sql) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }

    @Override
    public Rmap<Map<String, Object>> sumByTruckid(Integer truckid) {
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        map.setMessage("服务异常");
        return map;
    }


}