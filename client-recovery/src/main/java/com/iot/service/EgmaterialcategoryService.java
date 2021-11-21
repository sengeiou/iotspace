package com.iot.service;

import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;
import com.iot.bean.Rmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-recovery", fallback = EgmaterialcategoryServiceImpl.class)
public interface EgmaterialcategoryService {
    @RequestMapping(value = "/gmaterialcategory/select", method = RequestMethod.GET)
    public Rmap<Egmaterialcategory> select(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectAll", method = RequestMethod.GET)
    public Rmap<Egmaterialcategory> selectAll();

    @RequestMapping(value = "/gmaterialcategory/selectByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialcategory> selectByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialcategory> selectBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialcategory> selectByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialcategory/selectV", method = RequestMethod.GET)
    public Rmap<Egmaterialcategoryv> selectV(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectVAll", method = RequestMethod.GET)
    public Rmap<Egmaterialcategoryv> selectVAll();

    @RequestMapping(value = "/gmaterialcategory/selectVByPage", method = RequestMethod.GET)
    public  Rmap<Egmaterialcategoryv> selectVByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectVBySql", method = RequestMethod.GET)
    public Rmap<Egmaterialcategoryv> selectVBySql(@RequestParam("sql") String sql);

    @RequestMapping(value = "/gmaterialcategory/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialcategoryv> selectVByPrimaryKey(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialcategory/insert", method = RequestMethod.POST)
    public Rmap<Egmaterialcategory> insert(@RequestBody Egmaterialcategory record);

    @RequestMapping(value = "/gmaterialcategory/update", method = RequestMethod.PUT)
    public Rmap<Egmaterialcategory> update(@RequestBody Egmaterialcategory record);

    @RequestMapping(value = "/gmaterialcategory/delete", method = RequestMethod.DELETE)
    public Rmap<Egmaterialcategory> delete(@RequestParam("id") Integer id);

    @RequestMapping(value = "/gmaterialcategory/deletes", method = RequestMethod.DELETE)
    public Rmap<Egmaterialcategory> deletes(@RequestParam("ids") String ids);

    /**
     * 更新库存 添加或减少
     * @param id
     * @param stock
     * @return
     */
    @RequestMapping(value = "/gmaterialcategory/updateStock", method = RequestMethod.PUT)
    public Rmap<Integer> updateStock(@RequestParam("id") Integer id, @RequestParam("stock") Double stock);
}