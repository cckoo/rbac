package cn.cckoo.yu.qrcode.domain;


import cn.cckoo.yu.common.util.Md5;
import cn.cckoo.yu.qrcode.repo.QRCode;
import com.google.zxing.WriterException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class UrlQRCodes {
    private final QRCodeFactory qrCodeFactory;

    @Autowired
    public UrlQRCodes(QRCodeFactory qrCodeFactory) {
        this.qrCodeFactory = qrCodeFactory;
    }

    public BufferedImage createToBufferedImageForTerminal(long terminalId, String accessKey, String uri) throws IOException, WriterException {
        QRCode qrCode = new QRCode();
        String content = getContent(terminalId, accessKey, uri);
        qrCode.setContent(content);
        qrCode.setOutFileUri("terminal-" + terminalId);
        return qrCodeFactory.createBufferedImage(qrCode);
    }

    private String getContent(long terminalId, String accessKey, String uri) {
        JSONObject json = new JSONObject();
        json.put("terminalId", terminalId);
        long time = System.currentTimeMillis();
        json.put("createTime", time);
        String sign = makeSign(json.toJSONString(), accessKey);
        return uri + "?terminalId=" + terminalId + "&createTime=" + time + "&sign=" + sign;
    }

    private String makeSign(String string, String code) {
        return Md5.getMD5(string + code);
    }

    public boolean checkSign(String string, String code, String sign) {
        return Md5.getMD5(string + code).equals(sign);
    }

    public void createToWeb(String content, OutputStream outputStream) throws IOException, WriterException {
        QRCode qrCode = new QRCode();
        qrCode.setContent(content);
        qrCodeFactory.createToWeb(qrCode, outputStream);
    }
}
