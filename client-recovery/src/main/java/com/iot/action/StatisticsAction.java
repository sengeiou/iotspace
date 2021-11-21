package com.iot.action;

import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Rmap;
import com.iot.service.EdepartmentService;
import com.iot.service.EhospitalService;
import com.iot.service.EsupervisionunitService;
import com.iot.service.EtruckhospitalService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: Barry
 * @Date: 2020/4/21
 * @Description:
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsAction extends  BaseController{

    @Resource
    private EhospitalService ehospitalService;
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EdepartmentService edepartmentService;

    //数据月份补全
    private static final String montharray[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: address 行政区
     * @Param: paddress 父级行政区
     * @Param: level 级别 全国 省 市 区
     * @Description:  全国 省 市 区  医院数量/重量
     */
    @AuthToken
    @RequestMapping(value = "/numberStatistics", method = RequestMethod.GET)
    public Map<String, Object> numberStatistics(String  address, String paddress, String level,Integer hospitalid){
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(level)){
            if(level.equals("全国")){
                //医院数量
                Rmap<Integer> hospitalNum = ehospitalService.countryHospitalNum();
                //塑料重量
                Rmap<Integer> plasticweight = etruckhospitalService.countryPlasticWeight();
                //玻璃重量
                Rmap<Integer> glassweight = etruckhospitalService.countryGlassWeight();
                if(null != hospitalNum.getObject()){
                    map.put("hospitalnum", hospitalNum.getObject());
                }else{
                    map.put("hospitalnum", null);
                }
                if(null != plasticweight.getObject()){
                    map.put("plasticweight", plasticweight.getObject());
                }else{
                    map.put("plasticweight", null);
                }
                if(null != glassweight.getObject()){
                    map.put("glassweight", glassweight.getObject());
                }else{
                    map.put("glassweight", null);
                }
            }else if(level.equals("省")){
                Rmap<Integer> hospitalNum = ehospitalService.provinceHospitalNum(address);
                Rmap<Integer> plasticweight = etruckhospitalService.provincePlasticWeight(address);
                Rmap<Integer> glassweight = etruckhospitalService.provinceGlassWeight(address);;
                if(null != hospitalNum.getObject()){
                    map.put("hospitalnum", hospitalNum.getObject());
                }else{
                    map.put("hospitalnum", null);
                }
                if(null != plasticweight.getObject()){
                    map.put("plasticweight", plasticweight.getObject());
                }else{
                    map.put("plasticweight", null);
                }
                if(null != glassweight.getObject()){
                    map.put("glassweight", glassweight.getObject());
                }else{
                    map.put("glassweight", null);
                }
            }else if(level.equals("市")){
                if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
                    address = address+"市";
                }
                Rmap<Integer> hospitalNum = ehospitalService.cityHospitalNum(address);
                Rmap<Integer> plasticweight = etruckhospitalService.cityPlasticWeight(address);
                Rmap<Integer> glassweight = etruckhospitalService.cityGlassWeight(address);;
                if(null != hospitalNum.getObject()){
                    map.put("hospitalnum", hospitalNum.getObject());
                }else{
                    map.put("hospitalnum", null);
                }
                if(null != plasticweight.getObject()){
                    map.put("plasticweight", plasticweight.getObject());
                }else{
                    map.put("plasticweight", null);
                }
                if(null != glassweight.getObject()){
                    map.put("glassweight", glassweight.getObject());
                }else{
                    map.put("glassweight", null);
                }
            }else if(level.equals("区")){
                if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
                    paddress = paddress+"市";
                }
                Rmap<Integer> hospitalNum = ehospitalService.platHospitalNum(address,paddress);
                Rmap<Integer> plasticweight = etruckhospitalService.platPlasticWeight(address,paddress);
                Rmap<Integer> glassweight = etruckhospitalService.platGlassWeight(address,paddress);;
                if(null != hospitalNum.getObject()){
                    map.put("hospitalnum", hospitalNum.getObject());
                }else{
                    map.put("hospitalnum", null);
                }
                if(null != plasticweight.getObject()){
                    map.put("plasticweight", plasticweight.getObject());
                }else{
                    map.put("plasticweight", null);
                }
                if(null != glassweight.getObject()){
                    map.put("glassweight", glassweight.getObject());
                }else{
                    map.put("glassweight", null);
                }
            }
        }else{//医疗机构
            Rmap<Integer> plasticweight = etruckhospitalService.hospitalPlasticWeight(hospitalid);
            //玻璃重量
            Rmap<Integer> glassweight = etruckhospitalService.hospitalGlassWeight(hospitalid);
            map.put("hospitalnum", edepartmentService.countByhospitalid(hospitalid).getObject());
            if(null != plasticweight.getObject()){
                map.put("plasticweight", plasticweight.getObject());
            }else{
                map.put("plasticweight", null);
            }
            if(null != glassweight.getObject()){
                map.put("glassweight", glassweight.getObject());
            }else{
                map.put("glassweight", null);
            }
        }
        if(!map.containsKey("status")){
            map.put("status",260);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: address 行政区
     * @Param: paddress 父级行政区
     * @Param: level 级别 全国 省 市 区
     * @Description:  全国 省 市 区 最新5条回收数据
     */
    @AuthToken
    @RequestMapping(value = "/newData", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> newData(String  address, String paddress, String level,Integer hospitalid){
        Rmap<Etruckhospitalv> map = new  Rmap<Etruckhospitalv>();
        if(StringUtils.isNotBlank(level)){
            if(level.equals("全国")){
                map = etruckhospitalService.countrySelectLimit();
            }else if(level.equals("省")){
                map = etruckhospitalService.provinceSelectLimit(address);
            }else if(level.equals("市")){
                if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
                    address = address+"市";
                }
                map = etruckhospitalService.citySelectLimit(address);
            }else if(level.equals("区")){
                if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
                    paddress = paddress+"市";
                }
                map = etruckhospitalService.platSelectLimit(address,paddress);
            }
        }else{//医疗机构
            map = etruckhospitalService.hospitalSelectLimit(hospitalid);
        }
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: address
     * @Param: paddress
     * @Param: level
     * @Param: hospitalid
     * @Param: flag 0今年 1去年 2前年
     * @Description: 全国 省市区 医疗机构 某年按月分组
     */
    @AuthToken
    @RequestMapping(value = "/yearWeightStatistics", method = RequestMethod.GET)
    public Map<String, Object> yearWeightStatistics(String  address, String paddress, String level,Integer hospitalid,String flag){
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(level)){
            if(level.equals("全国")){
                Rmap<Map<String, Object>> result1 = etruckhospitalService.countryYearGlassWeightGroupMonth(Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.countryYearPlasticWeightGroupMonth(Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassweight", list);
                }else{
                    map.put("glassweight", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticweight", list);
                }else{
                    map.put("plasticweight", null);
                }
            }else if(level.equals("省")){
                Rmap<Map<String, Object>> result1 = etruckhospitalService.provinceYearGlassWeightGroupMonth(address,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.provinceYearPlasticWeightGroupMonth(address,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassweight", list);
                }else{
                    map.put("glassweight", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticweight", list);
                }else{
                    map.put("plasticweight", null);
                }
            }else if(level.equals("市")){
                if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
                    address = address+"市";
                }
                Rmap<Map<String, Object>> result1 = etruckhospitalService.cityYearGlassWeightGroupMonth(address,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.cityYearPlasticWeightGroupMonth(address,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassweight", list);
                }else{
                    map.put("glassweight", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticweight", list);
                }else{
                    map.put("plasticweight", null);
                }
            }else if(level.equals("区")){
                if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
                    paddress = paddress+"市";
                }
                Rmap<Map<String, Object>> result1 = etruckhospitalService.platYearGlassWeightGroupMonth(address,paddress,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.platYearPlasticWeightGroupMonth(address,paddress,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassweight", list);
                }else{
                    map.put("glassweight", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticweight", list);
                }else{
                    map.put("plasticweight", null);
                }
            }
        }else{ //医疗机构
            Rmap<Map<String, Object>> result1 = etruckhospitalService.hospitalYearGlassWeightGroupMonth(hospitalid,Integer.valueOf(flag));
            Rmap<Map<String, Object>> result2 = etruckhospitalService.hospitalYearPlasticWeightGroupMonth(hospitalid,Integer.valueOf(flag));
            if(null != result1.getList()){
                List<Map<String, Object>> list = result1.getList();
                add(list);
                sort(list);
                map.put("glassweight", list);
            }else{
                map.put("glassweight", null);
            }
            if(null != result2.getList()){
                List<Map<String, Object>> list = result2.getList();
                add(list);
                sort(list);
                map.put("plasticweight", list);
            }else{
                map.put("plasticweight", null);
            }
        }
        if(!map.containsKey("status")){
            map.put("status",260);
        }
        return map;
    }


    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: belongid
     * @Param: type
     * @Param: flag  0今年 1去年 2 前年
     * @Description: 某一年数量统计 按月分组
     */
    @AuthToken
    @RequestMapping(value = "/yearNumbertistics", method = RequestMethod.GET)
    public Map<String, Object> yearNumbertistics(String  address, String paddress, String level,Integer hospitalid,String flag){
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(level)){
            if(level.equals("全国")){
                Rmap<Map<String, Object>> result1 = etruckhospitalService.countryYearGlassNumberGroupMonth(Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.countryYearPlasticNumberGroupMonth(Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassnumber", list);
                }else{
                    map.put("glassnumber", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticnumber", list);
                }else{
                    map.put("plasticnumber", null);
                }
            }else if(level.equals("省")){
                Rmap<Map<String, Object>> result1 = etruckhospitalService.provinceYearGlassNumberGroupMonth(address,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.provinceYearPlasticNumberGroupMonth(address,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassnumber", list);
                }else{
                    map.put("glassnumber", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticnumber", list);
                }else{
                    map.put("plasticnumber", null);
                }
            }else if(level.equals("市")){
                if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
                    address = address+"市";
                }
                Rmap<Map<String, Object>> result1 = etruckhospitalService.cityYearGlassNumberGroupMonth(address,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.cityYearPlasticNumberGroupMonth(address,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassnumber", list);
                }else{
                    map.put("glassnumber", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticnumber", list);
                }else{
                    map.put("plasticnumber", null);
                }
            }else if(level.equals("区")){
                if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
                    paddress = paddress+"市";
                }
                Rmap<Map<String, Object>> result1 = etruckhospitalService.platYearGlassNumberGroupMonth(address,paddress,Integer.valueOf(flag));
                Rmap<Map<String, Object>> result2 = etruckhospitalService.platYearPlasticNumberGroupMonth(address,paddress,Integer.valueOf(flag));
                if(null != result1.getList()){
                    List<Map<String, Object>> list = result1.getList();
                    add(list);
                    sort(list);
                    map.put("glassnumber", list);
                }else{
                    map.put("glassnumber", null);
                }
                if(null != result2.getList()){
                    List<Map<String, Object>> list = result2.getList();
                    add(list);
                    sort(list);
                    map.put("plasticnumber", list);
                }else{
                    map.put("plasticnumber", null);
                }
            }
        }else{//医疗机构
            Rmap<Map<String, Object>> result1 = etruckhospitalService.hospitalYearGlassNumberGroupMonth(hospitalid,Integer.valueOf(flag));
            Rmap<Map<String, Object>> result2 = etruckhospitalService.hospitalYearPlasticNumberGroupMonth(hospitalid,Integer.valueOf(flag));
            if(null != result1.getList()){
                List<Map<String, Object>> list = result1.getList();
                add(list);
                sort(list);
                map.put("glassnumber", list);
            }else{
                map.put("glassnumber", null);
            }
            if(null != result2.getList()){
                List<Map<String, Object>> list = result2.getList();
                add(list);
                sort(list);
                map.put("plasticnumber", list);
            }else{
                map.put("plasticnumber", null);
            }
        }
        if(!map.containsKey("status")){
            map.put("status",260);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 数据月份补全
     */
    public List<Map<String, Object>> add(List<Map<String, Object>> list) {
       /* System.out.println(list.toString());*/
        for (String str : montharray) {
            if (!list.toString().contains("name="+str)) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", str);
                map.put("value", 0);
                list.add(map);
            }
        }
        return list;
    }
    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 排序 升序
     */
    public void sort(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                String name1 = (String) m1.get("name");//name1是从你list里面拿出来的一个
                String name2 = (String) m2.get("name");
                return name1.compareTo(name2);
            }
        });
    }



   /* public static void main(String[] args) { //测试
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=  new HashMap<>();
        map1.put("name", "04");
        map1.put("value", 30);
        list.add(map1);
        for(String str: montharray){
            if(!list.toString().contains(str)){
                Map<String,Object> map = new HashMap<>();
                map.put("name", str);
                map.put("value", 0);
                list.add(map);
            }

        }
        sort(list);
        System.out.println(list.toString());
    }*/

}
