package pl.java.scalatech.config.approach2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.ProfileApp;

/*
 * AuthorizationServerConfigurerAdapter for carrying out key authentication and authorization functions
 * configure() is used to define what client applications are registered with our authentication service
 * takes a single parameter called clients of type ClientDetailsServiceConfigurer
 * The ClientDetailsServiceConfigurer class supports two different types of stores for application information: a in-memory store and a JDBC store.
 * The two method calls .withClient() and secret() provide the name of the application
(myApp) that we are registering along with a secret (a password, mySecret) that will be
  presented when the myApp application calls our OAuth2 server to receive an OAuth2 access token.
  authorizedGrantTypes() is a passed a comma-separated list of the authorization grant types that will be supported by our OAuth2 service
  The scopes() method is used to define the boundaries that the calling application can
operate in when they are asking our OAuth2 server for an access token.
offer two different versions of the same application, a web-based
application and a mobile phone based application. Each of these apps can use the same client
name and secret key to ask for access to resources protected by our OAuth2 server.

By using scope, we can then define
authorization rules in our protected services that can limit what actions a client application can
take based on the application they are logging in with.
 */

@Configuration
@Profile(ProfileApp.PROFILE)
@EnableAuthorizationServer
@Slf4j
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;

	private final SecConfig secConfig;

	private final UserDetailsService userDetailsService;

	@Autowired
	private TokenStore tokenStore;

	private final DataSource dataSource;

	public OAuth2Config(DataSource dataSource, AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService, SecConfig secConfig) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.secConfig = secConfig;
		this.dataSource = dataSource;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Bean
	public TokenStoreUserApprovalHandler userApprovalHandler() {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	public ApprovalStore approvalStore() throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	/*
	 * @Override public void configure(AuthorizationServerSecurityConfigurer
	 * oauthServer) throws Exception {
	 * oauthServer.allowFormAuthenticationForClients(); }
	 */

	@Override
	// @formatter:off
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		log.info("++++++++++++ configure {}", secConfig);
		clients.jdbc(dataSource).withClient(secConfig.getClientId()).secret(secConfig.getClientSecret())
				.authorizedGrantTypes(secConfig.grantTypes).scopes(secConfig.getScopes())
				.resourceIds(secConfig.getAppName()).accessTokenValiditySeconds(secConfig.getTokenValidity)
				.refreshTokenValiditySeconds(secConfig.getRefreshTokenValidity);
	}
	// @formatter:on

	// @formatter:off
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}
	// @formatter:on

}