package pl.java.scalatech.web;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SimpleController {

    private final Environment environment;
    private final DiscoveryClient client;

    @GetMapping("/simple")
    String getMessage() {
        return "sample message from  NBP : port : " + environment.getProperty("server.port");
    }

    @GetMapping("/hello")
    public String hello() {
        ServiceInstance localInstance = client.getLocalServiceInstance();
        return "Hello World: " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }

    @GetMapping("/discovery/{name}")
    public String getDiscoveryInfo(@PathVariable String name) {
        StringBuilder sb = new StringBuilder(
                "Instance --> ");
        client.getInstances(name).forEach((ServiceInstance serviceInstance) -> {
            sb.append(serviceInstance.getServiceId()).append("\nServer: ")
                    .append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort())
                    .append("\nURI: ").append(serviceInstance.getUri()).append("\n\n\n");
        });
        return sb.toString();
    }

}
