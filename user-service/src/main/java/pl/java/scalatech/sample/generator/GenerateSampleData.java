package pl.java.scalatech.sample.generator;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.java.scalatech.sample.repo.PersonRepo;

@Component
public class GenerateSampleData implements CommandLineRunner {

    private final GeneratorSetting setting;

    private final PersonRepo personRepo;

    private final UserGenerate userGenerator;

    public GenerateSampleData(PersonRepo personRepo, UserGenerate userGenerator, GeneratorSetting setting) {
        super();
        this.personRepo = personRepo;

        this.userGenerator = userGenerator;
        this.setting = setting;
    }

    @Override
    public void run(String... args) throws Exception {
        populateDB();
    }

    private void populateDB() {
        IntStream.range(0, setting.getCount()).forEach(value -> personRepo.save(userGenerator.generateSingleUser()));

    }

}
