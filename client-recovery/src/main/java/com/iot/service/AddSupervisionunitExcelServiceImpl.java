package com.iot.service;

import com.iot.bean.Esupervisionunit;
import com.iot.bean.Euser;
import com.iot.bean.Rmap;
import com.iot.util.Md5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2020/5/28
 * @Description:
 */
@Service
@Slf4j
public class AddSupervisionunitExcelServiceImpl implements AddSupervisionunitExcelService{

    @Resource
    private EsupervisionunitService esupervisionunitService;
    @Resource
    private EuserService euserService;

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: file
     * @Param: pid
     * @Param: usertype
     * @Description: 批量导入监管单位
     */
    @Override
    public void addsupervisionunit(File file, Integer pid,String usertype) {
        Workbook wb = null;
        try{
            String level = null;
            if(usertype.equals("国家管理单位")){
                level = "省";
            }else if(usertype.equals("省管理单位")){
                level = "市";
            }else if(usertype.equals("市管理单位")){
                level = "区";
            }
            wb = getWb(file);
            List<List<String>> lists = getExcelRows(getSheet(wb, 0), -1, -1);
            for (List<String> list : lists) {
                try{
                    if (list == lists.get(0)) {//跳过表头
                        continue;
                    }
                    if(StringUtils.isBlank(list.get(0))){ //空跳过
                        continue;
                    }
                    if(this.isValidate(list.get(0))){
                        Esupervisionunit esupervisionunit = new Esupervisionunit();
                        esupervisionunit.setName(list.get(0));
                        esupervisionunit.setAddress(list.get(3));
                        esupervisionunit.setPid(pid);
                        esupervisionunit.setLevel(level);
                        Rmap<Esupervisionunit> esupervisionunitRmap = esupervisionunitService.insert(esupervisionunit);
                        if (null != esupervisionunitRmap.getObject()) {
                            if (isUserValidate(esupervisionunit.getName())) { //监管单位名称 用户名
                                //创建管理员
                                Euser user = new Euser();
                                user.setUsername(esupervisionunit.getName());
                                user.setRealname(list.get(1));
                                Md5 md5 = new Md5();
                                md5.md5s("123456"); //初始密码设置为123456
                                user.setPassword(md5.str);
                                user.setPhone(list.get(2));
                                user.setTime(new Date());
                                user.setType(level+"管理单位");
                                user.setRole("管理员");
                                user.setBelongid(esupervisionunitRmap.getObject().getId());
                                Rmap<Euser> euserRmap = euserService.insert(user);
                                if (null == euserRmap.getObject()) {
                                    if (null == euserRmap.getMessage()) {
                                        System.out.println("{ }excel导入监管单位创建管理员失败{ }");
                                    } else {
                                        System.out.println("{ }excel导入监管单位创建管理员异常{ }");
                                    }
                                }
                            } else {
                                System.out.println("{ }excel导入监管单位创建管理员用户名已存在或查询异常");
                            }
                        } else {
                            if (null == esupervisionunitRmap.getMessage()) {
                                System.out.println("{ }excel导入监管单位创建监管单位失败{ }");
                            } else {
                                System.out.println("{ }excel导入监管单位创建监管单位异常{ }");
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
             System.out.println("{ }excel监管单位异常{ }");
            e.printStackTrace();
        }
        if(null != wb){ //关闭文件
            try {
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: name 监管单位名称
     * @Description: 监管单位唯一性
     */
    public boolean isValidate(String name){
        Rmap<Esupervisionunit> map = esupervisionunitService.selectBySql("name = '" + name +"'");
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


    public Workbook getWb(File file) {
        try {
            return WorkbookFactory.create(file);
        } catch (Exception e) {
            throw new RuntimeException("读取EXCEL文件出错", e);
        }
    }

    public Sheet getSheet(Workbook wb, int sheetIndex) {
        if (wb == null) {
            throw new RuntimeException("工作簿对象为空");
        }
        int sheetSize = wb.getNumberOfSheets();
        if (sheetIndex < 0 || sheetIndex > sheetSize - 1) {
            throw new RuntimeException("工作表获取错误");
        }
        return wb.getSheetAt(sheetIndex);
    }

    public  List<List<String>> getExcelRows(Sheet sheet, int startLine, int endLine) {
        List<List<String>> list = new ArrayList<List<String>>();
        // 如果开始行号和结束行号都是-1的话，则全表读取
        if (startLine == -1)
            startLine = 0;
        if (endLine == -1) {
            endLine = sheet.getLastRowNum() + 1;
        } else {
            endLine += 1;
        }
        //列值
        int rowSize=0;
        for (int i = startLine; i < endLine; i++) {

            Row row = sheet.getRow(i);
            if(i==0) { //读取excel第一行数据，一般是表头，把最大列值设置为表头的列值
                rowSize=row.getLastCellNum();
            }
            if (row == null) { //为空直接跳过
                continue;
            }
//			int rowSize = row.getLastCellNum();
//			int rowSize= row.getLastCellNum();

            List<String> rowList = new ArrayList<String>();
            for (int j = 0; j < rowSize; j++) {
                Cell cell = row.getCell(j);
                String temp = "";
                if (cell == null) {
                    temp = null;
                } else {
                    int cellType = cell.getCellType();
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING:   //字符串类型
                            temp = cell.getStringCellValue().trim(); //去除前后空格
                            temp = StringUtils.isEmpty(temp) ? null : temp;
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:      //布尔类型
                            temp = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:   //公式型
                            temp = String.valueOf(cell.getCellFormula().trim());
                            break;
                        case Cell.CELL_TYPE_NUMERIC: //数值型
                            if (HSSFDateUtil.isCellDateFormatted(cell)) { //日期类型
//							temp = cell.getDateCellValue().toString();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = cell.getDateCellValue();
                                temp = sdf.format(date);
                            } else {  //数值型
                                temp = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_BLANK:  //空值
                            temp = null;
                            break;
                        case Cell.CELL_TYPE_ERROR: //错误类型
                            temp = null;
                            break;
                        default:
                            temp = cell.toString().trim();
                            break;
                    }
                }
                rowList.add(temp);
            }
            list.add(rowList);
        }
        return list;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/4
     * @Param: username
     * @Description: 用户唯一校验
     */
    public boolean isUserValidate(String username){
        Rmap<Euser> map = euserService.selectBySql("username = '" + username + "'");
        if(null != map.getList()){ //有数据
            return false;
        }else{
            if(null != map.getMessage()){//服务异常
                return false;
            }
            return true;
        }
    }


}
