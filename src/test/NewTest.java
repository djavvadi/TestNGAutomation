package test;

import org.testng.annotations.Test;

import Excel.Excel;
import Reports.Reports;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.*;

import org.testng.annotations.*;

public class NewTest {
	
	Reports report = new Reports();
	Excel excelData = new Excel();
	public String baseUrl = "http://newtours.demoaut.com"; 
	
	public WebDriver driver = new FirefoxDriver();
 	
 	
  @BeforeTest
  public void launchBrowser() {
	  
	 report.createTempPath("NewTest");
	 //int intArray[]= new int[20];
	 driver.get(baseUrl);
	 report.updateTestlog("Open Application URL", "URL"+baseUrl+" is entered ", "Done");
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
  }
  @Test
  public void verifyTitle(){
	  
	 String expectedTitle = "Welcome: Mercury Tours";
		 
	 String actual = driver.getTitle();
	 String username = excelData.getData("NewTest", "Username");
	 String password = excelData.getData("NewTest", "Password");
	 
	 if(expectedTitle.equalsIgnoreCase(actual)){
		 report.updateTestlog("Verify Title", "Title is displayed properly", "Pass");
		// excelData.putData("NewTest", "Result", "Pass");
		 
	 }else{
		 report.updateTestlog("Verify Title", "Title is NOT displayed properly", "Fail");
	 }
	 
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(username);
	 report.updateTestlog("Enter Username", "Username is entered", "Done");
	 driver.findElement(By.name("userName")).sendKeys(password);
	 report.updateTestlog("Enter Password", "Password is entered", "Done");
	 
	 
  }
  @AfterTest
  public void testClosure(){
	 report.testreportClosure();
	 driver.quit();  
  }
}
