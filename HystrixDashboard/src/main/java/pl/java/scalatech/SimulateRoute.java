package pl.java.scalatech;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class SimulateRoute {
    
  
    @Autowired
    pl.java.scalatech.DownloadConfig.DownloadCode downloadCode;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    Random r = new Random();

    @Scheduled(fixedDelay = 8000)
    public void shot() {
        String URI = "http://localhost:8787/busy";
        Integer result = restTemplate.getForObject(URI, Integer.class);
        log.info("result : {}", result);
    }

 
    @Scheduled(fixedDelay = 8000)
    public void shot3() {
        String URI = "http://localhost:8787/busy2";
        Integer result = restTemplate.getForObject(URI, Integer.class);
        log.info("result : {}", result);
    
    }

    
    @Scheduled(fixedDelay = 8000)
    public void service() {        
        URI uri = URI.create("http://localhost:8787/service");
        String result = simpleUserInvoke(uri);
        log.info("result : {}", result.substring(0, 1000));
    
    }
    
    @Scheduled(fixedDelay = 3000)
    @HystrixCommand(fallbackMethod = "emptyUser")
    public void serviceUser() {                
        int id = r.nextInt(200);
        URI uri = URI.create("http://localhost:8090/api/user/"+id);
        String result = simpleUserInvoke(uri);
        log.info("+++ id : {},  result: {}", id,result);    
    }

    @HystrixCommand(fallbackMethod = "emptyUser")
    public String simpleUserInvoke(URI uri) {
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
    
    String emptyUser(URI uri){
        return "user not exists or service is not available now !!";
        
    }
    
    String emptyUser(){
        return "user not exists or service is not available now !!";
        
    }
    

}
