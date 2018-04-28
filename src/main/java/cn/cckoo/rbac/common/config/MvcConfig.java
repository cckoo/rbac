package cn.cckoo.rbac.common.config;

import cn.cckoo.rbac.common.interceptor.ErrorMessageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorMessageInterceptor());
        super.addInterceptors(registry);
    }
}
