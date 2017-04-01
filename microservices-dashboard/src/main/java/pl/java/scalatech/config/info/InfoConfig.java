package pl.java.scalatech.config.info;

import static com.google.common.collect.ImmutableMap.builder;
import static com.google.common.collect.ImmutableMap.of;
import static java.time.Duration.between;
import static java.time.Instant.now;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ofPattern;
import static pl.java.scalatech.config.info.tool.HostInformationTool.getApplicationPID;
import static pl.java.scalatech.config.info.tool.HostInformationTool.getHostName;
import static pl.java.scalatech.config.info.tool.HostInformationTool.getIp;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class InfoConfig {
	private static final String PID = "pid";
	private static final String CONTEXT_PATH = "context_path";
	private static final String SERVER_CONTEXT_PATH = "server.contextPath";
	private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSSSSS";
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
	private final LocalDateTime startDate = LocalDateTime.now();

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
					  .put(CONTEXT_PATH,env.getProperty(SERVER_CONTEXT_PATH,"/"))
					  .put(PID, getApplicationPID())
					  .put(JAVA_VERSION, System.getProperty(JAVA_VERSION))
					  .put(START_DATE, startDate.format(ofPattern(FORMAT_DATE_TIME)))
					  .put(HEART_BEAT, LocalDateTime.now().format(ofPattern(FORMAT_DATE_TIME)))
					  .put(UPTIME, getUptime())
					  .build());

		};
		// @formatter:on
	}

	private String getUptime() {
		return between(startDate.toInstant(UTC), now()).toString();
	}
}
