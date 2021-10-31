package com.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qa.base.TestBase;

public class Testscenario2 extends TestBase {

	public static void main(String[] args)  throws InterruptedException 
	{
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver=new ChromeDriver();

		driver.manage().deleteAllCookies();		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.channelnewsasia.com/news/international");
		
		//ul[@class='main-menu']/child::li/following-sibling::li[@class='main-menu__item is-hide-link']/span
		
		
		Thread.sleep(600);
		WebElement AllSections=driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link']"));
		//div[@id='hamburger-nav']//ul/li[@class='hamburger-menu__item hamburger-menu__item--active hamburger-menu__item--with-sub']//ul/li//a[text()='Singapore']
		
		AllSections.click();
		//String allsectionstext=AllSections.getText();
		//System.out.println(allsectionstext);
		
		//frameWithIndex(0);
		
		//div[@id='hamburger-nav']//ul/li[@class='hamburger-menu__item hamburger-menu__item--active hamburger-menu__item--with-sub']/a[text()='Top Stories']
		WebElement topStories=driver.findElement(By.xpath("//div[@id='hamburger-nav']//ul/li[@class='hamburger-menu__item hamburger-menu__item--active hamburger-menu__item--with-sub']/a[text()='Top Stories']"));
		
		Actions action= new Actions(driver);
		action.moveToElement(topStories);
		
		WebElement Singapore=driver.findElement(By.xpath("(//a[text()='Singapore'])[3]"));
		String SingaporeText=Singapore.getText();
		System.out.println(SingaporeText);
		
		Thread.sleep(10000);
		clickElementByJS(driver,Singapore);
		//webdriverWaitClick(Singapore,driver);
		
		
		//h1[@class='h1 h1--block-heading block block-video-heading block-layout-builder block-field-blocknodelanding-pagetitle clearfix']/child::span
		
		//Thread.sleep(600);
		WebElement singaporeInNextPage=driver.findElement(By.xpath("//h1[@class='h1 h1--block-heading block block-video-heading block-layout-builder block-field-blocknodelanding-pagetitle clearfix']/span"));
		String singaporeInNextPageText = singaporeInNextPage.getText();
		System.out.println(singaporeInNextPageText);
		
		//Assert.assertEquals(singaporeInNextPageText,SingaporeText);
		if (SingaporeText.equals(singaporeInNextPageText)) {
			System.out.println("Both text are matched");
		}else{
			System.out.println("Both text are not matched");
		}
	driver.close();
	}
	
}


