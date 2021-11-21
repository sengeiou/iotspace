package com.iot.action;

import com.iot.bean.*;
import com.iot.service.EhospitalService;
import com.iot.service.EsupervisionunitService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/hospital")
public class EhospitalAction  extends BaseController{
    @Resource
    private EhospitalService ehospitalService;
    @Resource
    private EsupervisionunitService esupervisionunitService;

    //数据补全
    private static final String typearray[] = {"公立医院","民营医院","基层医疗卫生机构","专业公共卫生机构","其他机构"};

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: page
     * @Param: rows
     * @Param: treatmentplantid 处理厂id
     * @Param: transportcompanyid 运输公司id
     * @Description: 某一运输公司下的医院
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantId", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectByTreatmentplantId(Integer page, Integer rows, Integer treatmentplantid, Integer transportcompanyid,String name,String province,String city,String plat,String type){
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        if(null != transportcompanyid){ //传运输公司id
            sql.append("transportcompanyid = "+transportcompanyid);
            if(StringUtils.isNotBlank(name)){
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
            if(StringUtils.isNotBlank(province)){
                sql.append(" and province = '"+province+"'");
            }
            if(StringUtils.isNotBlank(city)){
                sql.append(" and city = '"+city+"'");
            }
            if(StringUtils.isNotBlank(plat)){
                sql.append(" and plat = '"+plat+"'");
            }
            if(StringUtils.isNotBlank(type)){
                sql.append(" and type = '"+type+"'");
            }
            map = ehospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
        }else{
            sql.append("transportcompanyid in (select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
            if(StringUtils.isNotBlank(name)){
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
            if(StringUtils.isNotBlank(province)){
                sql.append(" and province = '"+province+"'");
            }
            if(StringUtils.isNotBlank(city)){
                sql.append(" and city = '"+city+"'");
            }
            if(StringUtils.isNotBlank(plat)){
                sql.append(" and plat = '"+plat+"'");
            }
            if(StringUtils.isNotBlank(type)){
                sql.append(" and type = '"+type+"'");
            }
            map = ehospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
        }
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param: transportcompanyid
     * @Description: 某一运输公司 给类型医院数量；三级医院数量
     */
    @AuthToken
    @RequestMapping(value = "/countByTransportcompany", method = RequestMethod.GET)
    public Map<String,Object> countByTransportcompany(Integer transportcompanyid){
        Map<String,Object> map = new HashMap<>();
        if(null == transportcompanyid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Map<String,Object>> groupMap = ehospitalService.countByTransportcompanyGroupByType(transportcompanyid);
        if(null != groupMap.getList()){
            map.put("status",260);
            List<Map<String,Object>> list = groupMap.getList();
            add(list);
            map.put("typecount",list);
        }else{
            map.put("typecount",null);
        }
        Rmap<Map<String,Object>> levelMap = ehospitalService.countByTransportcompanyAndLevel(transportcompanyid,"三级医院");
        if(null != levelMap.getObject()){
            map.put("status",260);
            map.put("levelcount",levelMap.getObject());
        }else{
            map.put("levelcount",null);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Ehospital> selectAll(){
        Rmap<Ehospital> map = ehospitalService.selectAll();
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
     * @Param: address
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Ehospital> selectByPage(Integer page, Integer rows, String name, String level, String type, Integer supervisionunitid, String code, String address, String leadername, String leaderphone,Integer transportcompanyid){
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        StringBuilder sql = new StringBuilder();
        if(null != supervisionunitid){
            sql.append("supervisionunitid = ").append(supervisionunitid);
        }
        if (StringUtils.isNotBlank(name)) {
            if(sql.length() == 0){
                sql.append("name like ").append("'%25").append(name).append("%25'");
            }else{
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(address)) {
            if(sql.length() == 0){
                sql.append("address like ").append("'%25").append(address).append("%25'");
            }else{
                sql.append(" and address like ").append("'%25").append(address).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(level)) {
            if(sql.length() == 0){
                sql.append("level = '").append(level).append("'");
            }else{
                sql.append(" and level = '").append(level).append("'");
            }
        }
        if (StringUtils.isNotBlank(type)) {
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
            }
        }
        if (StringUtils.isNotBlank(code)) {
            if(sql.length() == 0){
                sql.append("code like ").append("'%25").append(code).append("%25'");
            }else{
                sql.append(" and code like ").append("'%25").append(code).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(leadername)) {
            if(sql.length() == 0){
                sql.append("leadername like ").append("'%25").append(leadername).append("%25'");
            }else{
                sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(leaderphone)) {
            if(sql.length() == 0){
                sql.append("leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }else{
                sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }
        }
        if(null != transportcompanyid){
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        map = ehospitalService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectVByPage(Integer page, Integer rows, String name, String level, String type, Integer supervisionunitid, String code, String address, String leadername, String leaderphone,Integer transportcompanyid,String province,String city,String plat){
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        /*if (null == supervisionunitid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }*/
        StringBuilder sql = new StringBuilder();
        if(null != supervisionunitid){
            sql.append("supervisionunitid = ").append(supervisionunitid);
        }
        if (StringUtils.isNotBlank(name)) {
            if(sql.length() == 0){
                sql.append("name like ").append("'%25").append(name).append("%25'");
            }else{
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(address)) {
            if(sql.length() == 0){
                sql.append("address like ").append("'%25").append(address).append("%25'");
            }else{
                sql.append(" and address like ").append("'%25").append(address).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(level)) {
            if(sql.length() == 0){
                sql.append("level = '").append(level).append("'");
            }else{
                sql.append(" and level = '").append(level).append("'");
            }
        }
        if (StringUtils.isNotBlank(province)) {
            if(sql.length() == 0){
                sql.append("province = '").append(province).append("'");
            }else{
                sql.append(" and province = '").append(province).append("'");
            }
        }
        if (StringUtils.isNotBlank(city)) {
            if(sql.length() == 0){
                sql.append("city = '").append(city).append("'");
            }else{
                sql.append(" and city = '").append(city).append("'");
            }
        }
        if (StringUtils.isNotBlank(plat)) {
            if(sql.length() == 0){
                sql.append("plat = '").append(plat).append("'");
            }else{
                sql.append(" and plat = '").append(plat).append("'");
            }
        }
        if (StringUtils.isNotBlank(type)) {
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
            }
        }
        if (StringUtils.isNotBlank(code)) {
            if(sql.length() == 0){
                sql.append("code like ").append("'%25").append(code).append("%25'");
            }else{
                sql.append(" and code like ").append("'%25").append(code).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(leadername)) {
            if(sql.length() == 0){
                sql.append("leadername like ").append("'%25").append(leadername).append("%25'");
            }else{
                sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(leaderphone)) {
            if(sql.length() == 0){
                sql.append("leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }else{
                sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }
        }
        if(null != transportcompanyid){
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        map = ehospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Ehospital> selectByPrimaryKey(Integer id){
        Rmap<Ehospital> map = ehospitalService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    //v查询
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ehospitalv> selectVByPrimaryKey(Integer id){
        Rmap<Ehospitalv> map = ehospitalService.selectVByPrimaryKey(id);
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
    public Rmap<Ehospital> insert(Ehospital record) {
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        if(null == record.getSupervisionunitid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        if(isValidate(record.getName(), 0)){
            Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectByPrimaryKey(record.getSupervisionunitid());
            if(null == supervisionunitMap.getObject()){
                map.setStatus(240);
                if(null == supervisionunitMap.getMessage()){
                    map.setMessage("未查询到监管单位信息");
                }else{
                    map.setMessage("查询监管单位信息异常");
                }
                return map;
            }
            //所属级别
            record.setBelonglevel(supervisionunitMap.getObject().getLevel());
            record.setTime(new Date());
            map = ehospitalService.insert(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加医院失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该医院已存在");
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
    public Rmap<Ehospital> update(Ehospital record) {
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        if(null == record.getSupervisionunitid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        if(isValidate(record.getName(), record.getId())){
            Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectByPrimaryKey(record.getSupervisionunitid());
            if(null == supervisionunitMap.getObject()){
                map.setStatus(240);
                if(null == supervisionunitMap.getMessage()){
                    map.setMessage("未查询到监管单位信息");
                }else{
                    map.setMessage("查询监管单位信息异常");
                }
                return map;
            }
            //所属级别
            record.setBelonglevel(supervisionunitMap.getObject().getLevel());
            map = ehospitalService.update(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改医院失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该医院已存在");
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
    public Rmap<Ehospital> delete(Integer id) {
        Rmap<Ehospital> map = ehospitalService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除医院失败");
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
    public Rmap<Ehospital> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Ehospital> map = ehospitalService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除医院失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/8
     * @Param:
     * @Description: 医院注册类型
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("公立医院");
        select.setValue("公立医院");
        list.add(select);
        select = new Select();
        select.setText("民营医院");
        select.setValue("民营医院");
        list.add(select);
        select = new Select();
        select.setText("基层医疗卫生机构");
        select.setValue("基层医疗卫生机构");
        list.add(select);
        select = new Select();
        select.setText("专业公共卫生机构");
        select.setValue("专业公共卫生机构");
        list.add(select);
        select = new Select();
        select.setText("其他机构");
        select.setValue("其他机构");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/levelList", method = RequestMethod.GET)
    public Rmap<Select> levelList(String type){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        if(type.equals("公立医院") || type.equals("民营医院")){
            select.setText("一级医院");
            select.setValue("一级医院");
            list.add(select);
            select = new Select();
            select.setText("二级医院");
            select.setValue("二级医院");
            list.add(select);
            select = new Select();
            select.setText("三级医院");
            select.setValue("三级医院");
            list.add(select);
            select = new Select();
            select.setText("未定级医院");
            select.setValue("未定级医院");
            list.add(select);
        }else if(type.equals("基层医疗卫生机构")){
            select.setText("乡镇卫生院");
            select.setValue("乡镇卫生院");
            list.add(select);
            select = new Select();
            select.setText("诊所（医务室）");
            select.setValue("诊所（医务室）");
            list.add(select);
            select = new Select();
            select.setText("村卫生室");
            select.setValue("村卫生室");
            list.add(select);
            select = new Select();
            select.setText("社区卫生服务中心（站）");
            select.setValue("社区卫生服务中心（站）");
            list.add(select);
        }else if(type.equals("专业公共卫生机构")){
            select.setText("疾病预防控制中心");
            select.setValue("疾病预防控制中心");
            list.add(select);
            select = new Select();
            select.setText("妇幼保健机构");
            select.setValue("妇幼保健机构");
            list.add(select);
            select = new Select();
            select.setText("专科疾病防治院（所、站）");
            select.setValue("专科疾病防治院（所、站）");
            list.add(select);
            select = new Select();
            select.setText("卫生监督所（中心）");
            select.setValue("卫生监督所（中心）");
            list.add(select);
            select = new Select();
            select.setText("计划生育技术服务机构");
            select.setValue("计划生育技术服务机构");
            list.add(select);
        }
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 数据类型补全
     */
    public List<Map<String, Object>> add(List<Map<String, Object>> list) {
        System.out.println(list.toString());
        for (String str : typearray) {
            if (!list.toString().contains("type="+str)) {
                Map<String, Object> map = new HashMap<>();
                map.put("type", str);
                map.put("count", 0);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: name 医院名称
     * @Param: id
     * @Description: 唯一性校验
     */
    public boolean isValidate(String name, Integer id){
        Rmap<Ehospital> map = ehospitalService.selectBySql("name = '" + name + "' and id != " + id);
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

    /*
     * @Date 2020/8/5 10:09
     * @Description 监管单位查询医院
     * @Param address 当前监管单位地址  provinceaddress 省监管单位地址 cityaddress 市监管单位地址 plataddress区监管单位地址
     * level 当前监管单位级别
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/supervisionunitHospital", method = RequestMethod.GET)
    public Rmap<Ehospitalv> supervisionunitHospital(Integer page, Integer rows,String level, String address, String provinceaddress,String cityaddress,String plataddress, String name, String hospitallevel,String type,String belonglevel,String code) {
        Rmap<Ehospitalv> map = new Rmap<Ehospitalv>();
        if(StringUtils.isBlank(level)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" 1=1 ");
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        if (StringUtils.isNotBlank(hospitallevel)) {
            sql.append(" and level = '").append(hospitallevel).append("'");
        }
        if (StringUtils.isNotBlank(type)) {
            sql.append(" and type = '").append(type).append("'");
        }
        if (StringUtils.isNotBlank(code)) {
            sql.append(" and code like ").append("'%25").append(code).append("%25'");
        }
        if (StringUtils.isNotBlank(belonglevel)) {
            sql.append(" and belonglevel = '"+belonglevel+"'");
        }
        if(level.equals("全国")){
            if(StringUtils.isNotBlank(plataddress)){ //区查询
                if(StringUtils.isBlank(cityaddress)){
                    map.setStatus(240);
                    map.setMessage("参数不全");
                    return map;
                }
                map = ehospitalService.platHospital(page, rows,"id","desc", plataddress,cityaddress, sql.toString());
            }else if(StringUtils.isNotBlank(cityaddress)){//市查询
                map = ehospitalService.cityHospital(page, rows,"id","desc", cityaddress, sql.toString());
            }else if(StringUtils.isNotBlank(provinceaddress)){//省查询
                map = ehospitalService.provinceHospital(page, rows,"id","desc", provinceaddress, sql.toString());
            }else{
                map = ehospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
            }
        }else if(level.equals("省")){
            if(StringUtils.isNotBlank(plataddress)){ //区查询
                if(StringUtils.isBlank(cityaddress)){
                    map.setStatus(240);
                    map.setMessage("参数不全");
                    return map;
                }
                map = ehospitalService.platHospital(page, rows,"id","desc", plataddress,cityaddress, sql.toString());
            }else if(StringUtils.isNotBlank(cityaddress)){//市查询
                map = ehospitalService.cityHospital(page, rows,"id","desc", cityaddress, sql.toString());
            }else {
                if(StringUtils.isBlank(address)){
                    map.setStatus(240);
                    map.setMessage("参数不全");
                    return map;
                }
                map = ehospitalService.provinceHospital(page, rows,"id","desc", address, sql.toString());
            }
        }else if(level.equals("市")){
            if(StringUtils.isBlank(address)){
                map.setStatus(240);
                map.setMessage("参数不全");
                return map;
            }
            if(StringUtils.isNotBlank(plataddress)){ //区查询
                map = ehospitalService.platHospital(page, rows,"id","desc", plataddress,address, sql.toString());
            }else{
                map = ehospitalService.cityHospital(page, rows,"id","desc", address, sql.toString());
            }
        }else if(level.equals("区")){
            if(StringUtils.isBlank(address) || StringUtils.isBlank(cityaddress)){
                map.setStatus(240);
                map.setMessage("参数不全");
                return map;
            }
            map = ehospitalService.platHospital(page, rows,"id","desc", address,cityaddress, sql.toString());
        }else{
            map.setStatus(240);
            map.setMessage("参数错误");
            return map;
        }
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    //不同监管单位查看医院不同级别
    @AuthToken
    @RequestMapping(value = "/belonglevelList", method = RequestMethod.GET)
    public Rmap<Select> belonglevelList(String level) {
        Rmap<Select> map = new Rmap<Select>();
        if(StringUtils.isBlank(level)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        if (level.equals("全国")) {
            select.setText("全国");
            select.setValue("全国");
            list.add(select);
            select = new Select();
            select.setText("省");
            select.setValue("省");
            list.add(select);
            select = new Select();
            select.setText("市");
            select.setValue("市");
            list.add(select);
            select = new Select();
            select.setText("区");
            select.setValue("区");
            list.add(select);
        } else if (level.equals("省")) {
            select = new Select();
            select.setText("省");
            select.setValue("省");
            list.add(select);
            select = new Select();
            select.setText("市");
            select.setValue("市");
            list.add(select);
            select = new Select();
            select.setText("区");
            select.setValue("区");
            list.add(select);
        }else if (level.equals("市")) {
            select = new Select();
            select.setText("市");
            select.setValue("市");
            list.add(select);
            select = new Select();
            select.setText("区");
            select.setValue("区");
            list.add(select);
        }else if (level.equals("区")) {
            select = new Select();
            select.setText("区");
            select.setValue("区");
            list.add(select);
        }else{
            map.setStatus(240);
            map.setMessage("参数错误");
            return map;
        }
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * 更改医院省市区经纬度
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/updateLongitudeLatitude", method = RequestMethod.PUT)
    public Rmap<Ehospital> updateLongitudeLatitude(Ehospital record) {
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        if(null == record.getId()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = ehospitalService.updateLongitudeLatitude(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改失败");
                }
            }
        return map;
        }

}