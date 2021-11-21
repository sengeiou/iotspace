package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Etransportcompany;
import com.iot.bean.Rmap;
import com.iot.service.EtransportcompanyService;
import com.iot.util.AuthToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: Barry
 * @Date: 2020/4/16
 * @Description:
 */
@RestController
@RequestMapping("/api/transportcompany")
public class EtransportcompanyAppAction extends BaseController {

    @Resource
    private EtransportcompanyService etransportcompanyService;

    /**
     * @Auther: Barry
     * @Date: 2020/5/28
     * @Param: treatmentplantid
     * @Description: 根据处理厂id查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantId", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByTreatmentplantId(Integer treatmentplantid){
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = ").append(treatmentplantid);
        sql.append(" or treatmentplantid is NULL ");
        map = etransportcompanyService.selectBySql(sql.toString());
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
     * @Description: 根据名字查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByName(String name){
        StringBuilder sql =new StringBuilder();
        sql.append("name like ").append("'%25").append(name).append("%25'");
        Rmap<Etransportcompany> map = etransportcompanyService.selectBySql(sql.toString());
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
    public Rmap<Etransportcompany> selectAll(){
        Rmap<Etransportcompany> map = etransportcompanyService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

}
