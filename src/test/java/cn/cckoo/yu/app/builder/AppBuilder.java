package cn.cckoo.yu.app.builder;

import cn.cckoo.yu.app.repo.App;

public class AppBuilder {
    public static App build() {
        App app = new App();
        app.setName("brric");
        app.setDescribe("is a app for manage file");
        return app;
    }
}
