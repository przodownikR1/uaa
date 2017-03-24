package pl.java.scalatech.camel.nbp;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

class RecipientListBean {
    private List<String> uris;

    public RecipientListBean(String... uris) {
        this.uris = Arrays.asList(uris);
    }

    @Handler
    public List<String> route(Exchange exchange) {
        return uris;
    }
}