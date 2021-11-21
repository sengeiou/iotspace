package com.iot.service;

import com.iot.bean.*;
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
public class AddDepartmentExcelServiceImpl implements AddDepartmentExcelService{

    @Resource
    private EdepartmentService edepartmentService;

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: file
     * @Param: hospitalid 医院id
     * @Description: 导入科室
     */
    @Override
    public void adddepartment(File file,Integer hospitalid) {
        Workbook wb = null;
        try{
            wb = getWb(file);
            List<List<String>> lists = getExcelRows(getSheet(wb, 0), -1, -1);
            for (List<String> list : lists) {
                try{
                    if (list == lists.get(0)) {//跳过表头
                        continue;
                    }
                    if(StringUtils.isBlank(list.get(0))){
                        continue;
                    }
                    if(this.isValidate(list.get(0),hospitalid)){ //数据唯一性校验
                        Edepartment edepartment = new Edepartment();
                        edepartment.setName(list.get(0));
                        edepartment.setLeadername(list.get(1));
                        edepartment.setLeaderphone(list.get(2));
                        edepartment.setHospitalid(hospitalid);
                        edepartment.setNote(list.get(3));
                        Rmap<Edepartment> map = edepartmentService.insert(edepartment);
                        if(null == map.getObject()){
                            if(null == map.getMessage()){
                                System.out.println("{ }excel导入创建科室失败{ }");
                            }else{
                                System.out.println("{ }excel导入创建科室异常{ }");
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
             System.out.println("{ }excel导入科室异常{ }");
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
     * @Param: name 科室名
     * @Param: hospitalid 医院id
     * @Description: 科室唯一性校验
     */
    public boolean isValidate(String name, Integer hospitalid){
        Rmap<Edepartment> map = edepartmentService.selectBySql("name = '" + name +"' and hospitalid =  "+hospitalid);
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
        //列最大值，以第一行表头为准
        int rowSize=0;
        for (int i = startLine; i < endLine; i++) {
            //行
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

}
