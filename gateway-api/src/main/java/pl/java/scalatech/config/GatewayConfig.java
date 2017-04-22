package pl.java.scalatech.config;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
@ComponentScan(basePackages = "pl.java.scalatech.filter")
public class GatewayConfig {
	private static final String FALLBACK = "fallback";
	private static final String CUSTOMER = "customer";

	@Bean
	Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

	@Bean
	ZuulFallbackProvider zuulFallbackProvider() {
		return new ZuulFallbackProvider() {
			@Override
			public String getRoute() {
				return CUSTOMER;
			}

			@Override
			public ClientHttpResponse fallbackResponse() {
				return new ClientHttpResponse() {
					@Override
					public HttpStatus getStatusCode() throws IOException {
						return OK;
					}

					@Override
					public int getRawStatusCode() throws IOException {
						return 200;
					}

					@Override
					public String getStatusText() throws IOException {
						return "OK";
					}

					@Override
					public void close() {

					}

					@Override
					public InputStream getBody() throws IOException {
						return new ByteArrayInputStream(FALLBACK.getBytes());
					}

					@Override
					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(APPLICATION_JSON);
						return headers;
					}
				};
			}
		};
	}
}
