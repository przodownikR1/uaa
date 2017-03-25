package pl.java.scalatech.config.connector;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient(name="nbp-service")
@Component
public interface NBPFeignResource {
    
    
	@RequestMapping("/byCode/{code}")
    String getMutlipierByCode(@PathVariable String code);

	  @GetMapping("/simple")
	  String getMessage();
    
    
}

