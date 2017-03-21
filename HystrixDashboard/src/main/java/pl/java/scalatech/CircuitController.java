package pl.java.scalatech;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.DownloadConfig.DownloadCode;

//@RestController
@Slf4j
public class CircuitController {
   @Autowired
   private DownloadCode downloadCode;
    
   
   public static final int TEST_TIMEOUT = 2000;
   
    private Random random = new Random();

    int second(String token) {
        log.info("+++ token : {}", token);
        return 55;
    }
    
    int second2(String token) {
        log.info("+++ token : {}", token);
        return 22;
    }

    @HystrixCommand(fallbackMethod = "second")
    @GetMapping("/busy")
    int derive(@RequestHeader(value = "X-SelfToken", required = false) String token) {
        log.info("+++ token : {}", token);
        return calculate();
    }
    
   /*
    * 
    */
   
    @HystrixCommand(fallbackMethod = "second2", commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="3"),
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value= "5000")
    })
    @GetMapping("/busy2")
    int derive2(@RequestHeader(value = "X-SelfToken", required = false) String token) {
        log.info("+++ token : {}", token);
        return calculate2();
    }
    
    @HystrixCommand(fallbackMethod = "serviceFallback")
    @GetMapping("/service")
    public String service(@RequestHeader(value = "X-SelfToken", required = false) String token) throws InterruptedException, ExecutionException, TimeoutException {
        log.info("+++ token : {}", token);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<String> result = pool.submit(downloadCode);
        return result.get(1, TimeUnit.SECONDS);
        
    }
    
    
    String serviceFallback(String token){
        return "sorry, resource is not awailable, exception reason: "+token;
    }
    
    
  /*  @HystrixCommand(commandProperties = { @HystrixProperty(name = "executionIsolationStrategy", value = "THREAD") })
    public int getThreadId() {
        return Thread.currentThread().hashCode();
    }*/

    @SneakyThrows
    int calculate() {
        if (random.nextInt(10) < 5) {
            Thread.sleep(random.nextInt(20000));
            throw new RuntimeException("!!!! crash");
        }
        return 1;
    }
    
    @SneakyThrows
    int calculate2() {
        if (random.nextInt(10) > 3) {
            Thread.sleep(15000);
            throw new RuntimeException("!!!! crash");
        }
        return 1;
    }
  
}
