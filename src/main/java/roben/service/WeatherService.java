package roben.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Service;

import roben.entities.Weather;


@Service
public class WeatherService {

	

	public Weather findWeather(String city) throws IOException, ParseException {
		System.out.println("retrieve data from openweather API");
		String apiKey = "58ddd82d35336f2247fbcb70cddd5960";

		JSONObject json = readJsonFromUrl(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + apiKey);

		JSONObject json2 = (JSONObject) json.get("main");
		Double str1 = (Double) json2.get("temp");
		String str2 = (String) json.get("name").toString();

		Weather weatherObj = new Weather();

		weatherObj.setCityName(str2);
		weatherObj.setTemp(str1);

//		JSONObject json3 = new JSONObject();
//
//		json3.put("temp", str1);
//		json3.put("name", str2);

		return weatherObj;

		// return json3;
	}

	

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, ParseException {

		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = (JSONObject) new JSONParser().parse(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
