package pl.java.scalatech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@EnableZuulProxy
@EnableOAuth2Sso
@EnableDiscoveryClient
@EnableHystrix
@Slf4j
@RequiredArgsConstructor
public class GatewayAPIApplication {

    public static void main(String[] args) {
        springPIDAppRun(args, GatewayAPIApplication.class);
    }

    private static void springPIDAppRun(String[] args, Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(
                clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    private final DiscoveryClient discoveryClient;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            discoveryClient.getServices().forEach(service -> {
                discoveryClient.getInstances(service).forEach(inst -> {
                    log.info("++++  service  : {} -> {}", service, inst.getUri());
                });
            });

        };
    }

}
