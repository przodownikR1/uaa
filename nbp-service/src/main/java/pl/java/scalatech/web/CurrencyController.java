package pl.java.scalatech.web;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
class CurrencyController {

    private final NbpCurrentExchange ce;
    private final DiscoveryClient client;

    @GetMapping("/byCode/{code}")
    public String getMutlipierByCode(@PathVariable String code) {
        ServiceInstance localInstance = client.getLocalServiceInstance();
        return ce.getCurrentMultiplier(code) + " " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }

}
