package pl.java.scalatech.config.connector;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import feign.Headers;
@FeignClient(name="car-service",fallback=GreetingFeignResource.HystrixUserClientFallback.class)
@Component
public interface GreetingFeignResource {
    
    @GetMapping(value = "/message/sean")
    String getMessageNoName();
    
    @GetMapping(value = "/message/{name}")
    String getMessage(@PathVariable("name") String name);

    @Headers("Content-Type: application/json")
    @PostMapping(value = "/message/{newGreeting}")
    void updateMessage(@PathVariable("newGreeting") String message);
    
    @Component
    class HystrixUserClientFallback implements  GreetingFeignResource{

		private static final String FALLBACK = "fallback...";

		@Override
		public String getMessageNoName() {
			return FALLBACK;
		}

		@Override
		public String getMessage(String name) {			
			return FALLBACK;
		}

		@Override
		public void updateMessage(String message) {			
		}


    }
}

