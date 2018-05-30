package cn.cckoo.yu.common.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zip {
    public static void unzip(String fromFile, String toFile, String charset) throws IOException {
        unzip(new File(fromFile), new File(toFile), Charset.forName(charset));
    }

    public static void unzip(File file, File path, Charset charset) throws IOException {
        ZipInputStream zipInputStream =new ZipInputStream(new FileInputStream(file), charset);
        BufferedInputStream bufferedInputStream =new BufferedInputStream(zipInputStream);
        if (! path.exists())
            path.mkdirs();
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null && !zipEntry.isDirectory()){
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path, zipEntry.getName()));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int b;
            while ((b= bufferedInputStream.read()) != -1)
                bufferedOutputStream.write(b);
            bufferedOutputStream.close();
            fileOutputStream.close();
        }
        bufferedInputStream.close();
        zipInputStream.close();
    }
}
