package com.iot.service;

import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "service-recovery", fallback = EhospitalServiceImpl.class)
public interface EhospitalService {
    @RequestMapping(value = "/hospital/select", method = RequestMethod.GET)
    public Rmap<Ehospital> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectAll", method = RequestMethod.GET)
    public Rmap<Ehospital> selectAll();

    @RequestMapping(value = "/hospital/selectByPage", method = RequestMethod.GET)
    public Rmap<Ehospital> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectBySql", method = RequestMethod.GET)
    public Rmap<Ehospital> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ehospital> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/hospital/selectV", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectVAll", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectVAll();

    @RequestMapping(value = "/hospital/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Ehospitalv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/hospital/insert", method = RequestMethod.POST)
    public Rmap<Ehospital> insert(@RequestBody Ehospital record);

    @RequestMapping(value = "/hospital/update", method = RequestMethod.PUT)
    public Rmap<Ehospital> update(@RequestBody Ehospital record);

    @RequestMapping(value = "/hospital/delete", method = RequestMethod.DELETE)
    public Rmap<Ehospital> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/hospital/deletes", method = RequestMethod.DELETE)
    public Rmap<Ehospital> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/hospital/countryHospitalNum", method = RequestMethod.GET)
    public Rmap<Integer> countryHospitalNum();

    @RequestMapping(value = "/hospital/provinceHospitalNum", method = RequestMethod.GET)
    public Rmap<Integer> provinceHospitalNum(@RequestParam("address") String address);

    @RequestMapping(value = "/hospital/cityHospitalNum", method = RequestMethod.GET)
    public Rmap<Integer> cityHospitalNum(@RequestParam("address") String address);

    @RequestMapping(value = "/hospital/platHospitalNum", method = RequestMethod.GET)
    public Rmap<Integer> platHospitalNum(@RequestParam("address") String address,@RequestParam("paddress") String paddress);

    @RequestMapping(value = "/hospital/countByTransportcompanyGroupByType", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> countByTransportcompanyGroupByType(@RequestParam("transportcompanyid") Integer transportcompanyid);

    @RequestMapping(value = "/hospital/countByTransportcompanyAndLevel", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> countByTransportcompanyAndLevel(@RequestParam("transportcompanyid") Integer transportcompanyid,@RequestParam("level") String level);

    @RequestMapping(value = "/hospital/hospitalNumBySql", method = RequestMethod.GET)
    public Rmap<Integer> hospitalNumBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/provinceHospital", method = RequestMethod.GET)
    public  Rmap<Ehospitalv> provinceHospital(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("address") String address, @RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/cityHospital", method = RequestMethod.GET)
    public  Rmap<Ehospitalv> cityHospital(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("address") String address, @RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/platHospital", method = RequestMethod.GET)
    public  Rmap<Ehospitalv> platHospital(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("address") String address,@RequestParam("paddress") String paddress, @RequestParam("sql") String sql);

    @RequestMapping(value = "/hospital/updateLongitudeLatitude", method = RequestMethod.PUT)
    public Rmap<Ehospital> updateLongitudeLatitude(@RequestBody Ehospital record);

}