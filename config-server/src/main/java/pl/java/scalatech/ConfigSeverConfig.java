package pl.java.scalatech;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigServer
@EnableEurekaClient
public class ConfigSeverConfig {

}
