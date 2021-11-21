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
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Auther: Barry
 * @Date: 2020/4/28
 * @Description:
 */
@Controller
public class TestController {

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: msg
     * @Param: httpServletResponse
     * @Description: 生成条形码
     */
@RequestMapping("test001")
    public static void getBarCode(String msg, HttpServletResponse httpServletResponse){
        try {
            ServletOutputStream stream = httpServletResponse.getOutputStream();
            //选择条形码类型(好多类型可供选择)
            Code128Bean bean=new Code128Bean();
            //设置长宽
            final double moduleWidth=0.20;
            final int resolution=150;
            bean.setModuleWidth(moduleWidth);
            bean.doQuietZone(false);
            String format = "image/png";
            httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + new String("二维码.png".getBytes("utf-8"), "iso8859-1"));
            httpServletResponse.setContentType("application/x-download");
            // 输出流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider((OutputStream) stream, format,
                    resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //生成条码
            bean.generateBarcode(canvas,msg);
            canvas.finish();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: width
     * @Param: request
     * @Param: resp
     * @Description: 生成二维码
     */
    @RequestMapping("teeone")
    public void download(@RequestParam final Integer width, final HttpServletRequest request, final HttpServletResponse resp) throws IOException {
        ServletOutputStream stream = null;
        try {
            final String url = "科室二维码";
            if (StringUtils.isNotBlank(url)) {
                final int height = width;
                stream = resp.getOutputStream();
                final QRCodeWriter writer = new QRCodeWriter();
                final BitMatrix bitMatrix = writer.encode( new String(url.getBytes("utf-8"), "iso8859-1"), BarcodeFormat.QR_CODE, height, (int) width);
                final int[] rec = bitMatrix.getEnclosingRectangle();
                final int resWidth = rec[2] + 1;
                final int resHeight = rec[3] + 1;
                final BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
                resMatrix.clear();
                for (int i = 0; i < resWidth; ++i) {
                    for (int j = 0; j < resHeight; ++j) {
                        if (bitMatrix.get(i + rec[0], j + rec[1])) {
                            resMatrix.set(i, j);
                        }
                    }
                }
                resp.addHeader("Content-Disposition", "attachment;filename=" + new String("二维码.png".getBytes("utf-8"), "iso8859-1"));
                resp.setContentType("application/x-download");
                MatrixToImageWriter.writeToStream(resMatrix, "png", (OutputStream) stream);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
