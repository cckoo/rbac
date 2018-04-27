package cn.cckoo.rbac.app.builder;

import cn.cckoo.rbac.app.repo.App;

public class AppBuilder {
    public static App build() {
        App app = new App();
        app.setName("brric");
        app.setDescribe("is a app for manage file");
        return app;
    }
}
