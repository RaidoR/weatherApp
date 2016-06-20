package roben.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import roben.entities.Weather;
import roben.repositories.WeatherRepository;

@Component
public class WeatherCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {

		for (Weather w : this.weatherRepository.findByCityName("Tallinn"))
			System.out.println(w.toString());

	}

	@Autowired
	WeatherRepository weatherRepository;

}
