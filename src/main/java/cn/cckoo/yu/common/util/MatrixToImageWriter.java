package cn.cckoo.yu.common.util;

import cn.cckoo.yu.qrcode.domain.QRCodeFactory;
import com.google.zxing.common.BitMatrix;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用，当然你也可以自己写个
 生产条形码的基类
 */
public class MatrixToImageWriter {
    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
//                image.setRGB(x, y,  (matrix.get(x, y) ? Color.YELLOW.getRGB() : Color.CYAN.getRGB()));
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file, String logUri) throws IOException {

        BufferedImage image = toBufferedImage(matrix);
        //设置logo图标
        QRCodeFactory logoConfig = new QRCodeFactory();
        if (null != logUri && !logUri.isEmpty())
            image = setMatrixLogo(image, logUri);

        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }else{
        }
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream, String logUri) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        //设置logo图标
        if (null != logUri && !logUri.isEmpty())
            image = setMatrixLogo(image, logUri);

        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static BufferedImage writeToBufferedImage(BitMatrix matrix, String format, String logUri) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        //设置logo图标
        if (null != logUri && !logUri.isEmpty())
            image = setMatrixLogo(image, logUri);

        return image;
    }


    /**
     * 给生成的二维码添加中间的logo
     *
     * @param matrixImage 生成的二维码
     * @param logUri      logo地址
     * @return 带有logo的二维码
     * @throws IOException logo地址找不到会有io异常
     */
    private static BufferedImage setMatrixLogo(BufferedImage matrixImage, String logUri) throws IOException {
        /**
         * 读取二维码图片，并构建绘图对象
         */
        Graphics2D g2 = matrixImage.createGraphics();
        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();

        /**
         * 读取Logo图片
         */
        Resource resource = new ClassPathResource(logUri);
        BufferedImage logo = ImageIO.read(resource.getFile());

        //开始绘制图片
        g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);
        //绘制边框
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        // 设置笔画对象
        g2.setStroke(stroke);
        //指定弧度的圆角矩形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
        g2.setColor(Color.white);
        // 绘制圆弧矩形
        g2.draw(round);

        //设置logo 有一道灰色边框
        BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        // 设置笔画对象
        g2.setStroke(stroke2);
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
        g2.setColor(new Color(128, 128, 128));
        g2.draw(round2);// 绘制圆弧矩形


        g2.dispose();
        matrixImage.flush();
        return matrixImage;

    }
}
