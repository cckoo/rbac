package cn.cckoo.yu.wechat.repo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionKey {
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("openid")
    private String openId;
}
