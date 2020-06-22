package com.testvagrant;
import java.util.Map;

import org.testng.annotations.Test;
import com.testvagrant.GetWeatherDataFromWeb;
import com.testvagrant.WhetherAPIUtils;

public class Comporator {
	private static boolean compareTempWithGrace(Double valueInWeb, Double valueFromAPI , Double delta)
	{
		double deltaActual = valueInWeb - valueFromAPI;
	    if(deltaActual <= delta)
	    	return true;
	    else
	    	return false;
	}
	
	private static boolean compareHumidityWithGrace(Double valueInWeb, Double valueFromAPI , Integer delta)
	{
		double deltaActual = valueInWeb - valueFromAPI;
		if(deltaActual==0) return true;
	    if(deltaActual > 0)
	    {
	    	int perMore = (int) ((deltaActual/valueFromAPI)*100);
	    	if(perMore <= delta)
	    		return true;
	    		else
	    	return false;
	    }
	    else
	    {
	    	int perMore = (int) ((deltaActual/valueInWeb)*100);
	    	if(perMore <= delta)
	    		return true;
	    		else
	    	return false;
	    }
	}

	@Test
	public void compareWebAndApiData()
	{
		Map<String, Double> webWeatherData = GetWeatherDataFromWeb.getWeatherDataFromWeb("Bengaluru");
		Map<String,Double> apiWetaheData = WhetherAPIUtils.getWeatherDataInMetric("Bengaluru");
		System.out.println("Temp is within deviation ? "+ Comporator.compareTempWithGrace(webWeatherData.get("temp"), apiWetaheData.get("temp"), 2.0));
		System.out.println("Humidity is within deviation ? "+ Comporator.compareHumidityWithGrace(webWeatherData.get("humidity"), apiWetaheData.get("humidity"), 10));
	}
}
