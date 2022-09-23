package center.helloworld.auth.service.impl;

import center.helloworld.auth.properties.WoguaAuthProperties;
import center.helloworld.auth.properties.WoguaValidateCodeProperties;
import center.helloworld.auth.service.ValidateCodeService;
import center.helloworld.common.core.exception.ApiException;
import center.helloworld.starter.redis.service.RedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private final RedisService redisService;
    private final WoguaAuthProperties properties;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key)) {
            throw new ApiException("验证码key不能为空");
        }
        WoguaValidateCodeProperties code = properties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set("wogua.captcha." + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    @Override
    public void check(String key, String value) throws Exception {
        Object codeInRedis = redisService.get("wogua.captcha." + key);
        if (StringUtils.isBlank(value)) {
            throw new ApiException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new ApiException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new ApiException("验证码不正确");
        }
    }

    private Captcha createCaptcha(WoguaValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), "gif")) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, "gif")) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
