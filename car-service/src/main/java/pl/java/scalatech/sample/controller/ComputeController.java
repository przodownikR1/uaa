import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ComputeController {

    private String welcome = "Welcome to Feign";
 
    private final RestTemplate restTemplate;
    private final Random random = new Random();
    
    @GetMapping("/message/{name}")
    @HystrixCommand(fallbackMethod = "cache")
    public String add(@PathVariable(value = "name") String name) {            
        String url = "http://nbp-service/nbp/byCode/USD";
        calculate();
        String response = restTemplate.getForObject(url, String.class);
        String result = welcome + " " + name + "  current usd value : " + response;
        log.info("result : {}",result);
        return result;
    }
    
    String cache(String name) {
        log.info("+++ token : {}", name);
        return "I welcome you the great ! " + name + " currency : 3,666" ;
    }
  
    @SneakyThrows
    void calculate() {
        if (random.nextInt(10) < 3) {
            Thread.sleep(random.nextInt(2000));
            throw new RuntimeException("!!!! too slow");
        }        
    }
    
    @RequestMapping("/nbp/hello")
    @HystrixCommand(fallbackMethod="fallback")
    public String nbp() {        
        String url = "http://nbp-service/nbp/hello";
        calculate();
        return restTemplate.getForObject(url, String.class);
    }
    
    
    
    String fallback(){
        return "Service is temporarily slow ..";
    }
        
    @PostMapping("/message/{greeting}")
    public void updateGreeting(@PathVariable(value = "greeting") String greeting) {
        welcome = greeting;
        log.info("Greeting update to {}", welcome);
    }
}
