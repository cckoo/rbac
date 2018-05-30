package cn.cckoo.yu.common.exception;

public class AddAppFailedException extends BaseException{

    public AddAppFailedException() {
        super(ExceptionCode.ADD_APP_FAILED_CODE, "Add app failed");
    }
}
