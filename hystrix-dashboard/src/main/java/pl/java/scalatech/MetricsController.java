package pl.java.scalatech;

import java.util.Map;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

	private final DiscoveryClient eurekaClient;

	private final RestTemplate restTemplate;

	@GetMapping("/metricTest")
	public Map<String, Object> getMetrics() {

		eurekaClient.getServices().forEach(service -> {
			String url = "http://" + service + "/metrics";
			log.error(" @@@@@@  {} , - > {}                 ", service, url);

			Map<String, Object> metrics = restTemplate.getForObject(url, Map.class);
			log.error("metrics : {} ", metrics);
		});
		return Maps.newHashMap();

	}

}
