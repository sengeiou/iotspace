package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Etreatmentplant;
import com.iot.bean.Rmap;
import com.iot.service.EtreatmentplantService;
import com.iot.util.AuthToken;
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
@RequestMapping("/api/treatmentplant")
public class EtreatmentplantAppAction extends BaseController {

    @Resource
    private EtreatmentplantService etreatmentplantService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: name
     * @Description: 根据名字查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectByName(String name){
        StringBuilder sql =new StringBuilder();
        sql.append("name like ").append("'%25").append(name).append("%25'");
        Rmap<Etreatmentplant> map = etreatmentplantService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectAll(){
        Rmap<Etreatmentplant> map = etreatmentplantService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }
}
