package pl.java.scalatech;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EnvReadController {

	private static final String USER = "USER";
	private final Environment env;
	
	@GetMapping("/envUser")
	String getEnvUser(){
		return env.getProperty(USER);
	}
}
