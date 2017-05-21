package pl.java.scalatech.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.config.connector.GreetingFeignResource;
import pl.java.scalatech.config.connector.NBPFeignResource;

@RestController
@RequiredArgsConstructor
public class SampleFeignClientController {

    private final GreetingFeignResource greetingFeignResource;

    private final NBPFeignResource nbpFeignResource;

    @GetMapping("/feignTest/{name}")
    String getTest(@PathVariable String name) {
        return greetingFeignResource.getMessage(name);
    }

    @GetMapping("/feignTest2")
    String getTest2() {
        return greetingFeignResource.getMessageNoName();
    }

    @GetMapping("/nbpByCode/{code}")
    String getCurrencyByCoce(@PathVariable String code) {
        return nbpFeignResource.getMutlipierByCode(code);
    }

    @GetMapping("/nbpSimple")
    String getNbpSimpleMessage() {
        return nbpFeignResource.getMessage();
    }
}
