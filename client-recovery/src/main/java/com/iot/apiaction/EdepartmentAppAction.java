package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Edepartment;
import com.iot.bean.Rmap;
import com.iot.service.EdepartmentService;
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
@RequestMapping("/api/department")
public class EdepartmentAppAction extends BaseController {

    @Resource
    private EdepartmentService edepartmentService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param: id
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Edepartment> selectByPrimaryKey(Integer departmentid){
        Rmap<Edepartment> map = edepartmentService.selectByPrimaryKey(departmentid);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }
}
