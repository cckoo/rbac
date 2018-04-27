package cn.cckoo.rbac.common.util;

import java.sql.Timestamp;

public class Date {
    public static Timestamp getNowTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
