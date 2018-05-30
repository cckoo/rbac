package cn.cckoo.yu.wechat.domain;

import cn.cckoo.yu.wechat.repo.SessionKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Service
@PropertySource("classpath:wechat.properties")
public class WeChats {
    @Value("${AppID}")
    public String appId;

    @Value("${AppSecret}")
    public String appSecret;

    public String getTokenUrl(String code) {
        return "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appId
                + "&secret="
                + appSecret
                + "&js_code="
                + code
                + "&grant_type=authorization_code";
    }

    public SessionKey getSessionKey(String code) throws IOException {
        String urlString = getTokenUrl(code);
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));

        return new ObjectMapper().readValue(buffer.readLine(), SessionKey.class);
    }

}
