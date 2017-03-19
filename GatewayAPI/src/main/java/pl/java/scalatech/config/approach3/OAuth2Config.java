package pl.java.scalatech.config.approach3;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import pl.java.scalatech.config.approach2.SecConfig;

@Configuration
@EnableAuthorizationServer
@Profile("a3")
class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	
	private final SecConfig secConfig;

	public OAuth2Config(AuthenticationManager authenticationManager,SecConfig securityConfig) {
		this.authenticationManager = authenticationManager;
		this.secConfig = securityConfig;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
	}

	@Override
	// @formatter:off 
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		     .withClient(secConfig.getClientId())
		     .secret(secConfig.getClientSecret())
			 .authorizedGrantTypes(secConfig.getGrantTypes())
			 .scopes(secConfig.getScopes());
	}
    // @formatter:on
}