package center.helloworld.server.demo.service;

import center.helloworld.starter.security.annotation.EnableWoguaCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/21 18:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWoguaCloudResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) //注解，表示开启Spring Cloud Security权限注解
public class ServerDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerDemoApp.class, args);
    }
}
