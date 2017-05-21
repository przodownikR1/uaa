package pl.java.scalatech.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.approach2.SecConfig;

@EnableOAuth2Client
@Configuration
@Profile("a2")
@Slf4j
public class OAuthClientConfig {

    private final SecConfig oauthSetting;

    @Value("${oauth.resource:http://localhost:9001/auth}")
    private String baseUrl;

    @Value("${oauth.authorize:http://localhost:9001/auth/oauth/authorize}")
    private String authorizeUrl;

    @Value("${oauth.token:http://localhost:9001/auth/oauth/token}")
    private String tokenUrl;

    public OAuthClientConfig(SecConfig oauthSetting) {
        super();
        this.oauthSetting = oauthSetting;

    }
    /*
     * @Profile("next") //propably better solution
     * @Bean(name="restOAuth2") public OAuth2RestTemplate
     * restTemplate(UserInfoRestTemplateFactory factory) { return
     * factory.getUserInfoRestTemplate(); }
     */

    @Bean("oauth2RestOperations")
    OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(
                resource(), oauth2ClientContext);
    }

    @Bean
    OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setUserAuthorizationUri(authorizeUrl);
        resource.setClientId(oauthSetting.getClientId());
        resource.setClientSecret(oauthSetting.getClientSecret());
        return resource;
    }

    @Bean
    @Profile("resource")
    OAuth2ProtectedResourceDetails resourceOwner() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri("http://localhost:8080/oauth/token");
        resource.setClientId("client");
        resource.setClientSecret("secret");
        resource.setGrantType("password");
        resource.setUsername("test");
        resource.setPassword("1234");
        return resource;
    }

    @Bean
    @Profile("credential")
    OAuth2ProtectedResourceDetails resourceCredential() {
        log.debug("Creating resource with token URL: '{}', client ID: '{}', client Secret: '{}'", tokenUrl,
                oauthSetting.getClientId(), oauthSetting.getClientSecret());
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId(oauthSetting.getClientId());
        resource.setClientSecret(oauthSetting.getClientSecret());
        return resource;
    }

    /*
     * @Bean public OAuth2RestTemplate redditRestTemplate(OAuth2ClientContext
     * clientContext) { OAuth2RestTemplate template = new
     * OAuth2RestTemplate(reddit(), clientContext); AccessTokenProvider
     * accessTokenProvider = new AccessTokenProviderChain(
     * Arrays.<AccessTokenProvider> asList( new
     * MyAuthorizationCodeAccessTokenProvider(), new
     * ImplicitAccessTokenProvider(), new
     * ResourceOwnerPasswordAccessTokenProvider(), new
     * ClientCredentialsAccessTokenProvider()) );
     * template.setAccessTokenProvider(accessTokenProvider); return template; }
     * @Bean public OAuth2ProtectedResourceDetails reddit() {
     * AuthorizationCodeResourceDetails details = new
     * AuthorizationCodeResourceDetails(); details.setId("reddit");
     * details.setClientId(clientID); details.setClientSecret(clientSecret);
     * details.setAccessTokenUri(accessTokenUri);
     * details.setUserAuthorizationUri(userAuthorizationUri);
     * details.setTokenName("oauth_token");
     * details.setScope(Arrays.asList("identity"));
     * details.setPreEstablishedRedirectUri("http://localhost/login");
     * details.setUseCurrentUri(false); return details; }
     */
}