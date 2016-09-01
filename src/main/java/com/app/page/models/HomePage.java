package com.app.page.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.waitEvt.AccessBaseWebElements;

public class HomePage extends AccessBaseWebElements {
	
	private WebDriver driver;
	
	@FindBy(linkText="PRIVACY POLICY")
	WebElement link;
	
	@FindBy(css="address.copyright")
	WebElement address;
	
	@FindBy(linkText="MOBILE")
	WebElement mobileLink;
	
	public void verifyTitle(String title){
		Assert.assertEquals(driver.getTitle(), title);
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement findLinkWebElement(){
		return link;
	}
	
	public void loadWebPageToMaximise(String urlStr){
		driver.get(urlStr);
		driver.manage().window().maximize();
	}
	
	
	public WebElement findAddressTag(){
		return address;
	}
	
	public void veifyAddress(){
		Assert.assertEquals(address.getText(), "© 2015 Magento Demo Store. All Rights Reserved.");
	}
	
	public MobilePage clickOnMobileLink(WebDriver driver){
		mobileLink.click();
		return new MobilePage(driver);
	}
}
