package center.helloworld.server.demo.service.app.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/21 18:01
 */
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "febs-server-system";
    }

    @GetMapping("useradd")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String useradd(){
        return "wogua-useradd";
    }

    @GetMapping("userupdate")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String userupdate(){
        return "wogua-userupdate";
    }

    @GetMapping("usertest")
    @PreAuthorize("hasAnyAuthority('user:test')")
    public String usertest(){
        return "wogua-test";
    }



    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
