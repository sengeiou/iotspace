package com.iot.service;

import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EcmaterialcategoryServiceImpl.class)
public interface EcmaterialcategoryService {
    @RequestMapping(value = "/cmaterialcategory/select", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectAll();

    @RequestMapping(value = "/cmaterialcategory/selectByPage", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialcategory/selectV", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVAll();

    @RequestMapping(value = "/cmaterialcategory/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Ecmaterialcategoryv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectVBySql", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/cmaterialcategory/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialcategory/insert", method = RequestMethod.POST)
    public Rmap<Ecmaterialcategory> insert(@RequestBody Ecmaterialcategory record);

    @RequestMapping(value = "/cmaterialcategory/update", method = RequestMethod.PUT)
    public Rmap<Ecmaterialcategory> update(@RequestBody Ecmaterialcategory record);

    @RequestMapping(value = "/cmaterialcategory/delete", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialcategory> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/cmaterialcategory/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialcategory> deletes(@RequestParam("ids") String ids);

    /**
     * 更新库存 添加或减少
     * @param id
     * @param stock
     * @return
     */
    @RequestMapping(value = "/cmaterialcategory/updateStock", method = RequestMethod.PUT)
    public Rmap<Integer> updateStock(@RequestParam("id") Integer id,@RequestParam("stock") Double stock);
}