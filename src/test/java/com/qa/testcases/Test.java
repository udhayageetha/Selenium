package com.qa.testcases;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver=new ChromeDriver();

		driver.manage().deleteAllCookies();		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.channelnewsasia.com/weather");
		
		//li[@class='asia-lists tabular-list']/ul/li/div[2]
		
		WebElement city=driver.findElement(By.xpath("//li[@class='asia-lists tabular-list']/ul"));
		int totalCities = city.findElements(By.tagName("li")).size();
		System.out.println(totalCities);
		for (int i = 1; i < totalCities; i++) {
			String cityName=city.findElement(By.xpath("li["+i+"]/div[1]/div[1]")).getText();
			if (cityName.contains("Kuala Lumpur")) {
				String weatherCondition=city.findElement(By.xpath("li["+i+"]/div[1]/div[2]")).getText();
				String high=city.findElement(By.xpath("li["+i+"]/div[2]/span[1]")).getText();
				String low=city.findElement(By.xpath("li["+i+"]/div[2]/span[2]")).getText();
				
				Assert.assertEquals(weatherCondition, "Thunderstorm","Weather are not macthed");
				Assert.assertEquals(high, "33°","high temperature are not macthed");
				Assert.assertEquals(low, "24°","low temperature are not macthed");
				
				 
				
				
			}
			System.out.println(cityName);
		}
	
	
		driver.close();
	}

}
