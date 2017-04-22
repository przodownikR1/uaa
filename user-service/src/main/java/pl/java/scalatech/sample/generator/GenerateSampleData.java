package pl.java.scalatech.sample.generator;

import static java.util.stream.IntStream.range;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.sample.repo.PersonRepo;

@Component
@RequiredArgsConstructor
public class GenerateSampleData implements CommandLineRunner {

    private final GeneratorSetting setting;

    private final PersonRepo personRepo;

    private final UserGenerate userGenerator;

    @Override
    public void run(String... args) throws Exception {
        populateDB();
    }

    private void populateDB() {
        range(0, setting.getCount()).forEach(value -> personRepo.save(userGenerator.generateSingleUser()));

    }

}
