package pl.java.scalatech.config.info.tool;

import static com.google.common.io.Files.readLines;
import static java.net.InetAddress.getLocalHost;

import java.io.File;
import java.net.UnknownHostException;

import com.google.common.base.Charsets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HostInformationTool {
	private final static String PID_FILE_NAME = "application.pid";

	@SneakyThrows(UnknownHostException.class)
	public static String getIp() {
		return getLocalHost().getHostAddress();
	}

	@SneakyThrows(Exception.class)
	public static String getApplicationPID() {
		return readLines(new File(PID_FILE_NAME), Charsets.UTF_8).get(0);
	}

	@SneakyThrows
	public static String getHostName() {
		return getLocalHost().getHostName();
	}

}
