package center.helloworld.starter.security.interceptor;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.constant.WoguaConstant;
import center.helloworld.common.core.utils.ResponseUtil;
import center.helloworld.starter.security.properties.WoguaCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WoguaServerProtectInterceptor implements HandlerInterceptor {

    private WoguaCloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(WoguaConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(WoguaConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            Result result = new Result();
            result.setFlag(false);
            result.setCode(0);
            result.setMsg("请通过网关获取资源");
            ResponseUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE,HttpServletResponse.SC_FORBIDDEN,result);
            return false;
        }
    }

    public void setProperties(WoguaCloudSecurityProperties properties) {
        this.properties = properties;
    }
}
