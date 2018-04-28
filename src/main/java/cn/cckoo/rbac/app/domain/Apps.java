package cn.cckoo.rbac.app.domain;

import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.app.repo.AppRepo;
import cn.cckoo.rbac.common.exception.AddAppFailedException;
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

    public void generateTokenAndAdd(App app) throws AddAppFailedException {
        app.generateToken();
        add(app);
    }

    public void add(App app) throws AddAppFailedException {
        try {
            appRepo.save(app);
        } catch (IllegalArgumentException e) {
            throw new AddAppFailedException();
        }
    }

    public List<App> findAll() {
        return appRepo.findAll();
    }
}
