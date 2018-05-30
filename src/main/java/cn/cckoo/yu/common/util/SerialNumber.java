package cn.cckoo.yu.common.util;

import java.util.Random;

public class SerialNumber {
    private static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static char[] numbers = ("0123456789").toCharArray();
    private static Random numGen = new Random(47);
    private static Random strGen = new Random(47);

    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }

        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(getRandomChar());
        }

        return randomString.toString();
    }

    /** * 产生随机数值字符串 * */
    public static String randomNumString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[numGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    private static char getRandomChar() {
        return SOURCES.charAt(strGen.nextInt(SOURCES.length()));
    }

}
