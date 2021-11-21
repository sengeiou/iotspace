package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.bean.Rmap;
import com.iot.service.EgarbagebagrecordService;
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
@RequestMapping("/api/garbagebagrecord")
public class EgarbagebagrecordAppAction extends BaseController{

    @Resource
    private EgarbagebagrecordService egarbagebagrecordService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param: userid
     * @Description: 查看垃圾袋记录
     */
    @AuthToken
    @RequestMapping(value = "/selectVByUserId", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVByUserId(Integer page, Integer rows,Integer userid){
        Rmap<Egarbagebagrecordv> map = new  Rmap<Egarbagebagrecordv>();
        if(null == userid){
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        map = egarbagebagrecordService.selectVByPage(page, rows,"id","desc","userid = "+userid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

}
