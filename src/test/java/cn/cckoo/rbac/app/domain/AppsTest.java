package cn.cckoo.rbac.app.domain;

import cn.cckoo.rbac.app.builder.AppBuilder;
import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.app.repo.AppRepo;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AppsTest {
    private static final int TOKEN_LENGTH = 8;
    AppRepo mockAppRepo = mock(AppRepo.class);
    Apps apps = new Apps(mockAppRepo);
    App app = AppBuilder.build();

    @Test
    public void should_add_app() {
        apps.add(app);
        verify(mockAppRepo).save(app);
    }

    @Test
    public void should_return_true_when_save_with_no_exception() {
        assertTrue(apps.add(app));
    }

    @Test
    public void should_return_false_when_save_with_exception() {
        given_app_repo_save_will_failed();

        assertTrue(!apps.add(app));
    }

    private void given_app_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAppRepo).save(app);
    }
}
