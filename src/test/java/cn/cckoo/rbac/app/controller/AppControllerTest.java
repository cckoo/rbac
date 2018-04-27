package cn.cckoo.rbac.app.controller;

import cn.cckoo.rbac.app.builder.AppBuilder;
import cn.cckoo.rbac.app.domain.Apps;
import cn.cckoo.rbac.app.repo.App;
import cn.cckoo.rbac.common.respond.Respond;
import cn.cckoo.rbac.common.respond.SuccessRespond;
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

    private Respond addApp() {
        return controller.add(app);
    }

    public class Add {
        @Test
        public void add() {
            addApp();
            verify(mockApps).generateTokenAndAdd(app);
        }
    }

    public class Success {
        @Test
        public void should_return_success_respond_at_normal_situation() {
            Respond respond = addApp();
            assertThat(respond).isInstanceOf(SuccessRespond.class);
        }
    }
}