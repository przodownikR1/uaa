package pl.java.scalatech.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiscoveryClientLogger implements CommandLineRunner {

    private final DiscoveryClient discoveryClient;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(discoveryClient.description());

        discoveryClient.getInstances("car-service").forEach((ServiceInstance serviceInstance) -> {
            System.out.println(new StringBuilder(
                    "Instance --> ").append(serviceInstance.getServiceId())
                            .append("\nServer: ").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort())
                            .append("\nURI: ").append(serviceInstance.getUri()).append("\n\n\n"));
        });
    }

}
