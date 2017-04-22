package pl.java.scalatech.client;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.codahale.metrics.MetricRegistry;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.tools.RestRequestTimerInterceptor;

@RequiredArgsConstructor
public class RestClient {
    
    
    private MetricRegistry metricRegistry;
    

    @Autowired
    @Qualifier("restClientHttpFactory")
    private ClientHttpRequestFactory clientHttpRequestFactory;
    
    
    @Bean(name="restClient")
    RestOperations restTemplate() {
        final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.setInterceptors(asList(restRequestTimerInterceptor()));
        return restTemplate;
    }

    @Bean
    RestRequestTimerInterceptor restRequestTimerInterceptor() {
        return new RestRequestTimerInterceptor(metricRegistry);
    }

    @Bean(name = "restClientHttpFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        final SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
//        simpleClientHttpRequestFactory.setConnectTimeout(connectionTimeout);
//        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);
        return simpleClientHttpRequestFactory;
}
}
