package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        springPIDAppRun(args, UserApplication.class);
    }

    private static void springPIDAppRun(String[] args, Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(
                clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}
