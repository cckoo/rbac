package cn.cckoo.rbac.common.respond;

public class SuccessRespond extends Respond{
    private static final int SUCCESS_CODE = 0;
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final Object NO_DATA = null;

    public SuccessRespond() {
        super(SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, NO_DATA);
    }

    public SuccessRespond(Object data) {
        super(SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public SuccessRespond(String message, Object data) {
        super(SUCCESS_CODE, message, data);
    }
}
