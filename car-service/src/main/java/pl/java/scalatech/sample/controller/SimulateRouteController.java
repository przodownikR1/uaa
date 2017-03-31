import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@EnableScheduling
//@Configuration
@Slf4j
@RequiredArgsConstructor
public class SimulateRoute {

    private final RestTemplate restTemplate;

    @Scheduled(fixedDelay = 8000)
    public void shot() {
        String URI = "http://config-server/health";
        Object result = restTemplate.getForObject(URI, Object.class);
        log.info("result : {}", result);
    }

    @Scheduled(fixedDelay = 8000)
    public void shot2() {
        String URI = "http://zipkin-dashboard/info";
        String result = restTemplate.getForObject(URI, String.class);
        log.info("result : {}", result);

    }

    @Scheduled(fixedDelay = 3000)
    public void serviceNBP() {
        String url = "http://localhost:9010/car/message/hystrix-test";
        String result = restTemplate.getForObject(url, String.class);
        log.info("serviceNBP :  result: {}", result);
    }

}
