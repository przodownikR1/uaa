package pl.java.scalatech;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ComputeController {

    private String welcome = "Welcome to Feign";
    private final DiscoveryClient client;
    private final RestTemplate restTemplate;

    @GetMapping("/message/{name}")
    public String add(@PathVariable(value = "name") String name) {
        ServiceInstance localInstance = client.getLocalServiceInstance();       
        String url = "http://nbp-service/nbp/byCode/USD";
        String response = restTemplate.getForObject(url, String.class);
        return welcome + " " + name + "  current usd value : " + response + " " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }
    
  
    @RequestMapping("/nbp/hello")
    public String nbp() {
        String url = "http://nbp-service/nbp/hello";
        return restTemplate.getForObject(url, String.class);
    }
        
    @PostMapping("/message/{greeting}")
    public void updateGreeting(@PathVariable(value = "greeting") String greeting) {
        welcome = greeting;
        log.info("Greeting update to {}", welcome);
    }
}
