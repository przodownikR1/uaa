package pl.java.scalatech.web;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SimpleController {
   
    private final Environment environment;

    @GetMapping("/simple")
    String getMessage(){
        return "sample message from  NBP : port : " +  environment.getProperty("server.port"); 
    }
    
}