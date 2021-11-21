package com.iot.action;

import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.bean.Rmap;
import com.iot.service.EdepartmentService;
import com.iot.util.AuthToken;
import com.iot.util.ExcelExport;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/department")
public class EdepartmentAction extends BaseController{
    @Resource
    private EdepartmentService edepartmentService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询某个医院科室
     */
    @AuthToken
    @RequestMapping(value = "/selectByHospitalId", method = RequestMethod.GET)
    public Rmap<Edepartment> selectByHospitalId(Integer hospitalid){
        Rmap<Edepartment> map = new  Rmap<Edepartment>();
        if(null == hospitalid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = edepartmentService.selectBySql("hospitalid = "+hospitalid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: hospitalid
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Edepartment> selectByPage(Integer page, Integer rows, String name, Integer  hospitalid, String leadername, String leaderphone){
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospitalid = ").append(hospitalid);
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        if (StringUtils.isNotBlank(leadername)) {
            sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
        }
        if (StringUtils.isNotBlank(leaderphone)) {
            sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
        }
        map = edepartmentService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: hospitalid
     * @Param: leadername
     * @Param: leaderphone
     * @Description: v分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Edepartmentv> selectVByPage(Integer page, Integer rows, String name, Integer  hospitalid, String leadername, String leaderphone){
        Rmap<Edepartmentv> map = new Rmap<Edepartmentv>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospitalid = ").append(hospitalid);
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        if (StringUtils.isNotBlank(leadername)) {
            sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
        }
        if (StringUtils.isNotBlank(leaderphone)) {
            sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
        }
        map = edepartmentService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
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
    public Rmap<Edepartment> selectByPrimaryKey(Integer id){
        Rmap<Edepartment> map = edepartmentService.selectByPrimaryKey(id);
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
    public Rmap<Edepartment> insert(Edepartment record) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        if(isValidate(record.getName(), record.getHospitalid(), 0)){
            Rmap<Edepartment> rmap = edepartmentService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加科室失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该科室已存在");
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
    public Rmap<Edepartment> update(Edepartment record) {
        Rmap<Edepartment> map = new Rmap<Edepartment>();
        if(isValidate(record.getName(), record.getHospitalid(), record.getId())){
            Rmap<Edepartment> rmap = edepartmentService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改科室失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该科室已存在");
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
    public Rmap<Edepartment> delete(Integer id) {
        Rmap<Edepartment> map = edepartmentService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除科室失败");
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
    public Rmap<Edepartment> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Edepartment> map = edepartmentService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除科室失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: name 科室名
     * @Param: hospitalid 医院id
     * @Param: id
     * @Description: 唯一性
     */
    public boolean isValidate(String name, Integer hospitalid, Integer id){
        Rmap<Edepartment> map = edepartmentService.selectBySql("name = '" + name +"' and hospitalid =  "+hospitalid +" and id != " + id);
        if(null != map.getList()){
            return false;
        }else {
            if(null == map.getMessage()){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/29
     * @Param: hospitalid
     * @Param: response
     * @Description: 医院科室二维码
     */
    @RequestMapping(value = "/barcodeExcel")
    public void barcodeExcel(Integer hospitalid, HttpServletResponse response){
        try {
            if(null == hospitalid){
                String message = "参数不全";
                OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                response.setHeader("content-type", "text/html;charset=UTF-8");
                byte[] dataByteArr = message.getBytes("UTF-8");
                outputStream.write(dataByteArr);
                return;
            }
            StringBuilder sql = new StringBuilder();
            sql.append("hospitalid = ").append(hospitalid);
            Rmap<Edepartment> rmap = edepartmentService.selectBySql(sql.toString());
            if(null != rmap.getList()){
                String BARCODE_KEYS = "barcode,name";
                String BARCODE_TITLES = "二维码,科室名称";
                String[] titles = BARCODE_TITLES.split(",");
                String[] keys = BARCODE_KEYS.split(",");
                List<Map<String, Object>> list = new ArrayList<>();
                Long timestamp = new Date().getTime();
                for (int i = 0; i < rmap.getList().size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    //医院id-时间戳-科室id
                    map.put("barcode", hospitalid + "-" +timestamp+"-"+rmap.getList().get(i).getId());
                    map.put("name", rmap.getList().get(i).getName());
                    list.add(map);
                }
                String fileName = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date()) + "科室二维码" + ".xls";
                ExcelExport.setResponseHeader(response, fileName);
                OutputStream out = response.getOutputStream();
                ExcelExport.export(out, titles, keys, list);
            }else{
                if(null == rmap.getMessage()){
                    String message = "未查询到科室信息";
                    OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                    response.setHeader("content-type", "text/html;charset=UTF-8");
                    byte[] dataByteArr = message.getBytes("UTF-8");
                    outputStream.write(dataByteArr);
                    return;
                }else{
                    String message = "服务异常";
                    OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                    response.setHeader("content-type", "text/html;charset=UTF-8");
                    byte[] dataByteArr = message.getBytes("UTF-8");
                    outputStream.write(dataByteArr);
                    return;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}