package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {


	public static WebDriver driver;
	public static Properties prop;

	public static WebDriverWait wait;

	public TestBase() {

		try {
			prop=new Properties();
			FileInputStream file = new FileInputStream("G:\\Eclipse\\Eclipse_Workspace\\SDETFramework\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	


	}

	public static void initialization() {

		String browser=prop.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browser.equals("gecko")){
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			driver=new FirefoxDriver();
		}




		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	public static void frameWithIndex(int index) {
		driver.switchTo().frame(index);
	}

	public static void frameWithName(String name ) {
		driver.switchTo().frame(name);
	}

	public static void frameWithWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public static void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public static void getTextFromAlert() {
		driver.switchTo().alert().getText();
	}

	public static void sendMSGToAlert(String message) {
		driver.switchTo().alert().sendKeys(message);
	}

	public void selectDropDownUsingText(WebElement ele, String value) {

		new Select(ele)
		.selectByVisibleText(value);
	}


	public void selectDropDownUsingIndex(WebElement ele, int index) {
		new Select(ele)
		.selectByIndex(index);
	}


	public void selectDropDownUsingValue(WebElement ele, String value) {
		new Select(ele)
		.selectByValue(value);
	}

	public static void scrollPageIntoHeight(WebDriver driver) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void scrollPageDown(WebDriver driver) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,1000)");
	}

	public static void scrollPageDownByPixel(WebDriver driver,int pixel) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,"+pixel+")");
	}


	public static void scrollPageUp(WebDriver driver) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,-1000)");
	}

	public static void scrollPageUpByPixel(WebDriver driver,int pixel) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,"+pixel+")");
	}



	public static void scrollIntoView(WebDriver driver,WebElement element) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}

	public static void webdriverWaitClick(WebElement element,WebDriver driver) {
		
		wait = new WebDriverWait(driver, 100);
		//wait.until(WebDriver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void clickElementByJS(WebDriver driver,WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();",element);
	}

	






}