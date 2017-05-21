package pl.java.scalatech.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class EnvReadController {

    private static final String USER = "USER_APP";

    @Value("${" + USER + ":default}")
    String user;

    @GetMapping("/envUser")
    String getEnvUser() {
        return "User : " + user;
    }
}
