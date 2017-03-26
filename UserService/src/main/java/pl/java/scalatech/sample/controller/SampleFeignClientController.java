package pl.java.scalatech.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.config.connector.GreetingFeignResource;
import pl.java.scalatech.config.connector.NBPFeignResource;

@RestController
public class SampleFeignClientController {
    @Autowired
    private GreetingFeignResource greetingFeignResource;
    
    @Autowired
    private NBPFeignResource nbpFeignResource;
    
    
    @GetMapping("/feignTest/{name}")
    String getTest(@PathVariable String name){
      return greetingFeignResource.getMessage(name);   
    }
    @GetMapping("/feignTest2")
    String getTest2(){
      return greetingFeignResource.getMessageNoName();   
    }
    
    
    @GetMapping("/nbpByCode/{code}")
    String getCurrencyByCoce(@PathVariable String code){
      return nbpFeignResource.getMutlipierByCode(code);   
    }
    
    @GetMapping("/nbpSimple")
    String getNbpSimpleMessage(){
      return nbpFeignResource.getMessage();   
    }
}

