package com.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qa.base.TestBase;

public class Testscenario3 extends TestBase{

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver=new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("https://www.channelnewsasia.com/news/international");
		//Thread.sleep(10000);
		//driver.manage().timeouts().wait(600);
		WebElement AllSections=driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link']"));
		//div[@id='hamburger-nav']//ul/li[@class='hamburger-menu__item hamburger-menu__item--active hamburger-menu__item--with-sub']//ul/li//a[text()='Singapore']

		AllSections.click();
		String allsectionstext=AllSections.getText();
		System.out.println(allsectionstext);
		//div[@id='hamburger-nav']/ul/li[@class='hamburger-menu__item']/a[@class='hamburger-menu__link--parent section-menu hamburger-menu__link']
		Actions action=new Actions(driver);
		//Thread.sleep(4000);
		WebElement weather=driver.findElement(By.xpath("//div[@id='hamburger-nav']/ul/li[@class='hamburger-menu__item']/a[@class='hamburger-menu__link--parent section-menu hamburger-menu__link']"));
		action.moveToElement(weather);
		clickElementByJS(driver,weather);
		//String weathertext=weather.getText();
		//System.out.println(weathertext);
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
		
