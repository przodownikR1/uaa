package pl.java.scalatech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @Value("${name.cipher}")
  private String encrypt;

  @RequestMapping("/sampleEncrypt")
  public String index() {
    return encrypt;
  }
}