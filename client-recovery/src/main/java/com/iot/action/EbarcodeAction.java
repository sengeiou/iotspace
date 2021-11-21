package com.iot.action;

import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.bean.Rmap;
import com.iot.service.EbarcodeService;
import com.iot.util.AuthToken;
import com.iot.util.ExcelExport;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/barcode")
public class EbarcodeAction extends BaseController{
    @Resource
    private EbarcodeService ebarcodeService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectAll(){
        Rmap<Ebarcode> map = ebarcodeService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/29
     * @Param: page
     * @Param: rows
     * @Param: hospitalid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectByPage(Integer page, Integer rows, Integer  hospitalid){
        Rmap<Ebarcode> map = new Rmap<Ebarcode>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospitalid = ").append(hospitalid);
        map = ebarcodeService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/29
     * @Param: page
     * @Param: rows
     * @Param: hospitalid
     * @Description: v查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ebarcodev> selectVByPage(Integer page, Integer rows,  Integer  hospitalid){
        Rmap<Ebarcodev> map = new Rmap<Ebarcodev>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospitalid = ").append(hospitalid);
        map = ebarcodeService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: id
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ebarcode> selectByPrimaryKey(Integer id){
        Rmap<Ebarcode> map = ebarcodeService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 添加
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Ebarcode> insert(Ebarcode record) {
        record.setTime(new Date());
        Rmap<Ebarcode> map = ebarcodeService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("添加条形码记录失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Ebarcode> update(Ebarcode record) {
        Rmap<Ebarcode> map = ebarcodeService.update(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("修改条形码记录失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Ebarcode> delete(Integer id) {
        Rmap<Ebarcode> map = ebarcodeService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除条形码记录失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: ids
     * @Description: 批量删除
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Ebarcode> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Ebarcode> map = ebarcodeService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除条形码记录失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/29
     * @Param: httpServletResponse
     * @Description: 垃圾袋二维码导出excel
     */
/*
    @AuthToken
*/
    @RequestMapping(value = "/barcodeExcel")
    public void barcodeExcel(Integer hospitalid, Integer number, HttpServletResponse response){
        try {
            if (null == hospitalid || null == number || number.intValue() <= 0) {
                String message = "参数不全或参数错误";
                OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                response.setHeader("content-type", "text/html;charset=UTF-8");
                byte[] dataByteArr = message.getBytes("UTF-8");
                outputStream.write(dataByteArr);
                return;
            }
            String BARCODE_KEYS = "barcode";
            String BARCODE_TITLES = "二维码";
            String[] titles = BARCODE_TITLES.split(",");
            String[] keys = BARCODE_KEYS.split(",");
            List<Map<String, Object>> list = new ArrayList<>();
            Long timestamp = new Date().getTime();
            for (int i = 1; i <= number; i++) {
                Map<String, Object> map = new HashMap<>();
                //医疗机构-时间-i
                map.put("barcode", hospitalid + "-" + timestamp + "-" + i);
                list.add(map);
            }
            String fileName = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date()) + "二维码" + ".xls";
            ExcelExport.setResponseHeader(response, fileName);
            OutputStream out = response.getOutputStream();
            ExcelExport.export(out, titles, keys, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}