package com.app.waitEvt;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class AccessBaseWebElements {
	
	WebDriver driver;
	
	public void verifyTitle(String title){
		Assert.assertEquals(driver.getTitle(), title);
	}

}
