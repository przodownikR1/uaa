package pl.java.scalatech.config.connector;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nbp-service", fallback = NBPFeignResource.HystrixFallback.class)
@Component
public interface NBPFeignResource {

	@GetMapping(value = "/byCode/{code}")
	String getMutlipierByCode(@PathVariable("code") String code);

	@GetMapping(value = "/simple")
	String getMessage();

	@Component
	class HystrixFallback implements NBPFeignResource {

		private static final String TOO_SLOW = "too slow";

		@Override
		public String getMutlipierByCode(String code) {
			return TOO_SLOW;
		}

		@Override
		public String getMessage() {
			return TOO_SLOW;
		}

	}
}
