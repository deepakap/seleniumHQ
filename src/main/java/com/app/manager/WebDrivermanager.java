package com.app.manager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.app.eventsLogger.EventHandler;

public class WebDrivermanager {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WebDriver setEnvironment(String browserName){
		if(browserName.equalsIgnoreCase("IE")){
			File IEFile = new File("test path");
			System.setProperty("webdriver.ie.driver", IEFile.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}else if(browserName.endsWith("Firefox")){
			driver =  new FirefoxDriver();
			 EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
			 EventHandler handler = new EventHandler();
			 eventDriver.register(handler);
			 return eventDriver;
		}else if(browserName.equalsIgnoreCase("Chrome")){
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	public void waitExplicitDuration(WebDriver driver, WebElement ele){
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitImplicitDuration(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
}
