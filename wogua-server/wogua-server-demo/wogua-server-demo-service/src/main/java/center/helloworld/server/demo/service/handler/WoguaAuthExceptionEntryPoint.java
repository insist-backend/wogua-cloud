package center.helloworld.server.demo.service.handler;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.code.AuthExceptionCode;
import center.helloworld.common.core.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

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
 * @since 2022/9/22 11:23
 */
public class WoguaAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Result result = new Result();
        result.setFlag(false);
        result.setCode(AuthExceptionCode.ERROR_INVALID_TOKEN.getCode());
        result.setMsg(AuthExceptionCode.ERROR_INVALID_TOKEN.getMessage());

        ResponseUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_UNAUTHORIZED, result);
    }
}
