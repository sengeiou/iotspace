package com.iot.service;

import com.github.pagehelper.PageHelper;
import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;
import com.iot.mapper.EhospitalMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("ehospitalService")
public class EhospitalServiceImpl implements EhospitalService {  
    @Resource  
    private EhospitalMapper ehospitalMapper;

	public List<Ehospital> select(String sql){
		return ehospitalMapper.select(sql);
	}

	public List<Ehospital> selectAll(){
		return ehospitalMapper.selectAll();
	}

	public List<Ehospital> selectByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ehospitalMapper.selectAll();
		else
			return ehospitalMapper.selectBySql(sql);
	}


	public List<Ehospital> selectBySql(String sql){
		return ehospitalMapper.selectBySql(sql);
	}

	public Ehospital selectByPrimaryKey(Integer id){
		return ehospitalMapper.selectByPrimaryKey(id);
	}

	public List<Ehospitalv> selectV(String sql){
		return ehospitalMapper.selectV(sql);
	}

	public List<Ehospitalv> selectVAll(){
		return ehospitalMapper.selectVAll();
	}

	public List<Ehospitalv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql){
		if(page == null) page = 1;
		if(rows == null) rows = 20;
		if(sort == null || sort.isEmpty()) sort = "id";
		if(order == null || order.isEmpty()) order = "asc";
		PageHelper.startPage(page, rows, sort + " " + order);
		if(StringUtils.isBlank(sql))
			return ehospitalMapper.selectVAll();
		else
			return ehospitalMapper.selectVBySql(sql);
	}

	public List<Ehospitalv> selectVBySql(String sql){
		return ehospitalMapper.selectVBySql(sql);
	}

	public Ehospitalv selectVByPrimaryKey(Integer id){
		return ehospitalMapper.selectVByPrimaryKey(id);
	}

	public int insert(Ehospital record){
		return ehospitalMapper.insert(record);
	}

	public int update(Ehospital record){
		return ehospitalMapper.update(record);
	}

	public int delete(Integer id){
		return ehospitalMapper.delete(id);
	}

	public int deletes(String ids){
		return ehospitalMapper.deletes(ids);
	}

	@Override
	public int countryHospitalNum() {
		return ehospitalMapper.countryHospitalNum();
	}

	@Override
	public int provinceHospitalNum(String address) {
		return ehospitalMapper.provinceHospitalNum(address);
	}

	@Override
	public int cityHospitalNum(String address) {
		return ehospitalMapper.cityHospitalNum(address);
	}

	@Override
	public int platHospitalNum(String address,String paddress) {
		return ehospitalMapper.platHospitalNum(address, paddress);
	}

	@Override
	public List<Map<String, Object>> countByTransportcompanyGroupByType(Integer transportcompanyid) {
		return ehospitalMapper.countByTransportcompanyGroupByType(transportcompanyid);
	}

	@Override
	public Map<String, Object> countByTransportcompanyAndLevel(Integer transportcompanyid, String level) {
		return ehospitalMapper.countByTransportcompanyAndLevel(transportcompanyid,level);
	}

	@Override
	public int hospitalNumBySql(String sql) {
		return ehospitalMapper.hospitalNumBySql(sql);
	}

    @Override
    public List<Ehospitalv> provinceHospital(Integer page, Integer rows, String sort, String order, String address, String sql) {
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return ehospitalMapper.provinceHospital(address,sql);
    }

    @Override
    public List<Ehospitalv> cityHospital(Integer page, Integer rows, String sort, String order, String address, String sql) {
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return ehospitalMapper.cityHospital(address,sql);
	}

    @Override
    public List<Ehospitalv> platHospital(Integer page, Integer rows, String sort, String order, String address, String paddress, String sql) {
        if(page == null) page = 1;
        if(rows == null) rows = 20;
        if(sort == null || sort.isEmpty()) sort = "id";
        if(order == null || order.isEmpty()) order = "asc";
        PageHelper.startPage(page, rows, sort + " " + order);
        return ehospitalMapper.platHospital(address,paddress,sql);
    }

	public int updateLongitudeLatitude(Ehospital record){
		return ehospitalMapper.updateLongitudeLatitude(record);
	}

}