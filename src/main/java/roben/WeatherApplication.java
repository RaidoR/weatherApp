package roben;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(WeatherApplication.class, args);
		JSONObject json = readJsonFromUrl(
				"http://api.openweathermap.org/data/2.5/weather?q=Tallinn&APPID=58ddd82d35336f2247fbcb70cddd5960");
		System.out.println(json.toString());
		System.out.println(json.get("main"));
		JSONObject json2 = (JSONObject) json.get("main");
		Double str1 = (Double) json2.get("temp");
		String str2 = (String) json.get("name").toString();

		JSONObject json3 = new JSONObject();
		json3.put("temp", str1);
		json3.put("name", str2);

		System.out.println(str1);
		System.out.println(json3);

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
