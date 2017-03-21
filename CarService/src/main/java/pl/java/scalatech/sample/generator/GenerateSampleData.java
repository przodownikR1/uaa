package pl.java.scalatech.sample.generator;

import static java.util.stream.IntStream.range;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.java.scalatech.sample.repo.CarRepo;

@Component
public class GenerateSampleData implements CommandLineRunner {

    private final CarRepo carRepo;

    private final GeneratorSetting setting;

    private final CarGenerator carGenerator;

    public GenerateSampleData(CarRepo carRepo, CarGenerator carGenerator, GeneratorSetting setting) {
        super();
        this.carRepo = carRepo;

        this.carGenerator = carGenerator;

        this.setting = setting;
    }

    @Override
    public void run(String... args) throws Exception {
        populateDB();
    }

    private void populateDB() {
        range(0, setting.getCount()).forEach(value -> carRepo.save(carGenerator.generateSingleCar()));
    }

}
