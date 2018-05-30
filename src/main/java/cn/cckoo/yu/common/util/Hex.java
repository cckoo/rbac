package cn.cckoo.yu.common.util;

public class Hex {
    /**
     * 二进制转十六进制
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        String hv;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i] & 0xFF;
            hv = Integer.toHexString(digital);
            if (hv.length() < 2) {
                md5str.append("0");
            }
            md5str.append(hv);
        }
        return md5str.toString().toUpperCase();
    }

    /**
     * 十六进制转二进制
     * @param hexString
     * @return
     */
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 字符转字节
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
