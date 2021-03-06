package pl.java.scalatech.sample.generator;

import static java.util.stream.IntStream.range;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.sample.repo.CarRepo;
import pl.java.scalatech.sample.repo.PersonRepo;

@Component
@RequiredArgsConstructor
public class GenerateSampleData implements CommandLineRunner {

	private final CarRepo carRepo;

	private final GeneratorSetting setting;

	private final PersonRepo personRepo;

	private final CarGenerator carGenerator;

	private final UserGenerate userGenerator;

	private final Random carR = new Random();

	@Override
	public void run(String... args) throws Exception {
		populateDB();
	}

	private void populateDB() {
		range(0, setting.getCount()).mapToObj(p -> personRepo.save(userGenerator.generateSingleUser()))
				.forEach(person -> {
					for (int j = 0; j < carR.nextInt(setting.carsForSinglePersonLimit); j++) {
						carRepo.save(carGenerator.generateSingleCar(person));
					}
				});
	}

}
