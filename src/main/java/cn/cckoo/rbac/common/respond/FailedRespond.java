package cn.cckoo.rbac.common.respond;

public class FailedRespond extends Respond{
    private static final Object DEFAULT_DATA = null;

    public FailedRespond(int code, String message, Object data) {
        super(code, message, data);
    }

    public FailedRespond(int code, String message) {
        super(code, message, DEFAULT_DATA);
    }
}
