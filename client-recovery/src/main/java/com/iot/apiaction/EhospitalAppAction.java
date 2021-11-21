package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Ehospital;
import com.iot.bean.Rmap;
import com.iot.service.EhospitalService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: Barry
 * @Date: 2020/4/15
 * @Description:
 */
@RestController
@RequestMapping("/api/hospital")
public class EhospitalAppAction extends BaseController {

    @Resource
    private EhospitalService ehospitalService;

    /**
     * @Auther: Barry
     * @Date: 2020/5/28
     * @Param: transportcompanyid
     * @Description: 根据运输公司id查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByTransportcompanyId", method = RequestMethod.GET)
    public Rmap<Ehospital> selectByTransportcompanyId(Integer transportcompanyid,String name){
        Rmap<Ehospital> map = new  Rmap<Ehospital>();
        if(null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("transportcompanyid = "+transportcompanyid);
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        map = ehospitalService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: name
     * @Description: 医疗机构 名字查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public Rmap<Ehospital> selectByName(String name){
        StringBuilder sql =new StringBuilder();
        sql.append("name like ").append("'%25").append(name).append("%25'");
        Rmap<Ehospital> map = ehospitalService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
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
}
