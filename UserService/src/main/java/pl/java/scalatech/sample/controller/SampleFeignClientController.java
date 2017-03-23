package pl.java.scalatech.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SampleFeignClientController {
    
    private GreetingFeignResource greetingFeignResource;
    
    @GetMapping("/feignTest")
    String getTest(@PathVariable String name){
      return greetingFeignResource.getMessage(name);   
    }
}

