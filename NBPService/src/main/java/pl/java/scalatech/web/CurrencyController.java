package pl.java.scalatech.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.camel.nbp.NbpCurrentExchange;

@RestController
class CurrencyController {
    
    private final NbpCurrentExchange ce;
    //4.3 final nie musi by @Autowired before musi 
    public CurrencyController(NbpCurrentExchange currentExchange){
          this.ce =currentExchange;
    }
    @GetMapping("/byCode/{code}")
    public String getMutlipierByCode(@PathVariable String code){
        return ce.getCurrentMultiplier(code);
    }
    
}
