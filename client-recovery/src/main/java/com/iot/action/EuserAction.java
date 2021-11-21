package com.iot.action;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.iot.bean.*;
import com.iot.service.*;
import com.iot.util.AuthToken;
import com.iot.util.Md5;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
public class EuserAction extends BaseController{

	@Resource
	private EuserService euserService;
	@Resource
	private EhospitalService ehospitalService;
	@Resource
	private EdepartmentService edepartmentService;
	@Resource
	private EproductionplantService eproductionplantService;
	@Resource
	private EtreatmentplantService etreatmentplantService;
	@Resource
	private EtransportcompanyService etransportcompanyService;
	@Resource
	private EsupervisionunitService supervisionunitService;
	@Resource
	private EtransportcompanyService transportcompanyService;

	/*
	 * @Date 2020/9/7 12:28
	 * @Description 查询回收利用企业运输员
	 *
	 * @Param
	 * @Return
	 */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplant", method = RequestMethod.GET)
    public Rmap<Euser> selectByTreatmentplant(Integer treatmentplantid,Integer transportcompanyid){
        Rmap<Euser> map = new Rmap<Euser>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        if(null != treatmentplantid){
            map = euserService.selectBySql("belongid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")+ and role = '运输员'");
        }else{
            map = euserService.selectBySql("belongid = "+transportcompanyid+" and role = '运输员'");
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
	 * @Date: 2020/4/8
	 * @Param: type
	 * @Description:  运输公司用户
	 */
	@AuthToken
	@RequestMapping(value = "/transportcompanyUser", method = RequestMethod.GET)
	public Rmap<Euser> transportcompanyUser(String role, Integer transportcompany){
		Rmap<Euser> map = euserService.selectBySql("role = '"+role+"' and belongid = "+transportcompany);
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
	 * @Param: belongid
	 * @Param: role
	 * @Description:  所属单位 角色筛选用户
	 */
	@AuthToken
	@RequestMapping(value = "/selectByRoleAndBelongId", method = RequestMethod.GET)
	public Rmap<Euser> selectByRoleAndBelongId(Integer belongid, String role,String type){
		StringBuilder sql = new StringBuilder();
		sql.append("belongid = ").append(belongid);
		sql.append(" and role = '").append(role).append("'");
        sql.append(" and type = '").append(type).append("'");
		Rmap<Euser> map = euserService.selectBySql(sql.toString());
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
	 * @Param: username 用户名
	 * @Param: password 密码
	 * @Description: 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> map = new HashMap<>();
		Rmap<Euser> rmap = euserService.selectBySql("username='" + username + "'");
		if (null != rmap.getList()) {
			List<Euser> list = rmap.getList();
			Euser user = list.get(0);
			if(!user.getRole().equals("管理员")){
				map.put("status", 240);
				map.put("message", "用户无权限");
				return map;
			}
			if (user.getPassword().equals(password)) {
				map.put("status", 260);
				map.put("user", user);
				map.put("token", getToken(username, password));
				if(user.getType().equals("国家管理单位")|| user.getType().equals("省管理单位") || user.getType().equals("市管理单位") || user.getType().equals("区管理单位")){
					Rmap<Esupervisionunitv> supervisionunitMap = supervisionunitService.selectVByPrimaryKey(user.getBelongid());
					if(null != supervisionunitMap.getObject()){
						map.put("supervisionunit", supervisionunitMap.getObject());
					}else{
						map.put("supervisionunit", null);
					}
				}else if(user.getType().equals("医疗机构")){
					Rmap<Ehospitalv> hospitalServiceMap = ehospitalService.selectVByPrimaryKey(user.getBelongid());
					if(null != hospitalServiceMap.getObject()){
						map.put("supervisionunit", hospitalServiceMap.getObject());
					}else{
						map.put("supervisionunit", null);
					}
				}else if(user.getType().equals("回收物流")){ //运输公司
					Rmap<Etransportcompany> transportcompanyMap =  transportcompanyService.selectByPrimaryKey(user.getBelongid());
					if(null != transportcompanyMap.getObject()){
						map.put("supervisionunit", transportcompanyMap.getObject());
					}else{
						map.put("supervisionunit", null);
					}
				}else if(user.getType().equals("回收利用企业")){ // 处理厂
					Rmap<Etreatmentplant> treatmentplantMap = etreatmentplantService.selectByPrimaryKey(user.getBelongid());
					if(null != treatmentplantMap.getObject()){
						map.put("supervisionunit", treatmentplantMap.getObject());
					}else{
						map.put("supervisionunit", null);
					}
				}else if(user.getType().equals("再利用企业")){ //生产厂
					Rmap<Eproductionplant> productionplantMap = eproductionplantService.selectByPrimaryKey(user.getBelongid());
					if(null != productionplantMap.getObject()){
						map.put("supervisionunit", productionplantMap.getObject());
					}else{
						map.put("supervisionunit", null);
					}
				}
			} else {
				map.put("status", 240);
				map.put("message", "密码错误");
			}
		} else {
			map.put("status", 240);
			if (null == rmap.getMessage()) {
				map.put("message", "用户不存在");
			} else { //服务异常
				map.put("message", rmap.getMessage());
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: username
	 * @Param: phone
	 * @Param: password
	 * @Param: sendtime
	 * @Description: 管理员重置密码
	 */
	@AuthToken
	@RequestMapping(value = "/lost", method = RequestMethod.PUT)
	public Map<String, Object> lost(String username,String phone, String password,Long sendtime){
		Map<String, Object> map =new HashMap<>();
		if (new Date().getTime() - sendtime > 300000) { //验证码有效期5分钟
			map.put("status", 240);
			map.put("message", "验证码失效");
			return map;
		}
		Rmap<Euser> userMap = euserService.selectBySql("username = '" + username + "'");
		if (null != userMap.getList()) {
			List<Euser> userList = userMap.getList();
			Euser user = userList.get(0);
			user.setPassword(password);
			if (user.getPhone().equals(phone)) {
				userMap = euserService.update(user);
				user = userMap.getObject();
				if (null != user) {
					map.put("status", 260);
					map.put("user", user);
				} else {
					map.put("status", 240);
					if (null == userMap.getMessage()) {
						map.put("message", "修改密码失败");
					} else {//服务异常
						map.put("message", userMap.getMessage());
					}
				}
			} else {
				map.put("status", 240);
				map.put("message", "手机号码错误");
			}
		} else {
			map.put("status", 240);
			if (null == userMap.getMessage()) {
				map.put("message", "该用户不存在");
			} else {//服务异常
				map.put("message", userMap.getMessage());
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: userid 管理员id
	 * @Description: 管理员
	 */
	@AuthToken
	@RequestMapping(value = "/resetPwd", method = RequestMethod.PUT)
	public Map<String, Object> resetPwd(Integer userid,String password){
		Map<String, Object> map =new HashMap<>();
		Rmap<Euser> userMap =  euserService.selectByPrimaryKey(userid);
		if(null != userMap.getObject()){
			Euser user =userMap.getObject();
			user.setPassword(password);
			userMap = euserService.update(user);
			if(null != userMap.getObject()){
				map.put("status", 260);
			}else{
				map.put("status", 240);
				if(null == userMap.getMessage()){
					map.put("message", "修改密码失败");
				}else{//服务异常
					map.put("message", userMap.getMessage());
				}
			}
		}else{
			map.put("status", 240);
			if (null == userMap.getMessage()){
				map.put("message", "该用户不存在");
			}else{//服务异常
				map.put("message", userMap.getMessage());
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: record
	 * @Param: userid 当前登录管理员id
	 * @Description: 创建用户
	 */
	@AuthToken
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Map<String, Object> insert(Euser record, Integer userid) {
		Map<String, Object> map = new HashMap<>();
		if (null == userid) {
			map.put("status", 240);
			map.put("message", "参数不全");
			return map;
		}
		if (!isValidate(0, record.getUsername())) {
			map.put("status", 240);
			map.put("message", "该用户名已注册");
			return map;
		}
		Rmap<Euser> userMap = euserService.selectByPrimaryKey(userid);
		if (null == userMap.getObject()) {
			map.put("status", 240);
			if (null == userMap.getMessage()) {
				map.put("message", "未查询到当前登录用户信息");
			} else { //异常
				map.put("message", userMap.getMessage());
			}
			return map;
		}
		record.setTime(new Date()); //注册时间
		Md5 md5 = new Md5();
		md5.md5s("123456"); //初始密码设置为123456
		record.setPassword(md5.str);
		Rmap<Euser> rmap = euserService.insert(record);
		if (null != rmap.getObject()) {
			map.put("status", 260);
		} else {
			map.put("status", 240);
			if (null == rmap.getMessage()) {
				map.put("message", "创建失败");
			} else {
				map.put("message", rmap.getMessage());
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: page
	 * @Param: rows
	 * @Param: phone
	 * @Param: username
	 * @Param: type
	 * @Param: begintime
	 * @Param: endtime
	 * @Description: 分页查询用户
	 */

	@AuthToken
	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public Rmap<Euser> selectByPage(Integer page, Integer rows, String phone, String username, String realname, String type, String role, Integer belongid, Date begintime, Date endtime){
		Rmap<Euser> map = new Rmap<Euser>();
		if (null == belongid) {
			map.setStatus(240);
			map.setMessage("参数不全");
			return map;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("belongid = ").append(belongid);
		if (StringUtils.isNotBlank(phone)) {
			sql.append(" and phone like ").append("'%25").append(phone).append("%25'");
		}
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and username like ").append("'%25").append(username).append("%25'");
		}
		if (StringUtils.isNotBlank(realname)) {
			sql.append(" and realname like ").append("'%25").append(realname).append("%25'");

		}
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = '").append(type).append("'");

		}
		if (StringUtils.isNotBlank(role)) {
			sql.append(" and role = '").append(role).append("'");

		}
		if (null != begintime && null != endtime) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
		}
		map = euserService.selectByPage(page, rows, "time", "asc", sql.toString());
		if (null != map.getList()) {
			map.setStatus(260);
		} else {
			map.setStatus(240);
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/26
	 * @Param: userid
	 * @Description: 用户详情 所属单位
	 */
	@RequestMapping(value = "/userDetail", method = RequestMethod.GET)
	public Map<String, Object> userDetail(Integer userid){
		Map<String, Object> map = new HashMap<>();
		Rmap<Euser> userMap = euserService.selectByPrimaryKey(userid);
		if(null == userMap.getObject()){
			map.put("status", 240);
			if(null == userMap.getMessage()){
				map.put("message", "未查询到用户");
			}else{
				map.put("message", userMap.getMessage());
			}
			return map;
		}
		Euser user = userMap.getObject();
		if(user.getType().equals("医疗机构")){
			Rmap<Ehospital> hospitalMap = ehospitalService.selectByPrimaryKey(user.getBelongid());
			map.put("unit", hospitalMap.getObject());
		}else if(user.getType().equals("回收利用企业")){
			Rmap<Etreatmentplant> treatmentplantMap = etreatmentplantService.selectByPrimaryKey(user.getBelongid());
			map.put("unit", treatmentplantMap.getObject());
		}else if(user.getType().equals("再利用企业")){
			Rmap<Eproductionplant> productionplantMap = eproductionplantService.selectByPrimaryKey(user.getBelongid());
			map.put("unit", productionplantMap.getObject());
		}else if(user.getType().equals("回收物流")){
			Rmap<Etransportcompany> transportcompanyMap = transportcompanyService.selectByPrimaryKey(user.getBelongid());
			map.put("unit", transportcompanyMap.getObject());
		}else if(user.getType().equals("国家管理单位") || user.getType().equals("省管理单位") || user.getType().equals("市管理单位") || user.getType().equals("区管理单位") ){
			Rmap<Esupervisionunit> supervisionunitMap = supervisionunitService.selectByPrimaryKey(user.getBelongid());
			map.put("unit", supervisionunitMap.getObject());
		}
		map.put("status", 260);
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/26
	 * @Param: type
	 * @Description: 工作单位列表
	 */
	@RequestMapping(value = "/unitList", method = RequestMethod.GET)
	public Map<String, Object> unitList(String type){
		Map<String, Object> map = new HashMap<>();
		if(type.equals("医疗机构")|| type.equals("医疗机构回收员")){
			Rmap<Ehospital> hospitalMap = ehospitalService.selectAll();
			map.put("list", hospitalMap.getList());
			map.put("type", type);
		}else if(type.equals("科室")){
			Rmap<Edepartment> departmentMap = edepartmentService.selectAll();
			map.put("list", departmentMap.getList());
			map.put("type", type);

		}else if(type.equals("回收利用企业")){
			Rmap<Etreatmentplant> treatmentplantMap = etreatmentplantService.selectAll();
			map.put("list", treatmentplantMap.getList());
			map.put("type", type);
		}else if(type.equals("加工厂")){
			Rmap<Eproductionplant> productionplantMap = eproductionplantService.selectAll();
			map.put("list", productionplantMap.getList());
			map.put("type", type);
		}else if(type.equals("回收物流")){
			Rmap<Etransportcompany> transportcompanyMap = etransportcompanyService.selectAll();
			map.put("list", transportcompanyMap.getList());
			map.put("type", type);
		}
		map.put("status", 260);
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: userid 用户id
	 * @Description: 根据主键查询用户
	 */
	@AuthToken
	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	public Rmap<Euser> selectByPrimaryKey(Integer id){
		Rmap<Euser> map = euserService.selectByPrimaryKey(id);
		if (null != map.getObject()) {
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
	 * @Description: 用户更新
	 */
	@AuthToken
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Rmap<Euser> update(Euser record) {
		Rmap<Euser> map = new Rmap<Euser>();
		if (isValidate(record.getId(), record.getUsername())) {
			Rmap<Euser> rmap = euserService.update(record);
			if (null != rmap.getObject()) {
				map.setStatus(260);
			} else {
				map.setStatus(240);
				if (null == rmap.getMessage()) {
					map.setMessage("修改用户失败");
				} else {
					map.setMessage(rmap.getMessage());
				}
			}
		} else {
			map.setStatus(240);
			map.setMessage("该用户已存在");
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: id 用户id
	 * @Description: 删除用户
	 */
	@AuthToken
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Rmap<Euser> delete(Integer id) {
		Rmap<Euser> map = euserService.delete(id);
		if(null != map.getObject()){
			map.setStatus(260);
		}else {
			map.setStatus(240);
			if(null == map.getMessage()) {
				map.setMessage("删除用户失败");
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: ids 用户id数组
	 * @Description: 批量删除用户
	 */
	@AuthToken
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public Rmap<Euser> deletes(@RequestBody Integer[] ids){
		String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
		Rmap<Euser> map = euserService.deletes(strIds);
		if(null != map.getList()){
			map.setStatus(260);
		}else{
			map.setStatus(240);
			if(null == map.getMessage()){
				map.setMessage("删除用户失败");
			}
		}
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/4/13
	 * @Param:
	 * @Description: 用户类型
	 */
	@AuthToken
	@RequestMapping(value = "/typeList", method = RequestMethod.GET)
	public Rmap<Select> typeList(String type){
		Rmap<Select> map = new Rmap<Select>();
		List<Select> list = new ArrayList<Select>();
		Select select = new Select();
		if(type.equals("系统")){
			select = new Select();
			select.setText("国家管理单位");
			select.setValue("国家管理单位");
			list.add(select);
			select = new Select();
			select.setText("回收物流");
			select.setValue("回收物流");
			list.add(select);
			select = new Select();
			select.setText("回收利用企业");
			select.setValue("回收利用企业");
			list.add(select);
			select = new Select();
			select.setText("再利用企业");
			select.setValue("再利用企业");
			list.add(select);
		}else if(type.equals("国家管理单位")){
			select = new Select();
			select.setText("省管理单位");
			select.setValue("省管理单位");
			list.add(select);
		}else if(type.equals("省管理单位")){
			select = new Select();
			select.setText("市管理单位");
			select.setValue("市管理单位");
			list.add(select);
		}else if(type.equals("市管理单位")){
			select = new Select();
			select.setText("区管理单位");
			select.setValue("区管理单位");
			list.add(select);
		}else if(type.equals("区管理单位")){
			select = new Select();
			select.setText("医疗机构");
			select.setValue("医疗机构");
			list.add(select);
		}
		map.setStatus(260);
		map.setList(list);
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/4/8
	 * @Param:
	 * @Description: 角色列表
	 */
	@AuthToken
	@RequestMapping(value = "/roleList", method = RequestMethod.GET)
	public Rmap<Select> roleList(String type){
		Rmap<Select> map = new Rmap<Select>();
		List<Select> list = new ArrayList<Select>();
		Select select = new Select();
        if(type.equals("系统")){
            select = new Select();
            select.setText("管理员");
            select.setValue("管理员");
            list.add(select);
        }else if(type.equals("国家管理单位")){
            select = new Select();
            select.setText("管理员");
            select.setValue("管理员");
            list.add(select);
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
        }else if(type.equals("省管理单位")){
            select = new Select();
            select.setText("管理员");
            select.setValue("管理员");
            list.add(select);
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
        }else if(type.equals("市管理单位")){
            select = new Select();
            select.setText("管理员");
            select.setValue("管理员");
            list.add(select);
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
        }else if(type.equals("区管理单位")){
            select = new Select();
            select.setText("管理员");
            select.setValue("管理员");
            list.add(select);
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
        }else if(type.equals("医疗机构")){
            select = new Select();
            select.setText("医疗机构回收员");
            select.setValue("医疗机构回收员");
            list.add(select);
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
            select = new Select();
            select.setText("审核员");
            select.setValue("审核员");
            list.add(select);
        } else if(type.equals("回收物流")){
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
            select = new Select();
            select.setText("审核员");
            select.setValue("审核员");
            list.add(select);
			select = new Select();
			select.setText("运输员");
			select.setValue("运输员");
			list.add(select);
        }else if(type.equals("回收利用企业")){
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
            select = new Select();
            select.setText("审核员");
            select.setValue("审核员");
            list.add(select);
        }else if(type.equals("再利用企业")){
            select = new Select();
            select.setText("用户");
            select.setValue("用户");
            list.add(select);
            select = new Select();
            select.setText("审核员");
            select.setValue("审核员");
            list.add(select);
        }
		map.setStatus(260);
		map.setList(list);
		return map;
	}

	/**
	 * @Auther: Barry
	 * @Date: 2020/3/25
	 * @Param: id
	 * @Param: username
	 * @Description: 唯一性校验
	 */
	public boolean isValidate(Integer id, String username){
		Rmap<Euser> map = euserService.selectBySql("username = '" + username + "' and id != " + id);
		if(null != map.getList()){ //有数据
			return false;
		}else{
			if(null != map.getMessage()){//服务异常
				return false;
			}
			return true;
		}
	}

	public String getToken(String username, String password) {
		String token = "";
		token = JWT.create().withAudience(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60*24))
				.sign(Algorithm.HMAC256(password));
		return token;
	}
}