package pl.java.scalatech.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

import pl.java.scalatech.config.approach2.SecConfig;

public class DownstreamServiceHandler {

    private final SecConfig config;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    public DownstreamServiceHandler(@Qualifier("oauth2RestOperations") OAuth2RestTemplate oAuth2RestTemplate,
            SecConfig config) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.config = config;

    }

    public String callRead() {
        return callDownstream(String.format("%s/secured/read", config.getBaseUrl()));
    }

    public String callWrite() {
        return callDownstream(String.format("%s/secured/write", config.getBaseUrl()));
    }

    public String callInvalidScope() {
        return callDownstream(String.format("%s/secured/invalid", config.getBaseUrl()));
    }

    private String callDownstream(String uri) {
        try {
            ResponseEntity<String> responseEntity = this.oAuth2RestTemplate.getForEntity(uri, String.class);
            return responseEntity.getBody();
        } catch (HttpStatusCodeException statusCodeException) {
            return statusCodeException.getResponseBodyAsString();
        }
    }
}