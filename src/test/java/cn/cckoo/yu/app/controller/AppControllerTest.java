package cn.cckoo.yu.app.controller;

import cn.cckoo.yu.app.builder.AppBuilder;
import cn.cckoo.yu.app.domain.Apps;
import cn.cckoo.yu.app.repo.App;
import cn.cckoo.yu.common.exception.AddAppFailedException;
import cn.cckoo.yu.common.respond.Respond;
import cn.cckoo.yu.common.respond.SuccessRespond;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(NestedRunner.class)
public class AppControllerTest {
    App app = AppBuilder.build();
    Apps mockApps = mock(Apps.class);

    AppController controller = new AppController(mockApps);

    private Respond addApp() throws AddAppFailedException {
        return controller.add(app);
    }

    public class Add {
        @Test
        public void add() throws AddAppFailedException {
            addApp();
            verify(mockApps).generateTokenAndAdd(app);
        }
    }

    public class Success {
        @Test
        public void should_return_success_respond_at_normal_situation() throws AddAppFailedException {
            Respond respond = addApp();
            assertThat(respond).isInstanceOf(SuccessRespond.class);
        }
    }
}