package pl.java.scalatech.config.connector;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name="nbp-service",fallback=NBPFeignResource.HystrixFallback.class)
@Component
public interface NBPFeignResource {
    
    
	 @RequestMapping(value="/byCode/{code}",method = RequestMethod.GET)
     String getMutlipierByCode(@PathVariable("code") String code);

	  @RequestMapping(method = RequestMethod.GET, value="/simple")
	  String getMessage();
    
	  @Component
	    class HystrixFallback implements  NBPFeignResource{

		@Override
		public String getMutlipierByCode(String code) {
			return "too slow";
		}

		@Override
		public String getMessage() {
			return "too slow";
		}

			


	    }
}

