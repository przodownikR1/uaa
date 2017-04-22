package pl.java.scalatech.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UrlShotRestController {

	@Value("${urlShot}")
	private String urlShot;

	@Value("${nbp-service.ribbon.listOfServers}")
	private String ribbon;

	@GetMapping("/props")
	public String urls() {
		return urlShot + " ribbon :   " + ribbon;
	}

}