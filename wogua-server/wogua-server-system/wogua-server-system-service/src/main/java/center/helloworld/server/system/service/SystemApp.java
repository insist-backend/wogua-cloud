package center.helloworld.server.system.service;

import center.helloworld.starter.security.annotation.EnableWoguaCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWoguaCloudResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) //注解，表示开启Spring Cloud Security权限注解
public class SystemApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class);
    }
}
