package pl.java.scalatech.config;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
@FeignClient(name="hystrix-dashboard")
@Component
public interface GreetingFeignResource {
    
    @RequestMapping(method = RequestMethod.GET, value = "/message/sean")
    String getMessageNoName();
    
    @RequestMapping(method = RequestMethod.GET, value = "/message/{name}")
    String getMessage(@PathVariable("name") String name);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/message/{newGreeting}")
    void updateMessage(@PathVariable("newGreeting") String message);
    
}

