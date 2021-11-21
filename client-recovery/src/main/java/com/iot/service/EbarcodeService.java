package com.iot.service;

import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EbarcodeServiceImpl.class)
public interface EbarcodeService {
    @RequestMapping(value = "/barcode/select", method = RequestMethod.GET)
    public Rmap<Ebarcode> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/barcode/selectAll", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectAll();

    @RequestMapping(value = "/barcode/selectByPage", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);


    @RequestMapping(value = "/barcode/selectBySql", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectBySql(@RequestParam("sql") String sql);

;
    @RequestMapping(value = "/barcode/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/barcode/selectV", method = RequestMethod.GET)
    public Rmap<Ebarcodev> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/barcode/selectVAll", method = RequestMethod.GET)
    public Rmap<Ebarcodev> selectVAll();

    @RequestMapping(value = "/barcode/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Ebarcodev> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/barcode/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ebarcodev> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/barcode/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ebarcodev> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/barcode/insert", method = RequestMethod.POST)
    public Rmap<Ebarcode> insert(@RequestBody Ebarcode record);

    @RequestMapping(value = "/barcode/update", method = RequestMethod.PUT)
    public Rmap<Ebarcode> update(@RequestBody Ebarcode record);

    @RequestMapping(value = "/barcode/delete", method = RequestMethod.DELETE)
    public Rmap<Ebarcode> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/barcode/deletes", method = RequestMethod.DELETE)
    public Rmap<Ebarcode> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/barcode/countByhospitalid", method = RequestMethod.GET)
    public Rmap<Integer> countByhospitalid(@RequestParam("hospitalid") Integer hospitalid);

}