package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.mapper.EtrucktreatmentplantMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("etrucktreatmentplantService")
public class EtrucktreatmentplantServiceImpl implements EtrucktreatmentplantService {
    @Resource  
    private EtrucktreatmentplantMapper etrucktreatmentplantMapper;

	public List<Etrucktreatmentplant> select(String sql){
		return etrucktreatmentplantMapper.select(sql);
	}

	public List<Etrucktreatmentplant> selectAll(){
		return etrucktreatmentplantMapper.selectAll();
	}

	public List<Etrucktreatmentplant> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktreatmentplantMapper.selectAll();
		else
			return etrucktreatmentplantMapper.selectBySql(sql);
	}

	public List<Etrucktreatmentplant> selectBySql(String sql){
		return etrucktreatmentplantMapper.selectBySql(sql);
	}

	public Etrucktreatmentplant selectByPrimaryKey(Integer id){
		return etrucktreatmentplantMapper.selectByPrimaryKey(id);
	}

	public List<Etrucktreatmentplantv> selectV(String sql){
		return etrucktreatmentplantMapper.selectV(sql);
	}

	public List<Etrucktreatmentplantv> selectVAll(){
		return etrucktreatmentplantMapper.selectVAll();
	}

	public List<Etrucktreatmentplantv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return etrucktreatmentplantMapper.selectVAll();
		else
			return etrucktreatmentplantMapper.selectVBySql(sql);
	}

	public List<Etrucktreatmentplantv> selectVBySql(String sql){
		return etrucktreatmentplantMapper.selectVBySql(sql);
	}

	public Etrucktreatmentplantv selectVByPrimaryKey(Integer id){
		return etrucktreatmentplantMapper.selectVByPrimaryKey(id);
	}

	public int insert(Etrucktreatmentplant record){
		return etrucktreatmentplantMapper.insert(record);
	}

	public int update(Etrucktreatmentplant record){
		return etrucktreatmentplantMapper.update(record);
	}

	public int delete(Integer id){
		return etrucktreatmentplantMapper.delete(id);
	}

	public int deletes(String ids){
		return etrucktreatmentplantMapper.deletes(ids);
	}

	public List<Etrucktreatmentplantv> selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		return etrucktreatmentplantMapper.selectVByStatusAndSql(sql);
	}

	@Override
	public Integer sumGlassweightBySql(String sql) {
		return etrucktreatmentplantMapper.sumGlassweightBySql(sql);
	}

	@Override
	public Integer sumPlasticweightBySql(String sql) {
		return etrucktreatmentplantMapper.sumPlasticweightBySql(sql);
	}

	@Override
	public List<Map<String, Object>> yearGlassnumberGroupMonthSql(String sql) {
		return etrucktreatmentplantMapper.yearGlassnumberGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearPlasticnumberGroupMonthSql(String sql) {
		return etrucktreatmentplantMapper.yearPlasticnumberGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearGlassweightGroupMonthSql(String sql) {
		return etrucktreatmentplantMapper.yearGlassweightGroupMonthSql(sql);
	}

	@Override
	public List<Map<String, Object>> yearPlasticweightGroupMonthSql(String sql) {
		return etrucktreatmentplantMapper.yearPlasticweightGroupMonthSql(sql);
	}

    @Override
    public List<Map<String, Object>> trucktreatmentplantList(Integer page, Integer rows, String sort, String order, String sql) {
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return etrucktreatmentplantMapper.trucktreatmentplantList(sql);
    }

    @Override
    public List<Map<String, Object>> trucktreatmentplantListExport(String sql) {
        return etrucktreatmentplantMapper.trucktreatmentplantList(sql);
    }

    @Override
    public Map<String, Object> trucktreatmentplantSum(String sql) {
        return etrucktreatmentplantMapper.trucktreatmentplantSum(sql);
    }
}