package com.testvagrant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetWeatherDataFromWeb {
	public static Map<String,Double> getWeatherDataFromWeb(String city)
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(ops); 
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.ndtv.com/");
        
        driver.findElement(By.id("h_sub_menu")).click();
        driver.findElement(By.linkText("WEATHER")).click();
        driver.findElement(By.id("searchBox")).sendKeys(city);
        driver.findElement(By.xpath("//div[text()='"+city+"']")).click();
        String cityTemp = driver.findElement(By.xpath("//div//span[4]")).getText();
        String cityHumidity = driver.findElement(By.xpath("//div/span[3]")).getText();
        
        Map<String, Double> webData = new HashMap<String, Double> ();
        webData.put("temp", Double.valueOf(cityTemp.split(":")[1].trim()));
        String humFormatted = cityHumidity.split(":")[1].trim();
        webData.put("humidity", Double.valueOf(humFormatted.substring(0,humFormatted.length()-1)));
        driver.quit();
        return webData;
    }
}
