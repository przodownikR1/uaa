package pl.java.scalatech;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor
@Slf4j
public class MetricsCollectors implements CommandLineRunner{

    
    private final DiscoveryClient eurekaClient;
    
    private final RestTemplate restTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        
            while(true){ 
              eurekaClient.getServices().forEach(service -> {        System.out.println("discovered service "+ service);
                Map metrics = restTemplate.getForObject("http://"+service+"/metrics",Map.class);
                log.info("metrics : {} ", metrics);          
              });  
            }    
         
        
    }

    
    
}
