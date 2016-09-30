package test;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.*;


import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.*;

public class TestUser {

	public String baseUrl = "http://newtours.demoaut.com"; 
	
	public WebDriver driver = new FirefoxDriver();
 	
 	
  @BeforeTest
  public void launchBrowser() {
	 //int intArray[]= new int[20];
	 driver.get(baseUrl);
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
  }
  @Test
  public void verifyTitle(){
	  
	 String expectedTitle = "Welcome: Mercury Tours";
		 
	 String actual = driver.getTitle();
	 Assert.assertEquals(expectedTitle,actual);
	 driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]")).click();
	 
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
	// driver.findElement(By.name("userName")).sendKeys("admin");
	 
	 
  }
  @AfterTest
  public void closeBrowser(){
	 driver.quit();  
  }
}
