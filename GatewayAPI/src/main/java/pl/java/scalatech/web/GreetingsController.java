package pl.java.scalatech.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingsController {
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/secured/read")
    @ResponseBody
    public String read(Authentication authentication) {
        return String.format("Read Called: Hello %s", authentication.getCredentials());
    }

    @PreAuthorize("#oauth2.hasScope('resource.write')")
    @GetMapping(value = "/secured/write")
    @ResponseBody
    public String write(Authentication authentication) {
        return String.format("Write Called: Hello %s", authentication.getCredentials());
    }
}