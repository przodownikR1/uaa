package pl.java.scalatech;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConfigServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class EnvLoadTest {

	@Value("${local.server.port}")
	private int port = 0;

	@Test
	public void shouldConfigLoad() {
		// @formatter:off
		ResponseEntity<Map> entity = new TestRestTemplate()
				.getForEntity("http://localhost:" + port + "config-server/default",Map.class);
		log.info("+++ {}",entity);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		// @formatter:on
	}

	

}