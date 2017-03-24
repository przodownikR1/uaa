package pl.java.scalatech.camel.nbp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class CurrencyCodeNotExists extends RuntimeException{
    private static final long serialVersionUID = 8752408505076692930L;

}
