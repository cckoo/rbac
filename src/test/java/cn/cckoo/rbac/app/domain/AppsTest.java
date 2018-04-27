package cn.cckoo.rbac.app.domain;

import cn.cckoo.rbac.app.builder.AppBuilder;
import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.app.repo.AppRepo;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AppsTest {
    private static final int TOKEN_LENGTH = 8;
    AppRepo mockAppRepo = mock(AppRepo.class);
    Apps apps = new Apps(mockAppRepo);
    App app = AppBuilder.build();

    @Test
    public void should_generate_token_and_add_app() {
        apps.generateTokenAndAdd(app);

        assertThat(app.getToken()).isNotNull();
        assertThat(app.getToken().length()).isEqualTo(TOKEN_LENGTH);
        verify(mockAppRepo).save(app);
    }

    @Test
    public void should_return_true_when_save_with_no_exception() {
        assertThat(apps.add(app)).isTrue();
    }

    @Test
    public void should_return_false_when_save_with_exception() {
        given_app_repo_save_will_failed();

        assertThat(!apps.add(app)).isTrue();
    }

    private void given_app_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAppRepo).save(app);
    }
}
