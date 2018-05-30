package cn.cckoo.yu.qrcode.repo;

import lombok.Data;

import static cn.cckoo.yu.qrcode.config.QRCodeConst.*;

@Data
public class QRCode {

    private int width = DEFAULT_SIZE;
    private int height = DEFAULT_SIZE;
    private String type = DEFAULT_TYPE;
    private String outFileUri;
    private String logoUri = DEFAULT_LOGO_URI;
    private String content;

    public String getOutFileUrl() {
        return DEFAULT_DIRECTORY + outFileUri + "." + DEFAULT_TYPE;
    }
}
