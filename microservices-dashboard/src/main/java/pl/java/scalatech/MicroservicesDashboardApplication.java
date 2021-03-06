package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import be.ordina.msdashboard.EnableMicroservicesDashboardServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMicroservicesDashboardServer
@RefreshScope
public class MicroservicesDashboardApplication {

    public static void main(String[] args) {
        springPIDAppRun(args, MicroservicesDashboardApplication.class);
    }

    private static void springPIDAppRun(String[] args, Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(
                clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}
