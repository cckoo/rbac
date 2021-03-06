package cn.cckoo.yu.app.controller;

import cn.cckoo.yu.app.domain.Apps;
import cn.cckoo.yu.app.repo.App;
import cn.cckoo.yu.common.exception.AddAppFailedException;
import cn.cckoo.yu.common.respond.Respond;
import cn.cckoo.yu.common.respond.SuccessRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/app")
@PropertySource("classpath:error.properties")
@Validated
public class AppController {

    private Apps apps;

    @Value("${app.add.error}")
    private String error;

    @Autowired
    AppController(Apps apps) {
        this.apps = apps;
    }

    @PostMapping("/add")
    public Respond add(@Valid @ModelAttribute App app) throws AddAppFailedException {
        apps.generateTokenAndAdd(app);
        return new SuccessRespond();
    }

    @PostMapping("/list")
    public Respond list() {
        return new SuccessRespond(apps.findAll());
    }
}
