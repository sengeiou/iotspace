package com.iot.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author barry
 * 数据导出excel工具类
 *
 */
public class ExcelExport {

    public static void export(OutputStream outputStream, String[] titles, String[] keys, List<Map<String, Object>> data) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        generateHeader(workbook, sheet, titles);
        HSSFCellStyle style = getCellStyle(workbook, false);
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            Map<String, Object> map = data.get(i);
            int columnIndex = 0;
            SimpleDateFormat simpleDateFormat = null;
            for (int j=0;j<keys.length;j++) {
                Object value = map.get(keys[j]);
                HSSFCell cell = row.createCell(columnIndex);
                columnIndex++;
                cell.setCellStyle(style);
                if (value instanceof String) {
                    cell.setCellValue((String)value);
                }else if (value instanceof Integer) {
                    cell.setCellValue(((Integer) value).intValue());
                }else if (value instanceof Long) {
                    cell.setCellValue(((Long) value).longValue());
                }else if (value instanceof Date) {
                    if (null == simpleDateFormat) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    }
                    cell.setCellValue(simpleDateFormat.format(value));
                }else if (value instanceof Double) {
                    cell.setCellValue(((Double) value).doubleValue());
                }else {
                	if(null!=value) {
                		cell.setCellValue(value.toString());
                	}	
                }
            }
        }
        workbook.write(outputStream);
        outputStream.close();
    }
    
    /**
     * 多个sheet页下载
     * @param outputStream
     * @param titles
     * @param keys
     * @param data
     * @return
     * @throws IOException
     */
    public static HSSFWorkbook manyExport(OutputStream outputStream, String[] titles, String[] keys, List<Map<String, Object>> data) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        generateHeader(workbook, sheet, titles);
        HSSFCellStyle style = getCellStyle(workbook, false);
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            Map<String, Object> map = data.get(i);
            int columnIndex = 0;
            SimpleDateFormat simpleDateFormat = null;
            for (int j=0;j<keys.length;j++) {
                Object value = map.get(keys[j]);
                HSSFCell cell = row.createCell(columnIndex);
                columnIndex++;
                cell.setCellStyle(style);
                if (value instanceof String) {
                    cell.setCellValue((String)value);
                }else if (value instanceof Integer) {
                    cell.setCellValue(((Integer) value).intValue());
                }else if (value instanceof Long) {
                    cell.setCellValue(((Long) value).longValue());
                }else if (value instanceof Date) {
                    if (null == simpleDateFormat) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    }
                    cell.setCellValue(simpleDateFormat.format(value));
                }else if (value instanceof Double) {
                    cell.setCellValue(((Double) value).doubleValue());
                }else {
                	if(null!=value) {
                		cell.setCellValue(value.toString());
                	}	
                }
            }
        }
        return workbook;
    }
    
    /**
     * 多个sheet页下载
     * @param workbook
     * @param outputStream
     * @param titles
     * @param keys
     * @param data
     * @throws IOException
     */
    public static void manyExport(HSSFWorkbook workbook,OutputStream outputStream, String[] titles, String[] keys, List<Map<String, Object>> data) throws IOException {
       if(null==workbook) {
    	   workbook = new HSSFWorkbook();
       }
        HSSFSheet sheet = workbook.createSheet();
        generateHeader(workbook, sheet, titles);
        HSSFCellStyle style = getCellStyle(workbook, false);
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            Map<String, Object> map = data.get(i);
            int columnIndex = 0;
            SimpleDateFormat simpleDateFormat = null;
            for (int j=0;j<keys.length;j++) {
                Object value = map.get(keys[j]);
                HSSFCell cell = row.createCell(columnIndex);
                columnIndex++;
                cell.setCellStyle(style);
                if (value instanceof String) {
                    cell.setCellValue((String)value);
                }else if (value instanceof Integer) {
                    cell.setCellValue(((Integer) value).intValue());
                }else if (value instanceof Long) {
                    cell.setCellValue(((Long) value).longValue());
                }else if (value instanceof Date) {
                    if (null == simpleDateFormat) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    }
                    cell.setCellValue(simpleDateFormat.format(value));
                }else if (value instanceof Double) {
                    cell.setCellValue(((Double) value).doubleValue());
                }else {
                	if(null!=value) {
                		cell.setCellValue(value.toString());
                	}	
                }
            }
        }
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void generateHeader(HSSFWorkbook workbook, HSSFSheet sheet, String[] titles) {
        HSSFCellStyle style = getCellStyle(workbook, true);
        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);
        int cellIndex = 0;
        for (int i=0;i<titles.length;i++) {
            String header = titles[i];
            Cell cell = row.createCell(cellIndex);
            sheet.setColumnWidth((cellIndex++), header.getBytes().length * 2 * 255);
            cell.setCellValue(header);
            cell.setCellStyle(style);
        }
    }


    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, boolean isHeader) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        if (isHeader) {//表头样式加粗
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中对齐
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
        }
        return style;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName=URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }
}
