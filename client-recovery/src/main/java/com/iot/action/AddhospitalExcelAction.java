package com.iot.action;

import com.iot.service.AddHospitalExcelService;
import com.iot.util.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: Barry
 * @Date: 2020/5/28
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/addHospitalExcel")
public class AddhospitalExcelAction {
    @Resource
    private AddHospitalExcelService addHospitalExcelService;

    @AuthToken
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> keep(MultipartFile excelFile,  Integer transportcompanyid) {
        Map<String, Object> map = new HashMap<>();
        File newFile = null;
        try{
            if(null == excelFile || null == transportcompanyid){
                map.put("status", 240);
                map.put("message", "参数不全");
                return map;
            }
            String originalFilename = excelFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            if (!suffix.equals("xls") && !suffix.equals("xlsx")) {
                map.put("status", 240);
                map.put("message", "文件格式不正确");
                return map;
            }
            //临时文件目录  /opt/data/temp/
            String tempFilePath =File.separator+"opt"+File.separator+"data"+File.separator+"temp"+File.separator;

            try{
                File newFile1 = new File(tempFilePath);
                if (!newFile1.exists()) {
                    newFile1.mkdirs();
                }
                tempFilePath = tempFilePath + UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
                newFile = new File(tempFilePath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                fos.write(excelFile.getBytes());
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
                map.put("status", 240);
                map.put("message", "文件上传失败");
                return map;
            }
            try{
                System.out.println("{  }医院excel导入绝对路径{   }"+newFile.getAbsolutePath());
                addHospitalExcelService.addhospital(newFile, transportcompanyid);
            }catch(Exception e){
                e.printStackTrace();
                map.put("status", 240);
                map.put("message","创建医院异常");
                return map;
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", 240);
            map.put("message", "系统异常");
        }
        //删除文件
        if(null != newFile && newFile.exists()){
            try{
                newFile.delete();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        map.put("status", 260);
        return map;
    }
}
