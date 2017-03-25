package pl.java.scalatech.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
	@GetMapping(value = "/response")
	String response() {
		return "code";
	}

	@GetMapping(value = "/response", params = "code")
	String responseCode(@RequestParam("code") String code) {
		return "code: " + code;
	}

}
