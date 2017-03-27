package pl.java.scalatech;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import de.codecentric.boot.admin.config.EnableAdminServer;
import de.codecentric.boot.admin.notify.LoggingNotifier;
import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@RefreshScope
public class AdminDashboardApplication {

	public static void main(String[] args) {
		springPIDAppRun(args, AdminDashboardApplication.class);
	}

	private static void springPIDAppRun(String[] args, Class<?> clazz) {
		SpringApplication springApplication = new SpringApplication(clazz);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Configuration
	static class NotifierConfig {
		@Bean
		@Primary
		RemindingNotifier remindingNotifier() {
			RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(loggerNotifier()));
			notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10));
			return notifier;
		}

		@Scheduled(fixedRate = 1_000L)
		void remind() {
			remindingNotifier().sendReminders();
		}

		@Bean
		FilteringNotifier filteringNotifier(Notifier delegate) {
			return new FilteringNotifier(delegate);
		}

		@Bean
		LoggingNotifier loggerNotifier() {
			return new LoggingNotifier();
		}
	}
}
