package cn.cckoo.rbac.common.util;

import java.util.Random;

public class RandomString {
    private static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generateWithLength(int length) {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(getRandomChar());
        }

        return randomString.toString();
    }

    private static char getRandomChar() {
        Random random = new Random();
        return SOURCES.charAt(random.nextInt(SOURCES.length()));
    }


}
