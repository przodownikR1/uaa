package pl.java.scalatech;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigSeverConfig {

}
