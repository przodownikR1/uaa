package pl.java.scalatech.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.camel.nbp.NbpCurrentExchange;

@RestController
@RequiredArgsConstructor
class CurrencyController {
    
    private final NbpCurrentExchange ce;
    
    @GetMapping("/byCode/{code}")
    public String getMutlipierByCode(@PathVariable String code){
        return ce.getCurrentMultiplier(code);
    }
    
}
