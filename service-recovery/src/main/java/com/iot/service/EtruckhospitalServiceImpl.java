package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Etruckproductionplantv;
import com.iot.mapper.EtruckhospitalMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("etruckhospitalService")
public class EtruckhospitalServiceImpl implements EtruckhospitalService {
    @Resource  
    private EtruckhospitalMapper etruckhospitalMapper;

	public List<Etruckhospital> select(String sql){
		return etruckhospitalMapper.select(sql);
	}

	public List<Etruckhospital> selectAll(){
		return etruckhospitalMapper.selectAll();
	}

	public List<Etruckhospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckhospitalMapper.selectAll();
		else
			return etruckhospitalMapper.selectBySql(sql);
	}

	public List<Etruckhospital> selectBySql(String sql){
		return etruckhospitalMapper.selectBySql(sql);
	}

	public Etruckhospital selectByPrimaryKey(Integer id){
		return etruckhospitalMapper.selectByPrimaryKey(id);
	}

	public List<Etruckhospitalv> selectV(String sql){
		return etruckhospitalMapper.selectV(sql);
	}

	public List<Etruckhospitalv> selectVAll(){
		return etruckhospitalMapper.selectVAll();
	}

	public List<Etruckhospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etruckhospitalMapper.selectVAll();
		else
			return etruckhospitalMapper.selectVBySql(sql);
	}

	public List<Etruckhospitalv> selectVByTransportcompanyIdAndPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etruckhospitalMapper.selectVByTransportcompanyId(sql);
	}

	public List<Etruckhospitalv> selectVBySql(String sql){
		return etruckhospitalMapper.selectVBySql(sql);
	}

	public Etruckhospitalv selectVByPrimaryKey(Integer id){
		return etruckhospitalMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etruckhospital record){
		return etruckhospitalMapper.insert(record);
	}

	public int update(Etruckhospital record){
		return etruckhospitalMapper.update(record);
	}

	public int delete(Integer id){
		return etruckhospitalMapper.delete(id);
	}

	public int deletes(String ids){
		return etruckhospitalMapper.deletes(ids);
	}

	@Override
	public List<Map<String, Object>> statisticProvincePlasticWeight() {
		return etruckhospitalMapper.statisticProvincePlasticWeight();
	}

	@Override
	public List<Map<String, Object>> statisticProvinceGlassWeight() {
		return etruckhospitalMapper.statisticProvinceGlassWeight();
	}

	@Override
	public List<Map<String, Object>> statisticCityPlasticWeight(String address) {
		return etruckhospitalMapper.statisticCityPlasticWeight(address);
	}

	@Override
	public List<Map<String, Object>> statisticCityGlassWeight(String address) {
		return etruckhospitalMapper.statisticCityGlassWeight(address);
	}

	@Override
	public List<Map<String, Object>> statisticPlatPlasticWeight(String address) {
		return etruckhospitalMapper.statisticPlatPlasticWeight(address);
	}

	@Override
	public List<Map<String, Object>> statisticPlatGlassWeight(String address) {
		return etruckhospitalMapper.statisticPlatGlassWeight(address);
	}

	@Override
	public List<Map<String, Object>> statisticHospitalPlasticWeight(String address,String paddress) {
		return etruckhospitalMapper.statisticHospitalPlasticWeight(address,paddress);
	}

	@Override
	public List<Map<String, Object>> statisticHospitalGlassWeight(String address,String paddress) {
		return etruckhospitalMapper.statisticHospitalGlassWeight(address,paddress);
	}

	@Override
	public Integer countryPlasticWeight() {
		return etruckhospitalMapper.countryPlasticWeight();
	}

	@Override
	public Integer countryGlassWeight() {
		return etruckhospitalMapper.countryGlassWeight();
	}

	@Override
	public Integer provincePlasticWeight(String address) {
		return etruckhospitalMapper.provincePlasticWeight(address);
	}

	@Override
	public Integer provinceGlassWeight(String address) {
		return etruckhospitalMapper.provinceGlassWeight(address);
	}

	@Override
	public Integer cityPlasticWeight(String address) {
		return etruckhospitalMapper.cityPlasticWeight(address);
	}

	@Override
	public Integer cityGlassWeight(String address) {
		return etruckhospitalMapper.cityGlassWeight(address);
	}

	@Override
	public Integer platPlasticWeight(String address, String paddress) {
		return etruckhospitalMapper.platPlasticWeight(address, paddress);
	}

	@Override
	public Integer platGlassWeight(String address, String paddress) {
		return etruckhospitalMapper.platGlassWeight(address,paddress);
	}

	@Override
	public Integer hospitalPlasticWeight(Integer id) {
		return etruckhospitalMapper.hospitalPlasticWeight(id);
	}

	@Override
	public Integer hospitalGlassWeight(Integer id) {
		return etruckhospitalMapper.hospitalGlassWeight(id);
	}

	@Override
	public List<Etruckhospitalv> countrySelectLimit() {
		return etruckhospitalMapper.countrySelectLimit();
	}

	@Override
	public List<Etruckhospitalv> provinceSelectLimit(String address) {
		return etruckhospitalMapper.provinceSelectLimit(address);
	}

	@Override
	public List<Etruckhospitalv> citySelectLimit(String address) {
		return etruckhospitalMapper.citySelectLimit(address);
	}

	@Override
	public List<Etruckhospitalv> platSelectLimit(String address,String paddress) {
		return etruckhospitalMapper.platSelectLimit(address, paddress);
	}

	@Override
	public List<Etruckhospitalv> hospitalSelectLimit(Integer id) {
		return etruckhospitalMapper.hospitalSelectLimit(id);
	}

	@Override
	public List<Map<String, Object>> countryYearPlasticWeightGroupMonth(Integer year) {
		return etruckhospitalMapper.countryYearPlasticWeightGroupMonth(year);
	}

	@Override
	public List<Map<String, Object>> countryYearGlassWeightGroupMonth(Integer year) {
		return etruckhospitalMapper.countryYearGlassWeightGroupMonth(year);
	}

	@Override
	public List<Map<String, Object>> provinceYearPlasticWeightGroupMonth(String address, Integer year) {
		return etruckhospitalMapper.provinceYearPlasticWeightGroupMonth(address, year);
	}

	@Override
	public List<Map<String, Object>> provinceYearGlassWeightGroupMonth(String address, Integer year) {
		return etruckhospitalMapper.provinceYearGlassWeightGroupMonth(address, year);
	}

	@Override
	public List<Map<String, Object>> cityYearPlasticWeightGroupMonth(String address,Integer year) {
		return etruckhospitalMapper.cityYearPlasticWeightGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> cityYearGlassWeightGroupMonth(String address, Integer year) {
		return etruckhospitalMapper.cityYearGlassWeightGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> platYearPlasticWeightGroupMonth(String address,String paddress, Integer year) {
		return etruckhospitalMapper.platYearPlasticWeightGroupMonth(address,paddress,year);
	}

	@Override
	public List<Map<String, Object>>  platYearGlassWeightGroupMonth(String address,String paddress, Integer year) {
		return etruckhospitalMapper.platYearGlassWeightGroupMonth(address,paddress,year);
	}


	@Override
	public List<Map<String, Object>>  hospitalYearPlasticWeightGroupMonth(Integer id,Integer year) {
		return etruckhospitalMapper. hospitalYearPlasticWeightGroupMonth(id,year);
	}

	@Override
	public List<Map<String, Object>>   hospitalYearGlassWeightGroupMonth(Integer id,Integer year) {
		return etruckhospitalMapper.hospitalYearGlassWeightGroupMonth(id,year);
	}


	@Override
	public List<Map<String, Object>> countryYearPlasticNumberGroupMonth(Integer year) {
		return etruckhospitalMapper.countryYearPlasticNumberGroupMonth(year);
	}

	@Override
	public List<Map<String, Object>> countryYearGlassNumberGroupMonth(Integer year) {
		return etruckhospitalMapper.countryYearGlassNumberGroupMonth(year);
	}

	@Override
	public List<Map<String, Object>> provinceYearPlasticNumberGroupMonth(String address,Integer year) {
		return etruckhospitalMapper.provinceYearPlasticNumberGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> provinceYearGlassNumberGroupMonth(String address,Integer year) {
		return etruckhospitalMapper.provinceYearGlassNumberGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> cityYearPlasticNumberGroupMonth(String address,Integer year) {
		return etruckhospitalMapper.cityYearPlasticNumberGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> cityYearGlassNumberGroupMonth(String address,Integer year) {
		return etruckhospitalMapper.cityYearGlassNumberGroupMonth(address,year);
	}

	@Override
	public List<Map<String, Object>> platYearPlasticNumberGroupMonth(String address, String paddress, Integer year) {
		return etruckhospitalMapper.platYearPlasticNumberGroupMonth(address, paddress,year);
	}

	@Override
	public List<Map<String, Object>> platYearGlassNumberGroupMonth(String address, String paddress, Integer year) {
		return etruckhospitalMapper.platYearGlassNumberGroupMonth(address, paddress,year);
	}


	@Override
	public List<Map<String, Object>>  hospitalYearPlasticNumberGroupMonth(Integer id,Integer year) {
		return etruckhospitalMapper. hospitalYearPlasticNumberGroupMonth(id,year);
	}

	@Override
	public List<Map<String, Object>> hospitalYearGlassNumberGroupMonth(Integer id,Integer year) {
		return etruckhospitalMapper.hospitalYearGlassNumberGroupMonth(id,year);
	}

	public List<Etruckhospitalv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etruckhospitalMapper.selectVByStatusAndSql(sql);
	}

	@Override
	public Integer sumGlassweightBySql(String sql) {
		return etruckhospitalMapper.sumGlassweightBySql(sql);
	}

	@Override
	public Integer sumPlasticweightBySql(String sql) {
		return etruckhospitalMapper.sumPlasticweightBySql(sql);
	}

	@Override
	public List<Map<String, Object>> yearGlassweightGroupMonthSql(String sql) {
		return etruckhospitalMapper.yearGlassweightGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql) {
		return etruckhospitalMapper.yearPlasticweightGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql) {
		return etruckhospitalMapper.yearGlassnumberGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql) {
		return etruckhospitalMapper.yearPlasticnumberGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> sumGlassweightSql(String sql) {
		return etruckhospitalMapper.sumGlassweightSql(sql);
	}

	@Override
	public List<Map<String, Object>> sumPlasticweightSql(String sql) {
		return etruckhospitalMapper.sumPlasticweightSql(sql);
	}

    public List<Map<String, Object>> truckhospitalList (Integer page, Integer rows, String sort, String order, String sql){
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return etruckhospitalMapper.truckhospitalList(sql);
    }

    @Override
    public List<Map<String, Object>> truckhospitalExport(String sql) {
        return etruckhospitalMapper.truckhospitalList(sql);
    }

    @Override
    public List<Map<String, Object>> treatmentplantTruckhospitalList(Integer page, Integer rows, String sort, String order, String sql) {
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return etruckhospitalMapper.treatmentplantTruckhospitalList(sql);
    }

    @Override
    public List<Map<String, Object>> treatmentplantTruckhospitalExport(String sql) {
        return etruckhospitalMapper.treatmentplantTruckhospitalList(sql);
    }

    @Override
    public Map<String, Object> treatmentplantTruckhospitalSum(String sql) {
        return etruckhospitalMapper.treatmentplantTruckhospitalSum(sql);
    }

    @Override
    public Map<String, Object> sumByTruckid(Integer truckid) {
        return etruckhospitalMapper.sumByTruckid(truckid);
    }
}