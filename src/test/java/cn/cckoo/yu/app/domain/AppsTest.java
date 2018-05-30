package cn.cckoo.yu.app.domain;

import cn.cckoo.yu.app.builder.AppBuilder;
import cn.cckoo.yu.app.repo.App;
import cn.cckoo.yu.app.repo.AppRepo;
import cn.cckoo.yu.common.exception.AddAppFailedException;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AppsTest {
    private static final int TOKEN_LENGTH = 8;
    AppRepo mockAppRepo = mock(AppRepo.class);
    Apps apps = new Apps(mockAppRepo);
    App app = AppBuilder.build();

    @Test
    public void should_generate_token_and_add_app() throws AddAppFailedException {
        apps.generateTokenAndAdd(app);

        assertThat(app.getToken()).isNotNull();
        assertThat(app.getToken().length()).isEqualTo(TOKEN_LENGTH);
        verify(mockAppRepo).save(app);
    }

    @Test
    public void should_throw_app_add_failed_exception_when_save_with_exception() throws AddAppFailedException {
        given_app_repo_save_will_failed();
        assertThrows(AddAppFailedException.class, () -> apps.generateTokenAndAdd(app));
    }

    private void given_app_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAppRepo).save(app);
    }
}
