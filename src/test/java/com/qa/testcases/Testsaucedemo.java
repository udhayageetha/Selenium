/**
 * 
 */
package com.qa.testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.qa.base.TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testsaucedemo extends TestBase{


	public static void main(String[] args) {
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(cap);


		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com");
		String username="standard_user";
		String password="secret_sauce";
		
		//For Username
		String[] split = username.split("_");
		String uName1=split[0];
		String uName2=split[1];
		String undescore1="_";
		
		String UN=uName1+undescore1+uName2;
		
		System.out.println(UN);
		
		//For Password
		String[] splitPwd = password.split("_");
		String pwd1=splitPwd[0];
		String pwd2=splitPwd[1];
		int tValue=password.indexOf("t");
		int sValue=password.indexOf("s", 5);
		
		System.out.println(tValue+""+sValue);
		
		String underscore=password.substring(tValue+1, sValue);
		System.out.println(underscore);
		String PWD=pwd1+underscore+pwd2;
		System.out.println(PWD);
		

		driver.findElement(By.id("user-name")).sendKeys(UN);

		WebElement pass=driver.findElement(By.id("password"));
		pass.sendKeys(pwd1+"_"+pwd2);
		String attribute = pass.getAttribute("value");
		System.out.println(attribute);


		driver.findElement(By.id("login-button")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		driver.findElement(By.name("firstName")).sendKeys("Geetha");
		driver.findElement(By.id("last-name")).sendKeys("udhaya");
		driver.findElement(By.name("postalCode")).sendKeys("123");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.name("finish")).click();
		WebElement orderplaced=driver.findElement(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]"));
		String ordertext=orderplaced.getText();
		System.out.println(ordertext);
		String expected="THANK YOU FOR YOUR ORDER";
		Assert.assertEquals(ordertext, expected);
		System.out.println(expected);
		driver.close();
	}

}
