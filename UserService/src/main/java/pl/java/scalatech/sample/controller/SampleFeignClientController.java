package pl.java.scalatech.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.config.GreetingFeignResource;

@RestController
public class SampleFeignClientController {
    @Autowired
    private GreetingFeignResource greetingFeignResource;
    
    @GetMapping("/feignTest/{name}")
    String getTest(@PathVariable String name){
      return greetingFeignResource.getMessage(name);   
    }
    @GetMapping("/feignTest2/{name}")
    String getTest2(){
      return greetingFeignResource.getMessageNoName();   
    }
}
