package pl.java.scalatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import pl.java.scalatech.config.approach2.SecConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationBackendApplicationTests {

    
    @Autowired
    SecConfig config;
    
    
    @Autowired
    private TestRestTemplate restTemplate;
   @Test
   public void test(){
       /*ResponseEntity<Object> tokenResponse =
               restTemplate.withBasicAuth(CLIENT_ID, "1234")
                           .postForEntity(
                               "/oauth/token?grant_type=password&username={username}&password={password}&client_id={client_id}",
                               null, Object.class, userName, TEST_PASSWORD, CLIENT_ID);

           assertNotNull(tokenResponse.getBody().getAccessToken());*/
   }
}
