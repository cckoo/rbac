package cn.cckoo.rbac.common.handler;

import cn.cckoo.rbac.common.exception.BaseException;
import cn.cckoo.rbac.common.respond.FailedRespond;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {
    BaseException mockBaseException = mock(BaseException.class);
    GlobalExceptionHandler handler = new GlobalExceptionHandler();
    @Test
    public void catch_base_exception_will_return_failed_respond() {
        assertThat(handler.baseExceptionHandler(mockBaseException)).isInstanceOf(FailedRespond.class);
    }

}