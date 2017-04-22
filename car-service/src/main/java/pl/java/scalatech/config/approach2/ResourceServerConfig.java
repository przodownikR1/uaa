package pl.java.scalatech.config.approach2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.ProfileApp;

@EnableResourceServer
@Configuration
@Profile(ProfileApp.PROFILE)
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter{

   /* @Bean 
    public Filter userContextFilter() { 
      UserContextFilter userContextFilter = new UserContextFilter(); 
      return userContextFilter; 
    } */
   
   private final SecConfig secConfig;
 
  
// @formatter:off
  @Override
  public void configure(HttpSecurity http) throws Exception {
      log.info("+++++++             ResourceServerConfig configure : {}",http);
      //http.antMatcher("/secured/**").authorizeRequests().anyRequest().authenticated();

      http.exceptionHandling()
      .and().logout().logoutUrl("/oauth/logout")          
      .and().authorizeRequests().antMatchers("/test/**").permitAll()
      //.and().authorizeRequests().antMatchers("/auth/oauth/token").permitAll()      
      .and().authorizeRequests().antMatchers("/health").access("#oauth2.hasScope('metrics')")
      .and().authorizeRequests().antMatchers("/info").access("#oauth2.hasScope('metrics')")      
      .antMatchers(HttpMethod.OPTIONS, "/api/user/**","/api/car/**").access("#oauth2.hasScope('read')")
      .antMatchers(HttpMethod.POST, "/api/user/**","/api/car/**").access("#oauth2.hasScope('write')")
      .antMatchers(HttpMethod.PUT, "/api/user/**","/api/car/**").access("#oauth2.hasScope('update')")
      .antMatchers(HttpMethod.PATCH, "/api/user/**","/api/car/**").access("#oauth2.hasScope('write')")
      .antMatchers(HttpMethod.DELETE, "/api/user/**","/api/car/**").access("#oauth2.hasScope('remove')")
      .antMatchers("/metrics/**").access("#oauth2.hasScope('metrics')")
      .antMatchers("/mappings/**").access("#oauth2.hasScope('metrics')")
      .antMatchers("/shutdown/**").access("#oauth2.hasScope('shutdown')")
      .and().authorizeRequests()
      .anyRequest().authenticated();
      /*.and()
      .headers()
      .frameOptions().deny()
      .xssProtection().xssProtectionEnabled(true).and()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
      
      http.headers().frameOptions().disable();
      //http.csrf().disable().anonymous().disable();
      
/*
      .csrf()
          .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/token"))
          .and()
      */
   }
    // @formatter:on

  
       
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {       
      resources.resourceId(secConfig.getAppName()).tokenServices(remoteTokenServices());
  }
            
  
     
  private RemoteTokenServices remoteTokenServices() {      
      final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
      remoteTokenServices.setCheckTokenEndpointUrl(secConfig.getCheckTokenUrl());
      remoteTokenServices.setClientId(secConfig.getClientId());
      remoteTokenServices.setClientSecret(secConfig.getClientSecret());
      remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
      return remoteTokenServices;
  }
  
  private AccessTokenConverter accessTokenConverter() {
      return new DefaultAccessTokenConverter();
  }
 
}