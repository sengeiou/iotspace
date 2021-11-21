package com.iot.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: Barry
 * @Date: 2020/4/28
 * @Description:
 */
@Controller
public class TestControllerzip {

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: msg
     * @Param: httpServletResponse
     * @Description: 生成条形码
     */
@RequestMapping("test00001")
    public  void getBarCode(String msg, HttpServletResponse httpServletResponse){
        try {
//            ServletOutputStream stream = httpServletResponse.getOutputStream();
            File file=new File("F:\\测试\\1.png");
            OutputStream ous=new FileOutputStream(file);
            //选择条形码类型(好多类型可供选择)
            Code128Bean bean=new Code128Bean();
            //设置长宽
            final double moduleWidth=0.20;
            final int resolution=150;
            bean.setModuleWidth(moduleWidth);
            bean.doQuietZone(false);
            String format = "image/png";
          /*  httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + new String("二维码.png".getBytes("utf-8"), "iso8859-1"));
            httpServletResponse.setContentType("application/x-download");*/
            // 输出流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format,
                    resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //生成条码
          /*  for(int i = 0;i<2;i++){*/
                bean.generateBarcode(canvas,msg);
                canvas.finish();
           /* }*/
           zip("F:\\测试", "D:\\测试01.zip");
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=啊哈哈");

            FileInputStream fis=new FileInputStream("D:\\测试01.zip");
            BufferedInputStream buff=new BufferedInputStream(fis);

            byte [] b=new byte[1024];//相当于我们的缓存

            long k=0;//该值用于计算当前实际下载了多少字节

            //从response对象中得到输出流,准备下载

            OutputStream myout=httpServletResponse.getOutputStream();

            //开始循环下载

            while(k<file.length()){

                int j=buff.read(b,0,1024);
                k+=j;

                //将b中的数据写到客户端的内存
                myout.write(b,0,j);

            }

            //将写入到客户端的内存的数据,刷新到磁盘
            myout.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void zip(String souceFileName, String destFileName) {
        File file = new File(souceFileName);
        try {
            zip(file, destFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zip(File souceFile, String destFileName) throws IOException {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(destFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream out = new ZipOutputStream(fileOut);
        zip(souceFile, out, "");
        out.close();
    }

    private void zip(File souceFile, ZipOutputStream out, String base)
            throws IOException {

        if (souceFile.isDirectory()) {
            File[] files = souceFile.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (File file : files) {
                zip(file, out, base + file.getName());
            }
        } else {
            if (base.length() > 0) {
                out.putNextEntry(new ZipEntry(base));
            } else {
                out.putNextEntry(new ZipEntry(souceFile.getName()));
            }
            System.out.println("filepath=" + souceFile.getPath());
            FileInputStream in = new FileInputStream(souceFile);

            int b;
            byte[] by = new byte[1024];
            while ((b = in.read(by)) != -1) {
                out.write(by, 0, b);
            }
            in.close();
        }
    }
}
