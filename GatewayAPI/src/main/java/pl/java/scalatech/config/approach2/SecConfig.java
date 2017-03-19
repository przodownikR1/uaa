package pl.java.scalatech.config.approach2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Profile(ProfileApp.PROFILE)
@ConfigurationProperties(prefix = "oauth2")
public class SecConfig {
    String appName; 
    String clientId;
    String clientSecret;
    String[] scopes;
    String[] grantTypes;
    int getTokenValidity;
    int getRefreshTokenValidity;
    String baseUrl;
    String checkTokenUrl;

}
