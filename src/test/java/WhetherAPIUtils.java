import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class WhetherAPIUtils {
	private static final String BASE_URI = "https://api.openweathermap.org/";
	private static final String BASE_PATH = "data/2.5/weather";
	private static final String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";
	
	
	public static Map<String, Double> getWeatherDataInMetric(String city)
	{
		RestAssured.baseURI = BASE_URI;
		RestAssured.basePath = BASE_PATH;
		String response = 
		RestAssured
			.given()
			.queryParam("q", city)
			.queryParam("units", "metric")
			.queryParams("appid",API_KEY)
			.get()
			.then()
			.assertThat()
			.statusCode(200)
			.extract()
			.response()
			.asString();
		HashMap<String,Double> weatherData = new HashMap<String, Double>();
		JsonPath js = new JsonPath(response);
		double tempMin = js.getDouble("main.temp");
		double humidity = js.getDouble("main.humidity");
		weatherData.put("temp", tempMin);
		weatherData.put("humidity", humidity);
		return weatherData;
	}
}
