package pl.java.scalatech;

import static com.google.common.base.Charsets.UTF_8;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.io.CharStreams;

@Configuration
public class DownloadConfig {

	@Value("${urlShot}")
	private String urlShot;

	@Bean
	DownloadCode downloadCode() {
		return new DownloadCode();
	}

	class DownloadCode implements Callable<String> {
		@Override
		public String call() throws Exception {
			URL url = new URL(urlShot);
			InputStream is = url.openStream();
			return CharStreams.toString(new InputStreamReader(is, UTF_8));
		}
	}

}
