package cn.cckoo.yu.qrcode.domain;

import cn.cckoo.yu.common.util.MatrixToImageWriter;
import cn.cckoo.yu.qrcode.repo.QRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 本类用于对我们二维码进行参数的设定,生成我们的二维码：
 */
@Component
public class QRCodeFactory {
    public static final String imageUrl = "/tmp/images/";

    public void createToWeb(QRCode qrCode, OutputStream outputStream) throws WriterException, IOException {
        BitMatrix bitMatrix = getBitMatrix(qrCode);
        MatrixToImageWriter.writeToStream(bitMatrix, qrCode.getType(), outputStream, qrCode.getOutFileUri());
    }

    public String createToFile(QRCode qrCode) throws IOException, WriterException {
        BitMatrix bitMatrix = getBitMatrix(qrCode);

        // 生成二维码图片文件

        File outputFile = new File(imageUrl + qrCode.getOutFileUrl());
        //输出到文件
        MatrixToImageWriter.writeToFile(bitMatrix, qrCode.getType(), outputFile, qrCode.getOutFileUri());

        return outputFile.getAbsolutePath();
    }

    public BufferedImage createBufferedImage(QRCode qrCode) throws IOException, WriterException {
        BitMatrix bitMatrix = getBitMatrix(qrCode);

        //输出到文件
        return MatrixToImageWriter.writeToBufferedImage(bitMatrix, qrCode.getType(), qrCode.getLogoUri());
    }

    private BitMatrix getBitMatrix(QRCode qrCode) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
        //hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        return new MultiFormatWriter().encode(qrCode.getContent(),//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                qrCode.getWidth(), //条形码的宽度
                qrCode.getHeight(), //条形码的高度
                hints);
    }
}
