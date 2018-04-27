package cn.cckoo.rbac.app.controller;

import cn.cckoo.rbac.app.domain.Apps;
import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.common.respond.Respond;
import cn.cckoo.rbac.common.respond.SuccessRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {

    private Apps apps;

    @Autowired
    AppController(Apps apps) {
        this.apps = apps;
    }

    @PostMapping("/add")
    public Respond add(App app) {
        apps.generateTokenAndAdd(app);
        return new SuccessRespond();
    }

    @PostMapping("/list")
    public List<App> list() {
        return apps.findAll();
    }
}
