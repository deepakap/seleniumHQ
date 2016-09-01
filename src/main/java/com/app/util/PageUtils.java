package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtils {
	private WebDriverWait wait;
	
	public void waitExplicitDuration(WebDriver driver, WebElement ele){
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitImplicitDuration(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void navigateForward(WebDriver driver){
		driver.navigate().forward();
	}
	
	public void navigateBackward(WebDriver driver){
		driver.navigate().back();
	}
	
	public void takeScreenShot(WebDriver driver) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		org.apache.commons.io.FileUtils.copyFile(scrFile, new File("/Users/deepakkumar/Projects/Selenium/NgLearning/src/screenshots/screen.jpg"));
	}
	
	public void waitForPageLoad(WebDriver driver, String locator, String value){
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBe(By.tagName(locator), value));
	}
}
