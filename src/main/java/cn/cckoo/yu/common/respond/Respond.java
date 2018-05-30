package cn.cckoo.yu.common.respond;

import lombok.Getter;

@Getter
public abstract class Respond {
    protected final int code;
    protected String message;
    protected Object data;

    public Respond(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
