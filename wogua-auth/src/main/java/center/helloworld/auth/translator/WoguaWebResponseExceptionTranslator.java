package center.helloworld.auth.translator;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.code.ExceptionTranslatorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WoguaWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        Result result = new Result();
        result.setFlag(false);
        String message = "认证失败";
        log.error(message, e);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
            result.setMsg(ExceptionTranslatorCode.ERROR_UNSUPPROT_GRANT_TYPE.getMessage());
            result.setCode(ExceptionTranslatorCode.ERROR_UNSUPPROT_GRANT_TYPE.getCode());
            return status.body(result);
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                result.setMsg(ExceptionTranslatorCode.ERROR_INVALID_REFRESH_TOKEN.getMessage());
                result.setCode(ExceptionTranslatorCode.ERROR_INVALID_REFRESH_TOKEN.getCode());
                return status.body(result);
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                message = "用户已被锁定，请联系管理员";
                result.setMsg(ExceptionTranslatorCode.ERROR_ACCOUNT_LOCK.getMessage());
                result.setCode(ExceptionTranslatorCode.ERROR_ACCOUNT_LOCK.getCode());
                return status.body(result);
            }
            message = "用户名或密码错误";
            result.setMsg(ExceptionTranslatorCode.ERROR_USERNAME_OR_PASSWORD.getMessage());
            result.setCode(ExceptionTranslatorCode.ERROR_USERNAME_OR_PASSWORD.getCode());
            return status.body(result);
        }
        result.setMsg(ExceptionTranslatorCode.ERROR_AUTH.getMessage());
        result.setCode(ExceptionTranslatorCode.ERROR_AUTH.getCode());
        return status.body(result);
    }
}
