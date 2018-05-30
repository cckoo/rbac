package cn.cckoo.yu.common.exception;

public class BaseException extends Exception{
    private final int code;

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
