package pl.java.scalatech.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pl.java.scalatech.config.approach2.ProfileApp;


@Configuration
@Profile(ProfileApp.PROFILE)
public class GatewayConfig {

}
