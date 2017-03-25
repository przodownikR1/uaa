package pl.java.scalatech.config.info;

import static com.google.common.collect.ImmutableMap.builder;
import static com.google.common.collect.ImmutableMap.of;
import static java.time.Duration.between;
import static java.time.Instant.now;
import static pl.java.scalatech.tools.HostInformationTool.getApplicationPID;
import static pl.java.scalatech.tools.HostInformationTool.getHostName;
import static pl.java.scalatech.tools.HostInformationTool.getIp;

import java.util.Date;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class InfoConfig {
	private static final String SERVER_PORT = "server.port";
	private static final String SPRING_APPLICATION_NAME = "spring.application.name";
	private static final String JAVA_VERSION = "java.version";
	private static final String UPTIME = "uptime";
	private static final String HEART_BEAT = "heartBeat";
	private static final String START_DATE = "startDate";
	private static final String IP = "ip";
	private static final String HOST = "host";
	private static final String RUNTIME = "runtime";
	private static final String NETWORK = "network";
	private static final String ACTIVE_PROFILES = "activeProfiles";
	private static final String ENVIRONMENT = "environment";
	private final Environment env;
	private final Date startDate = new Date();

	@Bean	
	InfoContributor info() {
		// @formatter:off
		return builder -> {
			builder.withDetail(
					ENVIRONMENT,
					of(ACTIVE_PROFILES, env.getActiveProfiles())
					);
			builder.withDetail
			       (NETWORK,
					builder()
					        .put(HOST, getHostName()).put(IP, getIp())
							.put(SERVER_PORT, env.getProperty(SERVER_PORT))
							.put(SPRING_APPLICATION_NAME, env.getProperty(SPRING_APPLICATION_NAME))
							.build());
			builder.withDetail
			       (RUNTIME,
					builder()
					  .put("pid", getApplicationPID())
					  .put(JAVA_VERSION, System.getProperty(JAVA_VERSION))
					  .put(START_DATE, startDate)
					  .put(HEART_BEAT, new Date())
					  .put(UPTIME, getUptime())
					  .build());

		};
		// @formatter:on
	}

	private String getUptime() {
		return between(startDate.toInstant(), now()).toString();
	}
}
