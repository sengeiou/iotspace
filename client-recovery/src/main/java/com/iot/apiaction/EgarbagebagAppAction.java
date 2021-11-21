package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.EgarbagebagService;
import com.iot.service.EgarbagebagrecordService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2020/4/16
 * @Description:
 */
@RestController
@RequestMapping("/api/garbagebag")
public class EgarbagebagAppAction extends BaseController {

    @Resource
    private EgarbagebagService egarbagebagService;
    @Resource
    private EgarbagebagrecordService egarbagebagrecordService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param: record
     * @Description:   保洁创建垃圾袋
     */
    @AuthToken
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Rmap<Egarbagebag> create(Egarbagebag record, Integer userid) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        if(null == userid){
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        map = egarbagebagService.selectBySql("barcode = '"+record.getBarcode()+"'");
        if(null == map.getList() && null == map.getMessage()){
            Date now = new Date();
            record.setTime(now);
            map = egarbagebagService.insert(record);
            if (null != map.getObject()) {
                map.setStatus(260);
                //创建垃圾袋记录
                Egarbagebagrecord garbagebagrecord = new Egarbagebagrecord();
                garbagebagrecord.setUserid(userid);
                garbagebagrecord.setGarbagebagid(map.getObject().getId());
                garbagebagrecord.setStatus(record.getStatus());
                garbagebagrecord.setTime(now);
                garbagebagrecord.setBelongid(record.getBelongid());
                garbagebagrecord.setBelongname(record.getBelongname());
                garbagebagrecord.setBelongtype(record.getBelongtype());
                egarbagebagrecordService.insert(garbagebagrecord);
            } else {
                map.setStatus(240);
                if (null == map.getMessage()) {
                    map.setMessage("创建垃圾袋失败");
                }
            }
        }else{
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("该条码已使用");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: barcode
     * @Param: userid
     * @Param: belongid
     * @Param: belongtype
     * @Param: belongname
     * @Param: status
     * @Description: 扫码
     */
    @AuthToken
    @RequestMapping(value = "/process", method = RequestMethod.PUT)
    public Rmap<Egarbagebag> seal(String barcode, Integer userid,Integer belongid, String belongtype,String belongname,String status) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        if(StringUtils.isBlank(barcode) || null == userid || null == belongid || StringUtils.isBlank(belongtype)||
                StringUtils.isBlank(belongname) || StringUtils.isBlank(status)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = egarbagebagService.selectBySql("barcode = '"+barcode+"'");
        if(null != map.getList()){
            Egarbagebag garbagebag = map.getList().get(0);
            if(garbagebag.getStatus().equals(status)){
                map.setStatus(240);
                map.setMessage("该垃圾袋已"+status);
                return map;
            }
            Date now = new Date();
            garbagebag.setStatus(status);
            garbagebag.setBelongid(belongid);
            garbagebag.setBelongtype(belongtype);
            garbagebag.setBelongname(belongname);
            garbagebag.setTime(now);
            map = egarbagebagService.update(garbagebag);
            if(null != map.getObject()){
                map.setStatus(260);
                //创建记录
                Egarbagebagrecord garbagebagrecord = new Egarbagebagrecord();
                garbagebagrecord.setUserid(userid);
                garbagebagrecord.setGarbagebagid(garbagebag.getId());
                garbagebagrecord.setStatus(status);
                garbagebagrecord.setBelongid(belongid);
                garbagebagrecord.setBelongtype(belongtype);
                garbagebagrecord.setBelongname(belongname);
                garbagebagrecord.setTime(now);
                egarbagebagrecordService.insert(garbagebagrecord);
            }else{
                map.setStatus(240);
                if (null == map.getMessage()) {
                    map.setMessage("处理失败");
                }
            }
        }else{
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("未查询到垃圾袋信息");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param:
     * @Description: 垃圾袋类型
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("塑料");
        select.setValue("塑料");
        list.add(select);
        select = new Select();
        select.setText("玻璃瓶");
        select.setValue("玻璃瓶");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param:
     * @Description: 垃圾袋状态列表
     */
    @AuthToken
    @RequestMapping(value = "/statusList", method = RequestMethod.GET)
    public Rmap<Select> statusList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("使用中");
        select.setValue("使用中");
        list.add(select);
        select = new Select();
        select.setText("封袋");
        select.setValue("封袋");
        list.add(select);
        select = new Select();
        select.setText("运输");
        select.setValue("运输");
        list.add(select);
        select = new Select();
        select.setText("暂存");
        select.setValue("暂存");
        list.add(select);
        select = new Select();
        select.setText("已回收");
        select.setValue("已回收");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }


}
