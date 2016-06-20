package roben.controllers;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import roben.entities.Weather;
import roben.repositories.WeatherRepository;
import roben.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	WeatherService weatherService;

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	List<Weather> weatherStats() {
		return this.weatherRepository.findAll();
	}

	@RequestMapping(value = "/cityStats/{city}", method = RequestMethod.GET)
	Weather weatherCityStats(@PathVariable("city") String city)
			throws JsonParseException, JsonMappingException, IOException, ParseException {

		return this.weatherRepository.save(this.weatherService.findWeather(city));

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	String delete(@PathVariable("id") String id) {

		this.weatherRepository.deleteUsersByFirstName(id);
		return "City with a name " + id + " is deleted";

	}

	@Autowired
	WeatherRepository weatherRepository;

}
