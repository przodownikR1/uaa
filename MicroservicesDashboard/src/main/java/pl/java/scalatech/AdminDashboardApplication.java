package pl.java.scalatech;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import be.ordina.msdashboard.EnableMicroservicesDashboardServer;

@SpringBootApplication
@EnableMicroservicesDashboardServer
public class AdminDashboardApplication {

	public static void main(String[] args) {
	  springPIDAppRun(args,AdminDashboardApplication.class);
	}
	private static void springPIDAppRun(String[] args,Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}

