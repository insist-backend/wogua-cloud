package center.helloworld.starter.security.handler;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.code.AuthExceptionCode;
import center.helloworld.common.core.utils.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/22 11:31
 */
public class WoguaAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Result result = new Result();
        result.setFlag(AuthExceptionCode.ERROR_NO_PERMISSION.isFlag());
        result.setCode(AuthExceptionCode.ERROR_NO_PERMISSION.getCode());
        result.setMsg(AuthExceptionCode.ERROR_NO_PERMISSION.getMessage());
        ResponseUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, result);
    }
}
