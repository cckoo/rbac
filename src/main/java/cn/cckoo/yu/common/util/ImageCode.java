package cn.cckoo.yu.common.util;


import cn.cckoo.yu.common.exception.BaseException;
import cn.cckoo.yu.common.exception.ExceptionCode;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageCode {
    private static char mapTable[] = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '1',
            '2', '3', '4', '5', '6', '7',
            '8', '9'};

    public static Map<String, Object> getImageCode(int width, int height, OutputStream os) {
        Map<String,Object> returnMap = new HashMap<String, Object>();
        if (width <= 0) width = 100;
        if (height <= 0) height = 58;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        //生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        // 随机产生168条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 168; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //取随机产生的码
        String strEnsure = "";
        //4代表4位验证码,如果要生成更多位的认证码,则加大数值
        for (int i = 0; i < 6; ++i) {
            strEnsure += mapTable[(int) (mapTable.length * Math.random())];
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //直接生成
            String str = strEnsure.substring(i, i + 1);
            g.drawString(str, 14 * i + 6, (i % 2) * 8 + 36);
        }
        // 释放图形上下文
        g.dispose();
        returnMap.put("image",image);
        returnMap.put("strEnsure",strEnsure);
        return returnMap;
    }

    //给定范围获得随机颜色
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static void outPutImageCode(HttpSession session, HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        Map<String,Object> map = ImageCode.getImageCode(100, 58, os);
        session.setAttribute("imageCode", map.get("strEnsure").toString().toLowerCase());
        session.setAttribute("codeTime", new java.util.Date().getTime());
        ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
    }

    public static boolean checkImageCode(String checkCode, HttpSession session) throws Exception {
        Object cko = session.getAttribute("imageCode") ;
        if(cko == null){
            throw new BaseException(ExceptionCode.PARAMS_ERROR, "验证码已失效，请重新输入！");
        }
        String imageCode = cko.toString();
        java.util.Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
        if(StringUtils.isEmpty(checkCode) || imageCode == null ||  !(checkCode.equalsIgnoreCase(imageCode))) {
            throw new BaseException(ExceptionCode.PARAMS_ERROR, "验证码错误！");
        } else if ((now.getTime()-codeTime)/1000/60 > 5) {
            //验证码有效时长为5分钟
            throw new BaseException(ExceptionCode.PARAMS_ERROR, "验证码已失效，请重新输入！");
        }else {
            session.removeAttribute("imageCode");
            return true;
        }
    }
}
