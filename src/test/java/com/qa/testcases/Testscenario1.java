package com.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.qa.base.TestBase;

public class Testscenario1 extends TestBase {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.channelnewsasia.com/");

		//Validate the Headline news title
		
		WebElement headlineItem1=driver.findElement(By.xpath("//a[@class='feature-card__heading-link feature-card__heading-link--']"));
		String News1Heading = headlineItem1.getText();
		System.out.println(News1Heading);

		//Click the news and it should navigate to next page

		clickElementByJS(driver,headlineItem1);
		//After Navigation to first heading story

		WebElement actualHeadlineNewsPage=driver.findElement(By.xpath("//h1[@class='h1 h1--page-title']"));
		String news1ActualHeadline = actualHeadlineNewsPage.getText();
		System.out.println(driver.getTitle());
		System.out.println(news1ActualHeadline);
		if (News1Heading.equals(news1ActualHeadline)) {
			System.out.println("Both news1 headlines are matched");
		}else{
			System.out.println("Both new1 headlines are not matched");
		}
		Assert.assertEquals(News1Heading, news1ActualHeadline,"both are not matched");

		//Click on second news page
		scrollPageIntoHeight(driver);
		scrollPageUpByPixel(driver, -350);
		Thread.sleep(5000);
		scrollPageDownByPixel(driver, 680);
		Thread.sleep(3000);
		scrollPageDownByPixel(driver, 530);
		Thread.sleep(10000);
		WebElement secondNewsItem = driver.findElement(By.xpath("(//a[@class='article__read-full-story-button article__read-full-story-button--' and contains(text(),'Expand to read the full story')])[2]"));
		Thread.sleep(4000);
		scrollIntoView(driver,secondNewsItem);
		clickElementByJS(driver ,secondNewsItem);
		
		WebElement scrolledSecondNewsItem = driver.findElement(By.xpath("(//h1[contains(@class,'h1--page-title')])[2]"));
		String textOfSecondNewsItem = scrolledSecondNewsItem.getText();
		System.out.println(textOfSecondNewsItem);
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(textOfSecondNewsItem, title);

		driver.close();

	}

}