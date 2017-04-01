package pl.java.scalatech.config.connector;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
@FeignClient(name="car-service",fallback=GreetingFeignResource.HystrixUserClientFallback.class)
@Component
public interface GreetingFeignResource {
    
    @RequestMapping(method = RequestMethod.GET, value = "/car/message/sean")
    String getMessageNoName();
    
    @RequestMapping(method = RequestMethod.GET, value = "/car/message/{name}")
    String getMessage(@PathVariable("name") String name);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/car/message/{newGreeting}")
    void updateMessage(@PathVariable("newGreeting") String message);
    
    @Component
    class HystrixUserClientFallback implements  GreetingFeignResource{

		@Override
		public String getMessageNoName() {
			return "fallback...";
		}

		@Override
		public String getMessage(String name) {			
			return "fallback...";
		}

		@Override
		public void updateMessage(String message) {			
		}


    }
}

