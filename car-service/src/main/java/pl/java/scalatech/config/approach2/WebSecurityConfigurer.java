package pl.java.scalatech.config.approach2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.ProfileApp;



/*
 *   The AuthenticationManagerBean is used by Spring Security to handle authentication
 *   The UserDetailsService is used by Spring Security to handle user information that will be returned by the Spring
 *   the configure() method is where we are going to define our users, their passwords and roles
 *   
 *   public void configure(AuthorizationServerEndpointsConfigurer endpoints)     
   throws Exception { 
    endpoints 
        .authenticationManager(authenticationManager)  
        .userDetailsService(userDetailsService); 
}
These two beans are used to configure the /oauth/token and /user endpoints that we will
see in action shortly.
*/
@Configuration 
@Profile(ProfileApp.PROFILE)
@Slf4j
@EnableWebSecurity
//@EnableOAuth2Sso //annotation pulls in all the required configuration to wire up the spring security filters for OAuth2 flows:
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfigurer   extends WebSecurityConfigurerAdapter { 		 
   
  @Override 
  @Bean     		 
  public UserDetailsService userDetailsServiceBean() throws Exception { 
    return super.userDetailsServiceBean(); 
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
      web.ignoring()
         .antMatchers("/h2-console/**")
         .antMatchers("/info")
         .antMatchers("/health")
         .antMatchers("/mappings")
         .antMatchers("/metrics")
         .antMatchers("/message/**")
         .antMatchers("/nbp/**")
         .antMatchers("/env");
      super.configure(web);

      
  }
  
  @Autowired
  public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
      // @formatter:off
       auth.inMemoryAuthentication()                        
      .withUser("slawek") 
      .password("password") 
      .roles("USER","ACTUATOR")
      .and() 
      .withUser("admin") 
      .password("password") 
      .roles("USER", "ADMIN","ACTUATOR");
      // @formatter:on
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }

 
}