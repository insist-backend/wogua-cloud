package center.helloworld.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:wogua-auth.properties"})
@ConfigurationProperties(prefix = "wogua.auth")
public class WoguaAuthProperties {
    private WoguaClientsProperties[] clients = {};
    /**
     * access_token的有效时间，默认值为60 * 60 * 24秒；
     */
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    /**
     * refresh_token的有效时间，默认值为60 * 60 * 24 * 7秒。
     */
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
}
