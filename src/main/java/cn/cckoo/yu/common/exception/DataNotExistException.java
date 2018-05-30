package cn.cckoo.yu.common.exception;

public class DataNotExistException extends BaseException{
    public DataNotExistException(String message) {
        super(ExceptionCode.DATA_NOT_EXIST_CODE, message);
    }
}
