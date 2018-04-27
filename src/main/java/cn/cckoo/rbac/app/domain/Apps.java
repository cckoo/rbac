package cn.cckoo.rbac.app.domain;

import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.app.repo.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Apps {
    private AppRepo appRepo;

    @Autowired
    public Apps(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    public boolean generateTokenAndAdd(App app) {
        app.generateToken();
        return add(app);
    }

    public boolean add(App app) {
        try {
            appRepo.save(app);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<App> findAll() {
        return appRepo.findAll();
    }
}
