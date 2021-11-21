package com.iot.action;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/fileDownload")
public class FileDownloadAction extends BaseController{

    @RequestMapping(value = "/excel")
    public void barcodeExcel(HttpServletResponse response){
        try {
            ClassPathResource classPathResource = new ClassPathResource("file/企业批量导入医疗机构表.xlsx");
            InputStream inputStream = classPathResource.getInputStream();
            OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
            String fileName = URLEncoder.encode("模板文件.xlsx", "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            byte[] bys = new byte[1024];
            int len = 0;
            // 读一个字节数组
            while ((len = inputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, len);
            }
            inputStream.close();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * @Date 2020/9/7 16:34
     * @Description  科室导入模板文件下载
     * @Param
     * @Return
     */
    @RequestMapping(value = "/departmentExcel")
    public void departmentExcel(HttpServletResponse response){
        try {
            ClassPathResource classPathResource = new ClassPathResource("file/创建科室.xlsx");
            InputStream inputStream = classPathResource.getInputStream();
            OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
            String fileName = URLEncoder.encode("模板文件.xlsx", "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            byte[] bys = new byte[1024];
            int len = 0;
            // 读一个字节数组
            while ((len = inputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, len);
            }
            inputStream.close();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
