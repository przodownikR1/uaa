package pl.java.scalatech.camel.nbp;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.camel.nbp.bean.Tabela_kursow;
@Component("currentExchange")
@Slf4j
public class NbpCurrentExchange {
    private Tabela_kursow tk;
    
    public void store(Tabela_kursow tk){
        log.info("++++ store");
        this.tk = tk;
    }
    
    public String getCurrentMultiplier(String currencyCode){
        log.info(" +++ getCurrentMultiplier {}  ",currencyCode);
        return tk.getPozycje().stream()
        .filter(poz -> poz.getKod_waluty().equals(currencyCode))
        .map(poz->poz.getKurs_sredni())
        .findFirst().orElseThrow(()-> new CurrencyCodeNotExists());
    }
    
}
