package pl.java.scalatech.web;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
class SleuthMessageRestController {

	private final RestTemplate restTemplate;
	private final SpanAccessor spanAccessor;

	@GetMapping(value = "/userTest/{domain}}")
	ResponseEntity<?> call(@PathVariable String domain) {
		try {
			URI uri = URI.create("http://" + domain + ".pl");
			ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
			String body = response.getBody();
			MediaType mediaType = response.getHeaders().getContentType();
			return ok()
					.contentType(mediaType)
					.body(body);
		} finally {
			debug();
		}
	}

	private void debug() {
		Span span = this.spanAccessor.getCurrentSpan();
		this.log.info(String.format("traceId: %s, spanId: %s",
				span.getTraceId(), span.getSpanId()));
	}
}