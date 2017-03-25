package pl.java.scalatech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class ComputeController {

	private String welcome = "Welcome to Feign";

	@GetMapping("/message/{name}")
	public String add(@PathVariable(value = "name") String name) {

		log.info("Received request from {} for a greeting.", name);

		return welcome + " " + name;
	}

	@PostMapping("/message/{greeting}")
	public void updateGreeting(@PathVariable(value = "greeting") String greeting) {
		welcome = greeting;

		log.info("Greeting update to {}", welcome);
	}
}
